package ua.nure.demo.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import ua.nure.order.entity.order.ObjectFactory;
import ua.nure.order.entity.order.Orders;

/**
 * Controller for JAXB.
 * 
 * @author D.Kolesnikov, I. Mishcheriakov
 * 
 */
public class JAXBParser {

	/**
	 * XML --> Java (with validation against XSD). Throws no exception if XML is
	 * well-formed, but NOT valid (just prints validation warning message).
	 * 
	 * @param xmlFileName
	 *            Input XML file name (not null, required).
	 * @param xsdFileName
	 *            External XSD for validation. If equals to "", validation will
	 *            be against XSD indicated in XML document. If equals to null,
	 *            there is no validation during Orders object loading.
	 * @param ObjectFactoryclass1 
	 * @return Orders object, container with info from the input XML document.
	 */
	public static Orders loadOrders(final String xmlFileName,
			final String xsdFileName, Class<?> objectFactory) throws JAXBException, SAXException {
		JAXBContext jc = JAXBContext.newInstance(objectFactory);
		Unmarshaller unmarshaller = jc.createUnmarshaller();

		// obtain schema
		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

		if (xsdFileName != null) { // <-- setup validation on
			Schema schema = null;			
			if ("".equals(xsdFileName)) {
				// setup validation against XSD pointed in XML
				schema = sf.newSchema();
			} else {
				// setup validation against external XSD
				schema = sf.newSchema(new File(xsdFileName));
			}
			
			unmarshaller.setSchema(schema); // <-- set XML schema for validation

			// set up handler
			unmarshaller.setEventHandler(new ValidationEventHandler() {
				// this method will be invoked if XML is NOT valid
				@Override
				public boolean handleEvent(ValidationEvent event) {
					// at this point we have NOT valid XML document
					// just print message
					System.err.println("====================================");
					System.err.println(xmlFileName + " is NOT valid against "
							+ xsdFileName + ":\n" + event.getMessage());
					System.err.println("====================================");

					// if we place 'return false;' unmarshal method throws
					// exception
					// 'return true;' does not imply any exceptions
					return true;
				}
			});
		}

		// do unmarshal
		Orders orders = (Orders) unmarshaller.unmarshal(new File(xmlFileName));
		return orders; // <-- filled container
	}

	/**
	 * Java --> XML (with validation against XSD). Throws exception if XML is
	 * NOT valid.
	 * 
	 * @param xmlFileName
	 *            Output XML file name (not null, required).
	 * @param xsdFileName
	 *            XSD for validation. If equals to null, there is NO
	 *            validation.
	 * @throws JAXBException
	 *             If JAXB object is not valid against XSD document.
	 */
	public static void saveOrders(Orders orders, final String xmlFileName,
			final String xsdFileName, Class<?> objectFactory) throws JAXBException, SAXException {
		JAXBContext jc = JAXBContext.newInstance(objectFactory);
		Marshaller marshaller = jc.createMarshaller();

		// obtain schema
		SchemaFactory sf = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

		// setup validation against XSD
		if (xsdFileName != null) {
			Schema schema = sf.newSchema(new File(xsdFileName));

			marshaller.setSchema(schema);
			marshaller.setEventHandler(new ValidationEventHandler() {
				@Override
				public boolean handleEvent(ValidationEvent event) {
					// at this point we have NOT valid XML document
					// just print message
					System.err.println("====================================");
					System.err.println(xmlFileName + " is NOT valid against "
							+ xsdFileName + ":\n" + event.getMessage());
					System.err.println("====================================");

					// if we place 'return false;' marshal method throws
					// exception
					// 'return true;' does not imply any exceptions
					return false;
				}
			});
		}
		
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, Const.SCHEMA_LOCATION__URI);
		marshaller.marshal(orders, new File(xmlFileName));
	}
	
	public static void main1(String[] args) throws JAXBException, FileNotFoundException {
		JAXBContext jc = JAXBContext.newInstance(Const.OBJECT_FACTORY);
		Unmarshaller um = jc.createUnmarshaller();
		Orders orders = (Orders) um.unmarshal(new FileInputStream(Const.XML_FILE));
		System.out.println(orders);
	}
	
	public static void main(String[] args) throws JAXBException, SAXException {
		System.out.println("--== JAXB Parser ==--");
		// load Orders object from NOT valid XML (success, just prints validation
		// warning)
		Orders orders = loadOrders(Const.XML_FILE, Const.XSD_FILE, Const.OBJECT_FACTORY);

		// we have Orders object at this point
		System.out.println("====================================");
		System.out.println("Here is the orders: \n" + orders);
		System.out.println("====================================");

		// try to save Orders object to XML file (failed, exception)
		try {
			saveOrders(orders, Const.XML_FILE + ".jaxb.xml", Const.XSD_FILE, Const.OBJECT_FACTORY);
		} catch (Exception ex) {
			System.err.println("====================================");
			System.err.println("Object tree not valid against XSD.");
			System.err.println(ex.getClass().getName());
			System.err.println("====================================");
		}


		// save Orders object to XML (success)
//		saveOrders(orders, Const.XML_FILE + ".jaxb.xml", Const.XSD_FILE, Const.OBJECT_FACTORY);
	}
}