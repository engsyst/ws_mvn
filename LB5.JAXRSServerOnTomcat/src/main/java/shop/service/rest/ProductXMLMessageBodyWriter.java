package shop.service.rest;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import shop.entity.ObjectFactory;
import shop.entity.Product;

@Provider
@Produces(MediaType.APPLICATION_XML)
public class ProductXMLMessageBodyWriter<T> implements MessageBodyWriter<T> {

	private static final Class<?> CONMSUMES_TYPE = shop.entity.Product.class;
	private static final QName qName = new QName("shop.entity", "product");

	JAXBContext ctx;
	public ProductXMLMessageBodyWriter() throws JAXBException {
		ctx = JAXBContext.newInstance(ObjectFactory.class);
	}
	
	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		Class<?> t = type;
		while (t.getSuperclass() != null) {
			if (CONMSUMES_TYPE.equals(t)) {
				return true;
			}
			t = t.getSuperclass();
		}
		return false;
	}

	@Override
	public void writeTo(T t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException, WebApplicationException {
		
		OutputStreamWriter writer = new OutputStreamWriter(entityStream, "UTF-8");
		
		try {
			Product p = (Product) t;
			Marshaller m = ctx.createMarshaller();
		    JAXBElement<Product> root = new JAXBElement<>(qName, Product.class, p);
			m.marshal(root, writer);
		} catch (JAXBException e) {
			e.printStackTrace(); // log here
			throw new WebApplicationException(e);
		} 
        writer.close();
	}

}
