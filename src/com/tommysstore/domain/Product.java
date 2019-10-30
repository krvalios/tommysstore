package com.tommysstore.domain;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.tommysstore.generator.ProductUid;

@Entity
@Table(name = "products", uniqueConstraints = { @UniqueConstraint(columnNames = {"name", "category_id"}) })
public class Product {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne
	@JoinColumn(name = "product_id", nullable = false, unique = true)
	private ProductUid productId;
	
	@NotEmpty
	@Column(nullable = false)
	private String name;
	
	@NotNull
	@ManyToOne(optional = false)
//	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;
	
//	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, optional = false)
	@OneToOne(mappedBy = "product", optional = false, orphanRemoval = true, cascade = CascadeType.ALL)  
	private Inventory inventory;
	
	@NotNull
	@Column(nullable = false, precision = 7, scale = 2)
	private BigDecimal price;
	
//	@NotEmpty
	@Column(unique = true)
	private String picture;
	
	public Integer getId() {
		
		return id;
	}

	public void setId(Integer id) {
		
		this.id = id;
	}
	
	public ProductUid getProductId() {
		
		return productId;
	}

	public void setProductId(ProductUid productId) {
		
		this.productId = productId;
	}

	public String getName() {
		
		return name;
	}
	
	public void setName(String name) {
		
		this.name = name;
	}
	
	public Category getCategory() {
		
		return category;
	}
	
	public void setCategory(Category category) {
		
		this.category = category;
	}
	
	public Inventory getInventory() {
		
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		
		if (inventory == null) {
			
            if (this.inventory != null) {
            	
                this.inventory.setProduct(null);
            }
        }
        else {
        	
        	inventory.setProduct(this);
        }
		
        this.inventory = inventory;
	}

	public BigDecimal getPrice() {
		
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		
		this.price = price;
	}

	public String getPicture() {
		
		return picture;
	}

	public void setPicture(String picture) {
		
		this.picture = picture;
	}
	
//	@Override
//    public boolean equals(Object o) {
//		
//        if(this == o) 					return true;
//        if(!(o instanceof Product )) 	return false;
//        
//        return id != null && id.equals(((Product) o).getId());
//    }
//	
//    @Override
//    public int hashCode() {
//    	
//        return 31;
//    }
}
