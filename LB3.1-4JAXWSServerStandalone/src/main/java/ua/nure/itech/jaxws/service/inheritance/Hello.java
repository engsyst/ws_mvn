package ua.nure.itech.jaxws.service.inheritance;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

@WebService(name = "Hello")
public interface Hello {
	@WebMethod(operationName = "helloUser", action = "urn:Hello")
	@WebResult(name = "return")
	String hello(@WebParam(name = "userName") @XmlElement(required = true) String text);
}
