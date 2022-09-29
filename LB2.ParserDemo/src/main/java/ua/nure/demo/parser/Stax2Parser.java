package ua.nure.demo.parser;

import org.codehaus.stax2.XMLInputFactory2;
import org.codehaus.stax2.XMLStreamReader2;
import org.codehaus.stax2.validation.ValidationProblemHandler;
import org.codehaus.stax2.validation.XMLValidationException;
import org.codehaus.stax2.validation.XMLValidationProblem;
import org.codehaus.stax2.validation.XMLValidationSchema;
import org.codehaus.stax2.validation.XMLValidationSchemaFactory;
import ua.nure.order.entity.order.Order;

import javax.xml.stream.Location;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLReporter;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import static javax.xml.stream.XMLInputFactory.newInstance;

public class Stax2Parser {

    public static interface EventProcessor {
    	void process(XMLEvent event);
    }
    
	private EventHandler handler = new EventHandler();
    
    private Map<Integer, EventProcessor> processors = Map.of(
    		XMLStreamConstants.START_ELEMENT, handler::startElement, 
    		XMLStreamConstants.END_ELEMENT, handler::endElement, 
    		XMLStreamConstants.CHARACTERS, handler::characters);


    public static void main(String[] args) throws Exception {
        System.out.println("--== StAX2 Parser ==--");
        Stax2Parser parser = new Stax2Parser();
        List<Order> orders = parser.parse(new FileInputStream(Const.XML_FILE), Const.XSD_FILE);
        System.out.println("====================================");
        System.out.println("Here is the orders: \n" + orders);
        System.out.println("====================================");
        orders = parser.parse(new FileInputStream(Const.INVALID_XML_FILE), Const.XSD_FILE);
        System.out.println("====================================");
        System.out.println("Here is the invalid orders: \n" + orders);
        System.out.println("====================================");
    }

    private List<Order> parse(InputStream in, String schemaFile) throws XMLStreamException {
//        XMLInputFactory factory = newInstance();
		// XML parsers should not be vulnerable to XXE attacks
        // to be compliant, completely disable DOCTYPE declaration:
//		factory.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");    // not supported by StAX2
//		factory.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, ""); // not supported by StAX2

        XMLInputFactory xmlInputFactory = XMLInputFactory2.newInstance();
        // Configure factory
        xmlInputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);
        // Validation do not supported by Sun's StAX implementation
 		xmlInputFactory.setProperty(XMLInputFactory.IS_VALIDATING, Boolean.TRUE);
		xmlInputFactory.setProperty(XMLInputFactory.SUPPORT_DTD, false);

		XMLStreamReader2 reader = (XMLStreamReader2) xmlInputFactory.createXMLStreamReader(in);

		// setup custom validation handler if needed
		reader.setValidationProblemHandler(new ValidationProblemHandler() {
	
			@Override
			public void reportProblem(XMLValidationProblem problem) throws XMLValidationException {
				System.err.println("StAX validation error: " + problem.getMessage() 
						+ ", " + problem.getType() 
						+ ", " + problem.getLocation());
//				throw XMLValidationException.createException(problem);
			}
		});
		
		// creating validator
		XMLValidationSchemaFactory sf = XMLValidationSchemaFactory.newInstance(XMLValidationSchema.SCHEMA_ID_W3C_SCHEMA);
		XMLValidationSchema vs = sf.createSchema(new File(schemaFile));
		// reader should validate xml
		reader.validateAgainst(vs);

		// eventReader should read validated content
        XMLEventReader eventReader = xmlInputFactory.createXMLEventReader(reader);

		while (eventReader.hasNext()) {
			XMLEvent event;
			event = eventReader.nextEvent();
			// skip any empty content
			if (event.isCharacters() && event.asCharacters().isWhiteSpace()) {
				continue;
			}

			EventProcessor eventProcessor = processors.get(event.getEventType());
			if (eventProcessor != null) {
				eventProcessor.process(event);
			}
		}
		return handler.getOrders();
	}

}
