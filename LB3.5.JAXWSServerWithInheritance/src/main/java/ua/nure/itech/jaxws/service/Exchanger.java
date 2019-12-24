package ua.nure.itech.jaxws.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebParam.Mode;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.ws.Holder;
//@SOAPBinding(style=SOAPBinding.Style.DOCUMENT)

@WebService
public interface Exchanger {
	@WebMethod
	//@Action(fault=@javax.xml.ws.FaultAction(className = ExchangeException.class))
	@WebResult(name="value")
	double exchange(
			@WebParam(name="value") 
			double value,
			@WebParam(
					header=true,
					name="from"
					) // Use if this parameter MUST be in soap:Header
			String from,
			@WebParam(
					name="currency", 
//					header=true, 
					mode=Mode.OUT) 
			Holder<String> to) throws ExchangeException;
}
