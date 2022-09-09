package ua.nure.order.server.service;

import java.util.Collection;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import ua.nure.order.entity.Book;
import ua.nure.order.server.dao.DAOException;

@WebService(targetNamespace="http://order.nure.ua/server/service")
public interface BookService {

	@WebMethod()
	@WebResult(targetNamespace="http://order.nure.ua/entity")
	public Book getBook(
			@WebParam(name="id")
			int id) throws DAOException;

	@WebMethod()
	public int addBook(
			@WebParam(name="book", targetNamespace="http://order.nure.ua/entity")
			Book book) throws DAOException;
	
	@WebMethod()
	@WebResult(targetNamespace="http://order.nure.ua/entity")
	public Book deleteBook(
			@WebParam(name="id")
			int id) throws DAOException;
	
	@WebMethod()
	@WebResult(targetNamespace="http://order.nure.ua/entity")
	public Collection<Book> listBooks();

	@WebMethod()
	@WebResult(targetNamespace="http://order.nure.ua/entity")
	public Collection<Book> findByTitle(
			@WebParam(name="pattern")
			String pattern);
	
	@WebMethod()
	@WebResult(targetNamespace="http://order.nure.ua/entity")
	public Collection<Book> findByAuthor(
			@WebParam(name="pattern")
			String pattern);

}