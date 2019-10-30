package com.tommysstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tommysstore.bean.PopularProductBean;
import com.tommysstore.domain.Category;
import com.tommysstore.domain.Inventory;
import com.tommysstore.domain.OrderItem;
import com.tommysstore.domain.Product;
import com.tommysstore.exception.EntityDeletionException;
import com.tommysstore.exception.ProductNotFoundException;
import com.tommysstore.exception.UnavailableProductException;
import com.tommysstore.generator.ProductUid;
import com.tommysstore.repository.InventoryRepository;
import com.tommysstore.repository.ProductRepository;

@Service
@PropertySource("/WEB-INF/properties")
public class ProductServiceImpl implements ProductService {

	@Autowired
    private ProductRepository productRepository;
	
	@Autowired
    private InventoryRepository inventoryRepository;
	
	@Value("${product.maxResult}")
	private int maxResult;
	
	@Value("${product.dayLimit}")
	private int dayLimit;
	
	@Transactional
	public List<Product> list() {
		
		return productRepository.list();
	}
	
	@Transactional
	public List<Product> listByCategory(Category category) {
		
		return productRepository.listByCategory(category);
	}
	
	@Transactional
	public List<Product> listByName(String name) {
		
		return productRepository.listByName(name);
	}
	
	@Transactional
	public List<Product> findByProduct(Product product) {
		
		return productRepository.findByProduct(product);
	}
	
	@Transactional
	public List<PopularProductBean> listPopular() {
		
		return productRepository.listPopular(maxResult, dayLimit);
	}
	
	@Transactional
	public List<OrderItem> listOrders(Product product) {
		
		return productRepository.listOrders(product);
	}
	
	@Transactional
	public Product find(Integer id) throws ProductNotFoundException {
		
		Product product = productRepository.find(id);
		
		if(product == null) {
			
			throw new ProductNotFoundException();
		}
		
		return product;
	}
	
	@Transactional
	public Product findByProductId(ProductUid productId) throws ProductNotFoundException {
		
		Product product = productRepository.findByProductId(productId);
		
		if(product == null) {
			
			throw new ProductNotFoundException();
		}
		
		return product;
	}
	
	@Transactional
	public ProductUid generateProductUid(ProductUid productId) throws UnavailableProductException {
		
		return productRepository.generateProductUid(productId);
	}
	
	@Transactional(rollbackFor = {UnavailableProductException.class, ProductNotFoundException.class})
	public void add(Product product) throws UnavailableProductException, ProductNotFoundException {
		
		ProductUid productId = generateProductUid(new ProductUid());
		product.setProductId(productId);
		
		productRepository.add(product);
		
		Inventory inventory = new Inventory();
		inventory.setStocks(0);
		product = findByProductId(product.getProductId());
		inventory.setProduct(product);
		
		inventoryRepository.add(inventory);
		
//		Product validProduct = productService.findProductByProductId(product.getProductId());
//		inventory.setProduct(product);
//		inventoryService.addInventory(inventory);
//		validCategory.addProduct(product);
//		validCategory.getProducts().add(product);
//		categoryService.addCategory(validCategory);
	}
	
	@Transactional(rollbackFor = UnavailableProductException.class)
	public void save(Product product) throws UnavailableProductException {

		productRepository.save(product);
	}

	@Transactional(rollbackFor = ProductNotFoundException.class)
	public void remove(Product product) throws ProductNotFoundException, EntityDeletionException {
		
		List<OrderItem> orders = productRepository.listOrders(product);
		
		if(orders.size() != 0) {
			
			throw new EntityDeletionException();
		}
		
		inventoryRepository.remove(product.getId());
		productRepository.remove(product);
	}
}
