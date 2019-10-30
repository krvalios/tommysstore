package com.tommysstore.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "order_items")
public class OrderItem {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "product_id")
	private Product product;
	
	@Min(1)
	@Column(nullable = false)
	private int quantity;
	
	@NotNull
	@Column(nullable = false, precision = 7, scale = 2)
	private BigDecimal price;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "order_id")
	private Order order;

	public Integer getId() {
		
		return id;
	}

	public void setId(Integer id) {
		
		this.id = id;
	}

	public Product getProduct() {
		
		return product;
	}

	public void setProduct(Product product) {
		
		this.product = product;
	}

	public int getQuantity() {
		
		return quantity;
	}

	public void setQuantity(int quantity) {
		
		this.quantity = quantity;
	}
	
	public BigDecimal getPrice() {
		
		return price;
	}

	public void setPrice(BigDecimal price) {
		
		this.price = price;
	}

	public Order getOrder() {
		
		return order;
	}

	public void setOrder(Order order) {
		
		this.order = order;
	}
}
