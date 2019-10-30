<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Categories - Tommy's Store</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script type = "text/javascript" src = "https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.9.1/underscore.js"></script>
<style>
	.sticky-top { top: 5.5em; }
</style>
</head>
<body data-context-path="${pageContext.request.contextPath}">
	<jsp:include page = "navbar.jsp" />
	<div class="container mt-5"><br><br>
		<div class="row">
			<div class="col-md-4">
				<div class="card" id="edit_card" style="display: none;">
				  	<h5 class="card-header" align="center">Edit Category</h5>
				  	<div class="card-body">
				  		<form:form action="/ajax/category/edit" method="POST" modelAttribute="editCategoryForm" id="edit_form">
							<form:hidden path = "id" class="form-control" id="edit_id"/>
							<div class="form-group">
						  		<form:label path = "name">Category Name</form:label>
							    <form:input path = "name" class="form-control" id="edit_name"/>
						  	</div>
						  	<div align="center">
						  		<button type="submit" class="btn btn-primary">EDIT CATEGORY</button>
								<a href="${pageContext.request.contextPath}/admin/categories" class="btn btn-danger" id="edit_cancel">CANCEL</a>
							</div>
						</form:form>
				  	</div>
				</div>
				<div class="card sticky-top" id="add_card">
				  	<h5 class="card-header" align="center" id="meme">Add New Category</h5>
				  	<div class="card-body">
						<form:form action="/ajax/category/add" method="POST" modelAttribute="categoryForm" id="add_form"> 
							<div class="form-group">
								<form:label path = "name">Category Name</form:label>
							    <form:input path = "name" class="form-control" id="add_name"/>
							</div>
							<button type="submit" class="btn btn-primary btn-block">ADD CATEGORY</button>
						</form:form>
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
				<table class="table table-hover table-bordered" id="category_table">
				  	<thead>
				    	<tr>
				      		<th scope="col">Category Id</th>
				      		<th scope="col">Category Name</th>
				      		<th>Actions</th>
				    	</tr>
				  	</thead>
				  	<tbody></tbody>
				</table>
				<div class="alert alert-danger" role="alert" style="display: none;" id="alert_empty">There are no categories, please add a new category</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<c:url value="/javascript/commons.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/javascript/template.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/javascript/admin_categories.js"/>"></script>
</body>
</html>