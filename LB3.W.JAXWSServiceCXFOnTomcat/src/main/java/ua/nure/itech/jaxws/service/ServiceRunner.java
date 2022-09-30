package ua.nure.itech.jaxws.service;

import javax.xml.ws.Endpoint;
import javax.xml.ws.spi.http.HttpContext;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Scanner;
import java.util.Set;

public class ServiceRunner {

    private static final String ADDRESS_HELLO = "http://localhost:9090/hello";
    private static final String ADDRESS_HELLO_ANNOTATED = "http://localhost:9090/helloA";

    static class MockHttpContext extends HttpContext {
        final String path;

        MockHttpContext(String path) {
            this.path = path;
        }

        @Override
        public String getPath() {
            return path;
        }

        @Override
        public Object getAttribute(String name) {
            return null;
        }

        @Override
        public Set<String> getAttributeNames() {
            return null;
        }
    }

    public static void main(String[] args) throws GeneralSecurityException, IOException {
        Endpoint epHello = Endpoint.create(new Hello());
        epHello.publish(ADDRESS_HELLO);
        Endpoint epHelloAnnotated = Endpoint.create(new HelloAnnotated());
        epHelloAnnotated.publish(ADDRESS_HELLO_ANNOTATED);
        System.out.println(ADDRESS_HELLO + "?wsdl");
        System.out.println(ADDRESS_HELLO_ANNOTATED + "?wsdl");
        new Scanner(System.in).nextLine();
        epHello.stop();
        epHelloAnnotated.stop();
        System.out.println("Stopped");
    }

}
