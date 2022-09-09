
package ua.nure.itech.jaxws.service.inheritance;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.Action;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 3.0.0
 * Generated source version: 3.0
 * 
 */
@WebService(name = "Hello", targetNamespace = "http://inheritance.service.jaxws.itech.nure.ua/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Hello {


    /**
     * 
     * @param userName
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "urn:Hello")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "helloUser", targetNamespace = "http://inheritance.service.jaxws.itech.nure.ua/", className = "ua.nure.itech.jaxws.service.inheritance.HelloUser")
    @ResponseWrapper(localName = "helloUserResponse", targetNamespace = "http://inheritance.service.jaxws.itech.nure.ua/", className = "ua.nure.itech.jaxws.service.inheritance.HelloUserResponse")
    @Action(input = "urn:Hello", output = "http://inheritance.service.jaxws.itech.nure.ua/Hello/helloUserResponse")
    public String helloUser(
        @WebParam(name = "userName", targetNamespace = "")
        String userName);

}