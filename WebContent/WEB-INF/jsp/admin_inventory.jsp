<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inventory - Tommy's Store</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script type = "text/javascript" src = "https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.9.1/underscore.js"></script>
</head>
<body data-context-path="${pageContext.request.contextPath}">
	<jsp:include page = "navbar.jsp" />
	<div class="modal fade" tabindex="-1" role="dialog" id="restock_modal">
	  	<div class="modal-dialog" role="document">
	  		<form:form action="/ajax/inventory/restock" method="POST" modelAttribute="inventoryForm" id="restock_form">
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
			<table class="table table-hover table-bordered" id="inventory_table">
			  	<thead>
			    	<tr>
			    		<th scope="col"> </th>
			      		<th scope="col">Product Id</th>
			      		<th scope="col">Product Name</th>
			      		<th scope="col">Category</th>
			      		<th scope="col">Remaining Stock</th>
			      		<th scope="col">Replenish Stock</th>
			    	</tr>
			  	</thead>
			  	<tbody></tbody>
			</table>
			<div class="alert alert-danger" role="alert" style="display: none;" id="alert_empty">There are no products in the inventory</div>
		</div>
	</div>
	<script type="text/javascript" src="<c:url value="/javascript/commons.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/javascript/template.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/javascript/admin_inventory.js"/>"></script>
</body>
</html>