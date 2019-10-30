<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login - Tommy's Store</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script type = "text/javascript" src = "https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.9.1/underscore.js"></script>
<style>
	.error { color: red; }
</style>
</head>
<body data-context-path="${pageContext.request.contextPath}">
	<jsp:include page = "navbar.jsp" />
	<div class="container mt-5">
		<div class="col-md-4 offset-md-4"><br><br>
			<h3 align="center">Login</h3><br>
			<div class="alert alert-success" role="alert" id="alert_result" style="display: none;">
				<button type="button" class="close">
					<span aria-hidden="true">×</span>
				</button>
				<strong></strong>
		    </div>
			<form:form action="/processlogin" method="POST" modelAttribute="loginForm" id="login_form">
				<div class="form-group">
				    <form:label path = "email">Email</form:label>
				    <form:input path = "email" class="form-control" id="email"/>
			  	</div>
			  	<div class="form-group">
			  		<form:label path = "password">Password</form:label>
				    <form:password path = "password" class="form-control" id="password"/>
			  	</div>
			  	<button type="submit" class="btn btn-primary btn-block">LOGIN</button><br>
			</form:form>
			<a href="${pageContext.request.contextPath}/register" class="btn btn-link float-right">New User? Register here</a>
		</div>
	</div>
	<script type="text/javascript" src="<c:url value="/javascript/commons.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/javascript/template.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/javascript/login.js"/>"></script>
</body>
</html>