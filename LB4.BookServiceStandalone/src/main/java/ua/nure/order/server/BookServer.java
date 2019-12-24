package ua.nure.order.server;

import java.util.Scanner;

import javax.xml.ws.Endpoint;

public class BookServer {
	public static final Object implementor = new ua.nure.order.server.service.BookServiceImpl();
	public static final String address = "http://localhost:9000/books";
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Starting Server");
		Endpoint endpoint = Endpoint.publish(address, implementor);
		
		System.out.println("Server ready... at " + address);

		System.err.println("Press <enter> to stop service... ");
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
		sc.close();
		endpoint.stop();
		System.out.println("Server exit");
	}
}
