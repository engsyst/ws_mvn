
package ua.nure.itech.jaxws.service.handled;

import jakarta.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 3.0.0
 * Generated source version: 3.0
 * 
 */
@WebFault(name = "ValidationException", targetNamespace = "http://handled.service.jaxws.itech.nure.ua/")
public class ValidationException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private ValidationException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public ValidationException_Exception(String message, ValidationException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public ValidationException_Exception(String message, ValidationException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: ua.nure.itech.jaxws.service.handled.ValidationException
     */
    public ValidationException getFaultInfo() {
        return faultInfo;
    }

}
