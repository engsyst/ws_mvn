package ua.nure.itech.jaxws;

import java.util.Scanner;

import jakarta.xml.ws.Endpoint;

public class HelloServer{
 	static final String ADDRESS = "http://localhost:9090/hello";
	// ( 3 ) 
	static final Object implHelloWithSEI = new ua.nure.itech.jaxws.service.inheritance.HelloImpl();

    public static void main(String[] args) {
		// uncomment two lines below if you want log messages to the System.out on the server side
    	
    	System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dump", "true");
    	System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dump", "true");
    	
    	// Change implementation class to see different examples
        Endpoint ep = Endpoint.publish(ADDRESS, implHelloWithSEI);
        System.out.println("Server ready at " + ADDRESS + "?wsdl ...");
        
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        sc.close();
        System.out.println("Server exiting");
        ep.stop();
    }
}
