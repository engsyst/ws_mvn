package ua.nure.itech.jaxws.handler;

import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;

public class HelloHandler implements SOAPHandler<SOAPMessageContext> {

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		SOAPMessage msg = context.getMessage();
		boolean isOutbound = (boolean) context.get(SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY);
		boolean result = true;
		try {
			if (isOutbound) {
				result = handleOutboundMessge(msg);
			} else {
				result = handleInboundMessge(msg);
			}
		} catch (SOAPException e) {
			// TODO log
			result = generateSOAPErrMessage(msg, "Your reason");
		}
		return result;
	}

	private boolean handleInboundMessge(SOAPMessage msg) throws SOAPException {
		System.out.println("HelloHandler: Inbound message");
		return true;
	}

	private boolean handleOutboundMessge(SOAPMessage msg) throws SOAPException {
		System.out.println("HelloHandler: Outbound message");
		return true;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		System.out.println("HelloHandler: handleFault()");
		return true;
	}

	@Override
	public void close(MessageContext context) {
		System.out.println("HelloHandler: close()");
	}

	@Override
	public Set<QName> getHeaders() {
		System.out.println("HelloHandler: getHeaders()");
		return null;
	}

	
	private boolean generateSOAPErrMessage(SOAPMessage msg, String reason) {
		try {
			SOAPBody soapBody = msg.getSOAPPart().getEnvelope().getBody();
			SOAPFault soapFault = soapBody.addFault();
			soapFault.setFaultString(reason);
			soapFault.setFaultCode(reason);
			throw new SOAPFaultException(soapFault);
		} catch (SOAPException e) {
			// nothing
		}
		return false;
	}

}
