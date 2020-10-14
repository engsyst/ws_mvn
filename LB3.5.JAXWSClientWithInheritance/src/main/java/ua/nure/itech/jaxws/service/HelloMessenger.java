
package ua.nure.itech.jaxws.service;

import java.util.concurrent.Future;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.AsyncHandler;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.Response;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.2
 * Generated source version: 2.2
 * 
 */
@WebService(name = "HelloMessenger", targetNamespace = "http://service.jaxws.itech.nure.ua/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface HelloMessenger {


    /**
     * 
     * @param arg0
     * @return
     *     returns javax.xml.ws.Response<ua.nure.itech.jaxws.service.HelloResponse>
     */
    @WebMethod(operationName = "hello")
    @RequestWrapper(localName = "hello", targetNamespace = "http://service.jaxws.itech.nure.ua/", className = "ua.nure.itech.jaxws.service.Hello")
    @ResponseWrapper(localName = "helloResponse", targetNamespace = "http://service.jaxws.itech.nure.ua/", className = "ua.nure.itech.jaxws.service.HelloResponse")
    public Response<HelloResponse> helloAsync(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg0
     * @param asyncHandler
     * @return
     *     returns java.util.concurrent.Future<? extends java.lang.Object>
     */
    @WebMethod(operationName = "hello")
    @RequestWrapper(localName = "hello", targetNamespace = "http://service.jaxws.itech.nure.ua/", className = "ua.nure.itech.jaxws.service.Hello")
    @ResponseWrapper(localName = "helloResponse", targetNamespace = "http://service.jaxws.itech.nure.ua/", className = "ua.nure.itech.jaxws.service.HelloResponse")
    public Future<?> helloAsync(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "asyncHandler", targetNamespace = "")
        AsyncHandler<HelloResponse> asyncHandler);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "hello", targetNamespace = "http://service.jaxws.itech.nure.ua/", className = "ua.nure.itech.jaxws.service.Hello")
    @ResponseWrapper(localName = "helloResponse", targetNamespace = "http://service.jaxws.itech.nure.ua/", className = "ua.nure.itech.jaxws.service.HelloResponse")
    @Action(input = "http://service.jaxws.itech.nure.ua/HelloMessenger/helloRequest", output = "http://service.jaxws.itech.nure.ua/HelloMessenger/helloResponse")
    public String hello(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param from
     * @param parameters
     * @return
     *     returns javax.xml.ws.Response<ua.nure.itech.jaxws.service.ExchangeResponse>
     */
    @WebMethod(operationName = "exchange")
    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    public Response<ExchangeResponse> exchangeAsync(
        @WebParam(name = "exchange", targetNamespace = "http://service.jaxws.itech.nure.ua/", partName = "parameters")
        Exchange parameters,
        @WebParam(name = "from", targetNamespace = "http://service.jaxws.itech.nure.ua/", header = true, partName = "from")
        String from);

    /**
     * 
     * @param from
     * @param asyncHandler
     * @param parameters
     * @return
     *     returns java.util.concurrent.Future<? extends java.lang.Object>
     */
    @WebMethod(operationName = "exchange")
    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    public Future<?> exchangeAsync(
        @WebParam(name = "exchange", targetNamespace = "http://service.jaxws.itech.nure.ua/", partName = "parameters")
        Exchange parameters,
        @WebParam(name = "from", targetNamespace = "http://service.jaxws.itech.nure.ua/", header = true, partName = "from")
        String from,
        @WebParam(name = "exchangeResponse", targetNamespace = "", partName = "asyncHandler")
        AsyncHandler<ExchangeResponse> asyncHandler);

    /**
     * 
     * @param from
     * @param parameters
     * @return
     *     returns ua.nure.itech.jaxws.service.ExchangeResponse
     * @throws ExchangeException_Exception
     */
    @WebMethod
    @WebResult(name = "exchangeResponse", targetNamespace = "http://service.jaxws.itech.nure.ua/", partName = "result")
    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    @Action(input = "http://service.jaxws.itech.nure.ua/HelloMessenger/exchangeRequest", output = "http://service.jaxws.itech.nure.ua/HelloMessenger/exchangeResponse", fault = {
        @FaultAction(className = ExchangeException_Exception.class, value = "http://service.jaxws.itech.nure.ua/HelloMessenger/exchange/Fault/ExchangeException")
    })
    public ExchangeResponse exchange(
        @WebParam(name = "exchange", targetNamespace = "http://service.jaxws.itech.nure.ua/", partName = "parameters")
        Exchange parameters,
        @WebParam(name = "from", targetNamespace = "http://service.jaxws.itech.nure.ua/", header = true, partName = "from")
        String from)
        throws ExchangeException_Exception
    ;

}