var CommonUtil = (function(){
	"use strict";
	
	var contextPath = $(document.body).attr('data-context-path');
	var $alertEmpty = $('#alert_empty');
	var $alertResult = $('#alert_result');
	
	var showMessage = function(selector, type, message){
		selector.attr('class', 'alert alert-' + type);
		selector.find('strong').text(message);
		selector.show();
	};
	
	var clearFeedbacks = function(){
		$('.invalid-feedback').remove();
		$('.is-invalid').addClass('').removeClass('is-invalid');
		$('.alert').hide();
	};
	
	return {
		contextPath: contextPath,
		$alertEmpty: $alertEmpty,
		$alertResult: $alertResult,
		showMessage: showMessage,
		clearFeedbacks: clearFeedbacks
	}
})();

(function(){
	"use strict";
	
	$('.close').on('click', function(event){
		event.preventDefault();
	    $('.alert').hide();
	});
	
	$.ajaxSetup({
        error: function (x, status, error) {
        	alert('Status: ' + status + 'Error: ' + error);
        	window.location = 'error.jsp';
        }
    });
	
})();