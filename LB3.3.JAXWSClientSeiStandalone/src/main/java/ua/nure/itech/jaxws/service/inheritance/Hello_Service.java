
package ua.nure.itech.jaxws.service.inheritance;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceException;
import jakarta.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 3.0.0
 * Generated source version: 3.0
 * 
 */
@WebServiceClient(name = "Hello", targetNamespace = "http://inheritance.service.jaxws.itech.nure.ua/", wsdlLocation = "http://localhost:9090/hello?wsdl")
public class Hello_Service
    extends Service
{

    private final static URL HELLO_WSDL_LOCATION;
    private final static WebServiceException HELLO_EXCEPTION;
    private final static QName HELLO_QNAME = new QName("http://inheritance.service.jaxws.itech.nure.ua/", "Hello");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:9090/hello?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        HELLO_WSDL_LOCATION = url;
        HELLO_EXCEPTION = e;
    }

    public Hello_Service() {
        super(__getWsdlLocation(), HELLO_QNAME);
    }

    public Hello_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), HELLO_QNAME, features);
    }

    public Hello_Service(URL wsdlLocation) {
        super(wsdlLocation, HELLO_QNAME);
    }

    public Hello_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, HELLO_QNAME, features);
    }

    public Hello_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Hello_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns Hello
     */
    @WebEndpoint(name = "HelloImplPort")
    public Hello getHelloImplPort() {
        return super.getPort(new QName("http://inheritance.service.jaxws.itech.nure.ua/", "HelloImplPort"), Hello.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link jakarta.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Hello
     */
    @WebEndpoint(name = "HelloImplPort")
    public Hello getHelloImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://inheritance.service.jaxws.itech.nure.ua/", "HelloImplPort"), Hello.class, features);
    }

    private static URL __getWsdlLocation() {
        if (HELLO_EXCEPTION!= null) {
            throw HELLO_EXCEPTION;
        }
        return HELLO_WSDL_LOCATION;
    }

}
