package com.tommysstore.service;

import java.util.List;

import com.tommysstore.bean.form.LoginForm;
import com.tommysstore.constant.UserRole;
import com.tommysstore.domain.CreditCard;
import com.tommysstore.domain.ShippingAddress;
import com.tommysstore.domain.User;
import com.tommysstore.exception.CreditCardNotFoundException;
import com.tommysstore.exception.ShippingAddressNotFoundException;
import com.tommysstore.exception.UnavailableEmailException;
import com.tommysstore.exception.UserNotFoundException;

public interface UserService {
	
	User validateLogin(LoginForm login) throws UserNotFoundException;
	
	List<User> listNewUsers();
	
	List<User> list();
	
	List<User> listByRole(UserRole role);
	
	List<ShippingAddress> listShippingAddresses(User user);
	
	List<CreditCard> listCreditCards(User user);

	ShippingAddress findAddressById(Integer id) throws ShippingAddressNotFoundException;
	
	CreditCard findCreditCardById(Integer id) throws CreditCardNotFoundException;
	
	void addShippingAddress(ShippingAddress shippingAddress);
	
	void addCreditCard(CreditCard creditCard);
	
	void save(User user) throws UnavailableEmailException;
	
	boolean isValidEmail(String email);

}
