(function(){
	"use strict";
	
	var contextPath = CommonUtil.contextPath;
	var $alertEmpty = CommonUtil.$alertEmpty;
	var $alertResult = CommonUtil.$alertResult;

	var $container = $('#container_cart');
	var $cards = $('#cart_cards');
	
	function displayCart(){
		$.ajax({
			type : 'GET',
			url : contextPath + '/ajax/cart',
			dataType: 'json',
			success : function(response) {
				if (response.status == 'success') {
					if(response.data.length != 0){
						$container.show();
						$.each(response.data, function(i, cart){
							$cards.append(TplUtil.cartTpl(cart));
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
		
	displayCart();
	
	$container.on('click', '#clear_cart', function(event){
		event.preventDefault();
		$.ajax({
			type : 'GET',
			url : contextPath + '/ajax/cart/clear',
			dataType: 'json',
			success : function(response) {
				if (response.status == 'success') {
					CommonUtil.showMessage($alertResult, 'success', response.customMessage);
					$container.hide();
					$alertEmpty.show();
				} 
				else {
					CommonUtil.showMessage($alertResult, 'danger', response.customMessage);
				}
				console.log(response);
			}
		});
	});
	
	$cards.on('click', '.cart_edit', function(event){
		event.preventDefault();
		CommonUtil.clearFeedbacks();
		var $card = $(this).closest('.card');
	    var id = $card.attr('id');
	    $.ajax({
			type : 'POST',
			url : contextPath + '/ajax/cart/edit',
			dataType: 'json',
			data : $('#cart_form').serialize(),
			success : function(response) {
				if (response.status == 'success') {
					$('.input-group-text').html('&#8369;' + response.data.amount);
					$('#quantity').val(response.data.quantity);
					CommonUtil.showMessage($alertResult, 'success', response.customMessage);
				} 
				else {
					if(response.customMessage != null){
						CommonUtil.showMessage($alertResult, 'danger', response.customMessage);
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
	
	$cards.on('click', '.cart_remove', function(event){
		event.preventDefault();
		var $card = $(this).closest('.card');
	    var id = $card.attr('id');
	    $.ajax({
			type : 'POST',
			url : contextPath + '/ajax/cart/remove',
			dataType: 'json',
			data : {productId: id},
			success : function(response) {
				if (response.status == 'success') {
					CommonUtil.showMessage($alertResult, 'success', response.customMessage);
					$container.hide();
					$cards.empty();
					displayCart();
				} 
				else {
					CommonUtil.showMessage($alertResult, 'danger', response.customMessage);
				}
				console.log(response);
			}
		});
	});
})();