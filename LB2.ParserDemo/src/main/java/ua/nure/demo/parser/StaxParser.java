package ua.nure.demo.parser;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import ua.nure.order.entity.book.Author;
import ua.nure.order.entity.book.Book;
import ua.nure.order.entity.book.Category;
import ua.nure.order.entity.order.Order;
import ua.nure.order.entity.order.OrderItem;

public class StaxParser {


	private String current;
	private String countParent;
	private String titleParent;
	private Author author;
	private Book book;
	private OrderItem item;
	private Order order;
	private List<Order> orders;

    public static void main(String[] args) throws Exception {
        System.out.println("--== SAX Parser ==--");
        StaxParser parser = new StaxParser();
        parser.parse(new FileInputStream("orders.xml"));
        List<Order> orders = parser.getOrders();
        System.out.println("====================================");
        System.out.println("Here is the orders: \n" + orders);
        System.out.println("====================================");
    }

    public List<Order> getOrders() {
        return orders;
    }

    private void parse(InputStream in) throws XMLStreamException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        // Configure factory;
        factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);
        // Validation do not supported by Sun's StAX implementation
        // factory.setProperty(XMLInputFactory.IS_VALIDATING, Boolean.TRUE);
        XMLEventReader eventReader = factory.createXMLEventReader(in);

        while (eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();

			// skip any empty content
			if (event.isCharacters() && event.asCharacters().isWhiteSpace())
				continue;

            switch (event.getEventType()) {

                case XMLStreamConstants.START_ELEMENT:
                    StartElement startElement = event.asStartElement();
                    current = startElement.getName().getLocalPart();
                    System.out.println("StartElement: " + startElement.getName());
                    if (current.equals(Const.TAG_ORDERS)) {
                    	orders = new ArrayList<>();
                    }
                    if (current.equals(Const.TAG_ORDER)) {
                    	order = new Order();
                    }
                    if (current.equals(Const.TAG_ORDERITEM)) {
                    	item = new OrderItem();
                    	Attribute attr = startElement.getAttributeByName(new QName(Const.ATTR_ID));
                    	if (attr != null) {
                    		item.setId(Integer.parseInt(attr.getValue()));
                    	}
                    	countParent = Const.TAG_ORDERITEM;
                    }
                    if (current.equals(Const.TAG_BOOK)) {
                    	book = new Book();
                    	Attribute attr = startElement.getAttributeByName(new QName(Const.ATTR_ID));
                    	if (attr != null) {
                    		book.setId(Integer.parseInt(attr.getValue()));
                    	}
                    	countParent = Const.TAG_BOOK;
                    	titleParent = Const.TAG_BOOK;
                    }
                    if (current.equals(Const.TAG_AUTHOR)) {
                    	author = new Author();
                    	Attribute attr = startElement.getAttributeByName(new QName(Const.ATTR_ID));
                    	if (attr != null) {
                    		author.setId(Integer.parseInt(attr.getValue()));
                    	}
                    	titleParent = Const.TAG_AUTHOR;
                    }
                    break;

                case XMLStreamConstants.CHARACTERS:
                    Characters characters = event.asCharacters();
                    System.out.println("Characters: " + current);
                    if (Const.TAG_COUNT.equals(current)) {
                    	if (Const.TAG_ORDERITEM.equals(countParent)) {
                    		item.setCount(Integer.parseInt(characters.getData()));
                    	}
                    	if (Const.TAG_BOOK.equals(countParent)) {
                    		book.setCount(Integer.parseInt(characters.getData()));
                    	}
					} else if (Const.TAG_TITLE.equals(current)) {
						if (Const.TAG_AUTHOR.equals(titleParent)) {
							author.setTitle(characters.getData());
						}
						if (Const.TAG_BOOK.equals(titleParent)) {
							book.setTitle(characters.getData());
						}
					} else if (Const.TAG_CATEGORY.equals(current)) {
						book.setCategory(Category.fromValue(characters.getData()));
					} else if (Const.TAG_PRICE.equals(current)) {
						book.setPrice(Double.parseDouble(characters.getData()));
					}
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    EndElement endElement = event.asEndElement();
                    String localName = endElement.getName().getLocalPart();
            		if (Const.TAG_ORDER.equals(localName)) {
            			orders.add(order);
            		} else if (Const.TAG_ORDERITEM.equals(localName)) {
            			order.getOrderItem().add(item);
            		} else if (Const.TAG_BOOK.equals(localName)) {
            			item.setBook(book);
            		} else if (Const.TAG_AUTHOR.equals(localName)) {
            			book.getAuthor().add(author);
            		}
                    break;
            }
        }
    }

}
