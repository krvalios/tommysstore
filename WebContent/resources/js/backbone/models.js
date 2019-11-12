var ModelUtil = {};

(function(){
	"use strict";
	
	ModelUtil.Category = Backbone.Model.extend({
		validate: function(attributes) {
			var errors = [];
		    if (attributes.name === '') {
		    	errors.push({field: 'name', message: 'Category name is required'});
		    }
		    if(errors.length != 0){
		    	return errors;
		    }
		}
	});
	
	ModelUtil.Product = Backbone.Model.extend({
		validate: function(attributes) {
			var errors = [];
		    if (attributes.name === '') {
		    	errors.push({field: 'name', message: 'Product name is required'});
		    }
		    if (attributes.productId === null) {
		    	errors.push({field: 'productId', message: 'Category is required'});
		    }
		    if (attributes.price === '') {
		    	errors.push({field: 'price', message: 'Price is required'});
		    }
		    if(errors.length != 0){
		    	return errors;
		    }
		}
	});
})();