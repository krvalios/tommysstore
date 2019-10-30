package com.tommysstore.bean.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class StockHistoryForm {
	
	@NotNull
	private Integer inventoryId;
	
	@Min(value = 1, message = "Quantity must be at least 1")
	private int stockAdded;

	public Integer getInventoryId() {
		
		return inventoryId;
	}

	public void setInventoryId(Integer inventoryId) {
		
		this.inventoryId = inventoryId;
	}

	public int getStockAdded() {
		
		return stockAdded;
	}

	public void setStockAdded(int stockAdded) {
		
		this.stockAdded = stockAdded;
	}
}
