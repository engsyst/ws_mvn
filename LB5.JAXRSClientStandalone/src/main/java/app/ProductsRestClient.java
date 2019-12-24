package app;

import java.net.URI;
import java.util.List;
import java.util.concurrent.Future;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import shop.entity.Product;
import shop.entity.ProductList;
import shop.service.Constants;
import shop.service.rest.ProductJSONMessageBodyReader;
import shop.service.rest.ProductListJSONMessageBodyReader;

public class ProductsRestClient extends Application {

	private static final URI BASE_URI = getBaseURI(Constants.BASE_URI, Constants.PORT, Constants.APPLICATION_PATH,
			Constants.PRODUCTS_SERVICE_PATH);

	private static class Canceler<T> implements Runnable {
		final private Future<T> future;
		private long timeout = 30_000;
		
		public Canceler(final Future<T> future, long timeout) {
			this.future = future;
			this.timeout = timeout;
		}
		@Override
		public void run() {
			try {
				Thread.sleep(timeout);
			} catch (InterruptedException e) {
				return;
			}

			if (!future.isDone())
				future.cancel(true);
			System.out.println("Canceler. Wait done ...");
		}
	}
	
	public static void main(String[] args) {
		ProductList products = null;
		List<Product> list = null;
		Product product = null;
		
		Client client = ClientBuilder.newClient();
		// Дополнительная конфигурация клиента по умолчанию
		client
			.register(ProductJSONMessageBodyReader.class)
			.register(ProductListJSONMessageBodyReader.class)
//			.register(JacksonJsonProvider.class)
			;

		WebTarget target = client.target(BASE_URI);
		// Конфигурирование контекста запроса если необходимо
		// ... 

		System.out.println("\n---=== All products ===---");
		System.out.println("\n---=== XML to String ===---");
		Builder request = target.request()
				.accept(MediaType.APPLICATION_XML);
		
		// Выполнить запрос GET
		Response resp = request.get();
		// Здесь можно получить доп. информацию о запросе
		// ...
		// MultivaluedMap<String, Object> headers = r.getHeaders();
		// ...

		String body = null;
		if (Response.Status.OK.getStatusCode() == resp.getStatus()) {
			// Прочитать тело ответа
			body = resp.readEntity(String.class);
			System.out.println(body);
		}

		// или сразу получить объект при получении ответа
		// Выполнить запрос GET
		body = request.get(String.class);
		System.out.println(body);

		System.out.println("\n---=== XML to ProductList ===---");
		request = target.request()
				.header("Accept", MediaType.APPLICATION_XML);
		try {
			products = request.get(ProductList.class);
			System.out.println(products);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("\n---=== XML to List<Product> ===---");
		request = target.request()
				.header("Accept", MediaType.APPLICATION_XML);
		try {
			list = request.get(new GenericType<List<Product>>() {});
			System.out.println(list);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("\n---=== One product ===---");
		System.out.println("\n---=== XML to String ===---");
		request = target.path("2").request()
				.header("Accept", MediaType.APPLICATION_XML);
		body = request.get(String.class);
		System.out.println(body);

		System.out.println("\n---=== XML to Product ===---");
		request = target
				.path("2")
				.request()
				.header("Accept", MediaType.APPLICATION_XML);
		try {
			product = request.get(Product.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(product);

		System.out.println("\n---=== All products ===---");
		System.out.println("\n---=== JSON to String ===---");
		request = target
				.request()
				.header("Accept", MediaType.APPLICATION_JSON);
		body = request.get(String.class);
		System.out.println(body);

		System.out.println("\n---=== JSON to ProductList ===---");
		request = target
				.request()
				.header("Accept", MediaType.APPLICATION_JSON);
		try {
			products = request.get(ProductList.class);
			System.out.println(products);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("\n---=== JSON to List<Product> ===---");
		request = target
				.path("search;title=x;author=pup")
				.request()
				.header("Accept", MediaType.APPLICATION_JSON);
		try {
			list = request.get(new GenericType<List<Product>>() {});
			System.out.println(list);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("\n---=== One product ===---");
		System.out.println("\n---=== TEXT to String ===---");
		request = target.path("2").request()
				.header("Accept", MediaType.TEXT_PLAIN);
		body = request.get(String.class);
		System.out.println(body);

		System.out.println("\n---=== JSON to String ===---");
		request = target.path("2").request()
				.header("Accept", MediaType.APPLICATION_JSON);
		body = request.get(String.class);
		System.out.println(body);

		System.out.println("\n---=== JSON to Product ===---");
		request = target.path("2").request()
				.header("Accept", MediaType.APPLICATION_JSON);
		try {
			product = request.get(Product.class);
			System.out.println(product);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("\n---=== Async XML to Product ===---");
		request = target.path("2").request()
				.header("Accept", MediaType.APPLICATION_XML);

		Future<Response> future = null;
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
			System.out.println(e.getMessage());
		}
		
		System.out.println("\n---=== Async longWork XML to ProductList ===---");
		request = target.path("async").request()
				.header("Accept", MediaType.APPLICATION_XML);

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
			System.out.println(e.getMessage());
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
