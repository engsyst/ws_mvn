package shop.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

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
