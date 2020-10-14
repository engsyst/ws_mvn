package shop.service.rest;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

@Provider
public class JAXBContextProvider implements ContextResolver<JAXBContext> {
    private JAXBContext context = null;

    public JAXBContext getContext(Class<?> type) {
        if (context == null) {
            try {
                context = JAXBContext.newInstance (shop.entity.ObjectFactory.class, shop.order.ObjectFactory.class);
            } catch (JAXBException e) {
            	// log warning/error; 
            	// null will be returned which indicates that this
            	// provider won't/can't be used.
            	System.err.println(getClass().getName() + ": " + e.getMessage());
            }
        }
        return context;
    }
}