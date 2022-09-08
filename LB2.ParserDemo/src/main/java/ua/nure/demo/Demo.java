package ua.nure.demo;

import ua.nure.demo.parser.*;

public class Demo {

	public static void main(String[] args) throws Exception {
		DOMParser.main(new String[] {});
		SAXParser.main(new String[] {});
		StaxParser.main(new String[] {});
		Stax2Parser.main(new String[] {});
		JAXBParser.main(new String[] {});
//		XSLTransform.main(new String[] {"books.xsl", "books.xml", "books.html"});

		XSLTransform.main(new String[] { "listOrder.xsl", "orders.xml", "orders.html" });
	}
}
