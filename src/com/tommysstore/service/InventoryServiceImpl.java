package com.tommysstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tommysstore.domain.CartItem;
import com.tommysstore.domain.Inventory;
import com.tommysstore.domain.Product;
import com.tommysstore.domain.StockHistory;
import com.tommysstore.exception.InsufficientStockException;
import com.tommysstore.exception.ProductNotFoundException;
import com.tommysstore.repository.InventoryRepository;

@Service
@PropertySource("/WEB-INF/properties")
public class InventoryServiceImpl implements InventoryService {

	@Autowired
    private InventoryRepository inventoryRepository;
	
	@Value("${inventory.lowStockLimit}")
	private int lowStockLimit;
	
	@Value("${inventory.displayStockLimit}")
	private int displayStockLimit;
	
	public int getDisplayStockLimit() {
		
		return displayStockLimit;
	}

	@Transactional
	public List<Inventory> list() {
		
		return inventoryRepository.list();
	}
	
	@Transactional
	public List<Inventory> listByStock() {
		
		return inventoryRepository.listByStock(lowStockLimit);
	}
	
	@Transactional
	public List<StockHistory> listStockHistory(Product product) {
		
		return inventoryRepository.listStockHistory(product);
	}
	
	@Transactional
	public Inventory find(Integer id) throws ProductNotFoundException {
		
		Inventory inventory = inventoryRepository.find(id);
		
		if(inventory == null) {
			
			throw new ProductNotFoundException();
		}
		
		return inventory;
	}
	
	@Transactional
	public void addStock(StockHistory stockHistory) {
		
		Inventory inventory = stockHistory.getInventory();
		int currentStock = inventory.getStocks();
		int stockAdded = stockHistory.getStockAdded();
		
		inventory.setStocks(currentStock + stockAdded);
		
		inventoryRepository.save(inventory);
		inventoryRepository.saveHistory(stockHistory);
	}
	
	@Transactional
	public void save(Inventory inventory) {
		
		inventoryRepository.save(inventory);
	}
	
	public void checkStockSufficiency(CartItem cartItem) throws InsufficientStockException {
		
		Product product = cartItem.getProduct();
		int quantity = cartItem.getQuantity();
		int stocks = product.getInventory().getStocks();
		
		if(stocks < quantity) {
			
			throw new InsufficientStockException();
		}
	}
	
}
