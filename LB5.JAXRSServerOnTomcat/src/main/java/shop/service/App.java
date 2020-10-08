package shop.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.apache.log4j.Logger;
import shop.service.rest.JSONMessageBodyWriter;
import shop.service.rest.ProductListJSONMessageBodyWriter;
import shop.service.rest.ProductNotAvailableExceptionMapper;
import shop.service.rest.ProductXMLMessageBodyWriter;
import shop.service.rest.TextPlainMessageBodyWriter;

@ApplicationPath(Constants.APPLICATION_PATH)
public class App extends Application {

    @Override
    public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<>();
//		classes.add(ProductServiceRestImpl.class);
//		classes.add(TextPlainMessageBodyWriter.class);
//		classes.add(ProductXMLMessageBodyWriter.class);
//		classes.add(ProductListJSONMessageBodyWriter.class);
//		classes.add(JSONMessageBodyWriter.class);
//		classes.add(ProductNotAvailableExceptionMapper.class);
		return Collections.unmodifiableSet(classes);
    }
}
