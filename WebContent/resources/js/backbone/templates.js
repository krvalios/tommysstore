var TplUtil = {};
	
(function(){
	"use strict";
	
	var contextPath = CommonUtil.contextPath;
	
	TplUtil.invalidInputTpl = _.template(
		'<div class="invalid-feedback"><%= value %></div>'
	);
	
	TplUtil.selectOptionTpl = _.template(
		'<option value="<%= id %>"><%= name %></option>'
	);

	TplUtil.categoryTpl = _.template(
		  '<th scope="row"><%= categoryId %></th>'
		+ '<td><%= name %></td>'
	    + '<td>'
	    +	'<p><button class="btn btn-success" id="edit">Edit</button> '
		+	'<button type="button" class="btn btn-danger" id="remove">Remove</button></p>'
		+ '</td>'
	);
	
	TplUtil.categoriesTpl = _.template(
		 '<thead>' 
		+	'<tr>'
		+		'<th scope="col">Category Id</th>'
		+		'<th scope="col">Category Name</th>'
		+		'<th>Actions</th>'
		+	'</tr>'
		+ '</thead>'
		+ '<tbody>'
		+ '</tbody>'
	);
	
	TplUtil.editCategoryTpl = _.template(
		  '<h5 class="card-header" align="center">Edit Category</h5>'
	  	+ '<div class="card-body">'
	  	+	'<form id="edit_form">'
		+		'<input type="hidden" name="id" class="form-control" id="id"/>'
		+		'<div class="form-group">'
		+	  		'<label name="name">Category Name</label>'
		+		    '<input name="name" class="form-control" id="name"/>'
		+	  	'</div>'
		+	  	'<div align="center">'
		+	  		'<button type="submit" class="btn btn-primary" id="save">EDIT CATEGORY</button> '
		+			'<button class="btn btn-danger" id="cancel">CANCEL</button>'
		+		'</div>'
		+	'</form>'
	  	+ '</div>'
	);
	
	TplUtil.productTpl = _.template(
		  '<div class="row no-gutters d-flex h-100">'
		+	'<div class="col-md-5">'
		+		'<img src="' + contextPath + '/images/<%= picture %>" class="card-img-top" alt="No image" height="100%">'
		+	'</div>'
		+	'<div class="col-md-7 justify-content-center align-self-center">'
		+		'<div class="card-body">'
		+	  		'<h5 class="card-title"><strong><%= name %></strong></h5>'
		+			'<h6 class="card-subtitle mb-1 text-muted"><%= categoryName %></h6>'
		+			'<p class="card-text">'
		+				'<%= productId %><br>'
		+				'<%= price %> pesos <br>'
		+			'</p>'
		+		'</div>'
		+	'</div>'
		+ '</div>'
		+ '<div class="card-footer" align="center">'
		+	'<button type="button" class="btn btn-success btn-sm" id="edit">Edit</button> '
		+	'<button type="button" class="btn btn-danger btn-sm" id="remove">Remove</button> '
		+	'<button type="button" class="btn btn-primary btn-sm" id="stock_history">Stock History</button> '
		+	'<button type="button" class="btn btn-info btn-sm" id="orders">Orders</button>'
		+ '</div>'
	);
	
	TplUtil.productModalTpl = _.template(
		  '<input type="hidden" name="id" class="form-control" id="edit_id" value="<%= id %>"/>'
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
	  	+				'<h6 class="card-header"><strong><%= name %></strong></h6>'
		+				'<img src="' + contextPath + '/images/<%= picture %>" class="card-img-top px-5" alt="No image" height="180">'
		+				'<div class="card-body">'
		+				    '<h6 class="card-title"><strong><%= productId %></strong></h6>'
		+				   	'<h6 class="card-subtitle mb-2 text-muted"><%= categoryName %></h6>'
		+				    '<p class="card-text"><%= price %> pesos</p>'
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
		+		  		'<label for="name">Product Name</label>'
		+			    '<input type="text" name="name" class="form-control" id="edit_name" value="<%= name %>"/>'
		+		  	'</div>'
		+		  	'<div class="form-group" id="edit_category_select">'
		+		  		'<label for="categoryId">Category</label>'
		+		  	'</div>'
		+		  	'<div class="form-group">'
		+		  		'<label for="price">Price</label>'
		+			    '<input type="text" name="price" class="form-control" id="edit_price" value="<%= price %>"/>'
		+		  	'</div>'
		+		  	'<div class="form-group">'
		+		    	'<label id="picture">Product Picture</label>'
		+		    	'<input type="file" class="form-control-file" id="edit_picture">'
		+		  	'</div>'
		+	  	'</div>'
		+  	'</div>'
	  	+ '</div>'
	  	+ '<div class="modal-footer">'
	    +	'<button type="button" class="btn btn-primary" id="save">Edit Product</button>'
	    +	'<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>'
	  	+ '</div>'
	);
})();