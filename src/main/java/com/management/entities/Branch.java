package com.management.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"branchName"})})
public class Branch {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int primaryKey;
	
	@Column(unique = true)
	private String branchName;
	
	private Date createdAt;
	
		
	public Branch() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Branch(int primaryKey, String branchName, Date createdAt) {
		super();
		this.primaryKey = primaryKey;
		this.branchName = branchName;
		this.createdAt = createdAt;
	}

	public int getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Branch [primaryKey=" + primaryKey + ", branchName=" + branchName + ", createdAt=" + createdAt + "]";
	}

	
	
	
}
