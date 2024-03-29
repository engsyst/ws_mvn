package ua.nure.itech.jaxws.service.handler;

import java.io.StringWriter;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.soap.SOAPConstants;
import jakarta.xml.soap.SOAPEnvelope;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPFactory;
import jakarta.xml.soap.SOAPFault;
import jakarta.xml.soap.SOAPHeader;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;
import jakarta.xml.ws.soap.SOAPFaultException;
import ua.nure.itech.jaxws.service.handled.ObjectFactory;
import ua.nure.itech.jaxws.service.handled.SecurityHeader;

public class SecurityHandler implements SOAPHandler<SOAPMessageContext> {
	private final Logger log = LoggerFactory.getLogger(SecurityHandler.class);
	private static final String SERVICE_TOKEN = "I_am_server";
	JAXBContext jaxb;
	ObjectFactory factory;

	public SecurityHandler() throws JAXBException {
		factory = new ObjectFactory();
		jaxb = JAXBContext.newInstance("ua.nure.itech.jaxws.service.handled");
	}

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		boolean outbound = (boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		boolean result = true;
		if (outbound) {
			log.info("Outbound message");
			result = createSecurityHeader(context.getMessage());
		} else {
			log.info("Inbound message");
			result = checkSecurityHeader(context.getMessage());
		}
		return result;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		log.error("Service handleFault()");
		return true;
	}

	@Override
	public void close(MessageContext context) {
		log.debug("Service close()");
	}

	@Override
	public Set<QName> getHeaders() {
		return Set.of();
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
			log.info("Service token: {}", toString(header));
		} catch (SOAPException | JAXBException e) {
			String msg = e.getCause() != null ? e.getMessage() + "\nCause: " + e.getCause().getMessage()
					: e.getMessage();
			log.error(msg);
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
			log.error(faultString);
			throw new SOAPFaultException(createFault(message, faultString));
		}
	}

	private boolean isValidToken(String token) {
		if (token == null || token.isEmpty()) {
			log.error("Client token is incorrect: {}", token);
			return false;
		}
		log.info("Client token: {}", token);
		return true;
	}

	// How to replace/create your own Fault which depends from a protocol
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
			log.error(e.getMessage());
		}
		return stringResult.toString().replaceFirst("<\\?xml.+\\?>", "");
	}

}
