<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Checkout - Tommy's Store</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script type = "text/javascript" src = "https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.9.1/underscore.js"></script>
</head>
<body data-context-path="${pageContext.request.contextPath}">
	<jsp:include page = "navbar.jsp" />
	<div class="modal fade" tabindex="-1" role="dialog" id="modal" data-backdrop="static" data-keyboard="false">
	  	<div class="modal-dialog" role="document">
	    	<div class="modal-content">
		      	<div class="modal-header">
		        	<h5 class="modal-title"></h5>
		      	</div>
		      	<div class="modal-body">
		      		<div class="alert alert-success" role="alert" id="alert"></div>
		      	</div>
		      	<div class="modal-footer">
		        	<a href="${pageContext.request.contextPath}/customer/products" class="btn btn-primary">Browse Products</a>
		      	</div>
	    	</div>
	  	</div>
	</div>
	<div class="modal fade" tabindex="-1" role="dialog" id="address_modal">
	  	<div class="modal-dialog" role="document">
	    	<div class="modal-content">
	  			<form:form action="/ajax/user/address/add" method="POST" modelAttribute="addressForm" id="address_form">
			      	<div class="modal-header">
			        	<h5 class="modal-title">Add New Shipping Address</h5>
			        	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          	<span aria-hidden="true">&times;</span>
			        	</button>
			      	</div>
			      	<div class="modal-body">
			      		<div class="form-group">
					  		<form:label path = "address1">Address 1</form:label>
						    <form:input path = "address1" class="form-control" id="address1"/>
					  	</div>
					  	<div class="form-group">
					  		<form:label path = "address2">Address 2</form:label>
						    <form:input path = "address2" class="form-control" id="address2"/>
					  	</div>
					  	<div class="form-group">
					  		<form:label path = "country">Country</form:label>
					  		<form:select path="country" class="form-control country_select" id="country">
					  			
							</form:select>
					  	</div>
					  	<div class="form-group">
					  		<form:label path = "zipCode">Zip Code</form:label>
						    <form:input path = "zipCode" class="form-control" id="zipCode"/>
					  	</div>
			      	</div>
			      	<div class="modal-footer">
			        	<button type="submit" class="btn btn-primary">Add Shipping Address</button>
			        	<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
			      	</div>
			   	</form:form>
	    	</div>
	  	</div>
	</div>
	<div class="modal fade" tabindex="-1" role="dialog" id="credit_card_modal">
	  	<div class="modal-dialog" role="document">
	    	<div class="modal-content">
	  			<form:form action="/ajax/user/creditcard/add" method="POST" modelAttribute="creditCardForm" id="credit_card_form">
			      	<div class="modal-header">
			        	<h5 class="modal-title">Add New Credit Card</h5>
			        	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          	<span aria-hidden="true">&times;</span>
			        	</button>
			      	</div>
			      	<div class="modal-body">
			      		<div class="form-group">
					  		<form:label path = "number">Credit Card Number</form:label>
						    <form:input path = "number" class="form-control" id="number"/>
					  	</div>
					  	<div class="form-group">
					  		<form:label path = "securityCode">Security Code</form:label>
						    <form:input path = "securityCode" class="form-control" id="securityCode"/>
					  	</div>
			      	</div>
			      	<div class="modal-footer">
			        	<button type="submit" class="btn btn-primary">Add Credit Card</button>
			        	<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
			      	</div>
			   	</form:form>
	    	</div>
	  	</div>
	</div>
	<div class="container mt-5"><br><br>
		<form:form action="/ajax/cart/placecheckout" method="POST" modelAttribute="orderForm" id="checkout_form">
			<div class="row">
				<c:set var="totalPrice" value="0" scope="page" />
				<div class="col-md-4">
					<div class="card">
					  	<h5 class="card-header" align="center">Choose Shipping Address</h5>
					  	<div class="card-body">
					  		<div id="address_card_body">
					  			
					  		</div>
							<div class="alert alert-danger" role="alert" style="display: none;" id="alert_address">There are no shipping address, please add a new shipping address</div>
							<div align="right">
								<br><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#address_modal">Add Shipping Address</button>
					  		</div>
					  	</div>
					</div><br>
					<div class="card">
					  	<h5 class="card-header" align="center">Choose Payment Method</h5>
					  	<div class="card-body">
					  		<div id="credit_card_body">
					  			<div class="form-check">
								  	<input class="form-check-input" type="radio" name="creditCardId" id="creditCardId" value="0">
								  	<label class="form-check-label" for="creditCardId">Cash on Delivery (COD)</label>
								</div>
					  		</div><br>
							<div align="right">
								<button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#credit_card_modal">Add Credit Card</button>
					  		</div>
					  	</div>
					</div>
				</div>
				<div class="col-md-8">
					<div class="alert alert-success" role="alert" id="alert_result" style="display: none;">
						<button type="button" class="close">
							<span aria-hidden="true">×</span>
						</button>
						<strong></strong>
				    </div>
					<div class="float-right">
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text" id="total_price">Total Price: &#8369;</span>
							</div>
							<button class="btn btn-success" type="submit">PLACE CHECKOUT</button>
						</div>
					</div><br><br>
					<table class="table table-hover table-bordered" id="checkout_table">
					  	<thead>
					    	<tr>
					    		<th scope="col"> </th>
					      		<th scope="col">Product Name</th>
					      		<th scope="col">Category</th>
					      		<th scope="col">Quantity</th>
					      		<th scope="col">Price</th>
					      		<th scope="col">Amount</th>
					    	</tr>
					  	</thead>
					  	<tbody></tbody>
					</table>
				</div>
			</div>
		</form:form>
	</div>
	<script type="text/javascript" src="<c:url value="/javascript/commons.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/javascript/template.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/javascript/customer_checkout.js"/>"></script>
</body>
</html>