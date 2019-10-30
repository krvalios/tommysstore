package com.tommysstore.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.tommysstore.constant.PaymentMethod;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@OneToMany(mappedBy = "order", orphanRemoval = true)
	private List<OrderItem> orderItems;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_created", nullable = false)
	private Date dateOrdered;
	
	@Column(nullable = false, precision = 7, scale = 2)
	private BigDecimal totalAmount;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private PaymentMethod paymentMethod;
	
	@ManyToOne
	@JoinColumn(name = "shipping_address_id")
	private ShippingAddress shippingAddress;
	
	public Integer getId() {
		
		return id;
	}
	
	public void setId(Integer id) {
		
		this.id = id;
	}
	
	public List<OrderItem> getOrderItems() {
		
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		
		this.orderItems = orderItems;
	}
	
	public User getUser() {
		
		return user;
	}

	public void setUser(User user) {
		
		this.user = user;
	}

	public Date getDateOrdered() {
		
		return dateOrdered;
	}
	
	public void setDateOrdered(Date dateOrdered) {
		
		this.dateOrdered = dateOrdered;
	}
	
	public BigDecimal getTotalAmount() {
		
		return totalAmount;
	}
	
	public void setTotalAmount(BigDecimal totalAmount) {
		
		this.totalAmount = totalAmount;
	}
	
	public PaymentMethod getPaymentMethod() {
		
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		
		this.paymentMethod = paymentMethod;
	}

	public ShippingAddress getShippingAddress() {
		
		return shippingAddress;
	}

	public void setShippingAddress(ShippingAddress shippingAddress) {
		
		this.shippingAddress = shippingAddress;
	}
}
