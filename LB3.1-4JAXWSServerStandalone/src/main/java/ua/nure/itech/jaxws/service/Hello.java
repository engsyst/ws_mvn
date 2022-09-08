package ua.nure.itech.jaxws.service;

import jakarta.jws.WebService;

/**
 * Web-service class ua.nure.itech.jaxws.service.Hello
 * 
 * @author engsyst
 */

@WebService
public class Hello {
	public String hello(String name) {
		return "Hello " + name + "!";
	}
}
