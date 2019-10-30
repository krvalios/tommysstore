package com.tommysstore.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tommysstore.domain.Inventory;
import com.tommysstore.domain.Product;
import com.tommysstore.domain.StockHistory;

@Repository
public class InventoryRepositoryImpl implements InventoryRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Inventory> list() {
		
		TypedQuery<Inventory> query = entityManager.createQuery("SELECT i FROM Inventory i", Inventory.class);
		
        return query.getResultList();
	}

	public List<Inventory> listByStock(int lowStockLimit) {
		
		TypedQuery<Inventory> query = entityManager.createQuery("SELECT ip FROM Inventory ip WHERE ip.stocks"
															  + " < :lowStockLimit", Inventory.class);
		query.setParameter("lowStockLimit", lowStockLimit);
		
		return query.getResultList();
	}
	
	public List<StockHistory> listStockHistory(Product product) {
		
		TypedQuery<StockHistory> query = entityManager.createQuery("SELECT sh FROM StockHistory sh WHERE"
																 + " sh.inventory.id = :productId", StockHistory.class);
		query.setParameter("productId", product.getId());
		
		return query.getResultList();
	}
	
	public Inventory find(Integer id) {
		
		return entityManager.find(Inventory.class, id);
	}
	
	public void add(Inventory inventory) {
		
		entityManager.persist(inventory);
	}
	
	public void save(Inventory inventory) {
		
		entityManager.merge(inventory);
	}
	
	public void saveHistory(StockHistory stockHistory) {
		
		entityManager.persist(stockHistory);
	}
	
	public void remove(Integer id) {
		
		Inventory inventory = entityManager.find(Inventory.class, id);
		entityManager.remove(inventory);
	}
}
