package com.tommysstore.generator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "category_uid")
public class CategoryUid {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @GenericGenerator(
        name = "category_seq", 
        strategy = "com.tommysstore.generator.CategoryUidSequenceGenerator", 
        parameters = {
            @Parameter(name = CategoryUidSequenceGenerator.INCREMENT_PARAM, value = "1"),
            @Parameter(name = CategoryUidSequenceGenerator.VALUE_PREFIX_PARAMETER, value = "CAT-"),
            @Parameter(name = CategoryUidSequenceGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
    private String id;

	public String getId() {
		
		return id;
	}

	public void setId(String id) {
		
		this.id = id;
	}
}
