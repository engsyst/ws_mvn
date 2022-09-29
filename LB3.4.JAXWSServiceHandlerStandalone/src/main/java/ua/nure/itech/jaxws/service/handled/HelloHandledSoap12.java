package ua.nure.itech.jaxws.service.handled;

import jakarta.jws.HandlerChain;
import jakarta.jws.WebService;
import jakarta.xml.ws.BindingType;
import jakarta.xml.ws.soap.SOAPBinding;

@WebService(
		endpointInterface = "ua.nure.itech.jaxws.service.handled.Hello", 
		serviceName = "HelloService",
		portName = "HelloPortSoap12"
)
@HandlerChain(file = "hello_handler.xml")
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class HelloHandledSoap12 extends HelloHandled {

}
