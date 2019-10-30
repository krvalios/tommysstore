<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Users - Tommy's Store</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script type = "text/javascript" src = "https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.9.1/underscore.js"></script>
</head>
<body data-context-path="${pageContext.request.contextPath}">
	<jsp:include page = "navbar.jsp" />
	<div class="modal fade" tabindex="-1" role="dialog" id="add_modal">
	  	<div class="modal-dialog modal-lg" role="document">
	    	<div class="modal-content">
	  			<form:form action="/ajax/user/add" method="POST" modelAttribute="adminForm" id="add_form">
			      	<div class="modal-header">
			        	<h5 class="modal-title">Add New Administrator</h5>
			        	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          	<span aria-hidden="true">&times;</span>
			        	</button>
			      	</div>
			      	<div class="modal-body">
			      		<div class="alert alert-success" role="alert" id="alert_modal" style="display: none;">
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
			      	<div class="modal-footer">
			        	<button type="submit" class="btn btn-primary">Add Administrator</button>
			        	<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
			      	</div>
			   	</form:form>
	    	</div>
	  	</div>
	</div>
	<div class="container mt-5"><br><br>
		<div class="col-md-10 offset-md-1">
			<div class="alert alert-success" role="alert" id="alert_result" style="display: none;">
				<button type="button" class="close">
					<span aria-hidden="true">×</span>
				</button>
				<strong></strong>
		    </div>
			<ul class="list-inline">
				<li class="list-inline-item">
					<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#add_modal">Add New Administrator</button>
				</li>
				<li class="list-inline-item float-right">
					<div class="input-group">
						<div class="input-group-prepend">
					    	<span class="input-group-text">Filter</span>
					  	</div>
						<select class="form-control" id="filter">
					      	<option value="ALL">All</option>
					      	<option value="ADMIN">Admin</option>
					      	<option value="CUSTOMER">Customer</option>
					    </select>
					</div>
				</li>
			</ul>
			<table class="table table-hover table-bordered" id="user_table">
			  	<thead>
			    	<tr>
			      		<th scope="col">Email</th>
			      		<th scope="col">First Name</th>
			      		<th scope="col">Last Name</th>
			      		<th scope="col">Contact Number</th>
			      		<th scope="col">User Role</th>
			    	</tr>
			  	</thead>
			  	<tbody></tbody>
			</table>
			<div class="alert alert-danger" role="alert" id="alert_empty" style="display:none;">There are no users matched</div>
		</div>
	</div>
	<script type="text/javascript" src="<c:url value="/javascript/commons.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/javascript/template.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/javascript/admin_users.js"/>"></script>
</body>
</html>