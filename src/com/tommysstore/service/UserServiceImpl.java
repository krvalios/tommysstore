package com.tommysstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tommysstore.bean.form.LoginForm;
import com.tommysstore.constant.UserRole;
import com.tommysstore.domain.CreditCard;
import com.tommysstore.domain.ShippingAddress;
import com.tommysstore.domain.User;
import com.tommysstore.exception.CreditCardNotFoundException;
import com.tommysstore.exception.ShippingAddressNotFoundException;
import com.tommysstore.exception.UnavailableEmailException;
import com.tommysstore.exception.UserNotFoundException;
import com.tommysstore.repository.UserRepository;

@Service
@PropertySource("/WEB-INF/properties")
public class UserServiceImpl implements UserService {

	@Autowired
    private UserRepository userRepository;
	
	@Value("${user.dayLimit}")
	private int dayLimit;
	
	@Transactional
	public User validateLogin(LoginForm login) throws UserNotFoundException {
		
		User user = userRepository.findByEmail(login.getEmail());
		
		if(user != null && user.getPassword().equals(login.getPassword())) {
			
			return user;
			
		}
		
		throw new UserNotFoundException();
	}
	
	@Transactional
	public List<User> listNewUsers() {
		
		return userRepository.listNewCustomers(dayLimit);
	}
	
	@Transactional
	public List<User> list() {
		
		return userRepository.list();
	}
	
	@Transactional
	public List<User> listByRole(UserRole role) {
		
		return userRepository.listByRole(role);
	}
	
	@Transactional
    public List<ShippingAddress> listShippingAddresses(User user) {
		
		return userRepository.listShippingAddresses(user);
    }
	
	@Transactional
    public List<CreditCard> listCreditCards(User user) {
		
		return userRepository.listCreditCards(user);
    }
	
	@Transactional
	public ShippingAddress findAddressById(Integer id) throws ShippingAddressNotFoundException {
		
		ShippingAddress address = userRepository.findAddressById(id);
		
		if(address == null) {
			
			throw new ShippingAddressNotFoundException();
		}
		
		return address; 
	}
	
	@Transactional
	public CreditCard findCreditCardById(Integer id) throws CreditCardNotFoundException {
		
		CreditCard creditCard = userRepository.findCreditCardById(id);
		
		if(creditCard == null) {
			
			throw new CreditCardNotFoundException();
		}
		
		return creditCard;
	}
	
	@Transactional
	public void addShippingAddress(ShippingAddress shippingAddress) {
		
		userRepository.addShippingAddress(shippingAddress);
	}
	
	@Transactional
	public void addCreditCard(CreditCard creditCard) {
		
		userRepository.addCreditCard(creditCard);
	}
	
	@Transactional(rollbackFor = UnavailableEmailException.class)
	public void save(User user) throws UnavailableEmailException {
		
		if(!isValidEmail(user.getEmail())) {
			
			throw new UnavailableEmailException();
		}
		
		userRepository.save(user);
	}
	
	@Transactional
	public boolean isValidEmail(String email) {
		
		User user = userRepository.findByEmail(email);
		
		if(user != null) {
			
			return false;
		}
		
		return true;
	}
}
