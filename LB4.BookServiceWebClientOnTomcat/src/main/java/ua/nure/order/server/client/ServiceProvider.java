package ua.nure.order.server.client;

import java.net.MalformedURLException;
import java.net.URL;

import ua.nure.order.server.service.BookService;

public class ServiceProvider {
	private static BookService books;
	private static String url;
	
	private static BookService getDefaultBooks() {
		ua.nure.order.server.service.Books port = new ua.nure.order.server.service.Books();
		return port.getBookPort();
	}
	
	private static BookService getBooks(String url) throws MalformedURLException {
		ua.nure.order.server.service.Books port = new ua.nure.order.server.service.Books(new URL(url));
		return port.getBookPort();
	}
	
	public static BookService getInstance(String url) {
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
				getBooks(url);
				ServiceProvider.url = url;
			} catch (MalformedURLException e) {
				ServiceProvider.url = null;
				books = null;
			}
		}
		return books;
	}
}
