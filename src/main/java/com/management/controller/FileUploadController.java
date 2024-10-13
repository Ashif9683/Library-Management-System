package com.management.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.util.unit.DataSize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.management.Dao.BookDetailRepository;
import com.management.Dao.RegisterBookRepository;
import com.management.Dao.RegisterRepository;
import com.management.entities.Book;
import com.management.entities.Student;
import com.management.entities.bookDetail;
import com.management.entities.user;

import jakarta.servlet.http.HttpSession;

@RestController
public class FileUploadController {

	@Autowired
	RegisterRepository registerRepository;

	@Autowired
	BookDetailRepository bookdetailRepository;
	@Autowired
	RegisterBookRepository registerBookRepository;

	@PostMapping("/uploadStudentExcel")
	public String handleFileUploadStudent(@RequestPart("file") MultipartFile file) {
		String msg = null;
		try {

			Workbook workbook = WorkbookFactory.create(file.getInputStream());
			Sheet sheet = workbook.getSheetAt(0); // Assuming first sheet
			List<Student> students = new ArrayList<>();
			//int lastGeneratedmaxserailStud = getLastGeneratedSerialNumberStudent();
			for (Row row : sheet) {
				if (row.getRowNum() == 0)
					continue; // Skip header row
				List<String> getrollnumberList = this.registerRepository
						.getselectRollNumberexits(row.getCell(0).getStringCellValue());
				if (!getrollnumberList.isEmpty()) {
					msg = "Roll Number Already Exits !! " + getrollnumberList;
					return msg;
				}
				List<String> getemailList = this.registerRepository
						.getselectemailexits(row.getCell(4).getStringCellValue());
				if (!getemailList.isEmpty()) {
					msg = "Email Already Exits !! " + getemailList;
					return msg;
				}
				Student student = new Student();
				if (getrollnumberList.isEmpty() && getemailList.isEmpty()) {
					student.setRollnumber(getStringCellValue(row, 0));
					student.setFullName(getStringCellValue(row, 1));
					student.setFatherName(getStringCellValue(row, 2));
					student.setPhone(getStringCellValue(row, 3));
					student.setEmail(getStringCellValue(row, 4));
					student.setSelectBranch(getStringCellValue(row, 5));
					student.setSelectYear(getStringCellValue(row, 6));
					student.setSelectSemester(getStringCellValue(row, 7));
					student.setBookBank(getStringCellValue(row, 8));
					student.setBookAnnually(getStringCellValue(row, 9));
					student.setCreatedAt(new Date());
					student.setModifiedOn(new Date());
					students.add(student);
				}
			}
			registerRepository.saveAll(students);
			workbook.close();
			msg = "Uploaded student data saved successfully";
			return msg;
		} catch (IOException | EncryptedDocumentException ex) {
			ex.printStackTrace();
		}
		return msg;
	}

