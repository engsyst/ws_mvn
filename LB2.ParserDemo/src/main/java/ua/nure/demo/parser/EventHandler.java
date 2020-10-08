package ua.nure.demo.parser;

import ua.nure.order.entity.book.Author;
import ua.nure.order.entity.book.Book;
import ua.nure.order.entity.book.Category;
import ua.nure.order.entity.order.Order;
import ua.nure.order.entity.order.OrderItem;

import javax.xml.namespace.QName;
import javax.xml.stream.events.*;
import java.util.ArrayList;
import java.util.List;

public class EventHandler {
    private static final boolean LOG_ENABLED = false;

    private String current;
    private String countParent;
    private String titleParent;
    private Author author;
    private Book book;
    private OrderItem item;
    private Order order;
    private List<Order> orders;

    private static void log(Object o) {
        if (LOG_ENABLED) {
            System.out.println(o);
        }
    }

    public void endElement(XMLEvent event) {
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
    }

    public void characters(XMLEvent event) {
        Characters characters = event.asCharacters();
        log("Characters: " + current);
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
    }

    public void startElement(XMLEvent event) {
        StartElement startElement = event.asStartElement();
        current = startElement.getName().getLocalPart();
        log("StartElement: " + startElement.getName());
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
    }

    public List<Order> getOrders() {
        return orders;
    }
}
