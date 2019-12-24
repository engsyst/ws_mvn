package ua.nure.itech.jaxws.service;

import javax.jws.WebService;
import javax.xml.ws.Holder;

/**
 * Web-service class ua.nure.itech.jaxws.service.Hello
 * 
 * @author engsyst
 */
@WebService(endpointInterface = "ua.nure.itech.jaxws.service.HelloMessenger")
public class Hello implements HelloMessenger {

	public String hello(String name) {
		return "Hello " + name + "!";
	}

	public double exchange(double value, String from, Holder<String> to) throws ExchangeException {
		if (!"USD".equals(from))
			throw new ExchangeException();
		to.value = "UAH";
		return value * 25;
	}
}
