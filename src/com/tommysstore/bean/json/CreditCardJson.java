package com.tommysstore.bean.json;

import com.tommysstore.domain.CreditCard;

public class CreditCardJson {
	
	private Integer id;
	private Integer userId;
	private String number;
	
	public CreditCardJson() {
		
	}
	
	public CreditCardJson(CreditCard creditCard) {
		
		this.id = creditCard.getId();
		this.userId = creditCard.getUser().getId();
		this.number = creditCard.getNumber();
	}

	public Integer getId() {
		
		return id;
	}

	public void setId(Integer id) {
		
		this.id = id;
	}

	public Integer getUserId() {
		
		return userId;
	}

	public void setUserId(Integer userId) {
		
		this.userId = userId;
	}

	public String getNumber() {
		
		return number;
	}

	public void setNumber(String number) {
		
		this.number = number;
	}
}
