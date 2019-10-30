package com.tommysstore.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tommysstore.constant.UserRole;

@Entity
@Table(name = "users")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty(message = "Email is required")
	@Column(nullable = false)
	private String email;

	@NotEmpty(message = "Password is required")
	@Column(nullable = false)
	private String password;

	@NotEmpty(message = "First name is required")
	@Pattern(regexp = "^[A-Za-z ]+", message = "Must contain letters only")
	@Column(nullable = false)
	private String firstname;

	@NotEmpty(message = "Last name is required")
	@Pattern(regexp = "^[A-Za-z ]+", message = "Must contain letters only")
	@Column(nullable = false)
	private String lastname;

	@NotEmpty(message = "Contact number is required")
	@Pattern(regexp = "^[0-9+]+", message = "Must contain numbers only")
	@Column(name = "contact_number", nullable = false)
	private String contactNumber;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserRole role;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<ShippingAddress> addresses;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<CreditCard> creditCards;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_created", nullable = false)
	private Date dateCreated;
	
	public Integer getId() {
		
		return id;
	}

	public void setId(Integer id) {
		
		this.id = id;
	}

	public String getEmail() {
		
		return email;
	}
	
	public void setEmail(String email) {
		
		this.email = email;
	}
	
	public String getPassword() {
		
		return password;
	}
	
	public void setPassword(String password) {
		
		this.password = password;
	}
	
	public String getFirstname() {
		
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		
		this.firstname = firstname;
	}
	
	public String getLastname() {
		
		return lastname;
	}
	
	public void setLastname(String lastname) {
		
		this.lastname = lastname;
	}
	
	public String getContactNumber() {
		
		return contactNumber;
	}
	
	public void setContactNumber(String contactNumber) {
		
		this.contactNumber = contactNumber;
	}
	
	public List<ShippingAddress> getAddresses() {
		
		return addresses;
	}
	
	public void setAddresses(List<ShippingAddress> addresses) {
		
		this.addresses = addresses;
	}
	
	public List<CreditCard> getCreditCards() {
		
		return creditCards;
	}
	
	public void setCreditCards(List<CreditCard> creditCards) {
		
		this.creditCards = creditCards;
	}

	public UserRole getRole() {
		
		return role;
	}

	public void setRole(UserRole role) {
		
		this.role = role;
	}

	public Date getDateCreated() {
		
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		
		this.dateCreated = dateCreated;
	}
	
	
}
