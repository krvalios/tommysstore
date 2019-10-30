(function(){
	"use strict";
	
	var contextPath = CommonUtil.contextPath;
	var $alertEmpty = CommonUtil.$alertEmpty;
	var $alertResult = CommonUtil.$alertResult;

	var $list = $('#category_list');
	var $cards = $('#category_cards');
	var $alertEmptyProduct = $('#alert_empty_product');
	
	function displayCategories(){
		$.ajax({
			type : 'GET',
			url : contextPath + '/ajax/category',
			dataType: 'json',
			success : function(response) {
				if (response.status == 'success') {
					if(response.data.length != 0){
						$.each(response.data, function(i, category){
							$list.append(TplUtil.categoryListTpl(category));
			           	});
					}
					else {
						$('#alert_empty_category').show();
					}
				} 
				else {
					CommonUtil.showMessage($alertResult, 'danger', response.customMessage);
				}
				console.log(response);
			}
		});
	}
	
	function displayProducts(){
		$.ajax({
			type : 'GET',
			url : contextPath + '/ajax/product',
			dataType: 'json',
			success : function(response) {
				if (response.status == 'success') {
					if(response.data.length != 0){
						$.each(response.data, function(i, product){
							$cards.append(TplUtil.categoryCardTpl(product));
			           	});
					}
					else {
						$alertEmptyProduct.show();
					}
				} 
				else {
					CommonUtil.showMessage($alertResult, 'danger', response.customMessage);
				}
				console.log(response);
			}
		});
	}
	
	displayCategories();
	displayProducts();
	
	$list.on('click', '.list-group-item', function(event){
		event.preventDefault();
		CommonUtil.clearFeedbacks();
		$alertEmptyProduct.hide();
	    var id = $(this).attr('id');
	    if(id == 0) {
	    	$cards.empty();
	    	displayProducts();
	    	return;
	    }
	    $.ajax({
			type : 'GET',
			url : contextPath + '/ajax/category/' + id + '/products',
			dataType: 'json',
			success : function(response) {
				if (response.status == 'success') {
					$cards.empty();
					if(response.data.length != 0){
						$.each(response.data, function(i, product){
							$cards.append(TplUtil.categoryCardTpl(product));
			           	});
					}
					else {
						$alertEmptyProduct.show();
					}
				} 
				else {
					CommonUtil.showMessage($alertResult, 'danger', response.customMessage);
				}
			}
		});
	});
})();