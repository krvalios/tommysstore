(function(){
	"use strict";
	
	var contextPath = CommonUtil.contextPath;
	var $alertEmpty = CommonUtil.$alertEmpty;
	var $alertResult = CommonUtil.$alertResult;

	function displayPopularProducts(){
		$.ajax({
			type : 'GET',
			url : contextPath + '/ajax/product/popular',
			dataType: 'json',
			success : function(response) {
				if (response.status == 'success') {
					if(response.data.length != 0){
						$.each(response.data, function(i, product){
							$('.popular_product_cards').append(TplUtil.popularProductTpl(product));
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
	
	displayPopularProducts();
	
})();