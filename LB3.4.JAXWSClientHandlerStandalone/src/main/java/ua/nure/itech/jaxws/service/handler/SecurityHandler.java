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
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPHeader;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;
import ua.nure.itech.jaxws.service.Util;
import ua.nure.itech.jaxws.service.handled.ObjectFactory;
import ua.nure.itech.jaxws.service.handled.SecurityHeader;

public class SecurityHandler implements SOAPHandler<SOAPMessageContext> {
	private final Logger log = LoggerFactory.getLogger(SecurityHandler.class);
	private static final String CLIENT_TOKEN = "123";
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
//			System.out.println("\nOutbound message");
			result = createSecurityHeader(context.getMessage());
		} else {
			log.info("Inbound message");
//			System.out.println("\nInbound message");
			result = checkSecurityHeader(context.getMessage());
		}
		return result;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		log.info("Client handleFault()");
//		System.out.println("Client handleFault()");
		return true;
	}

	@Override
	public void close(MessageContext context) {
		log.debug("Client close()");
//		System.out.println("Client close()");
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
			SecurityHeader sh = Util.createSecurityHeader(CLIENT_TOKEN);
			marshaller.marshal (factory.createClientToken(sh), header);
			message.saveChanges();
			log.debug("Client's token was set into message header: {}", toString(header));
//			System.out.println("Client's token was set into message header\n" + toString(header));
		} catch (SOAPException | JAXBException e) {
			String msg = e.getMessage();
			msg += e.getCause() != null ? "\nCause: " + e.getCause().getMessage() : "";
			log.error(msg);
//			System.err.println(msg);
			return false;
		}
		return true;
	}

	private boolean checkSecurityHeader(SOAPMessage message) {
		try {
			SOAPHeader header = message.getSOAPHeader();
			Unmarshaller unmarshaller = jaxb.createUnmarshaller();
			JAXBElement<SecurityHeader> sh = unmarshaller.unmarshal(header.getFirstChild(), SecurityHeader.class);
			return isValidToken(sh.getValue().getToken());
		} catch (Exception e) {
			log.error("The message does not contain a service token");
//			System.err.println("The message does not contain a service token");
			String msg = e.getMessage() + (e.getCause() != null ? "\nCause: " + e.getCause().getMessage() : "");
			log.error(msg);
//			System.err.println(msg);
			return false;
		}
	}

	private boolean isValidToken(String token) {
		if (token == null || token.isEmpty()) {
			log.error("Service token is incorrect: {}", token);
//			System.err.println("Service token is incorrect: " + token);
			return false;
		}
		log.info("Service token: {}", token);
//		System.out.println("Service token: " + token);
		return true;
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
