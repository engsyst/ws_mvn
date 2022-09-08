package ua.nure.itech.jaxws;

import jakarta.xml.ws.Endpoint;

import java.util.Scanner;

public class HelloServer{
 	final static String address = "http://localhost:9090/hello";
 	// ( 1 ) 
	final static Object implHello = new ua.nure.itech.jaxws.service.Hello();

    public static void main(String args[]) {
		// uncomment two lines below if you want log messages to the System.out on the server side
    	
    	System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dump", "true");
    	System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dump", "true");
    	
    	// Change implementation class to see different examples
        Endpoint ep = Endpoint.publish(address, implHello);
        System.out.println("Server ready at " + address + "?wsdl ...");
        
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        sc.close();
        System.out.println("Server exiting");
        ep.stop();
    }
}
