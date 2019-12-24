<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
 	<c:when test="${empty books }">
		<h2>No books found</h2>
 	</c:when>
	<c:otherwise>
	<table class="brd">
	<thead class="tabhead">
		<colcolgroup class="left"/>
		<colcolgroup class="left"/>
		<colcolgroup class="left"/>
		<colcolgroup class="right"/>
		<colcolgroup class="right"/>
		<colcolgroup class="left"/>
		<tr>
			<th class="brd">Title</th>
			<th class="brd">Authors</th>
			<th class="brd">ISBN</th>
			<th class="brd">Price</th>
			<th class="brd">Count</th>
			<th class="brd">Action</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="book" items="${books }">
		<tr>
			<td class="brd">${book.title }</td>
			<td class="brd">
			<ul>
			<c:forEach var="author" items="${book.author }">
				<li>${author }</li>
			</c:forEach>
			</ul>
			</td>
			<td class="brd">${book.isbn }</td>
			<td class="brd right">${book.price }</td>
			<td class="brd right">${book.count }</td>
			<td class="brd"><form action="delete" method="post">
				<input type="hidden" name="id" value="${book.id }">
				<input type="submit" value="Delete">
			</form> </td>
		</tr>
	</c:forEach>
	</tbody>
	</table>
	</c:otherwise>
	</c:choose>
</body>
</html>