package ua.nure.it.demo;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class AgainstValidator {

	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			usage();
			return;
		}

		String xsd = args[0];
		String xml = args[1];

		// obtain schema factory
		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

		// obtain schema 
		Schema schema = null;
		if ("".equals(xsd)) {
			// setup validation against XSD pointed in XML
			schema = sf.newSchema();
		} else {
			// setup validation against external XSD
			schema = sf.newSchema(new File(xsd));
		}
		
		// creating validator
		Validator validator = schema.newValidator();
		Source source = new StreamSource(xml);

		// validation
		try {
			validator.validate(source);
			System.out.println(xml + " is valid.");
		} catch (SAXException ex) {
			System.out.println(xml + " is not valid because ");
			System.out.println(ex.getMessage());
		}
	}

	private static void usage() {
		System.out.println("Usage:\n"
				+ "java ua.nure.demo.AgainstValidator <xsd file> <xml file>");
	}

}
