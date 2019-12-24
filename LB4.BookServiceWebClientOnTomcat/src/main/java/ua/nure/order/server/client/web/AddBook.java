package ua.nure.order.server.client.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.log.Log;
import ua.nure.order.entity.Book;
import ua.nure.order.server.service.BookService;
import ua.nure.order.server.service.DAOException_Exception;

/**
 * Servlet implementation class AddBook
 */
public class AddBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Log log = Log.getInstance(Log.DEBUG, AddBook.class);

	private BookService service;
	
	@Override
	public void init() throws ServletException {
		super.init();
		ServletContext ctx = getServletContext();
		service = (BookService) ctx.getAttribute("BookService");
		log.trace("Get attribute BookService --> " + service);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Book book = new Book();
		HttpSession session = request.getSession();
		log.debug("Requst encoding --> " + request.getCharacterEncoding());
		try {
			book.setTitle(request.getParameter("title"));
			book.getAuthor().add(request.getParameter("author"));
			book.setIsbn(request.getParameter("isbn"));
			book.setPrice(Double.parseDouble(request.getParameter("price")));
			book.setCount(Integer.parseInt(request.getParameter("count")));
			log.debug("Get book from request --> " + book);
			
			// ----------------------------
			// TODO Validate book
			// ----------------------------
			
			// send request to web-service
			int id = service.addBook(book);
			log.debug("Book added with id --> " + id);
			
			// if ok set book into session attribute
			session.setAttribute("book", book);
		} catch (DAOException_Exception e) {
			// Internal exception in web-service 
			session.setAttribute("errorMsg", "Error: Can not add book into database");
			log.debug("Set errorMsg to the session", e.getMessage());
		} catch (NumberFormatException e) {
			// Can not parse price or count
			session.setAttribute("errorMsg", "Error: uncorrect price or count");
			log.debug("Set errorMsg to the session", e.getMessage());
		} catch (Exception e) {
			// Any other exceptions
			session.setAttribute("errorMsg", "Error: Can not comminicate with book service");
			log.debug("Set errorMsg to the session", e.getMessage());
		}
		
		// PRG pattern
		response.sendRedirect("viewbook.jsp");
		log.debug("Redirect to viewbook.jsp");
	}

}
