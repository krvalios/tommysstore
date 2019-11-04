(function(){
	"use strict";
	
	var contextPath = CommonUtil.contextPath;
	var $alertEmpty = CommonUtil.$alertEmpty;
	var $alertResult = CommonUtil.$alertResult;
	
	var $table = $('#category_table');
	var $addCard = $('#add_card');
	var $editCard = $('#edit_card');
	var $addForm = $('#add_form');
	
	function displayCategories(){
		$.ajax({
			type : 'GET',
			url : contextPath + '/ajax/category',
			dataType: 'json',
			success : function(response) {
				if (response.status == 'success') {
					if(response.data.length != 0){
						$alertEmpty.hide();
						$table.show();
						$.each(response.data, function(i, category){
							$table.append(TplUtil.categoryTpl(category));
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
	
	function cardReset(){
		CommonUtil.clearFeedbacks();
		$editCard.hide();
		$addCard.show();
	}
	
	displayCategories();
	
	$table.on('click', '.category_edit', function(event){
		event.preventDefault();
	    var $row = $(this).closest('tr');
	    var id = $row.attr('id');
	    CommonUtil.clearFeedbacks();
		$addForm[0].reset();
	    $.ajax({
			type : 'GET',
			url : contextPath + '/ajax/category/' + id,
			dataType: 'json',
			success : function(response) {
				if (response.status == 'success') {
					$addCard.hide();
					$editCard.show();
				    $('#edit_id').val(response.data.id);
				    $('#edit_name').val(response.data.name);
				} 
				else {
					CommonUtil.showMessage($alertResult, 'danger', response.customMessage);
				}
			}
		});
	});
	
	$('#edit_cancel').click(cardReset());
	
	$addForm.submit(function(event) {
		event.preventDefault();
	    CommonUtil.clearFeedbacks();
		$.ajax({
			type : 'POST',
			url : contextPath + '/ajax/category/add',
			dataType: 'json',
			data : $addForm.serialize(),
			success : function(response) {
				if (response.status == 'success') {
					$alertEmpty.hide();
					$table.show();
					$addForm[0].reset();
					CommonUtil.showMessage($alertResult, 'success', response.customMessage);
					$table.append(TplUtil.categoryTpl(response.data));
				} 
				else {
					if(response.customMessage != null){
						CommonUtil.showMessage($alertResult, 'danger', response.customMessage);
					}
					$.each(response.errorMessages, function(key, value){
						var $inputField = $('#add_' + key);
						$inputField.addClass('is-invalid');
						$inputField.after(TplUtil.invalidInputTpl({value: value}));
		           	});
				}
				console.log(response);
			}
		});
	});
	
	$('#edit_form').submit(function(event) {
		event.preventDefault();
		CommonUtil.clearFeedbacks();
		$.ajax({
			type : 'POST',
			url : contextPath + '/ajax/category/edit',
			dataType: 'json',
			data : $('#edit_form').serialize(),
			success : function(response) {
				if (response.status == 'success') {
					cardReset();
					CommonUtil.showMessage($alertResult, 'success', response.customMessage);
					var $row = $('#' + response.data.id);
				    var $name = $row.find('td').eq(0);
				    $name.text(response.data.name);
				} 
				else {
					if(response.customMessage != null){
						CommonUtil.showMessage($alertResult, 'danger', response.customMessage);
					}
					$.each(response.errorMessages, function(key, value){
						var $inputField = $('#edit_' + key);
						$inputField.addClass('is-invalid');
						$inputField.after(TplUtil.invalidInputTpl({value: value}));
		           	});
				}
				console.log(response);
			}
		});
	});
	
	$table.on('click', '.category_remove', function(event){
		event.preventDefault();
		CommonUtil.clearFeedbacks();
		$addForm[0].reset();
		var $row = $(this).closest('tr');
	    var id = $row.attr('id');
	    var name = $row.find('td:eq(0)').text();
		$.ajax({
			type : 'POST',
			url : contextPath + '/ajax/category/remove',
			dataType: 'json',
			data : {id: id, name: name},
			success : function(response) {
				if (response.status == 'success') {
					cardReset();
					CommonUtil.showMessage($alertResult, 'success', response.customMessage);
					$('#' + response.data.id).remove();
				} 
				else {
					cardReset();
					CommonUtil.showMessage($alertResult, 'danger', response.customMessage);
				}
				console.log(response);
			}
		});
	});
})();