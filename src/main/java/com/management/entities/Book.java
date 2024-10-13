package com.management.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int primaryKey;
	private int serailNo;
//	private String bookname;
	private String libraryName;      //done
	private String isbnNumber;
//	private String semesterName;
	private String libraryItemType;
	private String publicationYear;
	@Column(length = 500)
	private String booktitle;
	private String bookAddedIn;
	private String bookCategory;
	private String itemStatus;
	private String subjectTitle;
	private String languageName;
	private String authorName;
	private String seriesNo;
	private String seriesTitle;
	private String bookPublisherName;
	private String bookPublisherPlace;
	private int bookquantity;
	private String authorName2;
	private String authorName3;
	private String authorName4;
	private String authorName5;
	private String callNo;
	private String authorMark;
	private String volume;
	private String edition;
	private String vendorName;
	private String vendorPlace;
	private String billNumber;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date billDate;
	private String orderNumber;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date orderDate;
	private String costOfItem;
	private String currentStatus;
	private String currencyName;
	private String discountAmount;
	private String rackNumber;
	private String shelfNumber;
	private String pagesNumber;
	private String prePages;
	private String size;
	private String typeOfBinding;
	private String keywords;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date publicationDate;
	private String location;
	private String editor;
	private String compiler;
	private String translator;
	private String abstracts;
	private String discountInPercantage;  // fasle or ture  
	private String subTitle;
	private String userName;
	private String employeeName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date entryDate;
	private String verified;
	private Date createdAt;
	private Date modifiedOn;
	
	
	 private String accessionNumber;
	// many books 
	@JsonIgnore
	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL )
	private List<bookDetail> booklist = new ArrayList<>();
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(int primaryKey,int serailNo,  String libraryName, String isbnNumber, 
			String libraryItemType, String publicationYear, String booktitle, String bookAddedIn, String bookCategory,
			String itemStatus, String subjectTitle, String languageName, String authorName, String seriesNo,
			String seriesTitle, String bookPublisherName, String bookPublisherPlace, int bookquantity,
			String authorName2, String authorName3, String authorName4, String authorName5, String callNo,
			String authorMark, String volume, String edition, String vendorName, String vendorPlace, String billNumber,
			Date billDate, String orderNumber, Date orderDate, String costOfItem, String currentStatus,
			String currencyName, String discountAmount, String rackNumber, String shelfNumber, String pagesNumber,
			String prePages, String size, String typeOfBinding, String keywords, Date publicationDate,
			String location, String editor, String compiler, String translator, String abstracts,
			String discountInPercantage, String subTitle, String userName, String employeeName, Date entryDate,
			String verified, Date createdAt, Date modifiedOn, String accessionNumber,  List<bookDetail> booklist) {
		super();
		this.primaryKey = primaryKey;
		this.serailNo=serailNo;
		this.libraryName = libraryName;
		this.isbnNumber = isbnNumber;
	//	this.semesterName = semesterName;
		this.libraryItemType = libraryItemType;
		this.publicationYear = publicationYear;
		this.booktitle = booktitle;
		this.bookAddedIn = bookAddedIn;
		this.bookCategory = bookCategory;
		this.itemStatus = itemStatus;
		this.subjectTitle = subjectTitle;
		this.languageName = languageName;
		this.authorName = authorName;
		this.seriesNo = seriesNo;
		this.seriesTitle = seriesTitle;
		this.bookPublisherName = bookPublisherName;
		this.bookPublisherPlace = bookPublisherPlace;
		this.bookquantity = bookquantity;
		this.authorName2 = authorName2;
		this.authorName3 = authorName3;
		this.authorName4 = authorName4;
		this.authorName5 = authorName5;
		this.callNo = callNo;
		this.authorMark = authorMark;
		this.volume = volume;
		this.edition = edition;
		this.vendorName = vendorName;
		this.vendorPlace = vendorPlace;
		this.billNumber = billNumber;
		this.billDate = billDate;
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.costOfItem = costOfItem;
		this.currentStatus = currentStatus;
		this.currencyName = currencyName;
		this.discountAmount = discountAmount;
		this.rackNumber = rackNumber;
		this.shelfNumber = shelfNumber;
		this.pagesNumber = pagesNumber;
		this.prePages = prePages;
		this.size = size;
		this.typeOfBinding = typeOfBinding;
		this.keywords = keywords;
		this.publicationDate = publicationDate;
		this.location = location;
		this.editor = editor;
		this.compiler = compiler;
		this.translator = translator;
		this.abstracts = abstracts;
		this.discountInPercantage = discountInPercantage;
		this.subTitle = subTitle;
		this.userName = userName;
		this.employeeName = employeeName;
		this.entryDate = entryDate;
		this.verified = verified;
		this.createdAt = createdAt;
		this.modifiedOn = modifiedOn;
		
		this.booklist = booklist;
		this.accessionNumber= accessionNumber;
	}
	

	public Book(Book booknames) {
		// TODO Auto-generated constructor stub
		this.primaryKey = primaryKey;
		this.serailNo=serailNo;
		this.libraryName = libraryName;
		this.isbnNumber = isbnNumber;
	//	this.semesterName = semesterName;
		this.libraryItemType = libraryItemType;
		this.publicationYear = publicationYear;
		this.booktitle = booktitle;
		this.bookAddedIn = bookAddedIn;
		this.bookCategory = bookCategory;
		this.itemStatus = itemStatus;
		this.subjectTitle = subjectTitle;
		this.languageName = languageName;
		this.authorName = authorName;
		this.seriesNo = seriesNo;
		this.seriesTitle = seriesTitle;
		this.bookPublisherName = bookPublisherName;
		this.bookPublisherPlace = bookPublisherPlace;
		this.bookquantity = bookquantity;
		this.authorName2 = authorName2;
		this.authorName3 = authorName3;
		this.authorName4 = authorName4;
		this.authorName5 = authorName5;
		this.callNo = callNo;
		this.authorMark = authorMark;
		this.volume = volume;
		this.edition = edition;
		this.vendorName = vendorName;
		this.vendorPlace = vendorPlace;
		this.billNumber = billNumber;
		this.billDate = billDate;
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.costOfItem = costOfItem;
		this.currentStatus = currentStatus;
		this.currencyName = currencyName;
		this.discountAmount = discountAmount;
		this.rackNumber = rackNumber;
		this.shelfNumber = shelfNumber;
		this.pagesNumber = pagesNumber;
		this.prePages = prePages;
		this.size = size;
		this.typeOfBinding = typeOfBinding;
		this.keywords = keywords;
		this.publicationDate = publicationDate;
		this.location = location;
		this.editor = editor;
		this.compiler = compiler;
		this.translator = translator;
		this.abstracts = abstracts;
		this.discountInPercantage = discountInPercantage;
		this.subTitle = subTitle;
		this.userName = userName;
		this.employeeName = employeeName;
		this.entryDate = entryDate;
		this.verified = verified;
		this.createdAt = createdAt;
		this.modifiedOn = modifiedOn;
		
		this.booklist = booklist;
		this.accessionNumber= accessionNumber;
	}

	public int getSerailNo() {
		return serailNo;
	}

	public void setSerailNo(int serailNo) {
		this.serailNo = serailNo;
	}

	public int getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getLibraryName() {
		return libraryName;
	}

	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}

	public String getIsbnNumber() {
		return isbnNumber;
	}

	public void setIsbnNumber(String isbnNumber) {
		this.isbnNumber = isbnNumber;
	}

	public String getLibraryItemType() {
		return libraryItemType;
	}

	public void setLibraryItemType(String libraryItemType) {
		this.libraryItemType = libraryItemType;
	}

	public String getBooktitle() {
		return booktitle;
	}

	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}

	public String getBookAddedIn() {
		return bookAddedIn;
	}

	public void setBookAddedIn(String bookAddedIn) {
		this.bookAddedIn = bookAddedIn;
	}

	public String getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}

	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}
	public String getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(String publicationYear) {
		this.publicationYear = publicationYear;
	}
	public String getSubjectTitle() {
		return subjectTitle;
	}

	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getSeriesNo() {
		return seriesNo;
	}

	public String getAuthorName4() {
		return authorName4;
	}
	public void setAuthorName4(String authorName4) {
		this.authorName4 = authorName4;
	}
	public String getAuthorName5() {
		return authorName5;
	}
	public void setAuthorName5(String authorName5) {
		this.authorName5 = authorName5;
	}
	public void setSeriesNo(String seriesNo) {
		this.seriesNo = seriesNo;
	}

	public String getSeriesTitle() {
		return seriesTitle;
	}

	public void setSeriesTitle(String seriesTitle) {
		this.seriesTitle = seriesTitle;
	}

	public String getAccessionNumber() {
		return accessionNumber;
	}

	public void setAccessionNumber(String accessionNumber) {
		this.accessionNumber = accessionNumber;
	}

	public String getBookPublisherName() {
		return bookPublisherName;
	}

	public void setBookPublisherName(String bookPublisherName) {
		this.bookPublisherName = bookPublisherName;
	}

	public String getBookPublisherPlace() {
		return bookPublisherPlace;
	}

	public void setBookPublisherPlace(String bookPublisherPlace) {
		this.bookPublisherPlace = bookPublisherPlace;
	}

	

	public int getBookquantity() {
		return bookquantity;
	}

	public void setBookquantity(int bookquantity) {
		this.bookquantity = bookquantity;
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
	
	public List<bookDetail> getBooklist() {
		return booklist;
	}

	public void setBooklist(List<bookDetail> booklist) {
		this.booklist = booklist;
	}

	public String getAuthorName2() {
		return authorName2;
	}

	public void setAuthorName2(String authorName2) {
		this.authorName2 = authorName2;
	}

	public String getAuthorName3() {
		return authorName3;
	}

	public void setAuthorName3(String authorName3) {
		this.authorName3 = authorName3;
	}

	public String getAuthorMark() {
		return authorMark;
	}

	public void setAuthorMark(String authorMark) {
		this.authorMark = authorMark;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getVendorPlace() {
		return vendorPlace;
	}

	public void setVendorPlace(String vendorPlace) {
		this.vendorPlace = vendorPlace;
	}

	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getCostOfItem() {
		return costOfItem;
	}

	public void setCostOfItem(String costOfItem) {
		this.costOfItem = costOfItem;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getRackNumber() {
		return rackNumber;
	}

	public void setRackNumber(String rackNumber) {
		this.rackNumber = rackNumber;
	}

	public String getShelfNumber() {
		return shelfNumber;
	}

	public void setShelfNumber(String shelfNumber) {
		this.shelfNumber = shelfNumber;
	}

	public String getPagesNumber() {
		return pagesNumber;
	}

	public void setPagesNumber(String pagesNumber) {
		this.pagesNumber = pagesNumber;
	}

	public String getPrePages() {
		return prePages;
	}

	public void setPrePages(String prePages) {
		this.prePages = prePages;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getTypeOfBinding() {
		return typeOfBinding;
	}

	public void setTypeOfBinding(String typeOfBinding) {
		this.typeOfBinding = typeOfBinding;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getCompiler() {
		return compiler;
	}

	public void setCompiler(String compiler) {
		this.compiler = compiler;
	}

	public String getTranslator() {
		return translator;
	}

	public void setTranslator(String translator) {
		this.translator = translator;
	}

	public String getAbstracts() {
		return abstracts;
	}

	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public String getVerified() {
		return verified;
	}

	public void setVerified(String verified) {
		this.verified = verified;
	}

	public String getCallNo() {
		return callNo;
	}
	public void setCallNo(String callNo) {
		this.callNo = callNo;
	}
	public String getDiscountInPercantage() {
		return discountInPercantage;
	}
	public void setDiscountInPercantage(String discountInPercantage) {
		this.discountInPercantage = discountInPercantage;
	}

	@Override
	public String toString() {
		return "Book [primaryKey=" + primaryKey + ", libraryName=" + libraryName
				+ ", isbnNumber=" + isbnNumber + ", libraryItemType="
				+ libraryItemType + ", publicationYear=" + publicationYear + ", booktitle=" + booktitle
				+ ", bookAddedIn=" + bookAddedIn + ", bookCategory=" + bookCategory + ", itemStatus=" + itemStatus
				+ ", subjectTitle=" + subjectTitle + ", languageName=" + languageName + ", authorName=" + authorName
				+ ", seriesNo=" + seriesNo + ", seriesTitle=" + seriesTitle + ", bookPublisherName=" + bookPublisherName
				+ ", bookPublisherPlace=" + bookPublisherPlace + ", bookquantity=" + bookquantity + ", authorName2="
				+ authorName2 + ", authorName3=" + authorName3 + ", callNo=" + callNo + ", authorMark=" + authorMark
				+ ", volume=" + volume + ", edition=" + edition + ", vendorName=" + vendorName + ", vendorPlace="
				+ vendorPlace + ", billNumber=" + billNumber + ", billDate=" + billDate + ", orderNumber=" + orderNumber
				+ ", orderDate=" + orderDate + ", costOfItem=" + costOfItem + ", currentStatus=" + currentStatus
				+ ", currencyName=" + currencyName + ", discountAmount=" + discountAmount + ", rackNumber=" + rackNumber
				+ ", shelfNumber=" + shelfNumber + ", pagesNumber=" + pagesNumber + ", prePages=" + prePages + ", size="
				+ size + ", typeOfBinding=" + typeOfBinding + ", keywords=" + keywords + ", publicationDate="
				+ publicationDate + ", location=" + location + ", editor=" + editor + ", compiler=" + compiler
				+ ", translator=" + translator + ", abstracts=" + abstracts + ", discountInPercantage="
				+ discountInPercantage + ", subTitle=" + subTitle + ", userName=" + userName + ", employeeName="
				+ employeeName + ", entryDate=" + entryDate + ", verified=" + verified + ", createdAt=" + createdAt
				+ ", modifiedOn=" + modifiedOn + ", accessionNumber=" + accessionNumber + "]";
	}
}
