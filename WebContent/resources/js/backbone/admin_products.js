(function(){
	
	"use strict";
	
	var contextPath = CommonUtil.contextPath;
	var $addModal = $('#add_modal');
	var $addForm = $('#add_form');
	var $editModal = $('#edit_modal');
	var $editForm = $('#edit_form');
	
	var productsView = new ViewUtil.ProductsView();
	$('#container').append(productsView.render().$el);
	
	var categoriesOptionView = new ViewUtil.CategoriesOptionView();
	$('#add_category_select').append(categoriesOptionView.render().$el);
	
	$('#add_btn').click(function(event){
		event.preventDefault();
		CommonUtil.clearFeedbacks();
		$addForm[0].reset();
		$addModal.modal('show');
	});
	
	$addForm.submit(function(event) {
		event.preventDefault();
	    CommonUtil.clearFeedbacks();
	    
	    var data = CommonUtil.serializeFormJson($addForm);
	    var form = new ModelUtil.Product(data);
	    
	    if(!form.isValid()){
	    	$.each(form.validationError, function(i, error){
	    		var $inputField = $('#add_' + error.field);
		    	$inputField.addClass('is-invalid');
				$inputField.after(TplUtil.invalidInputTpl({value: error.message}));
	    	});
	    	return;
	    }
	    
	    productsView.getCollection().create(form, {
	    	wait: true,
			success: function(){
				$addModal.modal('hide');
				$addForm[0].reset();
				CommonUtil.showMessage($('#alert_result'), 'success', 'Product is successfully added');
			},
			error: function(category, response){
				CommonUtil.showMessage($('#alert_result'), 'danger', response.responseText);
		}});
	});
})();