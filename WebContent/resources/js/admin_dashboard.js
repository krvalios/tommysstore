(function(){
	"use strict";
	
	var contextPath = CommonUtil.contextPath;
	var $alertEmpty = CommonUtil.$alertEmpty;
	var $alertResult = CommonUtil.$alertResult;
	
	var $alertLowStock = $('#alert_low_stock');
	var $alertNewUser = $('#alert_new_user');
	
	function displayInventoryByStock(){
		$.ajax({
			type : 'GET',
			url : contextPath + '/ajax/inventory/low',
			dataType: 'json',
			success : function(response) {
				if (response.status == 'success') {
					if(response.data.length != 0){
						$alertLowStock.hide();
						$.each(response.data, function(i, inventory){
							$('#low_inventory_cards').append(TplUtil.lowInventoryTpl(inventory));
			           	});
					}
					else {
						$alertLowStock.show();
					}
				} 
				else {
					CommonUtil.showMessage($alertModal, 'danger', response.customMessage);
				}
				console.log(response);
			}
		});
	}
	
	function displayNewUsers(){
		$.ajax({
			type : 'GET',
			url : contextPath + '/ajax/user/new',
			dataType: 'json',
			success : function(response) {
				if (response.status == 'success') {
					if(response.data.length != 0){
						$alertNewUser.hide();
						$.each(response.data, function(i, user){
							$('#new_user_card').append(TplUtil.newUserTpl(user));
			           	});
					}
					else {
						$alertNewUser.show();
					}
				} 
				else {
					CommonUtil.showMessage($alertModal, 'danger', response.customMessage);
				}
				console.log(response);
			}
		});
	}
	
	displayInventoryByStock();
	displayNewUsers();
	
})();