package ua.nure.itech.jaxws.service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;

/**
 * Web-service class ua.nure.itech.jaxws.service.HelloAnnotated
 * 
 * Remove all annotation and it parameters exclude @WebService to restore default wsdl generation
 * 
 * @author engsyst
 */

//@Path("rest")
@WebService(portName="hello", serviceName="HelloAnnotated")
public class HelloAnnotated {
//	@GET
//	@Path("hello/{userName}")
	@WebMethod(operationName = "hello", action = "urn:HelloAnnotated")
	@RequestWrapper(className = "ua.nure.itech.jaxws.service.jaxws.HelloAnnotated", localName = "helloAnnotated", targetNamespace = "http://service.jaxws.itech.nure.ua/")
	@ResponseWrapper(className = "ua.nure.itech.jaxws.service.jaxws.HelloAnnotatedResponse", localName = "helloAnnotatedResponse", targetNamespace = "http://service.jaxws.itech.nure.ua/")
	@WebResult(name = "return")
	public String hello(
			@WebParam(name = "userName") 
//			@PathParam("userName")
			String name) {
		return "HelloAnnotated " + name + "!";
	}
}