	@PostMapping("/uploadBookExcel")
	public String handleFileUploadBookRecord(@RequestPart("file") MultipartFile file, HttpSession session) {
		String msg = null;

		// Configure the maximum file size
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize(DataSize.ofMegabytes(10));
		factory.setMaxRequestSize(DataSize.ofMegabytes(10));

		try {
			user User = (user) session.getAttribute("fectchUsernameAndPassword");
			Workbook workbook = WorkbookFactory.create(file.getInputStream());
			Sheet sheet = workbook.getSheetAt(0); // Assuming first sheet
			List<Book> books = new ArrayList<>();
			//int lastGeneratedAsscessionNumber = getLastGeneratedAsscessionNumber();
			int lastGeneratedNumber = 0;
		    int lastGeneratedCode = 0;
			//int lastMaxSerailNoBookDetail = getLastGeneratedSerialNumberbookdetail();
		//	int lastMaxSerailNoBook = getLastGeneratedSerialNumberbook();
			if (User != null) {
				for (Row row : sheet) {
					if (row.getRowNum() == 0)
						continue; // Skip header row
					Book book = new Book();
					List<bookDetail> bookDetails = new ArrayList<>();
					String getAssessioncode= getStringCellValue(row, 1);
					if(getAssessioncode.isEmpty()) {
				    	  lastGeneratedNumber = getLastGeneratedNumber();
				    }else {
				    	lastGeneratedCode = getLastGeneratedCode(getAssessioncode);
				    }
					book.setLibraryName(getStringCellValue(row, 0));
					book.setIsbnNumber(getStringCellValue(row, 2));
					book.setLibraryItemType(getStringCellValue(row, 3));
					int bookQuantity = getNumericCellValue(row, 4);
					book.setBookquantity(bookQuantity);
					book.setBooktitle(getStringCellValue(row, 5));
					book.setBookAddedIn(getStringCellValue(row, 6));
					book.setBookCategory(getStringCellValue(row, 7));
					book.setItemStatus(getStringCellValue(row, 8));
					book.setSubjectTitle(getStringCellValue(row, 9));
					book.setLanguageName(getStringCellValue(row, 10));
					book.setAuthorName(getStringCellValue(row, 11));
					book.setAuthorName2(getStringCellValue(row, 12));
					book.setAuthorName3(getStringCellValue(row, 13));
					book.setAuthorName4(getStringCellValue(row, 14));
					book.setAuthorName5(getStringCellValue(row, 15));
					book.setCallNo(getStringCellValue(row, 16));
					book.setAuthorMark(getStringCellValue(row, 17));
					book.setVolume(getStringCellValue(row, 18));
					book.setEdition(getStringCellValue(row, 19));
					book.setSeriesNo(getStringCellValue(row, 20));
					book.setSeriesTitle(getStringCellValue(row, 21));
					book.setBookPublisherName(getStringCellValue(row, 22));
					book.setBookPublisherPlace(getStringCellValue(row, 23));
					book.setVendorName(getStringCellValue(row, 24));
					book.setVendorPlace(getStringCellValue(row, 25));
					book.setPublicationYear(getStringCellValue(row, 26));
					book.setBillNumber(getStringCellValue(row, 27));
					book.setBillDate(getDateCellValue(row, 28));
					book.setOrderNumber(getStringCellValue(row, 29));
					book.setOrderDate(getDateCellValue(row, 30));
					book.setCostOfItem(getStringCellValue(row, 31));
					book.setCurrentStatus(getStringCellValue(row, 32));
					book.setCurrencyName(getStringCellValue(row, 33));
					book.setDiscountAmount(getStringCellValue(row, 34));
					book.setRackNumber(getStringCellValue(row, 35));
					book.setShelfNumber(getStringCellValue(row, 36));
					book.setPagesNumber(getStringCellValue(row, 37));
					book.setPrePages(getStringCellValue(row, 38));
					book.setSize(getStringCellValue(row, 39));
					book.setTypeOfBinding(getStringCellValue(row, 40));
					book.setKeywords(getStringCellValue(row, 41));
					book.setPublicationDate(getDateCellValue(row, 42));
					book.setLocation(getStringCellValue(row, 43));
					book.setEditor(getStringCellValue(row, 44));
					book.setCompiler(getStringCellValue(row, 45));
					book.setTranslator(getStringCellValue(row, 46));
					book.setAbstracts(getStringCellValue(row, 47));
					book.setDiscountInPercantage(getStringCellValue(row, 48));
					book.setSubTitle(getStringCellValue(row, 49));
					book.setEntryDate(getDateCellValue(row, 50));
					book.setVerified(getStringCellValue(row, 51));
					book.setUserName(User.getUsername());
					book.setEmployeeName(User.getEmployeename());
					book.setCreatedAt(new Date());
					book.setModifiedOn(new Date());
					for (int i = 0; i < bookQuantity; i++) {
						bookDetail bookDetail = new bookDetail();
						if(getAssessioncode.isEmpty()) {
							System.out.println("lastGeneratedNumber ---  "  + lastGeneratedNumber);
							String s =String.valueOf(lastGeneratedNumber);   
					    	bookDetail.setAccessionNumber(s);
					    	lastGeneratedNumber++;
					    }else {
					    	String changestring = String.valueOf(lastGeneratedCode); 
					    	System.out.println(" changestring String -------   " +changestring);
					    	String append = getAssessioncode.concat("-"+changestring);
					    	String s =String.valueOf(append);   
					    	bookDetail.setAccessionNumber(s);
					    	lastGeneratedCode++;
					    }
						bookDetail.setAccessionDate(new Date());
						bookDetail.setCreatedAt(new Date());
						bookDetail.setModifiedOn(new Date());
						bookDetail.setBook(book);
						bookDetail.setBooknameid(book.getBooktitle());
						bookDetails.add(bookDetail);
					}
					book.setBooklist(bookDetails);
					books.add(book);
				}
				registerBookRepository.saveAll(books);
				workbook.close();
				msg = "Uploaded Book data saved successfully";
			} else {
				msg = "User not found in session";
			}
		} catch (IOException | EncryptedDocumentException ex) {
			ex.printStackTrace();
			msg = "Something Went Wrong!!";
		}
		return msg;
	}

