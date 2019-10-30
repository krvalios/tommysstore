<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	<a class="navbar-brand" href="${pageContext.request.contextPath}/">Tommy's Store</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarSupportedContent">
  		<ul class="navbar-nav ml-auto">
   		<c:choose>
			<c:when test="${loginUser != null && loginUser.role == 'ADMIN'}">
				<li class="nav-item">
		        	<a class="nav-link" href="${pageContext.request.contextPath}/admin/dashboard">Dashboard</a>
		      	</li>
		      	<li class="nav-item">
		        	<a class="nav-link" href="${pageContext.request.contextPath}/admin/users">Users</a>
		      	</li>
		      	<li class="nav-item">
		        	<a class="nav-link" href="${pageContext.request.contextPath}/admin/categories">Categories</a>
		      	</li>
		      	<li class="nav-item">
		        	<a class="nav-link" href="${pageContext.request.contextPath}/admin/products">Products</a>
		      	</li>
		      	<li class="nav-item">
		        	<a class="nav-link" href="${pageContext.request.contextPath}/admin/inventory">Inventory</a>
		      	</li>
	      	</c:when>
			<c:otherwise>
   				<li class="nav-item">
		        	<a class="nav-link" href="${pageContext.request.contextPath}/customer/categories">Categories</a>
		      	</li>
		      	<li class="nav-item">
		        	<a class="nav-link" href="${pageContext.request.contextPath}/customer/products">Products</a>
		      	</li>
		      	<li class="nav-item">
		        	<a class="nav-link" href="${pageContext.request.contextPath}/customer/cart">Cart</a>
		      	</li>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${loginUser != null}">
				<li class="nav-item dropdown">
	        		<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	          			<c:out value="${loginUser.firstname}" />
	        		</a>
	        		<div class="dropdown-menu" aria-labelledby="navbarDropdown">
	          			<div class="dropdown-divider"></div>
	          			<a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Logout</a>
	       			</div>
	      		</li>
	      	</c:when>
			<c:otherwise>
   				<a href="${pageContext.request.contextPath}/login" class="btn btn-outline-info my-2 mx-2 my-sm-0" >Login</a>
			</c:otherwise>
		</c:choose>
		</ul>
	</div>
</nav>