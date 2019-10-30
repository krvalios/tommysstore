(function(){
	"use strict";
	
	var contextPath = CommonUtil.contextPath;
	var $alertEmpty = CommonUtil.$alertEmpty;
	var $alertResult = CommonUtil.$alertResult;

	var $alert = $('#alert');
	var $modal = $('#modal');
	var $alertAddress = $('#alert_address');
	var $addressCardBody = $('#address_card_body');
	var $creditCardBody = $('#credit_card_body');
	var $addressForm = $('#address_form');
	var $creditCardForm = $('#credit_card_form');
	
	function displayCart(){
		$.ajax({
			type : 'GET',
			url : contextPath + '/ajax/cart/checkout',
			dataType: 'json',
			success : function(response) {
				if (response.status == 'success') {
					if(response.data.cartItems.length != 0){
						$.each(response.data.cartItems, function(i, cartItem){
							$('#checkout_table').append(TplUtil.checkoutTpl(cartItem));
			           	});
						$('#total_price').append(parseFloat(response.data.totalPrice).toFixed(2));
					}
					else {
						$('.modal-title').text('Checkout Invalid');
						$alert.attr('class', 'alert alert-danger');
						$alert.text('Your cart is empty, add a product first before checkout');
						$modal.modal('show');
					}
				} 
				else {
					CommonUtil.showMessage($alertResult, 'danger', response.customMessage);
				}
				console.log(response);
			}
		});
	}
	
	function displayAddresses(){
		$.ajax({
			type : 'GET',
			url : contextPath + '/ajax/user/address',
			dataType: 'json',
			success : function(response) {
				if (response.status == 'success') {
					if(response.data.length != 0){
						$.each(response.data, function(i, address){
							$addressCardBody.append(TplUtil.addressTpl(address));
			           	});
					}
					else {
						$alertAddress.show();
					}
				} 
				else {
					CommonUtil.showMessage($alertResult, 'danger', response.customMessage);
				}
				console.log(response);
			}
		});
	}
	
	function displayCreditCards(){
		$.ajax({
			type : 'GET',
			url : contextPath + '/ajax/user/creditcard',
			dataType: 'json',
			success : function(response) {
				if (response.status == 'success') {
					$.each(response.data, function(i, creditCard){
						$creditCardBody.append(TplUtil.creditCardTpl(creditCard));
		           	});
				} 
				else {
					CommonUtil.showMessage($alertResult, 'danger', response.customMessage);
				}
				console.log(response);
			}
		});
	}
	
	function displayCountryOptions(){
		var $countrySelect = $('.country_select');
		$.ajax({
			type : 'GET',
			url : contextPath + '/ajax/user/address/country',
			dataType: 'json',
			success : function(response) {
				if (response.status == 'success') {
					if(response.data.length != 0){
						$.each(response.data, function(i, country){
							$countrySelect.append(TplUtil.selectOptionTpl(country));
			           	});
					}
					else {
						$countrySelect.addClass('is-invalid');
						$countrySelect.after(TplUtil.invalidInputTpl({value: 'Country is required'}));
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
	displayAddresses();
	displayCreditCards();
	displayCountryOptions();
	
	$('#checkout_form').submit(function(event){
		event.preventDefault();
		$.ajax({
			type : 'POST',
			url : contextPath + '/ajax/cart/placecheckout',
			dataType: 'json',
			data : $('#checkout_form').serialize(),
			success : function(response) {
				if (response.status == 'success') {
					$('.modal-title').text('Order Success');
					$alert.attr('class', 'alert alert-success');
					$alert.text('All items are successfully ordered');
					$modal.modal('show');
				} 
				else {
					var message = '';
					if(response.customMessage != null){
						message = message.concat(response.customMessage);
					}
					$.each(response.errorMessages, function(key, value){
						message = message.concat(' ', value);
		           	});
					CommonUtil.showMessage($alertResult, 'danger', message);
				}
				console.log(response);
			}
		});
	});
	
	$addressForm.submit(function(event) {
		event.preventDefault();
		CommonUtil.clearFeedbacks();
		$.ajax({
			type : 'POST',
			url : contextPath + '/ajax/user/address/add',
			dataType: 'json',
			data : $addressForm.serialize(),
			success : function(response) {
				if (response.status == 'success') {
					$alertAddress.hide();
					$('#address_modal').modal('hide');
					CommonUtil.showMessage($alertResult, 'success', response.customMessage);
					$addressForm[0].reset();
					$addressCardBody.append(TplUtil.addressTpl(response.data));
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
	
	$creditCardForm.submit(function(event) {
		event.preventDefault();
		$('.invalid-feedback').remove();
		$('.is-invalid').addClass('').removeClass('is-invalid');
		$.ajax({
			type : 'POST',
			url : contextPath + '/ajax/user/creditcard/add',
			dataType: 'json',
			data : $creditCardForm.serialize(),
			success : function(response) {
				if (response.status == 'success') {
					$('#credit_card_modal').modal('hide');
					CommonUtil.showMessage($alertResult, 'success', response.customMessage);
					$creditCardForm[0].reset();
					$creditCardBody.append(TplUtil.creditCardTpl(response.data));
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