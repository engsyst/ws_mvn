package shop.service.rest;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.xml.namespace.QName;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyWriter;
import jakarta.ws.rs.ext.Provider;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
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
