package ua.nure.demo.parser;

import ua.nure.order.entity.book.Author;
import ua.nure.order.entity.book.Book;
import ua.nure.order.entity.book.Category;
import ua.nure.order.entity.order.Order;
import ua.nure.order.entity.order.OrderItem;
import ua.nure.order.entity.order.Orders;

import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class StaxParser {

    private List<Order> orders;
    static Map<QName, Class<?>> elements;

    static {
        elements = new HashMap<>();
        elements.put(Const.QNAME_ORDERS, Orders.class);
        elements.put(Const.QNAME_ORDER, Order.class);
        elements.put(Const.QNAME_ORDERITEM, OrderItem.class);
        elements.put(Const.QNAME_BOOK, Book.class);
        elements.put(Const.QNAME_AUTHOR, Author.class);
    }

    static Map<QName, AbstractMapper> mappers;

    static {
        mappers = new HashMap<>();
        mappers.put(Const.QNAME_ORDER, new OrderToOrdersMapper());
        mappers.put(Const.QNAME_ORDERITEM, new OrderItemToOrderMapper());
        mappers.put(Const.QNAME_ORDER_COUNT, new OrderItemFieldsMapper());
        mappers.put(Const.QNAME_BOOK, new OrderItemFieldsMapper());
        mappers.put(Const.QNAME_TITLE, new BookFieldsMapper());
        mappers.put(Const.QNAME_CATEGORY, new BookFieldsMapper());
        mappers.put(Const.QNAME_PRICE, new BookFieldsMapper());
        mappers.put(Const.QNAME_BOOK_COUNT, new BookFieldsMapper());
    }

    private static abstract class AbstractMapper {

        abstract void map(QName qName, Object to, Object value);
    }

    private static class OrderToOrdersMapper extends AbstractMapper {
        @Override
        void map(QName qName, Object to, Object value) {
            throw new UnsupportedOperationException();
        }
    }

    private static class OrderItemToOrderMapper extends AbstractMapper {

        @Override
        void map(QName qName, Object to, Object value) {
            Order order = (Order) to;
            order.getOrderItem().add((OrderItem) value);
        }
    }

    private static class CountToOrderItemMapper extends AbstractMapper {

        @Override
        void map(QName qName, Object to, Object value) {
            OrderItem item = (OrderItem) to;
            item.setCount(Integer.parseInt((String) value));
        }
    }

    private static class ProductToOrderItemMapper extends AbstractMapper {

        @Override
        void map(QName qName, Object to, Object value) {
            OrderItem item = (OrderItem) to;
            item.setBook((Book) value);
        }
    }

    private static class OrderItemFieldsMapper extends AbstractMapper {
        static Map<QName, AbstractMapper> orderItemMappers;

        static {
            orderItemMappers = new HashMap<>();
            orderItemMappers.put(Const.QNAME_ORDER_COUNT, new CountToOrderItemMapper());
            orderItemMappers.put(Const.QNAME_BOOK, new ProductToOrderItemMapper());
        }

        @Override
        void map(QName qName, Object to, Object value) {
            orderItemMappers.get(qName).map(qName, to, value);
        }
    }

    private static class TitleMapper extends AbstractMapper {

        @Override
        void map(QName qName, Object to, Object value) {
            Class<?> c = to.getClass();
            Method method;
            try {
                method = c.getMethod("setTitle", String.class);
                method.invoke(0, value);
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

    private static class AuthorToBookMapper extends AbstractMapper {

        @Override
        void map(QName qName, Object to, Object value) {
            Book book = (Book) to;
            book.getAuthor().add((Author) value);
        }
    }

    private static class CountToBookMapper extends AbstractMapper {

        @Override
        void map(QName qName, Object to, Object value) {
            Book book = (Book) to;
            book.setCount(Integer.parseInt((String) value));
        }
    }

    private static class CategoryToBookMapper extends AbstractMapper {

        @Override
        void map(QName qName, Object to, Object value) {
            Book book = (Book) to;
            book.setCategory(Category.fromValue((String) value));
        }
    }

    private static class PriceToBookMapper extends AbstractMapper {
        @Override
        void map(QName qName, Object to, Object value) {
            Book book = (Book) to;
            book.setPrice(Double.parseDouble((String) value));
        }
    }

    private static class BookFieldsMapper extends AbstractMapper {
        static Map<QName, AbstractMapper> bookMappers;

        static {
            bookMappers = new HashMap<>();
            bookMappers.put(Const.QNAME_TITLE, new TitleMapper());
            bookMappers.put(Const.QNAME_AUTHOR, new AuthorToBookMapper());
            bookMappers.put(Const.QNAME_PRICE, new PriceToBookMapper());
            bookMappers.put(Const.QNAME_CATEGORY, new CategoryToBookMapper());
            bookMappers.put(Const.QNAME_BOOK_COUNT, new CountToBookMapper());
        }

        @Override
        void map(QName qName, Object to, Object value) {
            bookMappers.get(qName).map(qName, to, value);
        }
    }

    private interface StartElementResolver {
        void resolve(QName qName, Stack<QName> qNames, Stack<Object> objects, XMLEventReader eventReader)
                throws XMLStreamException, InvocationTargetException, NoSuchMethodException
                , InstantiationException, IllegalAccessException;
    }

    private class StartElementResolverFactory {

        protected Map<QName, StartElementResolver> resolvers = new HashMap<>();

        public void register(QName qName, StartElementResolver resolver) {
            resolvers.put(qName, resolver);
        }

        public StartElementResolver getResolver(QName qName) {
            final StartElementResolver resolver = resolvers.get(qName);
            return resolver;
        }
    }

    private class ElementOnlyResolver extends ElementWithDataResolver {

        @Override
        public void resolve(QName qName, Stack<QName> qNames, Stack<Object> objects, XMLEventReader eventReader) throws XMLStreamException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
//            final QName qName = eventReader.peek().asStartElement().getName();
            super.resolve(qName, qNames, objects, eventReader);
            objects.push(getObject(qName));
        }
    }

    private class ElementWithDataResolver implements StartElementResolver {

        @Override
        public void resolve(QName qName, Stack<QName> qNames, Stack<Object> objects, XMLEventReader eventReader) throws XMLStreamException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
            qNames.push(qName);
        }
    }

    private interface CharactersResolver {
        void resolve(Stack<QName> qNames, Object object, XMLEvent event)
                throws XMLStreamException, InvocationTargetException, NoSuchMethodException
                , InstantiationException, IllegalAccessException;
    }
    private class CharactersResolverFactory {

        protected Map<QName, CharactersResolver> resolvers = new HashMap<>();

        public void register(QName qName, CharactersResolver resolver) {
            resolvers.put(qName, resolver);
        }

        public CharactersResolver getResolver(QName qName) {
            final CharactersResolver resolver = resolvers.get(qName);
            return resolver;
        }
    }

    private class OrderTitleDataResolver implements CharactersResolver {

        @Override
        public void resolve(Stack<QName> qNames, Object object, XMLEvent event) throws XMLStreamException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
            final QName qName = qNames.peek();
            final String data = event.asCharacters().getData();

        }
    }

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

    private int getIdValue(StartElement startElement) {
        final Attribute attr = startElement.getAttributeByName(Const.QNAME_ATTR_ID);
        if (attr == null) {
            return 0;
        }
        return Integer.parseInt(attr.getValue());
    }

    private void parse(FileInputStream fileInputStream) throws XMLStreamException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Stack<QName> qNames = new Stack<>();
        Stack<Object> objects = new Stack<>();
        StartElementResolverFactory seFactory = new StartElementResolverFactory();
        seFactory.register(Const.QNAME_ORDERS, new ElementOnlyResolver());
        seFactory.register(Const.QNAME_ORDER, new ElementOnlyResolver());
        seFactory.register(Const.QNAME_ORDERITEM, new ElementOnlyResolver());
        seFactory.register(Const.QNAME_ORDER_COUNT, new ElementWithDataResolver());
        seFactory.register(Const.QNAME_BOOK, new ElementOnlyResolver());
        seFactory.register(Const.QNAME_AUTHOR, new ElementOnlyResolver());
        seFactory.register(Const.QNAME_TITLE, new ElementWithDataResolver());
        seFactory.register(Const.QNAME_PRICE, new ElementWithDataResolver());
        seFactory.register(Const.QNAME_CATEGORY, new ElementWithDataResolver());
        seFactory.register(Const.QNAME_ORDER_COUNT, new ElementWithDataResolver());
        seFactory.register(Const.QNAME_BOOK_COUNT, new ElementWithDataResolver());

        CharactersResolverFactory charsFactory = new CharactersResolverFactory();
        charsFactory.register(Const.QNAME_ORDERS, new ElementOnlyResolver());
        charsFactory.register(Const.QNAME_ORDER, new ElementOnlyResolver());
        charsFactory.register(Const.QNAME_ORDERITEM, new ElementOnlyResolver());
        charsFactory.register(Const.QNAME_ORDER_COUNT, new ElementWithDataResolver());
        charsFactory.register(Const.QNAME_BOOK, new ElementOnlyResolver());
        charsFactory.register(Const.QNAME_AUTHOR, new ElementOnlyResolver());
        charsFactory.register(Const.QNAME_TITLE, new ElementWithDataResolver());
        charsFactory.register(Const.QNAME_PRICE, new ElementWithDataResolver());
        charsFactory.register(Const.QNAME_CATEGORY, new ElementWithDataResolver());
        charsFactory.register(Const.QNAME_ORDER_COUNT, new ElementWithDataResolver());
        charsFactory.register(Const.QNAME_BOOK_COUNT, new ElementWithDataResolver());

        XMLInputFactory factory = XMLInputFactory.newInstance();
        // Configure factory;
        factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);
        // Validation do not supported by Sun's StAX implementation
        // factory.setProperty(XMLInputFactory.IS_VALIDATING, Boolean.TRUE);
        XMLEventReader eventReader = factory.createXMLEventReader(fileInputStream);

        while (eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();

            switch (event.getEventType()) {

                case XMLStreamConstants.START_ELEMENT:
                    StartElement startElement = event.asStartElement();
                    QName qName = startElement.getName();
                    System.out.println("StartElement" + qName);
                    seFactory.getResolver(qName).resolve(qName, qNames, objects, eventReader);
                    break;

                case XMLStreamConstants.CHARACTERS:
                    Characters characters = event.asCharacters();
                    QName current = qNames.peek();
                    System.out.println("Characters: " + current);
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    EndElement endElement = event.asEndElement();
//                    qNames.pop();
//                    if (endElement.getName().getLocalPart().equalsIgnoreCase("student")) {
//                        System.out.println("End Element : student");
//                        System.out.println();
//                    }
                    break;
            }
        }
    }

    private Object getObject(QName qName) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Class<?> c = elements.get(qName);
        return c.getConstructor().newInstance();
    }
}
