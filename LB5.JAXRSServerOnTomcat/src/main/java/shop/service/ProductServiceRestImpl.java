package shop.service;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.container.TimeoutHandler;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBException;

import shop.Store;
import shop.entity.Product;
import shop.entity.ProductList;

@Path(Constants.PRODUCTS_SERVICE_PATH)
public class ProductServiceRestImpl {

	private static final int LONG_WORK_WAIT_TIMEOUT = 1000;

	private static final int DEFAULT_THREAD_POOL_SIZE = 10;

	private Store store;

	ExecutorService executor = Executors.newFixedThreadPool(DEFAULT_THREAD_POOL_SIZE);

	private void log(String msg) {
		System.out.println(this.getClass().getSimpleName() + ": " + msg);
	}

	@PostConstruct
	public void init() throws JAXBException {
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
		return Response.ok(new ProductList(store.all().stream().
				map(p -> p.getProduct()).collect(Collectors.toList()))
		).build();
	}
	
	@GET
	@Path("async")
	public void longRuningJob(@Suspended AsyncResponse ar) {
		log("longRuningJob");
		ar.setTimeoutHandler(new TimeoutHandler() {
			@Override
			public void handleTimeout(AsyncResponse ar) {
				ar.resume(Response.status(Status.SERVICE_UNAVAILABLE)
						.entity("Operation timed out -- please try again")
						.build());
			}
		});
		ar.setTimeout(LONG_WORK_WAIT_TIMEOUT + 5, TimeUnit.MILLISECONDS);

		executor. execute(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("Begin long work");
					Thread.sleep(LONG_WORK_WAIT_TIMEOUT);
					ar.resume(all());
					System.out.println("End long work");
				} catch (Exception e) {
					System.err.println("Aborted");
				}
			}
		});
	}
	
	@GET
//	@Path("{pattern}")
	public ProductList find(@QueryParam("pattern") String pattern) {
		log("find");
		return new ProductList(store.all(pattern).stream().
				map(p -> p.getProduct()).collect(Collectors.toList()));
	}
	
	@Path("search")
	public ProductsSearcher find() throws IOException {
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
	public void update(Product product, @QueryParam("amount") int amount) throws ProductNotValidException, ProductNotAvailableException {
		log("update");
		store.update(new ProductLine(product, amount));
	}

	@DELETE
	public void remove() throws ProductNotAvailableException {
		log("remove");
		store.clear();
	}

}
