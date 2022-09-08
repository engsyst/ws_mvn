package ua.nure.demo.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ua.nure.order.entity.book.Author;
import ua.nure.order.entity.book.Book;
import ua.nure.order.entity.book.Category;
import ua.nure.order.entity.order.Order;
import ua.nure.order.entity.order.OrderItem;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SAXParser extends DefaultHandler {
	private static final boolean LOG_ENABLED = false;

	public static void log(Object o) {
		if (LOG_ENABLED) {
			System.out.println(o);
		}
	}

	private String current;
	private List<Order> orders;
	private OrderItem item;
	private Book book;
	private String countParent;
	private Author author;
	private String titleParent;
	private Order order;

	public List<Order> getOrders() {
		return orders;
	}

	@Override
	public void error(org.xml.sax.SAXParseException e) throws SAXException {
//		throw e; // throw exception if xml not valid
		System.err.println(e.getMessage());
	}

	public List<Order> parse(InputStream in, Schema schema) throws ParserConfigurationException, SAXException, IOException {

		/**
		 * SAXParserFactory factory = SAXParserFactory.newInstance(); 
		 * 
		 * // to be compliant, completely disable DOCTYPE declaration:
		 * factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true); 
		 * 
		 * // or completely disable external entities declarations:
		 * factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
		 * factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false); 
		 * 
		 * // or prohibit the use of all protocols by external entities:
		 * SAXParser parser = factory.newSAXParser(); // Noncompliant
		 * parser.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
		 * parser.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
		 * 
		 */
		// XML parsers should not be vulnerable to XXE attacks 
		// Fix by yourself
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setNamespaceAware(true);

		// make parser validating
//		factory.setFeature(Const.FEATURE__TURN_VALIDATION_ON, true);
//		factory.setFeature(Const.FEATURE__TURN_SCHEMA_VALIDATION_ON, true);

		factory.setSchema(schema);
		javax.xml.parsers.SAXParser parser = factory.newSAXParser();
		parser.parse(in, this);

		return orders;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) {

		current = localName;

		if (Const.TAG_ORDERS.equals(current)) {
			orders = new ArrayList<>();
		} else if (Const.TAG_ORDER.equals(current)) {
			order = new Order();
		} else if (Const.TAG_ORDERITEM.equals(current)) {
			countParent = Const.TAG_ORDERITEM;
			item = new OrderItem();
			if (attributes.getLength() > 0)
				item.setId(Integer.parseInt(attributes.getValue("id")));
		} else if (Const.TAG_BOOK.equals(current)) {
			book = new Book();
			if (attributes.getLength() > 0)
				book.setId(Integer.parseInt(attributes.getValue("id")));
			countParent = Const.TAG_BOOK;
			titleParent = Const.TAG_BOOK;
		} else if (Const.TAG_AUTHOR.equals(current)) {
			author = new Author();
			if (attributes.getLength() > 0)
				author.setId(Integer.parseInt(attributes.getValue("id")));
			titleParent = Const.TAG_AUTHOR;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) {
		String value = new String(ch, start, length);
		if (value.isBlank()) {
			return;
		}
		if (Const.TAG_COUNT.equals(current)) {
			if (Const.TAG_ORDERITEM.equals(countParent)) {
				item.setCount(Integer.parseInt(value));
			}
			if (Const.TAG_BOOK.equals(countParent)) {
				book.setCount(Integer.parseInt(value));
			}
		} else if (Const.TAG_TITLE.equals(current)) {
			if (Const.TAG_BOOK.equals(titleParent)) {
				book.setTitle(new String(ch, start, length));
			}
			if (Const.TAG_AUTHOR.equals(titleParent)) {
				author.setTitle(value);
			}
		} else if (Const.TAG_CATEGORY.equals(current)) {
			book.setCategory(Category.fromValue(value));
		} else if (Const.TAG_PRICE.equals(current)) {
			book.setPrice(Double.parseDouble(value));
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) {
		if (Const.TAG_ORDER.equals(localName)) {
			orders.add(order);
			log(current + " " + order);
		} else if (Const.TAG_ORDERITEM.equals(localName)) {
			order.getOrderItem().add(item);
			log(current + " " + item);
		} else if (Const.TAG_BOOK.equals(localName)) {
			item.setBook(book);
			log(current + " " + book);
		} else if (Const.TAG_AUTHOR.equals(localName)) {
			book.getAuthor().add(author);
		}
	}

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// Create against validation schema
		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = sf.newSchema(new File(Const.XSD_FILE));

		System.out.println("--== SAX Parser ==--");
		SAXParser parser = new SAXParser();
		parser.parse(new FileInputStream("orders.xml"), schema);
		List<Order> orders = parser.getOrders();
		System.out.println("====================================");
		System.out.println("Here is the orders: \n" + orders);
		System.out.println("====================================");
		parser.parse(new FileInputStream(Const.INVALID_XML_FILE), schema);
		orders = parser.getOrders();
		System.out.println("====================================");
		System.out.println("Here is the invalid orders: \n" + orders);
		System.out.println("====================================");
	}
}
