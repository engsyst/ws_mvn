package ua.nure.itech.jaxws.service.handled;

import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.xml.ws.Holder;

@WebService(
		endpointInterface = "ua.nure.itech.jaxws.service.handled.Hello", 
		serviceName = "Hello",
		portName = "HelloPort"
)
@HandlerChain(file = "hello_handler.xml")
public class HelloHandled implements Hello {

	@Override
	public String hello(String text, SecurityHeader clientToken, Holder<SecurityHeader> serverToken) {
//		System.out.println(clientToken.token);
//		serverToken.value = new SecurityHeader("I am server");
		return "Hello " + text + "!";
	}

}
