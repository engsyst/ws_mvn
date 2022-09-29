package ua.nure.order.server.client.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.nure.order.entity.Book;
import ua.nure.order.server.service.BookService;
import ua.nure.order.server.service.DAOException_Exception;

/**
 * Servlet implementation class AddBook
 */
public class AddBook extends HttpServlet {
	private static final String DEBUG_ERROR_MSG = "Set errorMsg to the session";
	private static final long serialVersionUID = 1L;
	private final transient Logger log = LoggerFactory.getLogger(AddBook.class);

	private transient BookService service;
	
	@Override
	public void init() throws ServletException {
		super.init();
		ServletContext ctx = getServletContext();
		service = (BookService) ctx.getAttribute("BookService");
		log.trace("Get attribute BookService : {}",service);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Book book = new Book();
		HttpSession session = request.getSession();
		log.debug("Requst encoding : {}",request.getCharacterEncoding());
		String errMsg = null;
		try {
			book.setTitle(request.getParameter("title"));
			book.getAuthor().add(request.getParameter("author"));
			book.setIsbn(request.getParameter("isbn"));
			book.setPrice(Double.parseDouble(request.getParameter("price")));
			book.setCount(Integer.parseInt(request.getParameter("count")));
			log.debug("Get book from request : {}",book);
			
			// ----------------------------
			// TO DO Validate book
			// ----------------------------
			
			// send request to web-service
			int id = service.addBook(book);
			log.debug("Book added with id : {}",id);
			
			// if ok set book into session attribute
			session.setAttribute("book", book);
		} catch (DAOException_Exception e) {
			// Internal exception in web-service 
			errMsg = "Error: Can not add book into database";
			log.debug(DEBUG_ERROR_MSG, e);
		} catch (NumberFormatException e) {
			// Can not parse price or count
			errMsg = "Error: incorrect price or count";
			log.debug(DEBUG_ERROR_MSG, e);
		} catch (Exception e) {
			// Any other exceptions
			errMsg = "Error: Can not communicate with book service";
			log.debug(DEBUG_ERROR_MSG, e);
		}
		if (errMsg != null) {
			session.setAttribute("errorMsg", errMsg);
		}
		
		// PRG pattern
		response.sendRedirect("viewbook.jsp");
		log.debug("Redirect to viewbook.jsp");
	}

}
