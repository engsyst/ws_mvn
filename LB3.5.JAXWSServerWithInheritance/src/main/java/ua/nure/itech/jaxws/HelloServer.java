package ua.nure.itech.jaxws;

import java.util.Scanner;

import javax.xml.ws.Endpoint;

public class HelloServer{
	final static String address = "http://localhost:9090/hello";
	final static Object implementor = new ua.nure.itech.jaxws.service.Hello();
	final static String addressB = "http://localhost:9090/b";
	final static Object implementorB = new ua.nure.itech.jaxws.service.B();

    public static void main(String args[]) throws Exception { 
        Endpoint endpoint = Endpoint.publish(address, implementor);
        System.out.println("Server ready at " + address + " ..."); 
        Endpoint endpointB = Endpoint.publish(addressB, implementorB);
        System.out.println("Server ready at " + addressB + " ..."); 
        System.out.println("\n1. Go to the < LB3WSClientJAXWSExampleWithInheritance > project.\n" + 
        		"2. Press right mouse button and select Show in > Terminal.\n" + 
        		"3. Type following:\n\n" + 
        		"  wsimport -clientjar service.jar http://localhost:9090/hello?wsdl -b bindings.xml\n\n" + 
        		"4. Press enter.\n" + 
        		"5. Add generated < service.jar > to the ClassPath: Project > Properties > Build Path > Libraries.\n" + 
        		"6. Extract < META-INF > folder from < service.jar > to the root of project.\n" + 
        		"7. Refresh project.\n" +
        		"7. Start project as Java Application."); 
        
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        sc.close();
        System.out.println("Server exiting");
        endpoint.stop();
        endpointB.stop();
    }
}
