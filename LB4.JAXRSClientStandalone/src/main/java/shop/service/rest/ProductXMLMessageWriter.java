package shop.service.rest;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.xml.XMLConstants;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

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

@Provider
@Produces(MediaType.APPLICATION_XML)
public class ProductXMLMessageWriter implements MessageBodyWriter<Product> {

	private static final String CONMSUMES_TYPE = "shop.entity.Product";

	JAXBContext ctx;

	public ProductXMLMessageWriter() throws JAXBException {
		ctx = JAXBContext.newInstance(ObjectFactory.class);
	}

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		String gtName = genericType.getTypeName();
		return CONMSUMES_TYPE.equals(gtName);
	}

	@Override
	public void writeTo(Product t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException, WebApplicationException {

		OutputStreamWriter writer = new OutputStreamWriter(entityStream);

		try {
			ctx = JAXBContext.newInstance(ObjectFactory.class);
			Marshaller m = ctx.createMarshaller();
			m.setSchema(
					SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(new File("product.xsd")));
			m.marshal(t, writer);
		} catch (JAXBException | SAXException e) {
			e.printStackTrace();
			throw new WebApplicationException(e);
		}
		writer.close();

	}

}
