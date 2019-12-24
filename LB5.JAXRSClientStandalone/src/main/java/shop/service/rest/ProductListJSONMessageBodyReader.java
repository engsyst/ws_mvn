package shop.service.rest;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBException;

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
