package shop.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import shop.Store;
import shop.entity.Product;

public class ProductsSearcher {

	private Store store = Store.getInstance();

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Product> search(@MatrixParam("title") String title, @MatrixParam("author") String author) {
		System.out.println("search for Title: " + title + " Author: " + author);
		List<Product> list = store.all(title, author)
				.stream()
				.map(p -> p.getProduct())
				.collect(Collectors.toList());
		return list;
	}

}
