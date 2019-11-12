var ViewUtil = {};

(function(){
	"use strict";
	
	var $alertEmpty = CommonUtil.$alertEmpty;
	var $alertResult = CommonUtil.$alertResult;
	
	ViewUtil.CategoryView = Backbone.View.extend({
		tagName: 'tr',
		template: TplUtil.categoryTpl,

		events: {
			'click button#edit': 'edit',
			'click button#remove': 'delete',
		},

		initialize: function() {
			this.listenTo(this.model, 'destroy', this.remove);
			this.modelId = this.model.attributes.id;
			this.$addCard = $('#add_card');
			this.$addForm = $('#add_form');
		},
		
		render: function() {
			this.$el.attr('id', this.modelId);
			this.$el.html(this.template(this.model.toJSON()));
			return this;
		},

		edit: function() {
			CommonUtil.clearFeedbacks();
			$('.edit_card').remove();
			var editCard = new ViewUtil.EditCategoryView({model: this.model});
			$('#card_container').prepend(editCard.render().$el);
			this.$addForm[0].reset();
			this.$addCard.hide();
		},

		'delete': function() {
			CommonUtil.clearFeedbacks();
			this.model.destroy({
				wait: true,
				success: function() {
					CommonUtil.showMessage($alertResult, 'success', 'Category is successfully removed');
				},
				error: function(category, response){
					CommonUtil.showMessage($alertResult, 'danger', response);
			}});
		}
	});

	ViewUtil.CategoriesView = Backbone.View.extend({
		tagName: 'table',
		className: 'table table-hover table-bordered',
		template: TplUtil.categoriesTpl,

		initialize: function() {
			this.collection = new CollectionUtil.Categories();
			this.$el.html(this.template());
			this.$tbody = this.$('tbody');

			this.listenTo(this.collection, 'reset', this.reset);
			this.listenTo(this.collection, 'add', this.addCategory);
			this.listenTo(this.collection, 'remove', this.reset);
		},
		
		getCollection: function() {
			return this.collection;
		},

		load: function() {
			return this.collection.fetch({reset: true});
		},

		render: function() {
			this.load();
			return this;
		},
		
		addCategory: function(category) {
			this.$el.show();
			this.$tbody.append(new ViewUtil.CategoryView({model: category}).render().$el);
		},

		reset: function() {
			var $tbody = this.$tbody;
			
			if(this.collection.length == 0){
				this.$el.hide();
				$alertEmpty.show();
				return;
			}

			this.$el.show();
			$alertEmpty.hide();
			$tbody.empty();
			this.collection.each(function(category) {
				$tbody.append(new ViewUtil.CategoryView({model: category}).render().$el);
			});
		}
	});
	
	ViewUtil.EditCategoryView = Backbone.View.extend({
		className: 'card edit_card',
		template: TplUtil.editCategoryTpl,

		initialize: function() {
			this.modelId = this.model.attributes.id;
			this.$addCard = $('#add_card');
		},
		
		events: {
			'keyup': 'keyUpEvent',
			'click button#cancel': 'cancel',
			'click button#save': 'save'
		},

		render: function() {
			this.$el.html(this.template());
			this.$("input#id").val(this.modelId);
			this.$('input#name').val(this.model.get('name'));

			return this;
		},
		
		keyUpEvent: function(event) {
			if (event.keyCode == 27) {  // escape key
				this.cancel();
			} 
			else if (event.keyCode == 13) { // enter key
				this.save();
			}
		},

		save: function(event) {
			event.preventDefault();
			CommonUtil.clearFeedbacks();

			var self = this;
			var $editForm = this.$('#edit_form');
			var data = CommonUtil.serializeFormJson($editForm);
		    var form = new ModelUtil.Category(data);
		    form.set('categoryId', null);

		    if(!form.isValid()){
		    	$.each(form.validationError, function(i, error){
		    		var $inputField = self.$('#' + error.field);
			    	$inputField.addClass('is-invalid');
					$inputField.after(TplUtil.invalidInputTpl({value: error.message}));
		    	});
		    	return;
		    }
			
			this.model.save(form, {
				wait: true,
				success: function() {
					self.cancel();
					CommonUtil.showMessage($alertResult, 'success', 'Category is successfully saved');
				},
				error: function(category, response){
					$editForm[0].reset();
					self.cancel();
					CommonUtil.showMessage($alertResult, 'danger', response.responseText);
				}
			});
		},
		
		cancel: function() {
			CommonUtil.clearFeedbacks();
			this.remove();
			this.$addCard.show();
			$('#' + this.modelId).replaceWith(new ViewUtil.CategoryView({model: this.model}).render().$el);
		}
	});
	
	ViewUtil.ProductView = Backbone.View.extend({
		className: 'card mb-3 mx-2',
		attributes: {
			'style': 'width: 350px; height: 220px'
		},
		template: TplUtil.productTpl,

		events: {
			'click button#edit': 'edit',
			'click button#remove': 'delete',
			'click button#stock_history': 'stockHistory',
			'click button#orders': 'orders'
		},

		initialize: function() {
			this.listenTo(this.model, 'destroy', this.remove);
			this.modelId = this.model.attributes.id;
		},
		
		render: function() {
			this.$el.attr('id', this.modelId);
			this.$el.html(this.template(this.model.toJSON()));
			return this;
		},

		edit: function() {
			CommonUtil.clearFeedbacks();
			var $editModal = $('#edit_modal');
			var $editForm = $('#edit_form');
			$editForm.empty();
			
			var editModal = new ViewUtil.EditProductView({model: this.model});
			$editForm.append(editModal.render().$el);
			$editForm[0].reset();
			$editModal.modal('show');
			
			var categoriesOptionView = new ViewUtil.CategoriesOptionView({model: this.model});
			editModal.$('#edit_category_select').append(categoriesOptionView.render().$el);
		},

		'delete': function() {
			CommonUtil.clearFeedbacks();
			this.model.destroy({
				wait: true,
				success: function() {
					CommonUtil.showMessage($alertResult, 'success', 'Product is successfully removed');
				},
				error: function(product, response){
					CommonUtil.showMessage($alertResult, 'danger', response);
			}});
		}
	});

	ViewUtil.ProductsView = Backbone.View.extend({
		className: 'row equal',
		
		initialize: function() {
			this.collection = new CollectionUtil.Products();
			
			this.listenTo(this.collection, 'reset', this.reset);
			this.listenTo(this.collection, 'add', this.addProduct);
			this.listenTo(this.collection, 'remove', this.reset);
		},
		
		getCollection: function() {
			return this.collection;
		},

		load: function() {
			return this.collection.fetch({reset: true});
		},

		render: function() {
			this.load();
			return this;
		},
		
		addProduct: function(product) {
			this.$el.show();
			this.$el.append(new ViewUtil.ProductView({model: product}).render().$el);
		},

		reset: function() {
			var $element = this.$el;
			
			if(this.collection.length == 0){
				$element.hide();
				$alertEmpty.show();
				return;
			}

			$element.show();
			$alertEmpty.hide();
			$element.empty();
			this.collection.each(function(product) {
				$element.append(new ViewUtil.ProductView({model: product}).render().$el);
			});
		}
	});
	
	ViewUtil.CategoriesOptionView = Backbone.View.extend({
		tagName: 'select',
		className: 'form-control',
		attributes: {
			'name': 'categoryId'
		},
		
		initialize: function() {
			this.collection = new CollectionUtil.Categories();
			this.listenTo(this.collection, 'reset', this.reset);
		},

		load: function() {
			return this.collection.fetch({reset: true});
		},

		render: function() {
			this.load();
			return this;
		},

		reset: function() {
			var $element = this.$el;
			
			if(this.collection.length == 0){
		    	$element.addClass('is-invalid');
				$element.after(TplUtil.invalidInputTpl({value: 'Category is required'}));
				return;
			}
			
			$element.empty();
			this.collection.each(function(category) {
				$element.append(TplUtil.selectOptionTpl(category.attributes));
			});
			
			if(this.model !== undefined){
				$element.val(this.model.attributes.categoryId);
			}
		}
	});
	
	ViewUtil.EditProductView = Backbone.View.extend({
		className: 'modal-content',
		template: TplUtil.productModalTpl,
		
		events: {
			'click button#save': 'save'
		},

		initialize: function() {
			this.modelId = this.model.attributes.id;
		},
		
		render: function() {
			this.$el.html(this.template(this.model.toJSON()));
			return this;
		},

		save: function() {
			CommonUtil.clearFeedbacks();
			var self = this;
			var $editModal = $('#edit_modal');
			var $editForm = $('#edit_form');
			var data = CommonUtil.serializeFormJson($editForm);
		    var form = new ModelUtil.Product(data);
		    
		    if(!form.isValid()){
		    	$.each(form.validationError, function(i, error){
		    		var $inputField = self.$('#edit_' + error.field);
			    	$inputField.addClass('is-invalid');
					$inputField.after(TplUtil.invalidInputTpl({value: error.message}));
		    	});
		    	return;
		    }

		    this.model.save(form, {
				wait: true,
				success: function() {
					$editModal.modal('hide');
					CommonUtil.showMessage($alertResult, 'success', 'Product is successfully saved');
					$('#' + self.modelId).replaceWith(new ViewUtil.ProductView({model: self.model}).render().$el);
				},
				error: function(category, response){
					self.$editForm[0].reset();
					CommonUtil.showMessage($alertResult, 'danger', response.responseText);
				}
			});
		}
	});
})();