package ua.nure.itech.jaxws.service;

import javax.jws.WebService;
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;

/**
 * Web-service class ua.nure.itech.jaxws.service.Hello
 * 
 * @author engsyst
 */

//@Path("hello/{userName")
@WebService
public class Hello {
//	@GET
	public String hello(String name) {
		return "Hello " + name + "!";
	}
}
