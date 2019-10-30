<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Orders - Tommy's Store</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script type = "text/javascript" src = "https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.9.1/underscore.js"></script>
</head>
<body data-context-path="${pageContext.request.contextPath}">
	<jsp:include page = "navbar.jsp" />
	<div class="container mt-5"><br><br>
		<h3 align="center">Orders of </h3>
		<h5 align="center">Category: </h5><br><br>
		<div class="col-md-8 offset-md-2"><br>
			<table class="table table-hover table-bordered" id="order_table">
			  	<thead>
			    	<tr>
			      		<th scope="col">Order Number</th>
			      		<th scope="col">Date Ordered</th>
			      		<th scope="col">Time Ordered</th>
			      		<th scope="col">Quantity Ordered</th>
			    	</tr>
			  	</thead>
			  	<tbody></tbody>
			</table>
			<div class="alert alert-danger" role="alert" id="alert_empty" style="display:none;">There are no orders made on this product</div>
		</div>
	</div>
	<script type="text/javascript" src="<c:url value="/javascript/commons.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/javascript/template.js"/>"></script>
<script type="text/javascript" src="<c:url value="/javascript/admin_product_orders.js"/>"></script>
</body>
</html>