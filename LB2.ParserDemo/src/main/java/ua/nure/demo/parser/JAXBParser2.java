package ua.nure.demo.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.namespace.QName;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import ua.nure.order.entity.order.Order;
import ua.nure.order.entity.order.Orders;

/**
 * Controller for JAXB.
 * 
 * @author D.Kolesnikov, I. Mishcheriakov
 * 
 */
public class JAXBParser2 {

	public static void main(String[] args) throws JAXBException, FileNotFoundException {
		JAXBContext jc = JAXBContext.newInstance(Const.OBJECT_FACTORY);
		Unmarshaller um = jc.createUnmarshaller();
		Orders orders = (Orders) um.unmarshal(new FileInputStream(Const.XML_FILE));
		System.out.println(orders);
		Order order = orders.getOrder().get(0);
		Marshaller marshaller = jc.createMarshaller();
		
		// serialization the object of class that is not annotated @XmlRootElement
		JAXBElement<Order> jorder = new JAXBElement<>(
				new QName(Const.ORDERS_NAMESPACE_URI, "order"), 
				Order.class, order);
		
		marshaller.marshal(jorder, new File("oneOrder.xml"));
	}
}