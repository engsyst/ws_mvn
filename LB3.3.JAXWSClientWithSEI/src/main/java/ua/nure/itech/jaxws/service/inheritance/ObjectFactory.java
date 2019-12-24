
package ua.nure.itech.jaxws.service.inheritance;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ua.nure.itech.jaxws.service.inheritance package. 
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

    private final static QName _HelloUserResponse_QNAME = new QName("http://inheritance.service.jaxws.itech.nure.ua/", "helloUserResponse");
    private final static QName _HelloUser_QNAME = new QName("http://inheritance.service.jaxws.itech.nure.ua/", "helloUser");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ua.nure.itech.jaxws.service.inheritance
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link HelloUserResponse }
     * 
     */
    public HelloUserResponse createHelloUserResponse() {
        return new HelloUserResponse();
    }

    /**
     * Create an instance of {@link HelloUser }
     * 
     */
    public HelloUser createHelloUser() {
        return new HelloUser();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://inheritance.service.jaxws.itech.nure.ua/", name = "helloUserResponse")
    public JAXBElement<HelloUserResponse> createHelloUserResponse(HelloUserResponse value) {
        return new JAXBElement<HelloUserResponse>(_HelloUserResponse_QNAME, HelloUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://inheritance.service.jaxws.itech.nure.ua/", name = "helloUser")
    public JAXBElement<HelloUser> createHelloUser(HelloUser value) {
        return new JAXBElement<HelloUser>(_HelloUser_QNAME, HelloUser.class, null, value);
    }

}
