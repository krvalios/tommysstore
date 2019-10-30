(function(){
	"use strict";
	
	var contextPath = CommonUtil.contextPath;
	var $alertEmpty = CommonUtil.$alertEmpty;
	var $alertResult = CommonUtil.$alertResult;

	var $loginForm = $('#login_form');
	var $registerForm = $('#register_form');
	
	$loginForm.submit(function(event) {
		event.preventDefault();
		CommonUtil.clearFeedbacks();
		$.ajax({
			type : 'POST',
			url : contextPath + '/ajax/user/login',
			dataType: 'json',
			data : $loginForm.serialize(),
			success : function(response) {
				if (response.status == 'success') {
					window.location.href = response.location;
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
	
	$registerForm.submit(function(event) {
		event.preventDefault();
		CommonUtil.clearFeedbacks();
		$.ajax({
			type : 'POST',
			url : contextPath + '/ajax/user/register',
			dataType: 'json',
			data : $registerForm.serialize(),
			success : function(response) {
				if (response.status == 'success') {
					window.location.href = response.location;
				} 
				else {
					if(response.customMessage != null){
						CommonUtil.showMessage('danger', response.customMessage);
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