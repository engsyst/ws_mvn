package ua.nure.order.server.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ua.nure.log.Log;
import ua.nure.order.entity.Book;
import ua.nure.order.entity.Category;

public class BookDAOInMemoryOld implements BookDAO {
	private static HashMap<Integer, Book> books = new HashMap<Integer, Book>();
	private static int bookIndex;
	private static final Log log = Log.getInstance(Log.DEBUG, BookDAOInMemoryOld.class);
	
	private static BookDAOInMemoryOld dao;
	
	private BookDAOInMemoryOld() {
		initBooks();
		log.trace("Initialized");
	}
	
	public static synchronized BookDAOInMemoryOld getInstance() {
		if (dao == null)
			dao = new BookDAOInMemoryOld();
		return dao;
	}


	@Override
	public int addBook(Book item) throws DAOException {
		if (item == null) {
			log.error("item is null");
			throw new DAOException("Book can not be a null");
		}
		item.setId(++bookIndex);
		books.put(bookIndex, item);
		log.debug("Book added -->" + item);
		return bookIndex;
	}

	@Override
	public Book deleteBook(int id) throws DAOException {
		Book book = books.remove(id);
		log.debug("Removed --> " + book);
		return book;
	}

	@Override
	public boolean updateBookCount(int id, int count) throws DAOException {
		if (count < 1) {
			log.error("Illegal count == " + count);
			throw new DAOException("Count must be greater 0");
		}
		Book o = books.get(id);
		log.debug("Removed --> " + o);
		if (o == null || o.getCount() < count) {
			log.error("Not found or count more then exist. Id --> " + id + ", Count --> " + o.getCount());
			throw new DAOException("Not found or count more then exist");
		}
		o.setCount(o.getCount() - count);
		return true;
	}

	@Override
	public Collection<Book> findByTitle(String pattern) {
		if (pattern == null || "".equals(pattern)) {
			log.debug("Empty pattern --> " + pattern);
			return books.values();
		}
		ArrayList<Book> found = new ArrayList<Book>();
		StringBuffer sb = new StringBuffer(".*");
		sb.append(pattern);
		sb.append(".*");
		log.debug("Pattern --> " + pattern);
		Pattern p = Pattern.compile(sb.toString(), Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
		for (Book o : books.values()) {
			Matcher m = p.matcher(o.getTitle());
			if (m.matches()) {
				log.debug("Found --> " + o.getTitle());
				found.add(o);
			}
		}
		return found;
	}

	@Override
	public Collection<Book> findByAuthor(String pattern) {
		if (pattern == null || "".equals(pattern)) {
			log.debug("Empty pattern --> " + pattern);
			return books.values();
		}
		ArrayList<Book> found = new ArrayList<Book>();
		StringBuffer sb = new StringBuffer(".*");
		sb.append(pattern);
		sb.append(".*");
		log.debug("Pattern --> " + pattern);
		Pattern p = Pattern.compile(sb.toString(), Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
		for (Book o : books.values()) {
			for (String a : o.getAuthor()) {
				Matcher m = p.matcher(a);
				if (m.matches()) {
					log.debug("Found --> " + o.getTitle());
					found.add(o);
				}
			}
		}
		return found;
	}

	@Override
	public Collection<Book> listBooks() {
		return books.values();
	}

	@Override
	public Book findById(Integer id) throws DAOException {
		Book b = books.get(id);
		if (b == null) {
			log.debug("Not found --> " + id);
			throw new DAOException("Книга не найдена");
		}
		log.debug("Found --> " + id);
		return b;
	}

	private Book newBook(String title, List<String> author, String isbn, double price, Category category, int count) {
		Book book = new Book();
		book.setTitle(title);
		book.getAuthor().addAll(author);
		book.setIsbn(isbn);
		book.setPrice (price);
		book.setCategory (category);
		book.setCount (count);
		return book;
	}

	private void initBooks() {
		Book[] books = new Book[] {
				newBook("Божественная комедия", 
						Arrays.asList(new String[] {"Данте Алигьери"}),
						"ISBN-01234-0123", 125.0, Category.LOVE_NOVEL, 3),
				newBook("Сказки",
						Arrays.asList(new String[] {"Ханс Кристиан Андерсен"}), 
						"ISBN-01234-0124", 300.0, Category.ACTION, 12),
				newBook("И пришло разрушение",
						Arrays.asList(new String[] {"Чинуа Ачебе"}), 
						"ISBN-01234-0125", 245.5, Category.ACTION, 4),
				newBook("Отец Горио",
						Arrays.asList(new String[] {"Оноре де Бальзак"}), 
						"ISBN-01234-0126", 245.5, Category.LOVE_NOVEL, 5),
				newBook("Вымыслы",
						Arrays.asList(new String[] {"Хорхе Луис Борхес"}), 
						"ISBN-01234-0127", 118.3, Category.FANTASY, 8),
				newBook("Декамерон",
						Arrays.asList(new String[] {"Джованни Боккаччо"}), 
						"ISBN-01234-0128", 148.7, Category.LOVE_NOVEL, 7),
				};
		for (int i = 0; i < books.length; i++) {
			try {
				addBook(books[i]);
			} catch (DAOException e) {
				throw new RuntimeException("Can not init");
			}
		}
	}

}
