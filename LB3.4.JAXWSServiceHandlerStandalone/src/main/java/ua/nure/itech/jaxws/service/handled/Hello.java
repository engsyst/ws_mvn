package ua.nure.itech.jaxws.service.handled;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebParam.Mode;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.ws.Holder;

@WebService(name = "Hello")
public interface Hello {
	@WebMethod(operationName = "helloUser", action = "urn:Hello")
	@WebResult(name = "return")
	String hello(
			@WebParam(name = "userName") 
			String text, 
			@WebParam(name = "clientToken", header=true) 
			SecurityHeader clientToken, 
			@WebParam(name = "serverToken", header=true, mode = Mode.OUT) 
			Holder<SecurityHeader> serverToken);
}
