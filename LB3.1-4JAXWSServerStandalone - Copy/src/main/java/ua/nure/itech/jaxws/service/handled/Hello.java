package ua.nure.itech.jaxws.service.handled;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebParam.Mode;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.Holder;

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
