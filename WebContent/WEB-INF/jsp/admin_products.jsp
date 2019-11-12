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
<script type = "text/javascript" src = "https://cdnjs.cloudflare.com/ajax/libs/backbone.js/1.4.0/backbone.js"></script>
</head>
<body data-context-path="${pageContext.request.contextPath}">
	<jsp:include page = "navbar.jsp" />
	<div class="modal fade" tabindex="-1" role="dialog" id="add_modal">
	  	<div class="modal-dialog" role="document">
	    	<div class="modal-content">
	  			<form:form action="/ajax/product/add" method="POST" enctype="multipart/form-data" modelAttribute="addProductForm" id="add_form">
			      	<div class="modal-header">
			        	<h5 class="modal-title">Add New Product</h5>
			        	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          	<span aria-hidden="true">&times;</span>
			        	</button>
			      	</div>
			      	<div class="modal-body">
			      		<div class="alert alert-success" role="alert" id="alert_add" style="display: none;">
							<button type="button" class="close">
								<span aria-hidden="true">×</span>
							</button>
							<strong></strong>
					    </div>
						<div class="form-group">
					  		<form:label path = "name">Product Name</form:label>
						    <form:input path = "name" class="form-control" id="add_name"/>
					  	</div>
					  	<div class="form-group" id="add_category_select">
					  		<form:label path = "categoryId">Category</form:label>
					  	</div>
					  	<div class="form-group">
					  		<form:label path = "price">Price</form:label>
						    <form:input path = "price" class="form-control" id="add_price"/>
					  	</div>
					  	<div class="form-group">
					    	<label id="picture">Product Picture</label>
					    	<!-- <input type="file" class="form-control-file" id="add_picture" name="picture"> -->
					    	<input type="file" class="form-control-file" id="add_picture">
					  	</div>
			      	</div>
			      	<div class="modal-footer">
			        	<button type="submit" class="btn btn-primary">Add Product</button>
			        	<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
			      	</div>
			   	</form:form>
	    	</div>
	  	</div>
	</div>
	<div class="modal fade" tabindex="-1" role="dialog" id="edit_modal">
	  	<div class="modal-dialog modal-lg" role="document">
	  		<form:form action="/ajax/product/edit" method="POST" enctype="multipart/form-data" modelAttribute="editProductForm" id="edit_form">
	    		
		   	</form:form>
	  	</div>
	</div>
	<div class="container mt-5"><br><br>
		<div class="col-md-12" id="container">
			<div class="alert alert-success" role="alert" id="alert_result" style="display: none;">
				<button type="button" class="close">
					<span aria-hidden="true">×</span>
				</button>
				<strong></strong>
		    </div>
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#add_modal" id="add_btn">Add New Product</button><br><br>
			<!-- <div class="row equal" id="product_cards">
				
			</div> -->
			<div class="alert alert-danger" role="alert" id="alert_empty" style="display: none;">There are no products, please add a new product</div>
		</div>
	</div>
	<script type="text/javascript" src="<c:url value="/javascript/commons.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/javascript/backbone/templates.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/javascript/backbone/models.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/javascript/backbone/collections.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/javascript/backbone/views.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/javascript/backbone/admin_products.js"/>"></script>
</body>
</html>