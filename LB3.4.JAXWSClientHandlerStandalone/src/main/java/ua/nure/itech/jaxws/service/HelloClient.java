package ua.nure.itech.jaxws.service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

import jakarta.xml.bind.JAXBException;
import jakarta.xml.ws.handler.Handler;
import jakarta.xml.ws.handler.HandlerResolver;
import jakarta.xml.ws.handler.PortInfo;
import ua.nure.itech.jaxws.service.handled.Hello;
import ua.nure.itech.jaxws.service.handled.HelloService;
import ua.nure.itech.jaxws.service.handled.HelloUser;
import ua.nure.itech.jaxws.service.handled.SecurityHeader;
import ua.nure.itech.jaxws.service.handled.User;
import ua.nure.itech.jaxws.service.handled.ValidationException_Exception;
import ua.nure.itech.jaxws.service.handler.SecurityHandler;

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
		HelloService service = new HelloService();

		// way to add message handler
		service.setHandlerResolver(new ClientHandlerResolver());
		Hello port = service.getHelloPort();
		SecurityHeader clientToken = Util.createSecurityHeader(CLIENT_TOKEN);
		HelloUser params = new HelloUser();
		Image avatar;
		try {
			avatar = ImageIO.read(new File("avatar.png"));
			params.setUser(new User("user", avatar));
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		try {
			System.out.println(port.helloUser(params, clientToken, null).getReturn());
			System.out.println(port.helloUser(params, null, null).getReturn());
		} catch (ValidationException_Exception e) {
			e.printStackTrace();
		}
	}

}
