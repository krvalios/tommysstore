<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
</head>
<body data-context-path="${pageContext.request.contextPath}">
	<jsp:include page = "navbar.jsp" />
	<div class="container mt-5"><br><br>
		<div class="row">
			<div class="col-md-3">
				<div class="list-group" id="category_list">
					<a href="${pageContext.request.contextPath}/customer/categories" class="list-group-item list-group-item-action" id="0">
						All
					</a>
				</div>
			</div>
			<div class="col-md-9">
				<div class="alert alert-success" role="alert" id="alert_result" style="display: none;">
					<button type="button" class="close">
						<span aria-hidden="true">×</span>
					</button>
					<strong></strong>
			    </div>
				<div class="row equal" id="category_cards">
					
				</div>
				<div class="alert alert-danger" role="alert" id="alert_empty_product" style="display:none;">There are no products in this category</div>
				<div class="alert alert-danger" role="alert" id="alert_empty_category" style="display:none;">There are no categories</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<c:url value="/javascript/commons.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/javascript/template.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/javascript/customer_categories.js"/>"></script>
</body>
</html>