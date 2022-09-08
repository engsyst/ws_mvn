# LB2.ParserDemo project

DOM, SAX, StAX, StAX2, JAXB, XSL Transformation examples


## Generating JAXB classes with maven

in terminal run the maven goal: `mvn compile`

##### Eclipse

Create "Run Configuration"
- Run > Run Configurations...
- In the search box type: *maven*
- Double click on *Maven*
- Select your client project in the *Base directory*
- In the Goal field type: *complile*
- Press: *Run*  
**Note**: Clean the target directory before generating the artifact.

##### Intellij Idea

In the *Maven* view:
- Run: *Lifecicle > compile*
