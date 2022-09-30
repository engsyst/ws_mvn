# [LB3.W.JAXWSServiceCXFOnTomcat](https://github.com/engsyst/ws_mvn/tree/master/LB3.W.JAXWSServiceCXFOnTomcat)

How to use CXF JaxWS implementation

This project supports JavaEE not ~~JakartaEE~~.

Configuration files: 
- [web.xml](src/main/webapp/WEB-INF/web.xml)
- [cxf-servlet.xml](src/main/webapp/WEB-INF/cxf-servlet.xml)

## Run project

There two options to run this project
- Console app. Simple run [ServiceRunner](src/main/java/ua/nure/itech/jaxws/service/ServiceRunner.java) 
- Web app:
  - Context path: see `pom.xnml` `appname` property;
  - Services paths `/ws/hello` and `/ws/ahello`
  - URL: `http://localhost:8080/${appname}/${path to service}`

### Run WebApp in Eclipse
Run "On Server"

### Run WebApp with Maven
`mvn package cargo:run` in a terminal

### InellijIdea community
- Install SmartTomcat plugin
- Open "Edit configurations..." in 'Run' menu
- Add "SmartTomcat" configuration
- Setup context path
- run it

or
- run maven goal (see __*'Run WebApp with Maven'*__)

