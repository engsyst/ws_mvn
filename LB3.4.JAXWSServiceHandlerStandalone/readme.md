# Web service with handler

This project works along with  __lb3-4-jaxws-client-handler__  project

- Run `HelloServer.java`

## SOAP 1.2

Setup binding type while publishing:

```
Endpoint ep = Endpoint.create(SOAPBinding.SOAP12HTTP_BINDING, implHelloWithHandler);
ep.publish(address);
```

There are an other way. Use `@BindingType(...)` annotation in [HelloHandled](src/main/java/ua/nure/itech/jaxws/service/handled/HelloHandled.java) class, and then publish it using static method: `Endpoint ep = Endpoint.publish(address, implHelloWithHandler);`


### Terminal
- Run maven goal `mvn clean jaxws:wsimport`

### Eclipse

Create "Run Configuration"
- Run > Run Configurations...
- In the search box type: *maven*
- Double click on *Maven*
- Select your client project in the *Base directory*
- In the Goal field type: *clean jaxws:wsimport*
- Press: *Run*  
**Note**: Clean the target directory before generating the artifact.

### Intellij Idea

In the *Maven* view:
- Select: *Run maven goal*
- Type: *mvn clean jaxws:wsimport*
