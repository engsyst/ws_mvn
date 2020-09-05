package ua.nure.itech.jaxws.service;

import javax.xml.ws.Holder;

import ua.nure.itech.jaxws.service.handled.Hello;
import ua.nure.itech.jaxws.service.handled.HelloUser;
import ua.nure.itech.jaxws.service.handled.Hello_Service;
import ua.nure.itech.jaxws.service.handled.SecurityHeader;

/**
 * Example. How to use Web service client artifact in the .jar.<br/>
 * Supplementary project is <b>LB3mvnWSServerJAXWSExample</b>.<br/>
 * Before run this project start <b>LB3mvnWSServerJAXWSExample</b>
 * with <b>ua.nure.itech.jaxws.service.handled.HelloHandled</b> class
 * as implementor.<br/>
 * To generate portable artifact run:
 * <pre>#>wsimport -clientjar hello.jar http://localhost:9090/hello?wsdl</pre>
 *
 * @author engsyst
 */
public class HelloClient {

    public static void main(String[] args) {
        Hello port = new Hello_Service().getHelloPort();
        HelloUser user = new HelloUser();
        user.setUserName("User");
        SecurityHeader clientToken = new SecurityHeader();
        clientToken.setToken("Client");
        System.out.println(port.helloUser(user, clientToken, new Holder<SecurityHeader>()).getReturn());
    }

}
