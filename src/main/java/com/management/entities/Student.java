package com.management.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int primaryKey;
	private int serailNo;
	@Column(unique = true)
	private String rollnumber;
	private String fullName;
	private String fatherName;
	private String selectBranch;
	private String selectYear;
	private String selectSemester;
	private String bookBank;
	private String bookAnnually;
	private String noDuess;
	@Column(unique = true)
	private String email;
	private String phone;
	private String address;
	private Date createdAt;
	private Date modifiedOn;
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(int primaryKey,int serailNo, String rollnumber, String fullName,String fatherName, String selectBranch , String selectYear, String selectSemester,String bookBank , String email, String phone, String address, String status,
			String bookAnnually,String noDuess, Date createdAt, Date modifiedOn ) {
		super();
		this.primaryKey = primaryKey;
		this.serailNo=serailNo;
		this.rollnumber = rollnumber;
		this.fatherName=fatherName;
		this.fullName = fullName;
		this.selectBranch = selectBranch;
		this.selectYear = selectYear;  
		this.selectSemester = selectSemester;
		this.bookBank= bookBank;
		this.bookAnnually=bookAnnually;
		this.noDuess=noDuess;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.createdAt = createdAt;
		this.modifiedOn = modifiedOn;
	}
	public String getBookAnnually() {
		return bookAnnually;
	}
	public void setBookAnnually(String bookAnnually) {
		this.bookAnnually = bookAnnually;
	}
	public int getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}
	public int getSerailNo() {
		return serailNo;
	}
	public void setSerailNo(int serailNo) {
		this.serailNo = serailNo;
	}
	public String getRollnumber() {
		return rollnumber;
	}
	public void setRollnumber(String rollnumber) {
		this.rollnumber = rollnumber;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getSelectBranch() {
		return selectBranch;
	}
	public void setSelectBranch(String selectBranch) {
		this.selectBranch = selectBranch;
	}
	public String getSelectYear() {
		return selectYear;
	}
	public void setSelectYear(String selectYear) {
		this.selectYear = selectYear;
	}
	public String getSelectSemester() {
		return selectSemester;
	}
	public void setSelectSemester(String selectSemester) {
		this.selectSemester = selectSemester;
	}
	public String getBookBank() {
		return bookBank;
	}
	public void setBookBank(String bookBank) {
		this.bookBank = bookBank;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	
	
	public String getNoDuess() {
		return noDuess;
	}
	public void setNoDuess(String noDuess) {
		this.noDuess = noDuess;
	}
	@Override
	public String toString() {
		return "Student [primaryKey=" + primaryKey + ", rollnumber=" + rollnumber + ", fullName=" + fullName
				+ ", fatherName=" + fatherName + ", selectBranch=" + selectBranch + ", selectYear=" + selectYear
				+ ", selectSemester=" + selectSemester + ", email=" + email + ", phone=" + phone + ", address="
				+ address + ", createdAt=" + createdAt + ", modifiedOn=" + modifiedOn + " , bookBank=" + bookBank + "]";
	}
	
	
	
	
}
