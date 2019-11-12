var CollectionUtil = {};

(function(){
	"use strict";
	
	var contextPath = CommonUtil.contextPath;
	
	CollectionUtil.Categories = Backbone.Collection.extend({
		url: contextPath + '/categories',
		model: ModelUtil.Category
	});
	
	CollectionUtil.Products = Backbone.Collection.extend({
		url: contextPath + '/products',
		model: ModelUtil.Product
	});
})();