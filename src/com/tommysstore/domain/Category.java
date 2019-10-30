package com.tommysstore.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
//import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tommysstore.generator.CategoryUid;

@Entity
@Table(name = "categories")
public class Category {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	@JoinColumn(name = "category_id", nullable = false, unique = true)
	private CategoryUid categoryId;

	@NotEmpty(message = "Category name is required")
	@Column(nullable = false, unique = true)
	private String name;
	
//	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@OneToMany(mappedBy = "category", orphanRemoval = true)
	@JsonIgnore
	private List<Product> products;
	
	public Integer getId() {
		
		return id;
	}

	public void setId(Integer id) {
		
		this.id = id;
	}
	
	public CategoryUid getCategoryId() {
		
		return categoryId;
	}

	public void setCategoryId(CategoryUid categoryId) {
		
		this.categoryId = categoryId;
	}
	
	public String getName() {
		
		return name;
	}

	public void setName(String name) {
		
		this.name = name;
	}

	public List<Product> getProducts() {
		
		return products;
	}

	public void setProducts(List<Product> products) {
		
		this.products = products;
	}
//	
//	public void addProduct(Product product) {
//		
//		products.add(product);
//		product.setCategory(this);
//    }
// 
//    public void removeProduct(Product product) {
//    	
//    	products.remove(product);
//    	product.setCategory(null);
//    }

}
