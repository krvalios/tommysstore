(function(){
	"use strict";
	
	var contextPath = CommonUtil.contextPath;
	var $alertEmpty = CommonUtil.$alertEmpty;
	var $alertResult = CommonUtil.$alertResult;

	var id = localStorage.getItem('productId');
	var $table = $('#order_table');
	
	function displayOrders(){
		$.ajax({
			type : 'GET',
			url : contextPath + '/ajax/product/orders/' + id,
			dataType: 'json',
			success : function(response) {
				if (response.status == 'success') {
					if(response.data.length != 0){
						$table.show();
						$alertEmpty.hide();
						$.each(response.data, function(i, orderItem){
							$table.append(TplUtil.orderTpl(orderItem));
			           	});
					}
					else {
						$table.hide();
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
	
	function displayProduct(){
	    $.ajax({
			type : 'GET',
			url : contextPath + '/ajax/product/' + id,
			dataType: 'json',
			success : function(response) {
				if (response.status == 'success') {
				    $('.container h3').append(response.data.name);
				    $('.container h5').append(response.data.categoryName);
				} 
				else {
					CommonUtil.showMessage($alertResult, 'danger', response.customMessage);
				}
			}
		});
	}
		
	displayOrders();
	displayProduct();
		
})();