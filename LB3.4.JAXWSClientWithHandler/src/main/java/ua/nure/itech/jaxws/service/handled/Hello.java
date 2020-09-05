
package ua.nure.itech.jaxws.service.handled;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.Holder;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Hello", targetNamespace = "http://handled.service.jaxws.itech.nure.ua/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Hello {


    /**
     * 
     * @param clientToken
     * @param parameters
     * @param serverToken
     * @return
     *     returns ua.nure.itech.jaxws.service.handled.HelloUserResponse
     */
    @WebMethod(action = "urn:Hello")
    @WebResult(name = "helloUserResponse", targetNamespace = "http://handled.service.jaxws.itech.nure.ua/", partName = "result")
    @Action(input = "urn:Hello", output = "http://handled.service.jaxws.itech.nure.ua/Hello/helloUserResponse")
    public HelloUserResponse helloUser(
        @WebParam(name = "helloUser", targetNamespace = "http://handled.service.jaxws.itech.nure.ua/", partName = "parameters")
        HelloUser parameters,
        @WebParam(name = "clientToken", targetNamespace = "http://handled.service.jaxws.itech.nure.ua/", header = true, partName = "clientToken")
        SecurityHeader clientToken,
        @WebParam(name = "serverToken", targetNamespace = "http://handled.service.jaxws.itech.nure.ua/", header = true, mode = WebParam.Mode.OUT, partName = "serverToken")
        Holder<SecurityHeader> serverToken);

}
