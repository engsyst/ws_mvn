# Web service client project

Works with LB3.1-4JAXWSServerStandalone project  
Set up as implementor:
> final static Object implHello = new ua.nure.itech.jaxws.service.Hello();

in the HelloServer class

## Generating the client artifact with maven

##### Eclipse

Create "Run Configuration"
- Run > Run Configurations...
- In the search box type: *maven*
- Double click on *Maven*
- Select your client project in the *Base directory*
- In the Goal field type: *jaxws:wsimport*
- Press: *Run*  
**Note**: Clean the target directory before generating the artifact.

##### Intellij Idea

In the *Maven* view:
- Select: *Run maven goal*
- Type: *mvn jaxws:wsimport*
