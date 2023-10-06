package ua.nure.order.server.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import ua.nure.order.server.service.BookService;
import ua.nure.order.server.service.Books;
import ua.nure.order.server.servicehandler.SecurityHandler;

public class ServiceProvider {
	private static BookService books;
	private static String url;
	
	protected ServiceProvider() {}
	
	private static BookService getDefaultBooks() {
		ua.nure.order.server.service.Books port = new ua.nure.order.server.service.Books();
		port.setHandlerResolver(portInfo -> List.of(new SecurityHandler()));
		return port.getBookPort();
	}
	
	private static BookService getBooks(String url) throws MalformedURLException {
		Books port = new ua.nure.order.server.service.Books(new URL(url));
		port.setHandlerResolver(portInfo -> List.of(new SecurityHandler()));
		return port.getBookPort();
	}
	
	public static synchronized BookService getInstance(String url) {
		if (books == null) {
			if (url == null) {
				books = getDefaultBooks();
			} else {
				try {
					books = getBooks(url);
				} catch (MalformedURLException e) {
					books = null;
				}
			}
		} else if (!url.equals(ServiceProvider.url)) {
			try {
				books = getBooks(url);
				ServiceProvider.url = url;
			} catch (MalformedURLException e) {
				ServiceProvider.url = null;
				books = null;
			}
		}
		return books;
	}
}
