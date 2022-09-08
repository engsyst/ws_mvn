package ua.nure.itech.jaxws.service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;

/**
 * Web-service class ua.nure.itech.jaxws.service.HelloAnnotated
 * 
 * Remove all annotation and it parameters excluding @WebService to restore default wsdl generation
 * 
 * @author engsyst
 */

@WebService(serviceName="HelloService", name = "Hello")
public class HelloAnnotated {
	@WebMethod(operationName = "hello", action = "urn:Hello")
	@RequestWrapper(className = "ua.nure.itech.jaxws.service.jaxws.Hello", localName = "hello", targetNamespace = "http://service.jaxws.itech.nure.ua/")
	@ResponseWrapper(className = "ua.nure.itech.jaxws.service.jaxws.HelloResponse", localName = "hellodResponse", targetNamespace = "http://service.jaxws.itech.nure.ua/")
	@WebResult(name = "return")
	public String hello(@WebParam(name = "userName") String name) {
		return "HelloAnnotated " + name + "!";
	}
}
