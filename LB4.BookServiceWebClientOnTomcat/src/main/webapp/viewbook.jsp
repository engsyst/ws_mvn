<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List of books</title>
<link href="css/default.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="jspf/head.jspf"></jsp:include>
 	<c:choose>
 	<c:when test="${not empty errorMsg }">
		<h2>${errorMsg }</h2>
 	</c:when>
	<c:otherwise>
		<ul>
			<li>${book.title }</li>
			<c:forEach var="author" items="${book.author }">
				<li>${author }</li>
			</c:forEach>
			<li>${book.isbn }</li>
			<li>${book.price }</li>
			<li>${book.count }</li>
		</ul>
	</c:otherwise>
	</c:choose>
	
	<!-- Due PRG pattern this page can be requested as get method only -->
	<!-- if was any error put it into html (see: errorMsg) and remove it from session -->
	<% session.removeAttribute("errorMsg"); %>
	<!-- Put book into html and remove it from session -->
	<% session.removeAttribute("book"); %>
</body>
</html>