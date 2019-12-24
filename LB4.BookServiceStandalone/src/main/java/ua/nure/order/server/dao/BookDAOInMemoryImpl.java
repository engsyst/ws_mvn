package ua.nure.order.server.dao;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import ua.nure.dbtable.DBTable;
import ua.nure.dbtable.DBTableFabrique;
import ua.nure.dbtable.Filter;
import ua.nure.order.entity.Book;
import ua.nure.order.entity.Category;

public class BookDAOInMemoryImpl implements BookDAO {

	DBTable<Book> books = DBTableFabrique.instance();
	
	private static BookDAOInMemoryImpl instance;

	private BookDAOInMemoryImpl() {
		initBooks();
	}
	
	public static synchronized BookDAOInMemoryImpl instance() {
		if (instance == null) {
			instance = new BookDAOInMemoryImpl();
		}
		return instance;
	}
	
	@Override
	public synchronized int addBook(Book item) {
		int id = books.insert(item);
		item.setId(id);
		try {
			books.update(id, item);
		} catch (SQLException e) {
			// do nothing, always exist
		}
		return id;
	}

	@Override
	public synchronized Book deleteBook(int id) throws DAOException {
		try {
			return books.delete(id);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public synchronized boolean updateBookCount(int id, int count) throws DAOException {
		Book b;
		try {
			b = books.get(id);
		} catch (SQLException e) {
			throw new DAOException("Not enouth books");
		}
		int c = b.getCount() - count;
		if (c < 0) 
			throw new DAOException("Not enouth books");
		b.setCount(c);
		try {
			return books.update(id, b);
		} catch (SQLException e) {
			throw new DAOException("Not enouth books");
		}
	}

	Filter titleFilter = new Filter() {
		@Override
		public boolean accept(Object pattern, Object item) {
			String p = (String) pattern;
			Book it = (Book) item;
			return it.getTitle().toUpperCase().contains(p.toUpperCase());
		}
	};
	
	Filter authorFilter = new Filter() {
		@Override
		public boolean accept(Object pattern, Object item) {
			String p = (String) pattern;
			Book it = (Book) item;
			List<String> authors = it.getAuthor();
			for (Iterator<String> iterator = authors.iterator(); iterator.hasNext();) {
				String string = iterator.next();
				if (string.toUpperCase().contains(p.toUpperCase()))
					return true;
			}
			return false;
		}
	};
	
	@Override
	public Collection<Book> findByTitle(String pattern) {
		return books.filter(pattern, titleFilter);
	}

	@Override
	public Collection<Book> findByAuthor(String pattern) {
		return books.filter(pattern, authorFilter);
	}

	@Override
	public Collection<Book> listBooks() {
		return books.selectAll();
	}

	@Override
	public Book findById(Integer id) throws DAOException {
		try {
			return books.get(id);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
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
			addBook(books[i]);
		}
	}
}
