package com.tommysstore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "credit_cards")
public class CreditCard {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id")
	private User user;

	@NotEmpty(message = "Card number is required")
	@Size(min = 10, max = 10, message = "Must have exact 10 numbers")
	@Pattern(regexp = "^[0-9]+", message = "Must contain numbers only")
	@Column(nullable = false)
	private String number;

	@NotEmpty(message = "Security code is required")
	@Size(min = 5, max = 5, message = "Must have exact 5 numbers")
	@Pattern(regexp = "^[0-9]+", message = "Must contain numbers only")
	@Column(name = "security_code", nullable = false)
	private String securityCode;
	
	public Integer getId() {
		
		return id;
	}

	public void setId(Integer id) {
		
		this.id = id;
	}

	public String getNumber() {
		
		return number;
	}
	
	public void setNumber(String number) {
		
		this.number = number;
	}

	public User getUser() {
		
		return user;
	}

	public void setUser(User user) {
		
		this.user = user;
	}

	public String getSecurityCode() {
		
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		
		this.securityCode = securityCode;
	}
}
