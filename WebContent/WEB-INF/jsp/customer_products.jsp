<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Products - Tommy's Store</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script type = "text/javascript" src = "https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.9.1/underscore.js"></script>
</head>
<body data-context-path="${pageContext.request.contextPath}">
	<jsp:include page = "navbar.jsp" />
	<div class="modal fade" tabindex="-1" role="dialog" id="add_modal">
	  	<div class="modal-dialog" role="document">
  			<form:form action="/ajax/cart/add" method="POST" modelAttribute="cartItemForm" id="add_form">
	    		<div class="modal-content">
			      	
	    		</div>
		   	</form:form>
	  	</div>
	</div>
	<div class="container mt-5"><br><br>
		<div class="col-md-12">
			<div class="alert alert-success" role="alert" id="alert_result" style="display: none;">
				<button type="button" class="close">
					<span aria-hidden="true">×</span>
				</button>
				<strong></strong>
		    </div>
			<div class="col-md-6 offset-md-6">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="Product Name" id="search_name"/>
					<select id="search_category" class="form-control" style="max-width:40%;">
						<option value="0">All</option>
					</select>
				</div>
			</div><br>
			<div class="row equal" id="product_cards">
				
			</div>
			<div class="alert alert-danger" role="alert" id="alert_empty" style="display:none;">There are no products matched</div>
		</div>
	</div>
	<script type="text/javascript" src="<c:url value="/javascript/commons.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/javascript/template.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/javascript/customer_products.js"/>"></script>
</body>
</html>