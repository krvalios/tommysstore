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

@Entity
@Table(name = "inventory")
public class Inventory {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne(optional = false)
//	@MapsId
	@JoinColumn(name = "product_id")
	private Product product;
	
	@Column(nullable = false)
	private int stocks;
	
	@OneToMany(mappedBy = "inventory", orphanRemoval = true)
	private List<StockHistory> stockHistory;
	
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
	
	public int getStocks() {
		
		return stocks;
	}
	
	public void setStocks(int stocks) {
		
		this.stocks = stocks;
	}

	public List<StockHistory> getStockHistory() {
		
		return stockHistory;
	}

	public void setStockHistory(List<StockHistory> stockHistory) {
		
		this.stockHistory = stockHistory;
	}
}
