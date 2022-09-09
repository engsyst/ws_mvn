package ua.nure.order.server.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ua.nure.order.server.client.ServiceProvider;

/**
 * Application Lifecycle Listener implementation class ContextListener
 *
 */
@WebListener
public class ContextListener implements ServletContextListener {

	public static final Logger log = LoggerFactory.getLogger(ContextListener.class);
	
    @Override
    public void contextInitialized(ServletContextEvent event)  { 
    	ServletContext ctx = event.getServletContext();
    	log.trace("Context initialized");
    	String url = ctx.getInitParameter("wsdl");
    	log.debug("Get Init Parameter: wsdl : {}",url);
		if ("".equals(url)) {
			ctx.setAttribute("BookService", ServiceProvider.getInstance(null));
		} else {
			ctx.setAttribute("BookService", ServiceProvider.getInstance(url));
		}
    }
	
}
