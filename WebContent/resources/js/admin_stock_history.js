(function(){
	"use strict";
	
	var contextPath = CommonUtil.contextPath;
	var $alertEmpty = CommonUtil.$alertEmpty;
	var $alertResult = CommonUtil.$alertResult;

	var id = localStorage.getItem('productId');
	var $table = $('#history_table');
	
	function displayStockHistory(){
		$.ajax({
			type : 'GET',
			url : contextPath + '/ajax/product/stockhistory/' + id,
			dataType: 'json',
			success : function(response) {
				if (response.status == 'success') {
					if(response.data.length != 0){
						$table.show();
						$alertEmpty.hide();
						$.each(response.data, function(i, history){
							$table.append(TplUtil.stockHistoryTpl(history));
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
	
	displayStockHistory();
	displayProduct();
	
})();