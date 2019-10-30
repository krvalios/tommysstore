package com.tommysstore.bean.json;

import com.tommysstore.constant.Country;

public class CountryJson {
	
	private String id;
	private String name;
	
	public CountryJson() {
		
	}
	
	public CountryJson(Country country) {
		
		this.id = country.name();
		this.name = country.getName();
	}

	public String getId() {
		
		return id;
	}

	public void setId(String id) {
		
		this.id = id;
	}

	public String getName() {
		
		return name;
	}

	public void setName(String name) {
		
		this.name = name;
	}
}
