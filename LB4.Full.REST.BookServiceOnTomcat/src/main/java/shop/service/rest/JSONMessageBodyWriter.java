package shop.service.rest;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyWriter;
import jakarta.ws.rs.ext.Provider;
import jakarta.xml.bind.JAXBException;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JSONMessageBodyWriter<T> implements MessageBodyWriter<T> {

	ObjectMapper mapper;

	public JSONMessageBodyWriter() throws JAXBException {
		mapper = new ObjectMapper();
	}

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return true;
	}

	@Override
	public void writeTo(T t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException, WebApplicationException {

		OutputStreamWriter writer = new OutputStreamWriter(entityStream, "UTF-8");
		mapper.writeValue(entityStream, t);
		writer.close();
	}

}
