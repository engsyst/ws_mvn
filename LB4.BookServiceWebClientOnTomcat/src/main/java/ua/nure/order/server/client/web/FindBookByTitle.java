package ua.nure.order.server.client.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.log.Log;
import ua.nure.order.entity.Book;
import ua.nure.order.server.service.BookService;

/**
 * Servlet implementation class FindBookByTitle
 */
public class FindBookByTitle extends HttpServlet {
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pattern = request.getParameter("pattern");
		log.debug("Get find pattern from request --> " + pattern);
		
		List<Book> books = service.findByTitle(pattern);
		log.debug("Get books from BookService --> " + books);
		request.setAttribute("books", books);
		log.trace("Set books to the session --> " + books);
		request.getRequestDispatcher("list.jsp").forward(request, response);
		log.trace("Redirect to list.jsp");
	}

}
