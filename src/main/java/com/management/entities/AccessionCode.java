package com.management.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AccessionCode {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int primaryKey;
	
	@Column(unique = true)
	private String accessionCodeCategory;

	public AccessionCode() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccessionCode(int primaryKey, String accessionCodeCategory) {
		super();
		this.primaryKey = primaryKey;
		this.accessionCodeCategory = accessionCodeCategory;
	}

	public int getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getAccessionCodeCategory() {
		return accessionCodeCategory;
	}

	public void setAccessionCodeCategory(String accessionCodeCategory) {
		this.accessionCodeCategory = accessionCodeCategory;
	}

	@Override
	public String toString() {
		return "AccessionCode [primaryKey=" + primaryKey + ", accessionCodeCategory=" + accessionCodeCategory + "]";
	}
	
	
	
}
