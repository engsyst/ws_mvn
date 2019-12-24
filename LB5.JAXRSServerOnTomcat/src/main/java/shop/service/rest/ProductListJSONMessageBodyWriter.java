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
import javax.xml.bind.JAXBException;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.entity.Product;
import shop.entity.ProductList;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class ProductListJSONMessageBodyWriter implements MessageBodyWriter<List<Product>> {

	ObjectMapper mapper;

	public ProductListJSONMessageBodyWriter() throws JAXBException {
		mapper = new ObjectMapper();
	}

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return true;
	}

	@Override
	public void writeTo(List<Product> t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException, WebApplicationException {
		
		
		mapper.writeValue(entityStream, new ProductList(t));
	}

}
