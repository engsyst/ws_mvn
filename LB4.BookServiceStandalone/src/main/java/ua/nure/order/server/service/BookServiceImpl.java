package ua.nure.order.server.service;

import java.util.Collection;

import javax.jws.WebService;

import ua.nure.order.entity.Book;
import ua.nure.order.server.dao.BookDAO;
import ua.nure.order.server.dao.BookDAOInMemoryImpl;
import ua.nure.order.server.dao.DAOException;

@WebService(serviceName="Books",
		portName="BookPort",
		endpointInterface="ua.nure.order.server.service.BookService",
		targetNamespace="http://order.nure.ua/server/service")
public class BookServiceImpl implements BookService {
	private static BookDAO bookDao = BookDAOInMemoryImpl.instance();

	@Override
	public Collection<Book> findByTitle(String pattern) {
		return bookDao.findByTitle(pattern);
	}

	@Override
	public Collection<Book> findByAuthor(String pattern) {
		return bookDao.findByAuthor(pattern);
	}

	@Override
	public Book getBook(int id) throws DAOException {
		return bookDao.findById(id);
	}

	@Override
	public Collection<Book> listBooks() {
		return bookDao.listBooks();
	}

	@Override
	public int addBook(Book book) throws DAOException {
		return bookDao.addBook(book);
	}

	@Override
	public Book deleteBook(int id) throws DAOException {
		return bookDao.deleteBook(id);
	}
	
}
