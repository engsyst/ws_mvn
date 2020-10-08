package ua.nure.itech.jaxws;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Holder;
import javax.xml.ws.Response;

import ua.nure.itech.jaxws.service.Exchange;
import ua.nure.itech.jaxws.service.ExchangeException_Exception;
import ua.nure.itech.jaxws.service.ExchangeResponse;
import ua.nure.itech.jaxws.service.HelloMessenger;
import ua.nure.itech.jaxws.service.HelloService;

/**
 * To enable asynchronous calls of Web service you should pass <b>bindings.xml</b> to the wsimport utility.<br/>
 * See root of this project.<br/>
 * Example:
 * <pre>
 * #>wsimport -clientjar name_of_file.jar http://server:port/name_of_service?wsdl -b bindings.xml
 * </pre>
 *
 * Supplementary project is <b><u>LB3mvnWSServerJAXWSExampleWithInheritance</u></b>.<br/>
 * Web service artifact packed to the <b><u>service.jar</u></b> in the "resources" folder.
 *
 * @author engsyst
 *
 */
public class HelloAsyncClient {

	/**
	 * Passing URL to HelloService constructor needs to override wsdl only. For example, for debug support.
	 * You can change port in the URL to use TCP-Monitor.<br/>
	 * Change port in the wsdl 
	 * <pre>
	 * &lt;soap:address location="http://localhost:<b>9090</b>/hello"&gt;&lt;/soap:address&gt;
	 * </pre>
	 * to  TCP-Monitor port
	 * <pre>
	 * &lt;soap:address location="http://localhost:<b>19090</b>/hello"&gt;&lt;/soap:address&gt;
	 * </pre>
	 */
	final static String url = "file:///" + new File("").getAbsolutePath() + "/META-INF/wsdl/HelloService.wsdl";

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		// Create the service object
		// You can use the constructor without parameters see javadoc for url  
		HelloService helloSEI = new HelloService(new URL(url));
		
		// Get service implementation
		HelloMessenger helloMessengerImpl = helloSEI.getHelloPort();
		
		
		// call remote operation on the service 
		Exchange value = new Exchange();
		try {
			ExchangeResponse result = helloMessengerImpl.exchange(value, "USD");
			System.out.println(result.getValue() + result.getCurrency());
		} catch (ExchangeException_Exception e1) {
			e1.printStackTrace();
		}
		
		// call remote operation on the service asynchronous
		value.setValue(7);
		Response<ExchangeResponse> response = helloMessengerImpl.exchangeAsync(value, "USD");
		try {
			ExchangeResponse result = response.get();
			System.out.println(result.getValue() + result.getCurrency());
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		value.setValue(8);
		Future<?> future = helloMessengerImpl.exchangeAsync(value, "USD", new AsyncHandler<ExchangeResponse>() {
			
			@Override
			public void handleResponse(Response<ExchangeResponse> res) {
				ExchangeResponse result;
				try {
					result = res.get();
					System.out.println(result.getValue() + result.getCurrency());
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
		});
		
		// Wait for end of requests
		try {
			future.get();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
