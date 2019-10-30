<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart - Tommy's Store</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script type = "text/javascript" src = "https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.9.1/underscore.js"></script>
</head>
<body data-context-path="${pageContext.request.contextPath}">
	<jsp:include page = "navbar.jsp" />
	<div class="container mt-5"><br><br>
		<div class="col-md-12">
			<div class="alert alert-success" role="alert" id="alert_result" style="display: none;">
				<button type="button" class="close">
					<span aria-hidden="true">×</span>
				</button>
				<strong></strong>
		    </div>
			<div id="container_cart" style="display:none;">
				<ul class="list-inline">
					<li class="list-inline-item">
						<button class="btn btn-secondary" type="button" id="clear_cart">Clear Cart</button>
					</li>
					<li class="list-inline-item">
						<a href="${pageContext.request.contextPath}/customer/cart/checkout" class="btn btn-primary" id="checkout_cart">Checkout</a>
					</li>
				</ul>
				<div class="row equal" id="cart_cards">
					
				</div>
			</div>
			<div class="alert alert-danger" role="alert" id="alert_empty" style="display:none;">
			 	 <h4><strong>Cart Empty</strong></h4>
			 	 <p>Add a product first to view cart</p>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<c:url value="/javascript/commons.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/javascript/template.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/javascript/customer_cart.js"/>"></script>
</body>
</html>