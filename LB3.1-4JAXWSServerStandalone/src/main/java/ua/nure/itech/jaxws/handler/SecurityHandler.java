package ua.nure.itech.jaxws.handler;

import java.io.StringWriter;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;

import org.w3c.dom.Node;

import ua.nure.itech.jaxws.service.handled.ObjectFactory;
import ua.nure.itech.jaxws.service.handled.SecurityHeader;

public class SecurityHandler implements SOAPHandler<SOAPMessageContext> {
	private static final String SERVICE_TOKEN = "I_am_server";
	JAXBContext jaxb;
	ObjectFactory factory;

	public SecurityHandler() throws JAXBException {
		factory = new ObjectFactory();
		jaxb = JAXBContext.newInstance("ua.nure.itech.jaxws.service.handled");
	}

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		Boolean outbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		boolean result = true;
		if (outbound) {
			System.out.println("\nOutbound message");
			result = createSecurityHeader(context.getMessage());
		} else {
			System.out.println("\nInbound message");
			result = checkSecurityHeader(context.getMessage());
		}
		return result;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		System.out.println("Service handleFault()");
		return true;
	}

	@Override
	public void close(MessageContext context) {
		System.out.println("Service close()");
	}

	@Override
	public Set<QName> getHeaders() {
		return null;
	}

	private boolean createSecurityHeader(SOAPMessage message) {
		try {
			SOAPHeader header = message.getSOAPHeader();
			header.extractAllHeaderElements();
			Marshaller marshaller = jaxb.createMarshaller();
			marshaller.setProperty("jaxb.fragment", true);
			SecurityHeader sh = new SecurityHeader(SERVICE_TOKEN);
			marshaller.marshal(factory.createServerToken(sh), header);
			message.saveChanges();
			System.out.println("Service token\n" + toString(header));
		} catch (SOAPException | JAXBException e) {
			String msg = e.getMessage() + e.getCause() != null ? "\nCause: " + e.getMessage() + e.getCause().getMessage() : "" ; 
			System.err.println(msg);
			return false;
		}
		return true;
	}

	private boolean checkSecurityHeader(SOAPMessage message) {
		try {
			SOAPHeader header = message.getSOAPPart().getEnvelope().getHeader();
			Unmarshaller unmarshaller = jaxb.createUnmarshaller();
			JAXBElement<SecurityHeader> sh = unmarshaller.unmarshal(header.getFirstChild(), SecurityHeader.class);
			return isValidToken(sh.getValue().getToken());
		} catch (Exception e) {
			String faultString = "Client token not found";
			System.err.println(faultString);
			throw new SOAPFaultException(createFault(message, faultString));
		}
	}
	
	private boolean isValidToken(String token) {
		if (token == null || token.isEmpty()) {
			System.err.println("Client token is incorrect: " + token);
			return false;
		}
		System.out.println("Client token: " + token);
		return true;
	}
	
	// Как подменить/создать свой Fault в зависимости от протокола
	private SOAPFault createFault(SOAPMessage message, String faultString) {
		SOAPFault fault = null;
		try {
			SOAPEnvelope env = message.getSOAPPart().getEnvelope();
			QName faultCode = null;
			String soapProtocol = SOAPConstants.SOAP_1_1_PROTOCOL;
			String code = "Client";
			String prefix = env.lookupPrefix(SOAPConstants.URI_NS_SOAP_1_1_ENVELOPE);
			if (prefix == null) {
				soapProtocol = SOAPConstants.SOAP_1_2_PROTOCOL;
				code = "Sender";
				prefix = env.lookupPrefix(SOAPConstants.URI_NS_SOAP_1_2_ENVELOPE);
			}
			faultCode = env.createQName(code, prefix);
			fault = SOAPFactory.newInstance(soapProtocol).createFault(faultString, faultCode);
		} catch (SOAPException e) {
			// e.printStackTrace();
		}
		return fault;
	}
	
	private String toString(Node header) {
		DOMSource source = new DOMSource(header);
		StringWriter stringResult = new StringWriter();
		try {
			TransformerFactory.newInstance().newTransformer().transform(source, new StreamResult(stringResult));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} 
		return stringResult.toString().replaceFirst("<\\?xml.+\\?>", "");
	}
	
}
