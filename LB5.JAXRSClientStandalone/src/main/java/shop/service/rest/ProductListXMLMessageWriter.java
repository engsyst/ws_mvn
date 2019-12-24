package shop.service.rest;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import shop.entity.ObjectFactory;
import shop.entity.Product;
import shop.entity.ProductList;

@Provider
@Produces(MediaType.APPLICATION_XML)
public class ProductListXMLMessageWriter implements MessageBodyWriter<List<Product>> {

	private static final String CONMSUMES_TYPE = "java.util.List<shop.entity.Product>";

	JAXBContext ctx;
	public ProductListXMLMessageWriter() throws JAXBException {
		ctx = JAXBContext.newInstance(ObjectFactory.class);
	}
	
	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		String gtName = genericType.getTypeName();
		if (CONMSUMES_TYPE.equals(gtName)) {
			return true;
		}
		return false;
	}

	@Override
	public void writeTo(List<Product> t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException, WebApplicationException {
		
		OutputStreamWriter writer = new OutputStreamWriter(entityStream);
		
		try {
			ProductList list = new ProductList(t);
			ctx = JAXBContext.newInstance(ObjectFactory.class);
			Marshaller m = ctx.createMarshaller();
			m.marshal(list, writer);
		} catch (JAXBException e) {
			e.printStackTrace(); // log here
			throw new WebApplicationException(e);
		} 
        writer.close();
	}

}
