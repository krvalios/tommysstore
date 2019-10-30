package com.tommysstore.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "stock_history")
public class StockHistory {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id")
	private User user;
	
	@NotNull
	@ManyToOne(optional = false)
//	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "inventory_id")
	private Inventory inventory;
	
	@Column(name = "stock_added", nullable = false)
	private int stockAdded;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_added", nullable = false)
	private Date dateAdded;
	
	public Integer getId() {
		
		return id;
	}

	public void setId(Integer id) {
		
		this.id = id;
	}

	public User getUser() {
		
		return user;
	}

	public void setUser(User user) {
		
//        if (user != null) {
//        	user.getAddresses().add(this);
//        } else if (this.user != null) {
//            this.user.getAddresses().remove(this);
//        }
        
		this.user = user;
	}

	public Inventory getInventory() {
		
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		
		this.inventory = inventory;
	}

	public int getStockAdded() {
		
		return stockAdded;
	}

	public void setStockAdded(int stockAdded) {
		
		this.stockAdded = stockAdded;
	}

	public Date getDateAdded() {
		
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		
		this.dateAdded = dateAdded;
	}
}
