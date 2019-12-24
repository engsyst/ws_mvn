<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Find by author</title>
</head>
<link href="css/default.css" rel="stylesheet">
<body>
	<jsp:include page="jspf/head.jspf"></jsp:include>
 	<div>
 		<form action="findauthor" method="post" >
 			<label for="pattern">Author: </label>
 			<input type="text" name="pattern" autofocus="autofocus" required="required"/>
 			<input type="submit" value="Find" />
 		</form>
 	</div>
</body>
</html>