package ua.nure.itech.jaxws.service;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import ua.nure.itech.jaxws.service.inheritance.Hello;
import ua.nure.itech.jaxws.service.inheritance.Hello_Service;


/**
 * Supplementary project is <b>LB3mvnWSServerJAXWSExample</b>.<br/>
 * Implementor is <b>ua.nure.itech.jaxws.service.inheritance.HelloImpl</b>.
 */
public class HelloClient {

	// This URL points to the internal wsdl.
	// In the internal wsdl service URL has changed port to use TCP-Monitor.
	final static String internalUrl = "file:///" + new File("").getAbsolutePath() + "/hello.wsdl";

	// If "Connection refused" exception occurs use server url instead or
	// run TCP-Monitor on 19090 port.
	final static String serviceUrl = "http://localhost:9090/hello.wsdl";

	private static final int SIZE = 10;

	public static void main(String[] args) throws MalformedURLException {
		// uncomment two lines above if you want log messages to the System.out on the client side
//		System.setProperty("com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump", "true");
//		System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");

		// Create service object
		Hello_Service service = new Hello_Service(new URL(internalUrl));

		// Get client for concrete PORT of HelloService
		Hello client = service.getHelloImplPort();

		// call remote operation on service as local method
		for (int i = 0; i < SIZE; i++) {
			try {
				System.out.println(client.helloUser("User " + i));
			} catch (Exception e) {
				System.out.println("TCP Monitor was not started.\n" +
						"Try use original wsdl");
				service = new Hello_Service(new URL(serviceUrl));
				client = service.getHelloImplPort();
			}
		}

	}
}
