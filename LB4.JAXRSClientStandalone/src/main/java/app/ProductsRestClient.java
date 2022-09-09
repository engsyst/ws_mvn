package app;

import java.net.URI;
import java.util.List;
import java.util.concurrent.Future;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation.Builder;
import jakarta.ws.rs.client.InvocationCallback;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.*;

import shop.entity.Product;
import shop.entity.ProductList;
import shop.service.Constants;

public class ProductsRestClient extends Application {

	private static final URI BASE_URI = getBaseURI(Constants.BASE_URI, Constants.PORT, Constants.APPLICATION_PATH,
			Constants.PRODUCTS_SERVICE_PATH);

	private static class Canceler<T> implements Runnable {
		private final Future<T> future;
		private final long timeout;
		
		public Canceler(final Future<T> future, long timeout) {
			this.future = future;
			this.timeout = timeout;
		}
		@Override
		public void run() {
			try {
				Thread.sleep(timeout);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return;
			}

			if (!future.isDone())
				future.cancel(true);
			System.out.println("Canceler. Wait done ...");
		}
	}
	
	public static void main(String[] args) {
		ProductList products;
		List<Product> list;
		Product product = null;
		
		Client client = ClientBuilder.newClient();
		// Дополнительная конфигурация клиента по умолчанию
//		client
//			.register(JAXBContextProvider.class)
//			.register(ProductJSONMessageBodyReader.class)
//			.register(ProductListJSONMessageBodyReader.class)
//			.register(ProductXMLMessageReader.class)
//			.register(ProductXMLMessageWriter.class)
//			.register(JacksonJsonProvider.class)
//			;

		WebTarget target = client.target(BASE_URI);
		// Конфигурирование контекста запроса если необходимо
		// ... 

		System.out.println("\n---=== All products ===---");
		System.out.println("\n--- XML to String ---");
		Builder request = target.request()
				.accept(MediaType.APPLICATION_XML);
		
		// Выполнить запрос GET
		Response resp = request.get();
		// Здесь можно получить доп. информацию о запросе
		// ...
		// MultivaluedMap<String, Object> headers = r.getHeaders();
		// ...

		String body;
		if (Response.Status.OK.getStatusCode() == resp.getStatus()) {
			// Прочитать тело ответа
			body = resp.readEntity(String.class);
			System.out.println(body);
		}

		// или сразу получить объект при получении ответа
		// Выполнить запрос GET
		body = request.get(String.class);
		System.out.println(body);

		System.out.println("\n--- XML to ProductList ---");
//		request = target.request()
//				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_XML);
		try {
			products = request.get(ProductList.class);
			System.out.println(products);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("\n--- XML to List<Product> ---");
//		request = target.request()
//				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_XML);
		try {
			list = request.get(new GenericType<List<Product>>() {});
			System.out.println(list);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("\n--- JSON to String ---");
		request = target
				.request()
				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
		body = request.get(String.class);
		System.out.println(body);

		System.out.println("\n--- JSON to ProductList ---");
//		request = target
//				.request()
//				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
		try {
			products = request.get(ProductList.class);
			System.out.println(products);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("\n--- JSON to List<Product> ---");
//		request = target
//				.path("search;title=x;author=pup")
//				.request()
//				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
		try {
			Response response = request.get();
//			list = response.readEntity(new GenericType<List<Product>>() {});
			list = response.readEntity(ProductList.class).getProduct();
			System.out.println(list);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("\n---=== One product ===---");
		
		System.out.println("\n--- XML to String ---");
		request = target.path("2").request()
				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_XML);
		body = request.get(String.class);
		System.out.println(body);

		System.out.println("\n--- XML to Product ---");
//		request = target
//				.path("2")
//				.request()
//				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_XML);
		try {
			product = request.get(Product.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(product);

		System.out.println("\n---=== JSON to String ===---");
		request = target.path("2").request()
				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
		body = request.get(String.class);
		System.out.println(body);

		System.out.println("\n---=== JSON to Product ===---");
//		request = target.path("2").request()
//				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
		try {
			product = request.get(Product.class);
			System.out.println(product);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("\n--- TEXT to String ---");
		request = target.path("2").request()
				.header(HttpHeaders.ACCEPT, MediaType.TEXT_PLAIN);
		body = request.get(String.class);
		System.out.println(body);
		
		System.out.println("\n---=== Async XML to Product ===---");
		request = target.path("2").request()
				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_XML);

		Future<Response> future;
		try {
			future = request.async().get();
			while (!future.isDone()) {
				System.out.println("Do something ...");
				Thread.sleep(1);
			}
			resp = future.get();
			System.out.println("Status: " + resp.getStatus());
			System.out.println("Result: " + resp.readEntity(Product.class));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			future = request.async().get(new InvocationCallback<Response>() {

				@Override
				public void completed(Response response) {
					System.out.println("InvocationCallback Status: " + response.getStatus());
					System.out.println("InvocationCallback Result: " + response.readEntity(Product.class));
				}
				// 
				@Override
				public void failed(Throwable throwable) {
					throwable.printStackTrace();
				}
			});

			// Если долго нет ответа от сервера отменить
			// Желательно в отдельном потоке
			new Thread(new Canceler<>(future, 600)).start();
			
			// Делаем полезную работу
			System.out.println("Do something ...");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		System.out.println("\n---=== Async longWork XML to ProductList ===---");
		request = target.path("async").request()
				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_XML);

		try {
			future = request.async().get();
			while (!future.isDone()) {
				System.out.println("Long work. Do something ...");
				Thread.sleep(300);
			}
			resp = future.get();
			System.out.println("Status: " + resp.getStatus());
			System.out.println("Result: " + resp.readEntity(String.class));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	public static URI getBaseURI(String basePath, int port, String... path) {
		UriBuilder builder = UriBuilder.fromUri(basePath).port(port);
		for (String part : path) {
			builder.path(part);
		}
		URI uri = builder.build();
		System.out.println("uri: " + uri);
		return uri;
	}
}
