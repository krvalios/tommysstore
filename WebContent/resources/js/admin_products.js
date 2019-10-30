(function(){
	"use strict";
	
	var contextPath = CommonUtil.contextPath;
	var $alertEmpty = CommonUtil.$alertEmpty;
	var $alertResult = CommonUtil.$alertResult;

	var $cards = $('#product_cards');
	var $alertAdd = $('#alert_add');
	var $alertEdit = $('#alert_edit');
	var $addModal = $('#add_modal');
	var $addForm = $('#add_form');
	var $editModal = $('#edit_modal');
	var $editForm = $('#edit_form');
	var categoryOptions = [];
	
	function displayProducts(){
		$.ajax({
			type : 'GET',
			url : contextPath + '/ajax/product',
			dataType: 'json',
			success : function(response) {
				if (response.status == 'success') {
					if(response.data.length != 0){
						$alertEmpty.hide();
						$.each(response.data, function(i, product){
							$cards.append(TplUtil.productTpl(product));
			           	});
					}
					else {
						$alertEmpty.show();
					}
				} 
				else {
					CommonUtil.showMessage($alertResult, 'danger', response.customMessage);
				}
				console.log(response);
			}
		});
	}
	
	/*function displayCategoryOptions(){
		var $categorySelect = $('.category_select');
		$.ajax({
			type : 'GET',
			url : contextPath + '/ajax/category',
			dataType: 'json',
			success : function(response) {
				if (response.status == 'success') {
					if(response.data.length != 0){
						$.each(response.data, function(i, category){
							$categorySelect.append(TplUtil.selectOptionTpl(category));
			           	});
					}
					else {
						$categorySelect.addClass('is-invalid');
						$categorySelect.after(TplUtil.invalidInputTpl({value: 'Category is required'}));
					}
				} 
				else {
					CommonUtil.showMessage($alertResult, 'danger', response.customMessage);
				}
				console.log(response);
			}
		});
	}*/
	
	function displayCategoryOptions(){
		
		$.ajax({
			type : 'GET',
			url : contextPath + '/ajax/category',
			dataType: 'json',
			success : function(response) {
				if (response.status == 'success') {
					if(response.data.length != 0){
						$.each(response.data, function(i, category){
							categoryOptions.push(category);
			           	});
					}
					else {
						$categorySelect.addClass('is-invalid');
						$categorySelect.after(TplUtil.invalidInputTpl({value: 'Category is required'}));
					}
				} 
				else {
					CommonUtil.showMessage($alertResult, 'danger', response.customMessage);
				}
				console.log(response);
			}
		});
	}
	
	displayProducts();
	displayCategoryOptions();
	
	$('#add_btn').on('click', function(event){
		event.preventDefault();
		CommonUtil.clearFeedbacks();
		$addForm[0].reset();
		$addModal.modal('show');
	});
	
	$addForm.submit(function(event){
		event.preventDefault();
		CommonUtil.clearFeedbacks();
		var form = $addForm[0];
		var data = new FormData(form);
		$.ajax({
			type : 'POST',
			enctype: 'multipart/form-data',
			url : contextPath + '/ajax/product/add',
			dataType: 'json',
			data : data,
			contentType: false,
			processData: false,
            cache: false,
			success : function(response) {
				if (response.status == 'success') {
					$addModal.modal('hide');
					CommonUtil.clearFeedbacks();
					CommonUtil.showMessage($alertResult, 'success', response.customMessage);
					$cards.append(TplUtil.productTpl(response.data));
				} 
				else {
					if(response.customMessage != null){
						CommonUtil.showMessage($alertAdd, 'danger', response.customMessage);
					}
					$.each(response.errorMessages, function(key, value){
						var $inputField = $('#add_' + key);
						$inputField.addClass('is-invalid');
						$inputField.after(TplUtil.invalidInputTpl({value: value}));
		           	});
				}
				console.log(response);
			}
		});
	});
	
	$cards.on('click', '.product_edit', function(event){
		event.preventDefault();
		CommonUtil.clearFeedbacks();
	    var $card = $(this).closest('.card');
	    var id = $card.attr('id');
	    $.ajax({
			type : 'GET',
			url : contextPath + '/ajax/product/' + id,
			dataType: 'json',
			success : function(response) {
				if (response.status == 'success') {
					$editModal.modal('show');
				    $('#edit_modal_content').html(TplUtil.productModalTpl(response.data));
				    $.each(categoryOptions, function(i, category){
				    	$('#edit_categoryId').append(TplUtil.selectOptionTpl(category));
		           	});
				    $('#edit_categoryId').val(response.data.categoryId);
//				    displayCategoryOptions();
//				    $('option[value=' + response.data.categoryId + ']', newOption).attr('selected', 'selected');
//				    $('#edit_categoryId').val(0);
//				    alert('[' + response.data.categoryId + ']');
				} 
				else {
					CommonUtil.showMessage($alertResult, 'danger', response.customMessage);
				}
			}
		});
	});
	
	$editForm.submit(function(event) {
		event.preventDefault();
		CommonUtil.clearFeedbacks();
		var form = $editForm[0];
		var data = new FormData(form);
		$.ajax({
			type : 'POST',
			url : contextPath + '/ajax/product/edit',
			dataType: 'json',
			data : data,
			contentType: false,
			processData: false,
            cache: false,
			success : function(response) {
				if (response.status == 'success') {
					$editModal.modal('hide');
					CommonUtil.showMessage($alertResult, 'success', response.customMessage);
					$('#' + response.data.id).remove();
					$cards.append(TplUtil.productTpl(response.data));
					formReset();
				} 
				else {
					if(response.customMessage != null){
						CommonUtil.showMessage($alertEdit, 'danger', response.customMessage);
					}
					$.each(response.errorMessages, function(key, value){
						var $inputField = $('#edit_' + key);
						$inputField.addClass('is-invalid');
						$inputField.after(TplUtil.invalidInputTpl({value: value}));
		           	});
				}
				console.log(response);
			}
		});
	});
	
	$cards.on('click', '.product_remove', function(event){
		event.preventDefault();
		CommonUtil.clearFeedbacks();
		var $card = $(this).closest('.card');
	    var id = $card.attr('id');
		$.ajax({
			type : 'POST',
			url : contextPath + '/ajax/product/remove',
			dataType: 'json',
			data : {id: id},
			success : function(response) {
				if (response.status == 'success') {
					CommonUtil.showMessage($alertResult, 'success', response.customMessage);
					$('#' + response.data.id).remove();
				}
				else {
					CommonUtil.showMessage($alertResult, 'danger', response.customMessage);
				}
				console.log(response);
			}
		});
	});
	
	$cards.on('click', '.product_history', function(event){
		event.preventDefault();
	    var $card = $(this).closest('.card');
	    var id = $card.attr('id');
	    localStorage.setItem('productId', id);
	    window.location.href = contextPath + '/admin/product/stockhistory';
	});
	
	$cards.on('click', '.product_orders', function(event){
		event.preventDefault();
	    var $card = $(this).closest('.card');
	    var id = $card.attr('id');
	    localStorage.setItem('productId', id);
	    window.location.href = contextPath + '/admin/product/orders';
	});
})();