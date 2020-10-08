package ua.nure.demo.parser;

import org.codehaus.stax2.XMLInputFactory2;
import org.codehaus.stax2.XMLStreamReader2;
import org.codehaus.stax2.validation.XMLValidationSchema;
import org.codehaus.stax2.validation.XMLValidationSchemaFactory;
import ua.nure.order.entity.order.Order;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import static javax.xml.stream.XMLInputFactory.newInstance;

public class Stax2Parser {


    public static void main(String[] args) throws Exception {
        System.out.println("--== StAX2 Parser ==--");
        Stax2Parser parser = new Stax2Parser();
        List<Order> orders = parser.parse(new FileInputStream("invalid_orders.xml"), "orders.xsd");
        System.out.println("====================================");
        System.out.println("Here is the orders: \n" + orders);
        System.out.println("====================================");
    }

    private List<Order> parse(InputStream in, String schemaFile) throws XMLStreamException {
        XMLInputFactory factory = newInstance();
		// XML parsers should not be vulnerable to XXE attacks
//		factory.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");    // not supported by StAX2
//		factory.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, ""); // not supported by StAX2

        XMLInputFactory2 xmlInputFactory = (XMLInputFactory2) factory;
        // Configure factory
        xmlInputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);
        // Validation do not supported by Sun's StAX implementation
 		xmlInputFactory.setProperty(XMLInputFactory.IS_VALIDATING, Boolean.TRUE);

		XMLStreamReader2 reader = (XMLStreamReader2) xmlInputFactory.createXMLStreamReader(in);

		// creating validator
		XMLValidationSchemaFactory sf = XMLValidationSchemaFactory.newInstance(XMLValidationSchema.SCHEMA_ID_W3C_SCHEMA);
		XMLValidationSchema vs = sf.createSchema(new File(schemaFile));
		// reader should validate xml
		reader.validateAgainst(vs);

		EventHandler handler = new EventHandler();

		// eventReader should read validated content
        XMLEventReader eventReader = xmlInputFactory.createXMLEventReader(reader);

        while (eventReader.hasNext()) {
            XMLEvent event;
            try {
                event = eventReader.nextEvent();
                // skip any empty content
                if (event.isCharacters() && event.asCharacters().isWhiteSpace()) {
                    continue;
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
                break;
            }

            switch (event.getEventType()) {
                case XMLStreamConstants.START_ELEMENT:
                    handler.startElement(event);
                    break;

                case XMLStreamConstants.CHARACTERS:
                    handler.characters(event);
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    handler.endElement(event);
                    break;
				default:
			}
        }
		return handler.getOrders();
	}

}
