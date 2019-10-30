package com.tommysstore.generator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "product_uid")
public class ProductUid {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @GenericGenerator(
        name = "product_seq", 
        strategy = "com.tommysstore.generator.ProductUidSequenceGenerator", 
        parameters = {
            @Parameter(name = ProductUidSequenceGenerator.INCREMENT_PARAM, value = "1"),
            @Parameter(name = ProductUidSequenceGenerator.VALUE_PREFIX_PARAMETER, value = "PROD-"),
            @Parameter(name = ProductUidSequenceGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
    private String id;

	public String getId() {
		
		return id;
	}

	public void setId(String id) {
		
		this.id = id;
	}
}
