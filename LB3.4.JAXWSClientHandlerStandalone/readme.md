# Web service with handler

This project works with  __lb3-4-jaxws-service-handler__  project  

- Run `HelloServer` in *lb3-4-jaxws-service-handler*
- Switch to  __this__  project
- Set your WSDL URL in `pom.xml` `<wsdlUrl>` tag
- Generate client artifact (if it necessary, see. below).
- Run [HelloClient.java](src/main/java/ua/nure/itech/jaxws/service/HelloClient.java)

## Generating the client artifact with maven

To support SOAP 1.2

```
<protocol>Xsoap1.2</protocol>
<extension>true</extension>
```

must be present on *configuration* tag of *jaxws-maven-plugin*

>> If you already have the client generated form WSDL of service with  __SOAP 1.1__  support its not necessary to regenerate it. The correct protocol will be selected if WSDL url references to currently exposed service.

##### Terminal
- Run maven goal: `mvn clean jaxws:wsimport`

##### Intellij Idea

In the *Maven* view:
- Select: *Run maven goal*
- Type: `mvn clean jaxws:wsimport`
