<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register - Tommy's Store</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script type = "text/javascript" src = "https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.9.1/underscore.js"></script>
</head>
<body data-context-path="${pageContext.request.contextPath}">
	<jsp:include page = "navbar.jsp" />
	<div class="container mt-5">
		<form:form action="/processregister" method="POST" modelAttribute="registerForm" id="register_form">
			<div class="col-md-8 offset-md-2"><br><br>
				<h3 align="center">Register</h3><br>
				<div class="alert alert-success" role="alert" id="alert_result" style="display: none;">
					<button type="button" class="close">
						<span aria-hidden="true">×</span>
					</button>
					<strong></strong>
			    </div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
						    <form:label path = "email">Email</form:label>
						    <form:input path = "email" class="form-control" id="email"/>
					  	</div>
					  	<div class="form-group">
					  		<form:label path = "password">Password</form:label>
						    <form:password path = "password" class="form-control" id="password"/>
					  	</div>
					  	<div class="form-group">
					  		<form:label path = "password">Confirm Password</form:label>
						    <input type="password" class="form-control" name="confirmPassword" id="confirmPassword"/>
					  	</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
						    <form:label path = "firstname">First Name</form:label>
						    <form:input path = "firstname" class="form-control" id="firstname"/>
					  	</div>
					  	<div class="form-group">
						    <form:label path = "lastname">Last Name</form:label>
						    <form:input path = "lastname" class="form-control" id="lastname"/>
					  	</div>
					  	<div class="form-group">
						    <form:label path = "contactNumber">Contact Number</form:label>
						    <form:input path = "contactNumber" class="form-control" id="contactNumber"/>
					  	</div>
					</div>
				</div>
			</div>
			<br>
			<div class="col-md-4 offset-md-4" align="center">
				<button type="submit" class="btn btn-primary btn-block">REGISTER</button><br>
			</div>
		</form:form>
		<div class="col-md-4 offset-md-4" align="center">
			<a href="${pageContext.request.contextPath}/login" class="btn btn-link">Already have an account? Login here</a>
		</div>
	</div>
	<script type="text/javascript" src="<c:url value="/javascript/commons.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/javascript/template.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/javascript/login.js"/>"></script>
</body>
</html>