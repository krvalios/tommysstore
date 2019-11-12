var TplUtil = (function(){
	"use strict";
	
	var contextPath = CommonUtil.contextPath;

	var alertTpl = _.template(
		  '<div class="alert alert-<%= type %>" role="alert">'
		+	'<button type="button" class="close">'
		+		'<span aria-hidden="true">×</span>'
		+	'</button>'
		+	'<strong><%= message %></strong>'
	    + '</div>'
	);
	
	var invalidInputTpl = _.template(
		'<div class="invalid-feedback"><%= value %></div>'
	);
	
	var selectOptionTpl = _.template(
		'<option value="<%- id %>"><%- name %></option>'
	);
	
	var lowInventoryTpl = _.template(
		  '<div class="card mb-3 mx-2" style="width: 350px; height: 150px">'
		+	'<a href="' + contextPath + '/admin/inventory" class="stretched-link"></a>'
		+  	'<div class="row no-gutters">'
		+    	'<div class="col-md-5">'
		+      		'<img src="' + contextPath + '/images/<%- picture %>" class="card-img-top" alt="No image" height="100%">'
		+    	'</div>'
		+    	'<div class="col-md-7">'
		+      		'<div class="card-body">'
		+        		'<h5 class="card-title"><strong><%- productName %></strong></h5>'
		+		    	'<p class="card-subtitle mb-1 text-muted"><%- categoryName %></p>'
		+		    	'<p class="card-text"><%- stocks %> remaining stocks</p>'
		+      		'</div>'
		+    	'</div>'
		+  	'</div>'
		+ '</div>'
	);
	
	var newUserTpl = _.template(
		  '<h5 class="card-title"><%- firstname %> <%- lastname %></h5>'
		+ '<h6 class="card-subtitle mb-2 text-muted">'
		+	'<%- email %><br>'
		+	'<%- contactNumber %>'
		+ '</h6><hr/>'
	);
	
	var userTpl = _.template(
		  '<tr>'
		+	'<th scope="row"><%- email %></th>'
		+	'<td><%- firstname %></td>'
		+	'<td><%- lastname %></td>'
		+	'<td><%- contactNumber %></td>'
		+	'<td><%- role %></td>'
		+ '</tr>'
	);
	
	var categoryTpl = _.template(
		  '<tr id="<%- id %>">'
		+	'<th scope="row"><%- categoryId %></th>'
	    + 	'<td><%- name %></td>'
	    +	'<td>'
	    +		'<p><a href="' + contextPath + '/admin/category/edit/<%- id %>" class="btn btn-success category_edit">Edit</a> '
		+		'<button type="button" class="btn btn-danger category_remove">Remove</button></p>'
	    +   '</td>'
	   	+ '</tr>'
	);
	
	var productTpl = _.template(
		  '<div class="card mb-3 mx-2" style="width: 350px; height: 220px" id="<%- id %>">'
		+	'<div class="row no-gutters d-flex h-100">'
		+	    '<div class="col-md-5">'
		+	      	'<img src="' + contextPath + '/images/<%- picture %>" class="card-img-top" alt="No image" height="100%">'
		+	    '</div>'
		+	    '<div class="col-md-7 justify-content-center align-self-center">'
		+	      	'<div class="card-body">'
		+	        	'<h5 class="card-title"><strong><%- name %></strong></h5>'
		+			    '<h6 class="card-subtitle mb-1 text-muted"><%- categoryName %></h6>'
		+			    '<p class="card-text">'
		+			    	'<%- productId %><br>'
		+			    	'<%- price %> pesos <br>'
		+				'</p>'
		+	      	'</div>'
		+	    '</div>'
		+	  '</div>'
		+	  '<div class="card-footer" align="center">'
		+	    '<a href="' + contextPath + '/ajax/product/edit/<%- id %>" class="btn btn-success btn-sm product_edit">Edit</a> '
		+		'<button type="button" class="btn btn-danger btn-sm product_remove">Remove</button> '
		+		'<a href="' + contextPath + '/admin/product/stockhistory/<%- id %>" class="btn btn-primary btn-sm product_history">Stock History</a> '
		+		'<a href="' + contextPath + '/admin/product/orders/<%- id %>" class="btn btn-info btn-sm product_orders">Orders</a>'
		+		'</div>'
		+ '</div>'
	);
	
	var productModalTpl = _.template(
		  '<input type="hidden" name="id" class="form-control" id="edit_id" value="<%- id %>"/>'
	  	+ '<div class="modal-header">'
	    +	'<h5 class="modal-title">Edit Product</h5>'
	    +	'<button type="button" class="close" data-dismiss="modal" aria-label="Close">'
	    +  	'<span aria-hidden="true">&times;</span>'
	    +	'</button>'
	  	+ '</div>'
	  	+ '<div class="modal-body">'
	  	+	'<div class="row">'
	  	+		'<div class="col-md-5">'
	  	+			'<div class="card" style="height: 330px">'
	  	+				'<h6 class="card-header"><strong><%- name %></strong></h6>'
		+				'<img src="' + contextPath + '/images/<%- picture %>" class="card-img-top px-5" alt="No image" height="180">'
		+				'<div class="card-body">'
		+				    '<h6 class="card-title"><strong><%- productId %></strong></h6>'
		+				   	'<h6 class="card-subtitle mb-2 text-muted"><%- categoryName %></h6>'
		+				    '<p class="card-text"><%- price %> pesos</p>'
		+				'</div>'
		+			'</div>'
	  	+		'</div>'
	  	+		'<div class="col-md-7">'
		+      		'<div class="alert alert-success" role="alert" id="alert_edit" style="display: none;">'
		+				'<button type="button" class="close">'
		+					'<span aria-hidden="true">×</span>'
		+				'</button>'
		+				'<strong></strong>'
		+		    '</div>'
		+			'<div class="form-group">'
		+		  		'<label for = "name">Product Name</label>'
		+			    '<input type="text" name = "name" class="form-control" id="edit_name" value="<%- name %>"/>'
		+		  	'</div>'
		+		  	'<div class="form-group">'
		+		  		'<label for = "categoryId">Category</label>'
		+		  		'<select name="categoryId" class="form-control category_select" id="edit_categoryId" value="<%- categoryId %>">'
		+				'</select>'
		+		  	'</div>'
		+		  	'<div class="form-group">'
		+		  		'<label for = "price">Price</label>'
		+			    '<input type="text" name = "price" class="form-control" id="edit_price" value="<%- price %>"/>'
		+		  	'</div>'
		+		  	'<div class="form-group">'
		+		    	'<label id="picture">Product Picture</label>'
		+		    	'<input type="file" class="form-control-file" id="edit_picture" name="picture">'
		+		  	'</div>'
		+	  	'</div>'
		+  	'</div>'
	  	+ '</div>'
	  	+ '<div class="modal-footer">'
	    +	'<button type="submit" class="btn btn-primary">Edit Product</button>'
	    +	'<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>'
	  	+ '</div>'
	);
	
	var stockHistoryTpl = _.template(
		  '<tr>'
		+	'<td><%- date %></td>'
	   	+	'<td><%- time %></td>'
	    +   '<td><%- addedBy %></td>'
	    +	'<td><%- stockAdded %></td>'
		+ '</tr>'
	);
	
	var orderTpl = _.template(
		  '<tr>'
		+	'<th><%- id %></th>'
	   	+	'<td><%- date %></td>'
	   	+	'<td><%- time %></td>'
	    +   '<td><%- quantity %></td>'
		+ '</tr>'
	);
	
	var inventoryTpl = _.template(
		  '<tr id="<%- id %>">'
		+	'<th scope="row"><img src="' + contextPath + '/images/<%- picture %>" alt="No image" width="70" height="70"/></th>'
	    +   '<td class="align-middle"><%- productId %></td>'
	    +   '<td class="align-middle"><%- productName %></td>'
	    +   '<td class="align-middle"><%- categoryName %></td>'
	    +   '<td class="align-middle"><%- stocks %> stocks</td>'
	    +   '<td class="align-middle">'
	    +		'<a href="' + contextPath + '/ajax/inventory/add" class="btn btn-success inventory_add">Add Stock</a>'
	    +	'</td>'
	   	+ '</tr>'
	);
	
	var inventoryModalTpl = _.template(
		  '<input type="hidden" name="inventoryId" class="form-control" id="restock_id" value="<%- id %>"/>'
		+	'<div class="modal-header">'
		+	 	'<h5 class="modal-title">Replenish Stock</h5>'
		+	   	'<button type="button" class="close" data-dismiss="modal" aria-label="Close">'
		+			'<span aria-hidden="true">&times;</span>'
		+	  	'</button>'
		+	'</div>'
		+	'<div class="modal-body">'
		+		'<div class="row">'
		+	    	'<div class="col-md-5">'
		+	      		'<div class="card" style="height: 200px">'
		+					'<img src="' + contextPath + '/images/<%- picture %>" class="card-img-top" alt="No image" height="197">'
		+				'</div>'
		+	      		'</div>'
		+	      		'<div class="col-md-7">'
		+			 		'<div class="alert alert-success" role="alert" id="alert_modal" style="display: none;">'
		+						'<button type="button" class="close">'
		+							'<span aria-hidden="true">×</span>'
		+						'</button>'
		+						'<strong></strong>'
		+					'</div>'
		+					'<h6 class="card-title"><strong><%- productName %></strong></h6>'
		+					'<h6 class="card-subtitle mb-2 text-muted"><%- categoryName %></h6>'
		+					'<p class="card-text" id="text_modal"><%- productId %><br><%- stocks %> remaining stocks</p><hr>'
		+					'<div class="form-group">'
		+					  	'<label for="stockAdded">Quantity</label>'
		+						'<input type="number" name="stockAdded" class="form-control" id="stockAdded"/>'
		+					'</div>'
		+				'</div>'
		+			'</div>'
		+	   	'</div>'
		+	 	'<div class="modal-footer">'
		+	        '<button type="submit" class="btn btn-primary">Add Stock</button>'
		+	        '<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>'
		+	   	'</div>'
	    +	'</div>'
	  	+ '</div>'
	);
	
	var popularProductTpl = _.template(
		  '<div class="card mx-2 mb-3" style="width: 180px; height: 330px">'
		+	'<a href="' + contextPath + '/customer/products" class="stretched-link"></a>'
		+  	'<img src="' + contextPath + '/images/<%- picture %>" class="card-img-top" alt="No image" height="170">'
		+  	'<div class="card-body">'
		+    	'<h6 class="card-title"><strong><%- productName %></strong></h6>'
		+    	'<h6 class="card-subtitle mb-2 text-muted">'
		+    		'<%- categoryName %>'
		+    	'</h6>'
		+    	'<p class="card-text">'
		+    		'<%- price %> pesos<br>'
		+    		'<%- quantitySold %> sold'
		+    	'</p>'
		+  	'</div>'
		+ '</div>'
	);
	
	var categoryListTpl = _.template(
		  '<a href="' + contextPath + '/ajax/category/<%- id %>/products" class="list-group-item list-group-item-action" id="<%- id %>">'
		+	'<%- name %>'
		+ '</a>'
	);
	
	var categoryCardTpl = _.template(
		  '<div class="card mx-2 mb-3" style="width: 180px; height: 320px">'
		+	'<a href="' + contextPath + '/customer/products" class="stretched-link"></a>'
		+	'<img src="' + contextPath + '/images/<%- picture %>" class="card-img-top" alt="No image" height="170">'
		+	'<div class="card-body">'
		+	    '<h6 class="card-title"><strong><%- name %></strong></h6>'
		+	    '<h6 class="card-subtitle mb-2 text-muted">'
		+	    	'<%- categoryName %>'
		+	    '</h6>'
		+	    '<p class="card-text"><%- price %> pesos</p>'
		+	  '</div>'
		+ '</div>'
	);
	var productBeanTpl = _.template(
		  '<div class="card mb-3 mx-2" style="width: 350px; height: 220px" id="<%- id %>">'
		+  	'<div class="row no-gutters d-flex h-100">'
		+    	'<div class="col-md-5 py-3">'
		+      		'<img src="' + contextPath + '/images/<%- picture %>" class="card-img-top" alt="No image" height="100%">'
		+    	'</div>'
		+    	'<div class="col-md-7 justify-content-center align-self-center">'
		+      		'<div class="card-body">'
		+        		'<h5 class="card-title"><strong><%- productName %></strong></h5>'
		+		    	'<h6 class="card-subtitle mb-2 text-muted"><%- categoryName %></h6>'
		+		    	'<p class="card-text">'
		+		    		'<%- price %> pesos <br>'
		+		    		'<%- inventoryStatus %><br>'
		+				'</p>'
		+				'<a href="' + contextPath + '/ajax/cart/add" class="btn btn-success cart_add">Add to Cart</a>'
		+     		'</div>'
		+   	'</div>'
		+	'</div>'
		+ '</div>'
	);
	
	var cartModalTpl = _.template(
		'<input type="hidden" name="product.id" class="form-control" id="add_id" value="<%- id %>"/>'
	  	+ '<div class="modal-header">'
	    +	'<h5 class="modal-title">Add Product to Cart</h5>'
	    +	'<button type="button" class="close" data-dismiss="modal" aria-label="Close">'
	    +  	'<span aria-hidden="true">&times;</span>'
	    +	'</button>'
	  	+ '</div>'
	  	+ '<div class="modal-body">'
	  	+	'<div class="row">'
	  	+		'<div class="col-md-5">'
	  	+			'<div class="card" style="height: 200px">'
		+				'<img src="' + contextPath + '/images/<%- picture %>" class="card-img-top" alt="No image" height="197">'
		+			'</div>'
	  	+		'</div>'
	  	+		'<div class="col-md-7">'
		+			'<div class="alert alert-success" role="alert" id="alert_modal" style="display: none;">'
		+				'<button type="button" class="close">'
		+					'<span aria-hidden="true">×</span>'
		+				'</button>'
		+				'<strong></strong>'
		+		    '</div>'
		+			'<h6 class="card-title"><strong><%- productName %></strong></h6>'
		+		   	'<h6 class="card-subtitle mb-2 text-muted"><%- categoryName %></h6>'
		+		    '<p class="card-text"><%- price %> pesos<br><%- inventoryStatus %><hr></p>'
		+			'<div class="form-group">'
		+		  		'<label for="quantity">Quantity</label>'
		+			    '<input type="number" name="quantity" class="form-control" id="quantity"/>'
		+		  	'</div>'
		+	  	'</div>'
		+  	'</div>'
	  	+ '</div>'
	  	+ '<div class="modal-footer">'
	    +	'<button type="submit" class="btn btn-primary">Add to Cart</button>'
	    +	'<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>'
	  	+ '</div>'
	);
	
	var cartTpl = _.template(
		 '<form action="/ajax/cart/edit" method="POST" id="cart_form_<%- id %>">' 
		+	'<input type="hidden" name = "product.id" value = "<%- id %>" />'
		+	'<div class="card mb-3 mx-2" style="width: 350px; height: 250px" id="<%- id %>">'
		+	  	'<div class="row no-gutters d-flex h-100">'
		+	    	'<div class="col-md-5">'
		+	      		'<img src="' + contextPath + '/images/<%- picture %>" class="card-img-top" alt="No image" height="100%">'
		+	    	'</div>'
		+	    	'<div class="col-md-7 justify-content-center align-self-center">'
		+	      		'<div class="card-body">'
		+	        		'<h5 class="card-title"><strong><%- productName %></strong></h5>'
		+			    	'<h6 class="card-subtitle mb-2 text-muted"><%- categoryName %></h6>'
		+			    	'<p class="card-text">'
		+			    		'<%- price %> pesos<br>'
		+			    		'<%- inventoryStatus %><br>'
		+					'</p>'
		+	      		'</div>'
		+	    	'</div>'
		+	  	'</div>'
		+	  	'<div class="card-footer" align="center">'
		+	  		'<div class="input-group form-group">'
		+	  			'<div class="input-group-prepend">'
		+			    	'<span class="input-group-text">&#8369;<%- amount %></span>'
		+			  	'</div>'
	    +  				'<input type="number" name = "quantity" id="quantity_<%- id %>" class="form-control" value = "<%- quantity %>"/>'
		+			  	'<div class="input-group-append">'
		+			    	'<button class="btn btn-success cart_edit" type="submit">Save</button>'
		+			  	'</div>'
		+			  	'<div class="input-group-append">'
		+			    	'<button class="btn btn-danger cart_remove" type="submit" formaction="' + contextPath + '/ajax/cart/remove">Remove</button>'
		+			  	'</div>'
		+			'</div>'
		+	  	'</div>'
		+	'</div>'
		+ '</form>'
	);
	
	var checkoutTpl = _.template(
		  '<tr>'
	    +    '<th scope="row"><img src="' + contextPath + '/images/<%- picture %>" alt="No image" width="70" height="70"/></th>'
	    +    '<td class="align-middle"><%- productName %></td>'
	    +    '<td class="align-middle"><%- categoryName %></td>'
	    +    '<td class="align-middle"><%- quantity %></td>'
	    +    '<td class="align-middle"><%- price %></td>'
	    +    '<td class="align-middle">&#8369;<%- amount %></td>'
	   	+ '</tr>'
	);
	
	var addressTpl = _.template(
		  '<div class="form-check">'
		+	'<input class="form-check-input" type="radio" name="shippingAddressId" value="<%- id %>" id="address_<%- id %>">'
	  	+	'<label class="form-check-label" for="address_<%- id %>"><%- address1 %>, <%- address2 %>, <%- country %>, <%- zipCode %></label>'
	    + '</div>'
	);
	
	var creditCardTpl = _.template(
		  '<div class="form-check">'
		+	'<input class="form-check-input" type="radio" name="creditCardId" value="<%- id %>" id="card_<%- id %>">'
	  	+	'<label class="form-check-label" for="card_<%- id %>"><%- number %></label>'
	    + '</div>'
	);
	
	return {
		invalidInputTpl: invalidInputTpl,
		selectOptionTpl: selectOptionTpl,
		lowInventoryTpl: lowInventoryTpl,
		newUserTpl: newUserTpl,
		userTpl: userTpl,
		categoryTpl: categoryTpl,
		productTpl: productTpl,
		productModalTpl: productModalTpl,
		stockHistoryTpl: stockHistoryTpl,
		orderTpl: orderTpl,
		inventoryTpl: inventoryTpl,
		inventoryModalTpl: inventoryModalTpl,
		popularProductTpl: popularProductTpl,
		categoryListTpl: categoryListTpl,
		categoryCardTpl: categoryCardTpl,
		productBeanTpl: productBeanTpl,
		cartModalTpl: cartModalTpl,
		cartTpl: cartTpl,
		checkoutTpl: checkoutTpl,
		addressTpl: addressTpl,
		creditCardTpl: creditCardTpl
	}
})();