package app;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.RuntimeDelegate;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import shop.service.App;
import shop.service.Constants;

public class AppServer {

	public static void main(String[] args) throws InterruptedException, IOException, URISyntaxException {
        System.out.println("\"Products\" Jersey Example Application");
        
        // создать сервер, слушающий порт 8080
        final HttpServer server = HttpServer.create(new InetSocketAddress(getBaseURI(Constants.BASE_URI, Constants.PORT, Constants.APPLICATION_PATH).getPort()), 0);
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                server.stop(0);
            }
        }));

        // создать обработчик JAX-RS Application
        HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(new App(), HttpHandler.class);

        // пусть сервер обрабатывает запросы JAX-RS обработчика по URI
        server.createContext(getBaseURI(Constants.BASE_URI, Constants.PORT, Constants.APPLICATION_PATH).getPath(), handler);
        
        // запустить сервер
        server.start();
        
        System.out.println("Application started.\n"
                + "Try accessing " + getBaseURI(Constants.BASE_URI, Constants.PORT, Constants.APPLICATION_PATH, Constants.PRODUCTS_SERVICE_PATH) +" in the browser.\n");

        Thread.currentThread().join();

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

	static void printProperties(Map<String, Object> map) {
		Set<String> keys = map.keySet();
		Iterator<String> it = keys.iterator();
		while (it.hasNext()) {
			Object object = (Object) it.next();
			System.out.printf("%s - %s", object, map.get(object));
		}
	}
}
