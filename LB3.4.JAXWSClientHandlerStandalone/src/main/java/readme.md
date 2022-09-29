# Web service simple client project

This works with  __LB3.1.JAXWSServiceSimpleStandalone__  project  
Set up as implementor:
- Run `HelloServer.java` in *LB3.1.JAXWSServiceSimpleStandalone*
- Switch to this project
- Set your WSDL URL in `pom.xml` `<wsdlUrl>` tag
- Generate client artifact
- Run `HelloClient.java`

## Generating the client artifact with maven

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
