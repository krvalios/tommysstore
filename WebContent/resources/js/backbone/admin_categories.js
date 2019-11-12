(function(){
	
	"use strict";
	
	var contextPath = CommonUtil.contextPath;
	var $alertResult = CommonUtil.$alertResult;
	var $addForm = $('#add_form');
	
	var categoriesView = new ViewUtil.CategoriesView();
	$('#table_container').append(categoriesView.render().$el);
	
	$addForm.submit(function(event) {
		event.preventDefault();
	    CommonUtil.clearFeedbacks();
	    
	    var data = CommonUtil.serializeFormJson($addForm);
	    var form = new ModelUtil.Category(data);

	    if(!form.isValid()){
	    	$.each(form.validationError, function(i, error){
	    		var $inputField = $('#add_' + error.field);
		    	$inputField.addClass('is-invalid');
				$inputField.after(TplUtil.invalidInputTpl({value: error.message}));
	    	});
	    	return;
	    }
	    
	    categoriesView.getCollection().create(form, {
	    	wait: true,
			success: function(){
				$addForm[0].reset();
				CommonUtil.showMessage($alertResult, 'success', 'Category is successfully added');
			},
			error: function(category, response){
				CommonUtil.showMessage($alertResult, 'danger', response.responseText);
		}});
	});
})();