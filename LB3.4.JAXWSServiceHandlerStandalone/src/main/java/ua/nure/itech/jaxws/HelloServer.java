package ua.nure.itech.jaxws;

import java.util.Scanner;

import jakarta.xml.ws.Endpoint;

public class HelloServer{
 	static  String address = "http://localhost:9090/hello";
	// ( 4 ) 
	static Object implHelloWithHandler = new ua.nure.itech.jaxws.service.handled.HelloHandled();

    public static void main(String[] args) {
		// uncomment two lines below if you want log messages to the System.out on the server side
    	
    	System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dump", "true");
    	System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dump", "true");
    	
    	// Change implementation class to see different examples
        Endpoint ep = Endpoint.publish(address, implHelloWithHandler);
        System.out.println("Server ready at " + address + "?wsdl ...");
        
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        sc.close();
        System.out.println("Server exiting");
        ep.stop();
    }
}
