package ua.nure.itech.jaxws.service.inheritance;

import jakarta.jws.WebService;

@WebService(
		endpointInterface = "ua.nure.itech.jaxws.service.inheritance.Hello", 
		serviceName = "Hello"
)
public class HelloImpl implements Hello {

	@Override
	public String hello(String text) {
		return "Hello " + text + "!";
	}

}
