package com.management.entities;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class BookIssue {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int primaryKey;
	
	private String selectbook;
	private String selectStudent;
	private int bookQuantityid;
	 @Column(name = "accessionNumber_id")
	private String accessionNumber;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date issueBookDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ReturnDate;
	private String duebook;
	private String semester;
	private String branch;
	private String rollnumberid;
	private String status;
	private Date createdAt;
	private Date modifiedOn;
	private String fine;
	private String bookBank;
	@Column(name = "fk_book_id")
    private int bookfk;
	
	@Column(name = "student_Return_book_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date studentRetrunBookDate;
	
    @Column(name = "fk_student_id")
    private int studentfk;
	public BookIssue() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookIssue(int primaryKey, String selectbook,String semester , String branch, String duebook, String selectStudent,int bookQuantityid, String accessionNumber, Date issueBookDate, Date returnDate,String rollnumberid,
			String status, Date createdAt, Date modifiedOn, String fine ,String bookBank, int studentfk ,int bookfk , Date studentRetrunBookDate ) {
		super();
		this.primaryKey = primaryKey;
		this.selectbook = selectbook;
		this.duebook= duebook;
		this.semester= semester;
		this.branch= branch;
		this.selectStudent = selectStudent;
		this.bookQuantityid = bookQuantityid;
		this.issueBookDate = issueBookDate;
		this.ReturnDate = returnDate;
		this.accessionNumber =  accessionNumber;
		this.rollnumberid = rollnumberid;
		this.status = status;
		this.createdAt = createdAt;
		this.modifiedOn = modifiedOn;
		this.fine = fine;
		this.bookBank=bookBank;
		this.studentfk=studentfk;
		this.bookfk=bookfk;
		this.studentRetrunBookDate=studentRetrunBookDate;
	}
	public int getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}
	public String getSelectbook() {
		return selectbook;
	}
	public void setSelectbook(String selectbook) {
		this.selectbook = selectbook;
	}
	public String getSelectStudent() {
		return selectStudent;
	}
	public void setSelectStudent(String selectStudent) {
		this.selectStudent = selectStudent;
	}
	public int getBookQuantityid() {
		return bookQuantityid;
	}
	public void setBookQuantityid(int bookQuantityid) {
		this.bookQuantityid = bookQuantityid;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public Date getIssueBookDate() {
		return issueBookDate;
	}
	public void setIssueBookDate(Date issueBookDate) {
		this.issueBookDate = issueBookDate;
	}
	public Date getReturnDate() {
		return ReturnDate;
	}
	public void setReturnDate(Date returnDate) {
		ReturnDate = returnDate;
	}
	public String getRollnumberid() {
		return rollnumberid;
	}
	public void setRollnumberid(String rollnumberid) {
		this.rollnumberid = rollnumberid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getFine() {
		return fine;
	}
	public void setFine(String fine) {
		this.fine = fine;
	}
	
	public String getBookBank() {
		return bookBank;
	}
	public void setBookBank(String bookBank) {
		this.bookBank = bookBank;
	}
	public int getStudentfk() {
		return studentfk;
	}
	public void setStudentfk(int studentfk) {
		this.studentfk = studentfk;
	}
	public String getAccessionNumber() {
		return accessionNumber;
	}
	public void setAccessionNumber(String accessionNumbers) {
		this.accessionNumber = accessionNumbers;
	}
	public int getBookfk() {
		return bookfk;
	}
	public void setBookfk(int bookfk) {
		this.bookfk = bookfk;
	}
	public Date getStudentRetrunBookDate() {
		return studentRetrunBookDate;
	}
	public void setStudentRetrunBookDate(Date studentRetrunBookDate) {
		this.studentRetrunBookDate = studentRetrunBookDate;
	}
	public String getDuebook() {
		return duebook;
	}
	public void setDuebook(String duebook) {
		this.duebook = duebook;
	}
	@Override
	public String toString() {
		return "BookIssue [primaryKey=" + primaryKey + ", selectbook=" + selectbook + ", selectStudent=" + selectStudent
				+ ", bookQuantityid=" + bookQuantityid + ", accessionNumber=" + accessionNumber + ", issueBookDate="
				+ issueBookDate + ", ReturnDate=" + ReturnDate + ", rollnumberid=" + rollnumberid + ", status=" + status
				+ ", createdAt=" + createdAt + ", modifiedOn=" + modifiedOn + ", fine=" + fine + ", studentfk="
				+ studentfk + "]";
	}
	
	
	
	
}

