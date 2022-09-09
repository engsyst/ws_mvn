package shop.service.rest;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyReader;
import jakarta.ws.rs.ext.Provider;
import jakarta.xml.bind.JAXBException;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.entity.Product;
import shop.entity.ProductList;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class ProductListJSONMessageBodyReader implements MessageBodyReader<List<Product>> {

	ObjectMapper mapper;
	public ProductListJSONMessageBodyReader() throws JAXBException {
		mapper = new ObjectMapper();
	}

	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
			return true;
	}

	@Override
	public List<Product> readFrom(Class<List<Product>> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {

		ProductList t = mapper.readValue (entityStream, ProductList.class);
		return t.getProduct();
	}
}
