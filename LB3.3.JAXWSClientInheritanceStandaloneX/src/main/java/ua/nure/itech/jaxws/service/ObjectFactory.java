
package ua.nure.itech.jaxws.service;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ua.nure.itech.jaxws.service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Hello_QNAME = new QName("http://service.jaxws.itech.nure.ua/", "hello");
    private final static QName _HellodResponse_QNAME = new QName("http://service.jaxws.itech.nure.ua/", "hellodResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ua.nure.itech.jaxws.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Hello_Type }
     * 
     */
    public Hello_Type createHello_Type() {
        return new Hello_Type();
    }

    /**
     * Create an instance of {@link HellodResponse }
     * 
     */
    public HellodResponse createHellodResponse() {
        return new HellodResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hello_Type }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Hello_Type }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.jaxws.itech.nure.ua/", name = "hello")
    public JAXBElement<Hello_Type> createHello(Hello_Type value) {
        return new JAXBElement<Hello_Type>(_Hello_QNAME, Hello_Type.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HellodResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link HellodResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.jaxws.itech.nure.ua/", name = "hellodResponse")
    public JAXBElement<HellodResponse> createHellodResponse(HellodResponse value) {
        return new JAXBElement<HellodResponse>(_HellodResponse_QNAME, HellodResponse.class, null, value);
    }

}
