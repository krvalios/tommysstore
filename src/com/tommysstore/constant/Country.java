package com.tommysstore.constant;

public enum Country {
	
	PH("Philippines"),
	USA("United States of America"),
	AU("Australia"),
	JP("Japan"),
	KR("Korea"),
	SG("Singapore"),
	CN("China"),
	UK("United Kingdom"),
	IN("India"),
	TH("Thailand");
	
	private String name;

	Country(String name) {
		
		this.name = name;
	}
	
	public String getName() {
		
		return this.name;
	}
}
