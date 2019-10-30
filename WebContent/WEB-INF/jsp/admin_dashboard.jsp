<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard - Tommy's Store</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script type = "text/javascript" src = "https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.9.1/underscore.js"></script>
</head>
<body data-context-path="${pageContext.request.contextPath}">
	<jsp:include page = "navbar.jsp" />
	<div class="container mt-5"><br><br>
		<div class="row">
			<div class="col-md-8">
				<h4>Low Stock Products</h4><br>
				<div class="row equal" id="low_inventory_cards">
							
				</div>
				<div class="alert alert-danger" role="alert" id="alert_low_stock" style="display:none;">There are no products that are low in stock</div>
			</div>
			<div class="col-md-4">
				<div class="card">
				  	<h5 class="card-header" align="center">New registered customers</h5>
				  	<div class="card-body" id="new_user_card">
				  		<div class="alert alert-danger" role="alert" id="alert_new_user" style="display:none;">There are no new registered customers</div>
				  	</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<c:url value="/javascript/commons.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/javascript/template.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/javascript/admin_dashboard.js"/>"></script>
</body>
</html>