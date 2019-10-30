package com.tommysstore.service;

import java.util.List;

import com.tommysstore.domain.CartItem;
import com.tommysstore.domain.Inventory;
import com.tommysstore.domain.Product;
import com.tommysstore.domain.StockHistory;
import com.tommysstore.exception.InsufficientStockException;
import com.tommysstore.exception.ProductNotFoundException;

public interface InventoryService {
	
	int getDisplayStockLimit();
	
	List<Inventory> list();
	
	List<Inventory> listByStock();
	
	List<StockHistory> listStockHistory(Product product);

	Inventory find(Integer id) throws ProductNotFoundException;
	
	void addStock(StockHistory stockHistory);
	
	void save(Inventory inventory);
	
	void checkStockSufficiency(CartItem cartItem) throws InsufficientStockException;
	
}
