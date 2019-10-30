(function(){
	"use strict";
	
	var contextPath = CommonUtil.contextPath;
	var $alertEmpty = CommonUtil.$alertEmpty;
	var $alertResult = CommonUtil.$alertResult;

	var $cards = $('#product_cards');
	var $searchCategory = $('#search_category');
	var $searchName = $('#search_name');
	var $addModal = $('#add_modal');
	var $addForm = $('#add_form');
	
	function displayProducts(){
		$.ajax({
			type : 'GET',
			url : contextPath + '/ajax/product/bean',
			dataType: 'json',
			success : function(response) {
				if (response.status == 'success') {
					$.each(response.data, function(i, bean){
						$cards.append(TplUtil.productBeanTpl(bean));
		           	});
				} 
				else {
					CommonUtil.showMessage($alertResult, 'danger', response.customMessage);
				}
				console.log(response);
			}
		});
	}
	
	function displayCategoryOptions(){
		$.ajax({
			type : 'GET',
			url : contextPath + '/ajax/category',
			dataType: 'json',
			success : function(response) {
				if (response.status == 'success') {
					if(response.data.length != 0){
						$.each(response.data, function(i, category){
							$searchCategory.append(TplUtil.selectOptionTpl(category));
			           	});
					}
					else {
						$searchCategory.addClass('is-invalid');
		  	       		$searchCategory.after(TplUtil.invalidInputTpl({value: 'No available category'}));
					}
				} 
				else {
					CommonUtil.showMessage($alertResult, 'danger', response.customMessage);
				}
				console.log(response);
			}
		});
	}
	
	function searchProduct(){
		$alertEmpty.hide();
		var name = $searchName.val().toLowerCase();
		var categoryId = $searchCategory.val();
		$.ajax({
			type : 'POST',
			url : contextPath + '/ajax/product/search',
			dataType: 'json',
			data : { productName: name, categoryId: categoryId},
			success : function(response) {
				if (response.status == 'success') {
					if(response.data.length != 0){
						$alertResult.hide();
						$cards.empty();
						$.each(response.data, function(i, bean){
							$cards.append(TplUtil.productBeanTpl(bean));
			           	});
					}
					else {
						$cards.empty();
						$alertEmpty.show();
					}
				} 
				else {
					if(response.customMessage != null){
						CommonUtil.showMessage($alertResult, 'danger', response.customMessage);
					}
				}
				console.log(response);
			}
		});
	}
	
	displayProducts();
	displayCategoryOptions();
	
	$searchName.on('keyup', searchProduct);
	$searchCategory.change(searchProduct);
	
	$cards.on('click', '.cart_add', function(event){
		event.preventDefault();
		CommonUtil.clearFeedbacks();
		$addForm[0].reset();
		var $card = $(this).closest('.card');
	    var id = $card.attr('id');
	    $.ajax({
			type : 'GET',
			url : contextPath + '/ajax/product/bean/' + id,
			dataType: 'json',
			success : function(response) {
				if (response.status == 'success') {
				    $addModal.modal('show');
				    $('.modal-content').html(TplUtil.cartModalTpl(response.data));
				} 
				else {
					CommonUtil.showMessage($alertResult, 'danger', response.customMessage);
				}
			}
		});
	});
	
	$addForm.submit(function(event){
		event.preventDefault();
		CommonUtil.clearFeedbacks();
		$.ajax({
			type : 'POST',
			url : contextPath + '/ajax/cart/add',
			dataType: 'json',
			data : $addForm.serialize(),
			success : function(response) {
				if (response.status == 'success') {
					$addModal.modal('hide');
					$addForm[0].reset();
					CommonUtil.showMessage($alertResult, 'success', response.customMessage);
				} 
				else {
					if(response.customMessage != null){
						CommonUtil.showMessage($alertModal, 'danger', response.customMessage);
					}
					$.each(response.errorMessages, function(key, value){
						var $inputField = $('#' + key);
						$inputField.addClass('is-invalid');
						$inputField.after(TplUtil.invalidInputTpl({value: value}));
		           	});
				}
				console.log(response);
			}
		});
	});
})();