	@PostMapping("/uploadBookExcelMasterData")
	public String handleFileUploadBookRecordMaster(@RequestPart("file") MultipartFile file, HttpSession session) {
		String msg = null;
		// Configure the maximum file size
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize(DataSize.ofMegabytes(10));
		factory.setMaxRequestSize(DataSize.ofMegabytes(10));
		try {
			user User = (user) session.getAttribute("fectchUsernameAndPassword");
			Workbook workbook = WorkbookFactory.create(file.getInputStream());
			Sheet sheet = workbook.getSheetAt(0); // Assuming first sheet
			List<Book> books = new ArrayList<>();
			if (User != null) {
				for (Row row : sheet) {
					if (row.getRowNum() == 0)
						continue; 
					Book book = new Book();
					List<bookDetail> bookDetails = new ArrayList<>();
					book.setLibraryName(getStringCellValue(row, 0));
					book.setIsbnNumber(getStringCellValue(row, 4));
					book.setLibraryItemType(getStringCellValue(row, 5));
					int bookQuantity = getNumericCellValue(row, 6);
					book.setBookquantity(bookQuantity);
					book.setBooktitle(getStringCellValue(row, 7));
					book.setBookAddedIn(getStringCellValue(row, 8));
					book.setBookCategory(getStringCellValue(row, 9));
					book.setItemStatus(getStringCellValue(row, 10));
					book.setSubjectTitle(getStringCellValue(row, 11));
					book.setLanguageName(getStringCellValue(row, 12));
					book.setAuthorName(getStringCellValue(row, 13));
					book.setAuthorName2(getStringCellValue(row, 14));
					book.setAuthorName3(getStringCellValue(row, 15));
					book.setAuthorName4(getStringCellValue(row, 16));
					book.setAuthorName5(getStringCellValue(row, 17));
					book.setCallNo(getStringCellValue(row, 18));
					book.setAuthorMark(getStringCellValue(row, 19));
					book.setVolume(getStringCellValue(row, 20));
					book.setEdition(getStringCellValue(row, 21));
					book.setSeriesNo(getStringCellValue(row, 22));
					book.setSeriesTitle(getStringCellValue(row, 23));
					book.setBookPublisherName(getStringCellValue(row, 24));
					book.setBookPublisherPlace(getStringCellValue(row, 25));
					book.setVendorName(getStringCellValue(row, 26));
					book.setVendorPlace(getStringCellValue(row, 27));
					book.setPublicationYear(getStringCellValue(row, 28));
					book.setBillNumber(getStringCellValue(row, 29));
					book.setBillDate(getDateCellValue(row, 30));
					book.setOrderNumber(getStringCellValue(row, 31));
					book.setOrderDate(getDateCellValue(row, 32));
					book.setCostOfItem(getStringCellValue(row, 33));
					book.setCurrentStatus(getStringCellValue(row, 34));
					book.setCurrencyName(getStringCellValue(row, 35));
					book.setDiscountAmount(getStringCellValue(row, 36));
					book.setRackNumber(getStringCellValue(row, 37));
					book.setShelfNumber(getStringCellValue(row, 38));
					book.setPagesNumber(getStringCellValue(row, 39));
					book.setPrePages(getStringCellValue(row, 40));
					book.setSize(getStringCellValue(row, 41));
					book.setTypeOfBinding(getStringCellValue(row, 42));
					book.setKeywords(getStringCellValue(row, 43));
					book.setPublicationDate(getDateCellValue(row, 44));
					book.setLocation(getStringCellValue(row, 45));
					book.setEditor(getStringCellValue(row, 46));
					book.setCompiler(getStringCellValue(row, 47));
					book.setTranslator(getStringCellValue(row, 48));
					book.setAbstracts(getStringCellValue(row, 49));
					book.setDiscountInPercantage(getStringCellValue(row, 50));
					book.setSubTitle(getStringCellValue(row, 51));
					book.setEntryDate(getDateCellValue(row, 52));
					book.setVerified(getStringCellValue(row, 53));
					book.setUserName(User.getUsername());
					book.setEmployeeName(User.getEmployeename());
					book.setCreatedAt(new Date());
					book.setModifiedOn(new Date());
					for (int i = 0; i < bookQuantity; i++) {
						bookDetail bookDetail = new bookDetail();
						bookDetail.setAccessionNumber(getStringCellValue(row, 1));
						bookDetail.setAccessionDate(getDateCellValue(row, 3));
						bookDetail.setCreatedAt(new Date());
						bookDetail.setModifiedOn(new Date());
						bookDetail.setBook(book);
						bookDetail.setBooknameid(book.getBooktitle());
						bookDetails.add(bookDetail);
					}
					book.setBooklist(bookDetails);
					books.add(book);
				}
				registerBookRepository.saveAll(books);
				workbook.close();
				msg = "Uploaded Book data saved successfully";
			} else {
				msg = "User not found in session";
			}
		} catch (IOException | EncryptedDocumentException ex) {
			ex.printStackTrace();
			msg = "Something Went Wrong!!";
		}
		return msg;
	}

