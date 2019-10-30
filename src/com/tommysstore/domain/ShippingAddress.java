package com.tommysstore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.tommysstore.constant.Country;

@Entity
@Table(name = "shipping_addresses")
public class ShippingAddress {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id")
	private User user;

	@NotEmpty(message = "Address 1 is required")
	@Column(nullable = false)
	private String address1;

	@NotEmpty(message = "Address 2 is required")
	@Column(nullable = false)
	private String address2;

	@NotEmpty(message = "Zip code is required")
	@Pattern(regexp = "^[0-9]+", message = "Must contain numbers only")
	@Column(name = "zip_code", nullable = false)
	private String zipCode;

	@NotNull(message = "Country is required")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Country country;
	
	public Integer getId() {
		
		return id;
	}

	public void setId(Integer id) {
		
		this.id = id;
	}

	public User getUser() {
		
		return user;
	}

	public void setUser(User user) {
		
//        if (user != null) {
//        	user.getAddresses().add(this);
//        } else if (this.user != null) {
//            this.user.getAddresses().remove(this);
//        }
        
		this.user = user;
	}

	public String getAddress1() {
		
		return address1;
	}
	
	public void setAddress1(String address1) {
		
		this.address1 = address1;
	}
	
	public String getAddress2() {
		
		return address2;
	}
	
	public void setAddress2(String address2) {
		
		this.address2 = address2;
	}
	
	public String getZipCode() {
		
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		
		this.zipCode = zipCode;
	}

	public Country getCountry() {
		
		return country;
	}
	
	public void setCountry(Country country) {
		
		this.country = country;
	}
}
