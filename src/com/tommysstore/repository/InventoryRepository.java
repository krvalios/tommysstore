package com.tommysstore.repository;

import java.util.List;

import com.tommysstore.domain.Inventory;
import com.tommysstore.domain.Product;
import com.tommysstore.domain.StockHistory;

public interface InventoryRepository {
	
	List<Inventory> list();
	
	List<Inventory> listByStock(int lowStockLimit);
	
	List<StockHistory> listStockHistory(Product product);

	Inventory find(Integer id);
	
	void add(Inventory inventory);
	
	void save(Inventory inventory);
	
	void saveHistory(StockHistory stockHistory);
	
	void remove(Integer id);
}
