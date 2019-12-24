<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List of books</title>
<link href="css/default.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="jspf/head.jspf"></jsp:include>
 	<div>
 		<form action="add" method="post">
 			<label for="title">Title: </label>
 			<input type="text" name="title" />
 			<label for="author">Author: </label>
 			<input type="text" name="author" />
 			<label for="isbn">ISBN: </label>
 			<input type="text" name="isbn" />
 			<label for="price">Price: </label>
 			<input type="number" name="price" min="0.0" step="0.1"/>
 			<label for="count">Count: </label>
 			<input type="number" name="count" min="0" />
 			<input type="submit" value="Add" />
 		</form>
 	</div>
</body>
</html>