	private int getLastGeneratedAsscessionNumber() throws IOException {
		String MaxAccessNO = this.bookdetailRepository.findMaxAccessionNumber();
		if (MaxAccessNO != null) {
			return Integer.parseInt(MaxAccessNO) + 1;
		}
		return 1;
	}
	private int getLastGeneratedNumber() throws IOException {
		String maxnumeric = this.bookdetailRepository.findMaxAccessionNumber();
		System.out.println("maxnumeric ---  "  + maxnumeric);
		if (maxnumeric != null) {
			return Integer.parseInt(maxnumeric) + 1;
		}
		return 1;
	}
	private int getLastGeneratedCode(String code) {
		int replacealpha = 0;
		String pattern = code+"-%";
		int countpattern = pattern.length();
		String maxcode = this.bookdetailRepository.findCodeMax(pattern ,countpattern);
		if(maxcode != null) {
			String  replacealphastring = maxcode.replaceAll(code+"-", "");
			replacealpha = Integer.parseInt(replacealphastring) + 1;
			 System.out.println("replavealpha ---  "  + replacealpha);
			 return replacealpha;
		}
		return replacealpha;
	}
	/*private int getLastGeneratedSerialNumberbookdetail() throws IOException {
		Integer MaxSerailNo = this.bookdetailRepository.findMaxSerailNumber();
		if (MaxSerailNo == null) {
			return 1; // or any default value that makes sense in your context
		}
		return MaxSerailNo;
	} */

/*	private int getLastGeneratedSerialNumberbook() throws IOException {
		Integer MaxSerailNo = this.registerBookRepository.findMaxSerailNumber();
		if (MaxSerailNo == null) {
			return 1; // or any default value that makes sense in your context
		}
		return MaxSerailNo;
	} */

	/*private int getLastGeneratedSerialNumberStudent() throws IOException {
		Integer MaxSerailNoStudent = this.registerRepository.findMaxSerailNumberStudent();
		if (MaxSerailNoStudent == null) {
			return 1; // or any default value that makes sense in your context
		}
		return MaxSerailNoStudent;
	}  */

	private Date getDateCellValue(Row row, int cellIndex) {
		Cell cell = row.getCell(cellIndex);
		return cell != null ? cell.getDateCellValue() : null;
	}

	private String getStringCellValue(Row row, int cellIndex) {
		Cell cell = row.getCell(cellIndex);
		return cell != null ? cell.getStringCellValue() : "";
	}

	private int getNumericCellValue(Row row, int cellIndex) {
		Cell cell = row.getCell(cellIndex);
		return cell != null ? (int) cell.getNumericCellValue() : 0;
	}
}