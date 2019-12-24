Для успешного запуска проекта необходимо:

1. Настроить tomcat (eclipse):
	- в проекте Servers открыть server.xml
	- во все теги <Connector ...> добавить атрибут --> URIEncoding="UTF-8" 
	
2. Поместить jstl api в папку WEB-INF/lib

3. Запустить сервер, как Java application: LB4jaxwsBookService --> BookServer

4. Запустить этот проект на tomcat