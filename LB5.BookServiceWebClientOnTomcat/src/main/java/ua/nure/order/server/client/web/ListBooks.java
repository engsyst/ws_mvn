package ua.nure.order.server.client.web;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.nure.order.entity.Book;
import ua.nure.order.server.service.BookService;

/**
 * Servlet implementation class ListBooks
 */
public class ListBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final transient Logger log = LoggerFactory.getLogger(ListBooks.class);

	private BookService service;
	
	@Override
	public void init() throws ServletException {
		super.init();
		ServletContext ctx = getServletContext();
		service = (BookService) ctx.getAttribute("BookService");
		log.trace("Get attribute BookService : {}",service);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Book> books = service.listBooks();
		log.debug("Get books from BookService : {}",books);
		request.setAttribute("books", books);
		log.trace("Set books to the session : {}",books);
		request.getRequestDispatcher("list.jsp").forward(request, response);
		log.trace("Redirect to list.jsp");
	}

}
