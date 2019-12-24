package ua.nure.order.server.service;

import java.util.Collection;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import ua.nure.order.entity.Book;
import ua.nure.order.server.dao.DAOException;

@WebService(targetNamespace="http://order.nure.ua/server/service")
public interface BookService {

	@WebMethod()
	@WebResult(targetNamespace="http://order.nure.ua/entity")
	public abstract Book getBook(
			@WebParam(name="id")
			int id) throws DAOException;

	@WebMethod()
	public abstract int addBook(
			@WebParam(name="book", targetNamespace="http://order.nure.ua/entity")
			Book book) throws DAOException;
	
	@WebMethod()
	@WebResult(targetNamespace="http://order.nure.ua/entity")
	public abstract Book deleteBook(
			@WebParam(name="id")
			int id) throws DAOException;
	
	@WebMethod()
	@WebResult(targetNamespace="http://order.nure.ua/entity")
	public abstract Collection<Book> listBooks();

	@WebMethod()
	@WebResult(targetNamespace="http://order.nure.ua/entity")
	public abstract Collection<Book> findByTitle(
			@WebParam(name="pattern")
			String pattern);
	
	@WebMethod()
	@WebResult(targetNamespace="http://order.nure.ua/entity")
	public abstract Collection<Book> findByAuthor(
			@WebParam(name="pattern")
			String pattern);

}