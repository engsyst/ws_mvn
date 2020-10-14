
package ua.nure.itech.jaxws.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


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

    private final static QName _ExchangeException_QNAME = new QName("http://service.jaxws.itech.nure.ua/", "ExchangeException");
    private final static QName _Exchange_QNAME = new QName("http://service.jaxws.itech.nure.ua/", "exchange");
    private final static QName _ExchangeResponse_QNAME = new QName("http://service.jaxws.itech.nure.ua/", "exchangeResponse");
    private final static QName _From_QNAME = new QName("http://service.jaxws.itech.nure.ua/", "from");
    private final static QName _Hello_QNAME = new QName("http://service.jaxws.itech.nure.ua/", "hello");
    private final static QName _HelloResponse_QNAME = new QName("http://service.jaxws.itech.nure.ua/", "helloResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ua.nure.itech.jaxws.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ExchangeException }
     * 
     */
    public ExchangeException createExchangeException() {
        return new ExchangeException();
    }

    /**
     * Create an instance of {@link Exchange }
     * 
     */
    public Exchange createExchange() {
        return new Exchange();
    }

    /**
     * Create an instance of {@link ExchangeResponse }
     * 
     */
    public ExchangeResponse createExchangeResponse() {
        return new ExchangeResponse();
    }

    /**
     * Create an instance of {@link Hello }
     * 
     */
    public Hello createHello() {
        return new Hello();
    }

    /**
     * Create an instance of {@link HelloResponse }
     * 
     */
    public HelloResponse createHelloResponse() {
        return new HelloResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExchangeException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ExchangeException }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.jaxws.itech.nure.ua/", name = "ExchangeException")
    public JAXBElement<ExchangeException> createExchangeException(ExchangeException value) {
        return new JAXBElement<ExchangeException>(_ExchangeException_QNAME, ExchangeException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exchange }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Exchange }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.jaxws.itech.nure.ua/", name = "exchange")
    public JAXBElement<Exchange> createExchange(Exchange value) {
        return new JAXBElement<Exchange>(_Exchange_QNAME, Exchange.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExchangeResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ExchangeResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.jaxws.itech.nure.ua/", name = "exchangeResponse")
    public JAXBElement<ExchangeResponse> createExchangeResponse(ExchangeResponse value) {
        return new JAXBElement<ExchangeResponse>(_ExchangeResponse_QNAME, ExchangeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.jaxws.itech.nure.ua/", name = "from")
    public JAXBElement<String> createFrom(String value) {
        return new JAXBElement<String>(_From_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.jaxws.itech.nure.ua/", name = "hello")
    public JAXBElement<Hello> createHello(Hello value) {
        return new JAXBElement<Hello>(_Hello_QNAME, Hello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.jaxws.itech.nure.ua/", name = "helloResponse")
    public JAXBElement<HelloResponse> createHelloResponse(HelloResponse value) {
        return new JAXBElement<HelloResponse>(_HelloResponse_QNAME, HelloResponse.class, null, value);
    }

}
