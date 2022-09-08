package ua.nure.demo.parser;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XSLTransform {

	public static void main(String[] args) throws TransformerException {
		if (args.length != 3) {
			usage();
			return;
		}
		
		String xsl = args[0];
		String xml = args[1];
		String htm = args[2];
		
		TransformerFactory factory = TransformerFactory.newInstance();
		// to be compliant, prohibit the use of all protocols by external entities:
		factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
		factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");

		Transformer t = factory.newTransformer(new StreamSource(new File(xsl)));
		t.transform(new StreamSource(new File(xml)), new StreamResult(new File(htm)));
		System.out.println("Input xml: " + xml);
		System.out.println("Input xsl: " + xsl);
		System.out.println("Output   : " + htm);
	}

	private static void usage() {
		System.out.println("Usage: <xsl file> <xml file> <html file>");
		
	}

}
