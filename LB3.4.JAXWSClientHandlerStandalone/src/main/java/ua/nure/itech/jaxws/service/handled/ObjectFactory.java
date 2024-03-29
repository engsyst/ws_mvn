
package ua.nure.itech.jaxws.service.handled;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ua.nure.itech.jaxws.service.handled package. 
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

    private final static QName _ValidationException_QNAME = new QName("http://handled.service.jaxws.itech.nure.ua/", "ValidationException");
    private final static QName _ClientToken_QNAME = new QName("http://handled.service.jaxws.itech.nure.ua/", "clientToken");
    private final static QName _HelloUser_QNAME = new QName("http://handled.service.jaxws.itech.nure.ua/", "helloUser");
    private final static QName _HelloUserResponse_QNAME = new QName("http://handled.service.jaxws.itech.nure.ua/", "helloUserResponse");
    private final static QName _ServerToken_QNAME = new QName("http://handled.service.jaxws.itech.nure.ua/", "serverToken");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ua.nure.itech.jaxws.service.handled
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ValidationException }
     * 
     */
    public ValidationException createValidationException() {
        return new ValidationException();
    }

    /**
     * Create an instance of {@link SecurityHeader }
     * 
     */
    public SecurityHeader createSecurityHeader() {
        return new SecurityHeader();
    }

    /**
     * Create an instance of {@link HelloUser }
     * 
     */
    public HelloUser createHelloUser() {
        return new HelloUser();
    }

    /**
     * Create an instance of {@link HelloUserResponse }
     * 
     */
    public HelloUserResponse createHelloUserResponse() {
        return new HelloUserResponse();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidationException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ValidationException }{@code >}
     */
    @XmlElementDecl(namespace = "http://handled.service.jaxws.itech.nure.ua/", name = "ValidationException")
    public JAXBElement<ValidationException> createValidationException(ValidationException value) {
        return new JAXBElement<ValidationException>(_ValidationException_QNAME, ValidationException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SecurityHeader }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SecurityHeader }{@code >}
     */
    @XmlElementDecl(namespace = "http://handled.service.jaxws.itech.nure.ua/", name = "clientToken")
    public JAXBElement<SecurityHeader> createClientToken(SecurityHeader value) {
        return new JAXBElement<SecurityHeader>(_ClientToken_QNAME, SecurityHeader.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloUser }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link HelloUser }{@code >}
     */
    @XmlElementDecl(namespace = "http://handled.service.jaxws.itech.nure.ua/", name = "helloUser")
    public JAXBElement<HelloUser> createHelloUser(HelloUser value) {
        return new JAXBElement<HelloUser>(_HelloUser_QNAME, HelloUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloUserResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link HelloUserResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://handled.service.jaxws.itech.nure.ua/", name = "helloUserResponse")
    public JAXBElement<HelloUserResponse> createHelloUserResponse(HelloUserResponse value) {
        return new JAXBElement<HelloUserResponse>(_HelloUserResponse_QNAME, HelloUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SecurityHeader }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SecurityHeader }{@code >}
     */
    @XmlElementDecl(namespace = "http://handled.service.jaxws.itech.nure.ua/", name = "serverToken")
    public JAXBElement<SecurityHeader> createServerToken(SecurityHeader value) {
        return new JAXBElement<SecurityHeader>(_ServerToken_QNAME, SecurityHeader.class, null, value);
    }

}
