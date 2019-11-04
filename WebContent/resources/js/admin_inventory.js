(function(){
	"use strict";
	
	var contextPath = CommonUtil.contextPath;
	var $alertEmpty = CommonUtil.$alertEmpty;
	var $alertResult = CommonUtil.$alertResult;
	
	var $table = $('#inventory_table');
	var $restockModal = $('#restock_modal');
	var $restockForm = $('#restock_form');

	function displayInventory(){
		$.ajax({
			type : 'GET',
			url : contextPath + '/ajax/inventory',
			dataType: 'json',
			success : function(response) {
				if (response.status == 'success') {
					if(response.data.length != 0){
						$alertEmpty.hide();
						$table.show();
						$.each(response.data, function(i, inventory){
							$table.append(TplUtil.inventoryTpl(inventory));
			           	});
					}
					else {
						$alertEmpty.show();
						$table.hide();
					}
				} 
				else {
					CommonUtil.showMessage($alertResult, 'danger', response.customMessage);
				}
				console.log(response);
			}
		});
	}
	
	displayInventory();
	
	$table.on('click', '.inventory_add', function(event){
		event.preventDefault();
		CommonUtil.clearFeedbacks();
		$restockForm[0].reset();
	    var $row = $(this).closest('tr');
	    var id = $row.attr('id');
	    $.ajax({
			type : 'GET',
			url : contextPath + '/ajax/inventory/' + id,
			dataType: 'json',
			success : function(response) {
				if (response.status == 'success') {
				    $restockModal.modal('show');
				    $('.modal-content').html(TplUtil.inventoryModalTpl(response.data));
				} 
				else {
					CommonUtil.clearFeedbacks();
					CommonUtil.showMessage($alertResult, 'danger', response.customMessage);
				}
			}
		});
	});
	
	$restockForm.submit(function(event){
		event.preventDefault();
		CommonUtil.clearFeedbacks();
		$.ajax({
			type : 'POST',
			url : contextPath + '/ajax/inventory/restock',
			dataType: 'json',
			data : $restockForm.serialize(),
			success : function(response) {
				if (response.status == 'success') {
					$restockModal.modal('hide');
					$restockForm[0].reset();
					CommonUtil.showMessage($alertResult, 'success', response.customMessage);
					var $row = $('#' + response.data.id);
				    var $stocks = $row.find('td').eq(3);
				    $stocks.text(response.data.stocks + ' stocks');
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
})();