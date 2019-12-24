package ua.nure.itech.jaxws.service;

import javax.jws.HandlerChain;
import javax.jws.WebService;

/**
 * Web-service class ua.nure.itech.jaxws.service.Hello
 * 
 * @author engsyst
 */

@WebService
//@HandlerChain(file="hello_handler.xml")
public class Hello {
	public String hello(String name) {
		return "Hello " + name + "!";
	}
}
