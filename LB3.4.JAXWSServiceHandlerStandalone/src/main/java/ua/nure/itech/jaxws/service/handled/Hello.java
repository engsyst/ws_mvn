package ua.nure.itech.jaxws.service.handled;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebParam.Mode;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.soap.SOAPConstants;
import jakarta.xml.ws.BindingType;
import jakarta.xml.ws.Holder;
import ua.nure.itech.jaxws.service.entity.User;

@WebService(name = "Hello")
//@BindingType(SOAPConstants.SOAP_1_2_PROTOCOL)
public interface Hello {
	@WebMethod(operationName = "helloUser", action = "urn:Hello")
	@WebResult(name = "return")
	
	String hello(
			@WebParam(name = "user")
			@XmlElement(required = true)
			User user, 
			@WebParam(name = "clientToken", header=true)
			SecurityHeader clientToken, 
			@WebParam(name = "serverToken", header=true, mode = Mode.OUT) 
			Holder<SecurityHeader> serverToken) throws ValidationException;
}
