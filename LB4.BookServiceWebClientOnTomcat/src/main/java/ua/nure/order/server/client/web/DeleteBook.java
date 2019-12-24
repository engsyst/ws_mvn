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
 * Servlet implementation class DeleteBook
 */
public class DeleteBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Log log = Log.getInstance(Log.DEBUG, DeleteBook.class);

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
		HttpSession session = request.getSession();
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			log.debug("Get id from request --> " + id);
			Book book = service.deleteBook(id);
			session.setAttribute("book", book);
			log.debug("Set deleted book to the session --> " + book);
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
