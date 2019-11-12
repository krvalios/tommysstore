var CommonUtil = {};
	
(function(){
	"use strict";
	
	CommonUtil.contextPath = $(document.body).attr('data-context-path');
	CommonUtil.$alertEmpty = $('#alert_empty');
	CommonUtil.$alertResult = $('#alert_result');
	
	CommonUtil.showMessage = function(selector, type, message){
		selector.attr('class', 'alert alert-' + type);
		selector.find('strong').text(message);
		selector.show();
	};
	
	CommonUtil.clearFeedbacks = function(){
		$('.invalid-feedback').remove();
		$('.is-invalid').addClass('').removeClass('is-invalid');
		$('.alert').hide();
	};
	
	CommonUtil.serializeFormJson = function(form) {

        var o = {};
        var a = form.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
    
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