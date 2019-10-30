<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tommy's Store</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
	<jsp:include page = "WEB-INF/jsp/navbar.jsp" />
	<div class="container mt-5" align="center">
		<br><br>
		<c:choose>
			<c:when test="${loginUser.role == 'ADMIN'}">
				<h3>Welcome to Tommy's Store!!!</h3>
			</c:when>
			<c:otherwise>
				<c:redirect url="/popular"/>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>