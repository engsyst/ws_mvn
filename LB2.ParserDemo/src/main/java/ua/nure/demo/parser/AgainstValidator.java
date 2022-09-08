package ua.nure.demo.parser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class AgainstValidator {

	private Validator validator;

	public AgainstValidator(String xsd) throws SAXException {
		// obtain schema factory
		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = sf.newSchema(new File(xsd));
		validator = schema.newValidator();
	}
	
	public AgainstValidator(Schema schema) {
		validator = schema.newValidator();
	}

	public void validate(String xmlFileName) throws SAXException, IOException {
		Source source = new StreamSource(xmlFileName);
		validator.validate(source);
	}
	
	public void validate(InputStream in) throws SAXException, IOException {
		Source source = new StreamSource(in);
		validator.validate(source);
	}

}
