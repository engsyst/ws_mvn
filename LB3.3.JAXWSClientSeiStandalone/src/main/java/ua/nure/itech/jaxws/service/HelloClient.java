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
	
	/*
	 * hello.wsdl and hello.xsd has been cached locally in the root folder of the project.
	 * The service port has been changed to 9000.
	 * The schema URL has been changed to hello.xsd.
	 * It provides the ability to be independent from the service wsdl.
	 * Also it provide ability to monitor traffic between service and client in real time.
	 * sew. comments below.
	 */

	// This URL points to the internal wsdl.
	// The internal wsdl service URL has changed port to use TCP-Monitor.
	static String internalUrl = "file:///" + new File("").getAbsolutePath() + "/hello.wsdl";

	// If "Connection refused" exception occurs, the server url will be used instead
	// or you should run TCP-Monitor on 9000 port.
	static String serviceUrl = "http://localhost:9090/hello.wsdl";

	private static final int N = 10;

	public static void main(String[] args) throws MalformedURLException {
		// uncomment two lines above if you want log messages to the System.out on the client side
//		System.setProperty("com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump", "true");
//		System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");

		// Create service object
		Hello_Service service = new Hello_Service(new URL(internalUrl));

		// Get client for the specific Web service PORT of HelloService
		Hello client = service.getHelloImplPort();

		// Invoke a remote operation on the service as local method
		for (int i = 0; i < N; i++) {
			try {
				System.out.println(client.helloUser("User " + i));
			} catch (Exception e) {
				System.err.println(e.getMessage());
				System.out.println("TCP Monitor is not started.\n" +
						"Try to use the original wsdl");
				service = new Hello_Service(new URL(serviceUrl));
				client = service.getHelloImplPort();
			}
		}

	}
}
