# Web-Services examples

Shows general concepts of Web-service development in Java. \
All examples developed using bottom-up way. \
There is a specific client for each service. \
The implementations moved to Jakarta EE. \
The most internal projects contain Readme, see it.

## Projects

### XML, XSD, XSLT examples
- [LB1.XMLXSD](https://github.com/engsyst/ws_mvn/tree/master/LB1.XMLXSD)

### DOM, SAX, StAX, StAX2 (woodstox), JAXB
- [LB2.ParserDemo](https://github.com/engsyst/ws_mvn/tree/master/LB2.ParserDemo)

### A simple "hello" web-service and client
- [LB3.1.JAXWSServiceSimpleStandalone](https://github.com/engsyst/ws_mvn/tree/master/LB3.1.JAXWSServiceSimpleStandalone) 
- [LB3.1.JAXWSClientSimpleStandalone](https://github.com/engsyst/ws_mvn/tree/master/LB3.1.JAXWSClientSimpleStandalone)

### How to use annotations to control wsdl generation.
- [LB3.2.JAXWSServiceAnnotatedStandalone](https://github.com/engsyst/ws_mvn/tree/master/LB3.2.JAXWSServiceAnnotatedStandalone) 
- [LB3.2.JAXWSClientAnnotatedStandalone](https://github.com/engsyst/ws_mvn/tree/master/LB3.2.JAXWSClientAnnotatedStandalone)

### How to use SEI (Service Endpoint Interface).
- [LB3.3.JAXWSServiceSeiStandalone](https://github.com/engsyst/ws_mvn/tree/master/LB3.3.JAXWSServiceSeiStandalone) 
- [LB3.3.JAXWSClientSeiStandalone](https://github.com/engsyst/ws_mvn/tree/master/LB3.3.JAXWSClientSeiStandalone)

### How to add message handlers (filters) to Web-Service
- [LB3.4.JAXWSServiceHandlerStandalone](https://github.com/engsyst/ws_mvn/tree/master/LB3.4.JAXWSServiceHandlerStandalone)
- [LB3.4.JAXWSClientHandlerStandalone](https://github.com/engsyst/ws_mvn/tree/master/LB3.4.JAXWSClientHandlerStandalone)

### Full example of SOAP Web-Service
- [LB3.Full.SOAP.BookService](https://github.com/engsyst/ws_mvn/tree/master/LB3.Full.SOAP.BookService)

### Full example of REST Web-Service
- [LB4.Full.REST.BookServiceOnTomcat](https://github.com/engsyst/ws_mvn/tree/master/B4.Full.REST.BookServiceOnTomcat)
- [LB4.JAXRSClientStandalone](https://github.com/engsyst/ws_mvn/tree/master/LB4.JAXRSClientStandalone)

### Java Web Application (client) for `LB3.Full.SOAP.BookService`
- [LB5.BookServiceWebClientOnTomcat](https://github.com/engsyst/ws_mvn/tree/master/LB5.BookServiceWebClientOnTomcat)

### How to deploy Web-Service on Tomcat
- ~~LB3.W.JAXWSServiceAXIS2OnTomcat~~
- [LB3.W.JAXWSServiceCXFOnTomcat](https://github.com/engsyst/ws_mvn/tree/master/LB3.W.JAXWSServiceCXFOnTomcat)
  - CXF provides inconvenient way to support JakartaEE. JavaEE is used.
- [LB3.W.JAXWSServiceMetroOnTomcat](https://github.com/engsyst/ws_mvn/tree/master/LB3.W.JAXWSServiceMetroOnTomcat)
