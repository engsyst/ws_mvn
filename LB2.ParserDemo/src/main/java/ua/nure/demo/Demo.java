package ua.nure.demo;

import ua.nure.demo.parser.DOMParser;
import ua.nure.demo.parser.JAXBParser;
import ua.nure.demo.parser.SAXParser;
import ua.nure.demo.parser.XSLTransform;

public class Demo {

	public static void main(String[] args) throws Exception {
		DOMParser.main(new String[] {});
		SAXParser.main(new String[] {});
		JAXBParser.main(new String[] {});
//		XSLTransform.main(new String[] {"orders.xsl", "orders.xml", "orders.html"});
	}
}
