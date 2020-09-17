package ua.nure.it.demo;

public class TransformerDemo {

	public static void main(String[] args) throws Exception {
		XSLTransform.main(new String[] {"xml/xslt/books.xsl", "xml/xslt/books.xml", "xml/xslt/books.html"});
	}
}
