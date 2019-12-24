package ua.nure.order.server.dao;

import java.util.Collection;

import ua.nure.order.entity.Book;

public interface BookDAO {
	/**
	 * Add a book to order
	 * 
	 * @param book
	 * @return
	 * @throws DAOException 
	 */
	public int addBook(Book item) throws DAOException;

	/**
	 * Add a book to order
	 * 
	 * @param book
	 * @return
	 * @throws DAOException 
	 */
	public Book deleteBook(int id) throws DAOException;
	
	/**
	 * Add a book to order
	 * 
	 * @param book
	 * @return
	 * @throws DAOException 
	 */
	public boolean updateBookCount(int id, int count) throws DAOException;
	
	/**
	 * Find book with patter in the order
	 * 
	 * @param name
	 * @return
	 */
	public Collection<Book> findByTitle(String pattern);

	/**
	 * Find book with pattern author in the order
	 * 
	 * @param name
	 * @return
	 */
	public Collection<Book> findByAuthor(String pattern);

	/**
	 * 
	 * @return All books in order
	 */
	public Collection<Book> listBooks();

	public Book findById(Integer id) throws DAOException;

}
