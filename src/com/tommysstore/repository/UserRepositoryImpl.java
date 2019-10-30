package com.tommysstore.repository;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tommysstore.constant.UserRole;
import com.tommysstore.domain.CreditCard;
import com.tommysstore.domain.ShippingAddress;
import com.tommysstore.domain.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public User findByEmail(String email) {
		
		try {
			
			TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", 
																User.class);
			query.setParameter("email", email);
	        
	        return query.getSingleResult();
		}
		
		catch (NoResultException e) {
			
			return null;
		}
	}
	
	public List<User> listNewCustomers(int dayLimit) {
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, dayLimit);
		Date oneDay = calendar.getTime();
		
		TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.role = :role"
														 + " AND u.dateCreated < CURRENT_TIMESTAMP"
														 + " AND u.dateCreated > :oneDay", User.class);
		query.setParameter("role", UserRole.CUSTOMER);
		query.setParameter("oneDay", oneDay, TemporalType.DATE);
		
        return query.getResultList();
	}
	
	public List<User> list() {
		
		TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
		
        return query.getResultList();
	}
	
	public List<User> listByRole(UserRole role) {
		
		TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.role = :role", User.class);
		query.setParameter("role", role);
		
        return query.getResultList();
	}
	
	public List<ShippingAddress> listShippingAddresses(User user) {
		
		TypedQuery<ShippingAddress> query = entityManager.createQuery("SELECT a FROM ShippingAddress a WHERE"
																	+ " a.user = :user", ShippingAddress.class);
		query.setParameter("user", user);
		
        return query.getResultList();
	}
	
	public List<CreditCard> listCreditCards(User user) {
		
		TypedQuery<CreditCard> query = entityManager.createQuery("SELECT c FROM CreditCard c WHERE c.user = :user",
																 CreditCard.class);
		query.setParameter("user", user);
		
        return query.getResultList();
	}
	
	public ShippingAddress findAddressById(Integer id) {
		
		return entityManager.find(ShippingAddress.class, id);
	}
	
	public CreditCard findCreditCardById(Integer id) {
		
		return entityManager.find(CreditCard.class, id);
	}
	
	public void addShippingAddress(ShippingAddress shippingAddress) {
		
		entityManager.persist(shippingAddress);
	}
	
	public void addCreditCard(CreditCard creditCard) {
		
		entityManager.persist(creditCard);
	}

	public void save(User user) {
		
		if (user.getId() == null) {
			
            entityManager.persist(user);
        } 
		
		else {
			
			entityManager.merge(user);
        }
	}
}
