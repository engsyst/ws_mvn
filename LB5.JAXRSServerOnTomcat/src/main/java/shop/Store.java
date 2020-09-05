package shop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import shop.entity.Book;
import shop.entity.Journal;
import shop.service.ProductLine;
import shop.service.ProductNotAvailableException;
import shop.service.ProductNotValidException;

@javax.annotation.ManagedBean
public class Store {

	private static Map<Long, ProductLine> items;
	private static long key;
	private static Store instance;

	static {
		items = new LinkedHashMap<>();
		items.put(++key, new ProductLine(new Book(key, "XML", 250, null, null, Arrays.asList("Pupkin"), null), 10));
		items.put(++key, new ProductLine(
				new Book(key, "JAX-WS в примерах", 250, null, null, Arrays.asList("Pupkin"), null), 10));
		items.put(++key, new ProductLine(new Journal(key, "SOAP Web-services", 50, null, null, null), 10));
	}

	private Store() {
	}

	public static synchronized Store getInstance() {
		if (instance == null) {
			instance = new Store();
		}
		return instance;
	}

	public synchronized List<ProductLine> all(String... pattern) {
		if (pattern == null || pattern.length == 0) {
			return new ArrayList<>(items.values());
		}
		return new ArrayList<>(items.values().stream().filter(p -> p.getProduct().getTitle().matches("(?Uui).*" + pattern[0] + ".*")).collect(Collectors.toList()));
	}

	public synchronized long add(ProductLine product) throws ProductNotValidException {
		String title = product.getProduct().getTitle();
		if (title == null || title.isEmpty()) {
			throw new ProductNotValidException("Не допустимое название товара: [" + title + "]");
		}

		product.setId(++key);
		items.put(key, product);
		return key;
	}

	public synchronized ProductLine get(long id) {
		return items.get(id);
	}

	public synchronized void remove(ProductLine product) throws ProductNotAvailableException {
		items.remove(getKey(product));
	}

	public synchronized void remove(long id) throws ProductNotAvailableException {
		items.remove(id);
	}
	
	public synchronized void clear() throws ProductNotAvailableException {
		items.clear();
	}
	
	public synchronized void update(ProductLine product) throws ProductNotAvailableException {
		items.put(getKey(product), product);
	}

	public synchronized int availableProduct(long id) throws ProductNotAvailableException {
		ProductLine p = items.get(id);
		if (p == null) {
			throw new ProductNotAvailableException("Не действительный товар, id: " + id);
		}
		return p.getAvailable();
	}

	private synchronized long getKey(ProductLine product) throws ProductNotAvailableException {
		ProductLine p = items.get(product.getId());
		if (p == null) {
			throw new ProductNotAvailableException("Не действительный товар, id: " + product.getId());
		}
		return p.getId();
	}
}
