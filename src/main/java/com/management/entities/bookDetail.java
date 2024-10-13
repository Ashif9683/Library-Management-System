package com.management.entities;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"accessionNumber"})})
public class bookDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int primaryKey;
	private int serailNo;
	private String accessionNumber;
	private Date accessionDate;
	private String bookAccessionCode;
	private Date createdAt;
	private Date modifiedOn;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "fk_book_id")
    private Book book;
	
    @Column(name = "book_name_id",  length = 500)
    private String booknameid;
	
	public bookDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public bookDetail(int primaryKey,int serailNo,  String accessionNumber,  Date accessionDate,String bookAccessionCode, Date createdAt, Date modifiedOn, Book book , String booknameid) {
		super();
		this.primaryKey = primaryKey;
		this.serailNo=serailNo;
		this.accessionNumber = accessionNumber;
		this.accessionDate=accessionDate;
		this.bookAccessionCode=bookAccessionCode;
		this.createdAt = createdAt;
		this.modifiedOn = modifiedOn;
		this.book = book;
		this.booknameid = booknameid;
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

	public String getAccessionNumber() {
		return accessionNumber;
	}

	

	public void setAccessionNumber(String accessionNumber) {
		this.accessionNumber = accessionNumber;
	}

	public Date getAccessionDate() {
		return accessionDate;
	}

	public String getBookAccessionCode() {
		return bookAccessionCode;
	}

	public void setBookAccessionCode(String bookAccessionCode) {
		this.bookAccessionCode = bookAccessionCode;
	}

	public void setAccessionDate(Date accessionDate) {
		this.accessionDate = accessionDate;
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

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getBooknameid() {
		return booknameid;
	}

	public void setBooknameid(String booknameid) {
		this.booknameid = booknameid;
	}

	@Override
	public String toString() {
		return "bookDetail [primaryKey=" + primaryKey + ", accessionNumber=" + accessionNumber + ", accessionDate="
				+ accessionDate + ", createdAt=" + createdAt + ", modifiedOn=" + modifiedOn + ", booknameid="
				+ booknameid + "]";
	}
}
