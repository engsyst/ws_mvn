package shop.service.rest;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyWriter;
import jakarta.ws.rs.ext.Provider;
import jakarta.xml.bind.JAXBException;
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
