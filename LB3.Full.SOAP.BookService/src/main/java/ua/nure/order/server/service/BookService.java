package ua.nure.order.server.service;

import java.util.Collection;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebParam.Mode;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.ws.Holder;
import ua.nure.order.entity.Book;
import ua.nure.order.server.dao.DAOException;

@WebService(targetNamespace= Const.SERVICE_NS)
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
			int id, 
			@WebParam(name="clientToken", header = true)
			String clientToken, 
			@WebParam(name="serverToken", header = true, mode = Mode.OUT)
			Holder<String> serverToken
			) throws DAOException;
	
	
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