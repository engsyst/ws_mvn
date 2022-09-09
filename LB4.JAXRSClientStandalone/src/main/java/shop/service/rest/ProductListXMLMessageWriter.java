package shop.service.rest;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyWriter;
import jakarta.ws.rs.ext.Provider;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import shop.entity.ObjectFactory;
import shop.entity.Product;
import shop.entity.ProductList;

@Provider
@Produces(MediaType.APPLICATION_XML)
public class ProductListXMLMessageWriter implements MessageBodyWriter<List<Product>> {

	private static final String CONSUMES_TYPE = "java.util.List<shop.entity.Product>";

	JAXBContext ctx;
	public ProductListXMLMessageWriter() throws JAXBException {
		ctx = JAXBContext.newInstance(ObjectFactory.class);
	}
	
	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		String gtName = genericType.getTypeName();
		if (CONSUMES_TYPE.equals(gtName)) {
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
