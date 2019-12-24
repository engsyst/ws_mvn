package shop.service.rest;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import shop.entity.ObjectFactory;
import shop.entity.Product;

@Provider
@Consumes(MediaType.APPLICATION_XML)
public class ProductXMLMessageReader implements MessageBodyReader<Product> {

	private final JAXBContext jaxb = JAXBContext.newInstance("shop.entity");
	private static final Class<Product> CONMSUMES_TYPE = shop.entity.Product.class;

	JAXBContext ctx;
	public ProductXMLMessageReader() throws JAXBException {
		ctx = JAXBContext.newInstance(ObjectFactory.class);
	}
	
	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		if (CONMSUMES_TYPE.equals(type))
			return true;
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Product readFrom(Class<Product> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {
		Product product = null;
		JAXBElement<Product> res;
		try {
			Unmarshaller um = jaxb.createUnmarshaller();
			res = (JAXBElement<Product>) um.unmarshal(entityStream);
			product = res.getValue();
		} catch (JAXBException e) {
			throw new IOException(e);
		}
		
		return product;
	}

}
