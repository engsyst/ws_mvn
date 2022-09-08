package ua.nure.itech.jaxws.service.inheritance;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlElement;

@WebService(name = "Hello")
public interface Hello {
	@WebMethod(operationName = "helloUser", action = "urn:Hello")
	@WebResult(name = "return")
	String hello(@WebParam(name = "userName") @XmlElement(required = true) String text);
}
