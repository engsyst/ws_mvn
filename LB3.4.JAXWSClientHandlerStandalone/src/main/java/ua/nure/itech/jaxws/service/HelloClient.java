package ua.nure.itech.jaxws.service;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.JAXBException;
import jakarta.xml.ws.Holder;
import jakarta.xml.ws.handler.Handler;
import jakarta.xml.ws.handler.HandlerResolver;
import jakarta.xml.ws.handler.PortInfo;
import ua.nure.itech.jaxws.SecurityHandler;
import ua.nure.itech.jaxws.service.handled.Hello;
import ua.nure.itech.jaxws.service.handled.HelloUser;
import ua.nure.itech.jaxws.service.handled.Hello_Service;
import ua.nure.itech.jaxws.service.handled.SecurityHeader;

public class HelloClient {

	private static final String CLIENT_TOKEN = "I_am_client";

	static class ClientHandlerResolver implements HandlerResolver {
		@SuppressWarnings("rawtypes")
		@Override
		public List<Handler> getHandlerChain(PortInfo portInfo) {
			List<Handler> list = new ArrayList<>();
			try {
				list.add(new SecurityHandler());
			} catch (JAXBException e) {
				System.err.println(e.getMessage());
			}
			return list;
		}
	}

	public static void main(String[] args) {
		Hello_Service service = new Hello_Service();

		// way to add message handler
		service.setHandlerResolver(new ClientHandlerResolver());
		Hello port = service.getHelloPort();
		SecurityHeader clientToken = new SecurityHeader(CLIENT_TOKEN);
		Holder<SecurityHeader> serverToken = new Holder<>();
		HelloUser userName = new HelloUser();
		userName.setUserName("User");
		System.out.println(port.helloUser(userName, clientToken, null).getReturn());
		try {
			System.out.println(port.helloUser(userName, null, null).getReturn());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
