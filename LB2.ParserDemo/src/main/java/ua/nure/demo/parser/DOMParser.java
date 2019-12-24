package ua.nure.demo.parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import ua.nure.order.entity.book.Author;
import ua.nure.order.entity.book.Book;
import ua.nure.order.entity.book.Category;
import ua.nure.order.entity.order.Order;
import ua.nure.order.entity.order.OrderItem;

public class DOMParser {
	private static boolean logEnabled = false;

	public static void log(Object o) {
		if (logEnabled) {
			System.out.println(o);
		}
	}

	private Order parseOrder(Node node) {
		Order order = new Order();
		NodeList nodes = node.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node item = nodes.item(i);
			log(item.getLocalName());
			if (Const.TAG_ORDERITEM.equals(item.getLocalName()))
				order.getOrderItem().add(parseOrderItem(item));
		}
		return order;
	}

	private OrderItem parseOrderItem(Node node) {
		OrderItem oItem = new OrderItem();
		if (node.hasAttributes()) {
			NamedNodeMap attrs = node.getAttributes();
			Node item = attrs.getNamedItem(Const.ATTR_ID);
			log(item.getLocalName() + " = " + item.getTextContent());
			oItem.setId(Integer.parseInt(item.getTextContent()));
		}
		NodeList nodes = node.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node item = nodes.item(i);
			log(item.getLocalName());
			if (Const.TAG_COUNT.equals(item.getLocalName())) {
				log(item.getLocalName() + " = " + item.getTextContent());
				oItem.setCount(Integer.parseInt(item.getTextContent()));
			} else if (Const.TAG_BOOK.equals(item.getLocalName())) {
				log(item.getLocalName() + " = " + item.getTextContent());
				oItem.setBook(parseBook(item));
			}
		}
		return oItem;
	}

	private Book parseBook(Node node) {
		Book book = new Book();
		NodeList nodes = node.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node item = nodes.item(i);
			log(item.getLocalName() + " NS = " + item.getPrefix() + " local = " + item.getLocalName());
			if (Const.TAG_COUNT.equals(item.getLocalName())) {
				log(item.getLocalName() + " = " + item.getTextContent());
				book.setCount(Integer.parseInt(item.getTextContent()));
			} else if (Const.TAG_TITLE.equals(item.getLocalName())) {
				log(item.getLocalName() + " = " + item.getTextContent());
				book.setTitle(item.getTextContent());
			} else if (Const.TAG_AUTHOR.equals(item.getLocalName())) {
				Author author = new Author();
				if (node.hasAttributes()) {
					NamedNodeMap attrs = node.getAttributes();
					Node attr = attrs.getNamedItem(Const.ATTR_ID);
					log(attr.getNodeName() + " = " + attr.getTextContent());
					author.setId(Integer.parseInt(attr.getTextContent()));
				}
				log(item.getLocalName() + " = " + item.getTextContent());
				author.setTitle(item.getTextContent());
				book.getAuthor().add(author);
			} else if (Const.TAG_CATEGORY.equals(item.getLocalName())) {
				log(item.getLocalName() + " = " + item.getTextContent());
				book.setCategory(Category.fromValue(item.getTextContent()));
			} else if (Const.TAG_ISBN.equals(item.getLocalName())) {
				log(item.getLocalName() + " = " + item.getTextContent());
				book.setTitle(item.getTextContent());
			} else if (Const.TAG_PRICE.equals(item.getLocalName())) {
				log(item.getLocalName() + " = " + item.getTextContent());
				book.setPrice(Double.parseDouble(item.getTextContent()));
			}
		}
		return book;
	}

	public List<Order> parse(InputStream in) throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		
		// make parser validating
		dbf.setFeature(Const.FEATURE__TURN_VALIDATION_ON, true);
		dbf.setFeature(Const.FEATURE__TURN_SCHEMA_VALIDATION_ON, true);

		DocumentBuilder db = dbf.newDocumentBuilder();
		db.setErrorHandler(new DefaultHandler() {
			@Override
			public void error(SAXParseException e) throws SAXException {
				throw e; // <-- throw exception if XML document is NOT valid
			}
		});

		Document root = db.parse(in);

		List<Order> orders = new ArrayList<>();

		Element e = root.getDocumentElement();
		NodeList xmlOrders = e.getElementsByTagNameNS(Const.ORDERS_NAMESPACE_URI, Const.TAG_ORDER);
		for (int i = 0; i < xmlOrders.getLength(); i++) {
			orders.add(parseOrder(xmlOrders.item(i)));
		}
		// String id = e.getAttribute(Const.ATTR_ID);
		return orders;

	}

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		System.out.println("--== DOM Parser ==--");
		DOMParser domParser = new DOMParser();
		InputStream in = new FileInputStream("orders.xml");
		List<Order> orders = domParser.parse(in);
		System.out.println("====================================");
		System.out.println("Here is the orders: \n" + orders);
		System.out.println("====================================");
	}
}
