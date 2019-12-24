package ua.nure.demo.parser;

public interface Const {

	String TAG_ORDERS = "orders";
	String TAG_ORDER = "order";
	String TAG_ORDERITEM = "orderItem";
	String ATTR_ID = "id";
	String TAG_COUNT = "count";
	String TAG_BOOK ="book";
	String TAG_TITLE = "title";
	String TAG_AUTHOR = "author";
	String TAG_PRICE = "price";
	String TAG_CATEGORY = "category";
	String TAG_ISBN = "isbn";
	
	String XML_FILE = "orders.xml";
	String INVALID_XML_FILE = "invalid_orders.xml";
	String XSD_FILE = "orders.xsd";
	Class<?> OBJECT_FACTORY = ua.nure.order.entity.order.ObjectFactory.class;
	
	String ORDERS_NAMESPACE_URI = "http://order.nure.ua/entity/order/";
	String SCHEMA_LOCATION__ATTR_NAME = "schemaLocation";
	String SCHEMA_LOCATION__ATTR_FQN = "xsi:schemaLocation";
	String XSI_SPACE__PREFIX = "xsi";
	String SCHEMA_LOCATION__URI = "http://order.nure.ua/entity/order/ orders.xsd";

	// validation features
	public static final String FEATURE__TURN_VALIDATION_ON = 
			"http://xml.org/sax/features/validation";
	public static final String FEATURE__TURN_SCHEMA_VALIDATION_ON = 
			"http://apache.org/xml/features/validation/schema";

}
