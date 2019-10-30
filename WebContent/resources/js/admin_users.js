(function(){
	"use strict";
	
	var contextPath = CommonUtil.contextPath;
	var $alertEmpty = CommonUtil.$alertEmpty;
	var $alertResult = CommonUtil.$alertResult;

	var $table = $('#user_table');
	var $addModal = $('#add_modal');
	var $addForm = $('#add_form');

	function displayUsers(){
		$.ajax({
			type : 'GET',
			url : contextPath + '/ajax/user',
			dataType: 'json',
			success : function(response) {
				if (response.status == 'success') {
					$.each(response.data, function(i, user){
						$table.append(TplUtil.userTpl(user));
		           	});
				} 
				else {
					CommonUtil.showMessage($alertResult, 'danger', response.customMessage);
				}
				console.log(response);
			}
		});
	}
	
	displayUsers();
	
	$addForm.submit(function(event){
		event.preventDefault();
		CommonUtil.clearFeedbacks();
		$.ajax({
			type : 'POST',
			url : contextPath + '/ajax/user/add',
			dataType: 'json',
			data : $addForm.serialize(),
			success : function(response) {
				if (response.status == 'success') {
					$addModal.modal('hide');
					CommonUtil.clearFeedbacks();
					$addForm[0].reset();
					CommonUtil.showMessage($alertResult, 'success', response.customMessage);
					$table.append(TplUtil.userTpl(response.data));
				} 
				else {
					if(response.customMessage != null){
						CommonUtil.showMessage($('#alert_modal'), 'danger', response.customMessage);
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
	
	$('#filter').change(function() {
        var role = this.value;
        $.ajax({
			type : 'POST',
			url : contextPath + '/ajax/user/filter',
			dataType: 'json',
			data : {userType: role},
			success : function(response) {
				if (response.status == 'success') {
					if(response.data.length != 0){
						$alertEmpty.hide();
						$table.show();
						$table.find('tr:gt(0)').remove();
						$.each(response.data, function(i, user){
							$table.append(TplUtil.userTpl(user));
			           	});
					}
					else {
						$table.hide();
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
    });
})();