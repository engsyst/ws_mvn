package ua.nure.it.demo;

public class TransformerDemo {

	public static void main(String[] args) throws Exception {
		XSLTransform.main(new String[] {"xml/xslt/books/books_xml2xml.xsl", "xml/xslt/books/books.xml", "xml/xslt/bookscatalog.xml"});
		XSLTransform.main(new String[] {"xml/xslt/cd/cd.xsl", "xml/xslt/cd/cd.xml", "xml/xslt/cd.html"});
		XSLTransform.main(new String[] {"xml/xslt/cd/cd2.xsl", "xml/xslt/cd/cd.xml", "xml/xslt/cd2.html"});
		XSLTransform.main(new String[] {"xml/xslt/content/content.xsl", "xml/xslt/content/content.xml", "xml/xslt/content.html"});
		XSLTransform.main(new String[] {"xml/xslt/nums/nums.xsl", "xml/xslt/nums/nums.xml", "xml/xslt/nums.html"});
	}
}
