package shop.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.container.AsyncResponse;
import jakarta.ws.rs.container.Suspended;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import shop.Store;
import shop.entity.Product;
import shop.entity.ProductList;

@Path(Constants.PRODUCTS_SERVICE_PATH)
public class ProductServiceRestImpl {

	private static final long LONG_WORK_WAIT_TIMEOUT = 1000;

	private static final int DEFAULT_THREAD_POOL_SIZE = 10;

	private Store store;

	ExecutorService executor = Executors.newFixedThreadPool(DEFAULT_THREAD_POOL_SIZE);

	private void log(String msg) {
		System.out.println(this.getClass().getSimpleName() + ": " + msg);
	}

	@PostConstruct
	public void init() {
		log("init");
		store = Store.getInstance();
	}

	@PreDestroy
	public void destroy() {
		log("destroy");
		store = null;
		executor.shutdown();
	}

	@GET
	public Response all() {
		log("all");
		return Response.ok(new ProductList(store.all("").stream().
				map(ProductLine::getProduct).collect(Collectors.toList()))
		).build();
	}
	
	@GET
	@Path("async")
	public void longRunningJob(@Suspended AsyncResponse ar) {
		log("longRunningJob");
		ar.setTimeoutHandler(ar1 -> ar1.resume(Response.status(Status.SERVICE_UNAVAILABLE)
				.entity("Operation timed out -- please try again")
				.build()));
		ar.setTimeout(LONG_WORK_WAIT_TIMEOUT + 5, TimeUnit.MILLISECONDS);

		executor. execute(() -> {
			try {
				System.out.println("Begin long work");
				Thread.sleep(LONG_WORK_WAIT_TIMEOUT);
				ar.resume(all());
				System.out.println("End long work");
			} catch (Exception e) {
				System.err.println("Aborted");
			}
		});
	}
	
	@GET
//	@Path("{pattern}")
	public ProductList find(@QueryParam("pattern") String pattern) {
		log("find");
		return new ProductList(store.all(pattern).stream().
				map(ProductLine::getProduct).collect(Collectors.toList()));
	}
	
	@Path("search")
	public ProductsSearcher find() {
		log("find");
		return new ProductsSearcher();
	}
	
	@GET
	@Path("/{id: \\d+}")
	public Product productDetails(@PathParam("id") long id) throws ProductNotAvailableException {
		log("productDetails");
		ProductLine product = store.get(id);
		if (product == null) {
			throw new ProductNotAvailableException("Unknown id: " + id);
		}
		return product.getProduct();
	}

	@PUT
	public long add(Product product, @QueryParam("amount") int amount) throws ProductNotValidException {
		log("add");
		return store.add(new ProductLine(product, amount));
	}

	@POST
	public void update(Product product, @QueryParam("amount") int amount) throws ProductNotAvailableException {
		log("update");
		store.update(new ProductLine(product, amount));
	}

	@DELETE
	public void remove() {
		log("remove");
		store.clear();
	}

}
