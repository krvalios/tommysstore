package com.tommysstore.repository;

import java.util.List;

import com.tommysstore.constant.UserRole;
import com.tommysstore.domain.CreditCard;
import com.tommysstore.domain.ShippingAddress;
import com.tommysstore.domain.User;

public interface UserRepository {
	
	User findByEmail(String email);
	
	List<User> listNewCustomers(int dayLimit);
	
	List<User> list();
	
	List<User> listByRole(UserRole role);
	
	List<ShippingAddress> listShippingAddresses(User user);
	
	List<CreditCard> listCreditCards(User user);

	ShippingAddress findAddressById(Integer id);
	
	CreditCard findCreditCardById(Integer id);
	
	void addShippingAddress(ShippingAddress shippingAddress);
	
	void addCreditCard(CreditCard creditCard);
	
	void save(User user);
	
}
