<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Maior consumidor</title>
</head>
<body>
<h1>Os clientes que mais consumiram foram: </h1>
<ul>
	<c:forEach var="consumidor" items="${consumidores}">
	<li>${consumidor} </li>
	</c:forEach> 
</ul>

<a href="index.html">Voltar</a>

</body>
</html>