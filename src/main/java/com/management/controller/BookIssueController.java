package com.management.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.poi.EncryptedDocumentException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.Dao.BookDetailRepository;
import com.management.Dao.BookIssueRepository;
import com.management.Dao.RegisterRepository;
import com.management.entities.Book;
import com.management.entities.BookIssue;
import com.management.entities.Student;
import com.management.entities.bookDetail;

@Controller
public class BookIssueController {

	@Autowired
	RegisterRepository registerRepository;

	@Autowired
	BookIssueRepository bookissueRepository;
	
	@Autowired
	BookDetailRepository bookDetailRepository;

	@PostMapping("bookissue")
	@ResponseBody
	public String Addbookissue(@ModelAttribute BookIssue bookissue, Model model) {
		String msg = null;
//		try {
//			BookIssue uniquebookassignnames = this.bookissueRepository.getuniquebookassignnames("Book Issued" , bookissue.getRollnumberid(), bookissue.getSelectbook());
//			Book bookobj= this.registerRepository.getselectBookName(bookissue.getSelectbook());
//			int leftbook = bookobj.getBookquantity() - bookissue.getBookQuantityid();
//			if(leftbook <= -1) {
//				msg= "There is no book " + bookissue.getSelectbook();
//			} else if(uniquebookassignnames == null) {
//				this.registerRepository.updateleftbook(leftbook, bookissue.getSelectbook());
//			
//				LocalDate updatedDate = LocalDate.now().plusDays(10);				 
//			    Instant instant = updatedDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
//		        Date tendaysdate = Date.from(instant);
//			    bookissue.setReturnDate(tendaysdate);
//				bookissue.setCreatedAt(new Date());
//				bookissue.setModifiedOn(new Date());
//				bookissue.setStatus("Book Issued");
//				bookissue.setFine("0 Rs.");
//				BookIssue register1 = this.registerRepository.save(bookissue);
//				msg="Book Issued Successfully";
//			}else if(uniquebookassignnames.getSelectbook().equals(bookissue.getSelectbook()) 
//					&& uniquebookassignnames.getSelectStudent().equals(bookissue.getSelectStudent())){
//				return "Please Return the book " + bookissue.getSelectbook() ;
//			}else {
//				this.registerRepository.updateleftbook(leftbook, bookissue.getSelectbook());
//				bookissue.setCreatedAt(new Date());
//				bookissue.setModifiedOn(new Date());
//				bookissue.setStatus("Book Issued");
//				bookissue.setFine("0 Rs.");
//				BookIssue register1 = this.registerRepository.save(bookissue);
//				msg="Book Issued Successfully";
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			msg="error";
//		}
		return msg;
	}

	@PostMapping("/fetchbookissue")
	@ResponseBody
	public BookIssue fetchbookissue(@RequestBody String primaryKey) {
		BookIssue bookisuerecord = this.registerRepository.getAllfetchbookisseBYID(primaryKey);

		Date cuurentdate = new Date();
		LocalDate currentdatebookreturn = cuurentdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		Date returnbookissue = bookisuerecord.getReturnDate();
		LocalDate actualreturnDate = returnbookissue.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		long daysDifferencebookreturn = ChronoUnit.DAYS.between(actualreturnDate, currentdatebookreturn);
		System.out.println(" daysDifferencebookreturn	" + daysDifferencebookreturn);
		if (daysDifferencebookreturn >= 0 && bookisuerecord.getStatus().equals("Book Issue")) {
			int multiplydaysfine = (int) (daysDifferencebookreturn * 5);
			String calculatefine = String.valueOf(multiplydaysfine);
			bookisuerecord.setFine(calculatefine + " Rs.");
		} else {
			bookisuerecord.setFine("0 Rs.");
		}
		return bookisuerecord;
	}

	@PostMapping("/deletebookissuePrimary")
	@ResponseBody
	public String handleDeletebookissueRequest(@RequestBody String primaryKey) {
		String msg;
		int pk = Integer.parseInt(primaryKey);
		System.out.println("Primary Key: " + pk);

		try {
			this.registerRepository.deleteBySomeFieldbookissue(pk);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		// Return an appropriate response (success, error, etc.)
		return "Done";
	}

	@PostMapping("/updatebookissueID")
	@ResponseBody
	public String updateBookissueID(@ModelAttribute BookIssue bookissue) {

		String msg = null;
		try {
			Book bookobj = this.registerRepository.getselectBookName(bookissue.getSelectbook());
			int addbook = bookobj.getBookquantity() + 1;
			Optional<BookIssue> findbookissue = this.bookissueRepository.findById(bookissue.getPrimaryKey());
			if (findbookissue.isPresent()) {
				BookIssue updatebookissue = findbookissue.get();

				updatebookissue.setStatus(bookissue.getStatus());

				updatebookissue.setFine("0 Rs.");

				updatebookissue.setModifiedOn(new Date());
				System.out.println("updatebookissue---" + updatebookissue);
				this.registerRepository.updateleftbook(addbook, bookobj.getPrimaryKey());
				BookIssue save = this.bookissueRepository.save(updatebookissue);
				msg = "done";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}

		return msg;

	}
	
	@PostMapping("issue")
	@ResponseBody
	public com.management.entities.Student issue(@RequestBody Map<String, String> requestData) {
	    String rollNumber = requestData.get("rollNumber");
	    String studentName = requestData.get("studentName");
	    String mobile = requestData.get("mobileNu");
	    com.management.entities.Student student = null;
	    if (rollNumber != null && !rollNumber.isEmpty()) {
	        student = this.registerRepository.getstudentrollnumberobject(rollNumber);
	    } else if ((studentName != null && !studentName.isEmpty()) && (mobile != null && !mobile.isEmpty())) {
	    	System.out.println("stu name " + studentName);
		    System.out.println("stu mobile " + mobile);
	        student = this.registerRepository.getfullnameandmobile(studentName, mobile);
	    }
	    return student != null ? student : null;
	}
	
	@PostMapping("retrunBookWithoutAccession")
	@ResponseBody
	public BookIssue retrunBookWithoutAccession(@RequestBody Map<String, String> requestData) {
	    String AccessionNumber = requestData.get("AccessionNumber");
	    BookIssue bookiss= this.bookissueRepository.withoutAccessionNumberFind("Book Issue",AccessionNumber);
		return bookiss;
	}
	
	@PostMapping("accessionFetchBook")
	@ResponseBody
	public Book accessionFetchBook(@RequestBody String accessionNumber) {
		bookDetail fectaccession = this.bookDetailRepository.getrecordaccessionNumber(accessionNumber);
		if (fectaccession != null) {
			Book bookrecord = this.registerRepository.gebookDetailsByPk(fectaccession.getBook().getPrimaryKey());
			return bookrecord;
		} else {
			return null;
		}
	}

	/*
	 * @PostMapping("/fetchsemesterbook")
	 * 
	 * @ResponseBody public List<String> fetchsemesterbook(@RequestBody String
	 * semester) { List<String> t =
	 * this.registerRepository.getfetchsemesterbook(semester);
	 * 
	 * return t; }
	 */

	@PostMapping("/displayintableissue")
	@ResponseBody
	public List<BookIssue> displayintableissue(@RequestBody String rollnumber) {
		List<BookIssue> returnobj = this.registerRepository.getAllbookissuetablerecord(rollnumber, "Book Issue");
		System.out.println("returnobj --- " + returnobj);
		return returnobj;
	}
	
	@PostMapping("/returnbookbutton")
	@ResponseBody
	public String returnbookbutton(@RequestBody String primaryKey) {
		int pk = Integer.parseInt(primaryKey);
		
		BookIssue bkissue = this.registerRepository.getAllfetchbookisseBYID(primaryKey);
		
		System.out.println("book name -- " + bkissue.getBookfk());
		
		Book bookobj = this.registerRepository.getselectBookNamePK(bkissue.getBookfk());
		
		int addbook = bookobj.getBookquantity() + 1;
		
		this.registerRepository.updateaddbookQuantity(addbook, bookobj.getPrimaryKey());
		
		Date date= new Date();
		System.out.println(" date student -- " + date);

		this.registerRepository.updatestatusbook("Book Return",date,"Return Book",pk);

		
		return "Book Retruned Successfully";
		
	}

	@PostMapping("/displayintableReturn")
	@ResponseBody
	public List<BookIssue> displayintableReturn(@RequestBody String rollnumber) {
		List<BookIssue> t = this.registerRepository.getAllbookissuetablerecord(rollnumber, "Book Return");
		return t;
	}

	@PostMapping("/optionbookselecet")
	@ResponseBody
	public String getData(@RequestBody Map<String, Object> requestData) {
		JSONObject json = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		String msg=null;
		try {
			String accessionNumber = (String) requestData.get("accessionNumber");
			String SelectBookBank = (String) requestData.get("SelectBookBank");
			Student student = objectMapper.convertValue(requestData.get("student"), Student.class);
	        LocalDate updatedDate = LocalDate.now().plusDays(10);
			Instant instant = updatedDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
			Date tendaysdate = Date.from(instant);
			Book bookrecord =null;
	        bookDetail fectaccession = this.bookDetailRepository.getrecordaccessionNumber(accessionNumber);
		    if (fectaccession != null) {
		    	 bookrecord = this.registerRepository.gebookDetailsByPk(fectaccession.getBook().getPrimaryKey());
			} else {
					msg = "No Book found!";
					json.put("message", msg);
					return json.toString();

			}
		    BookIssue uniquebookassignnames = this.bookissueRepository.getuniquebookassignnames(accessionNumber,"Book Issue" , student.getRollnumber(), bookrecord.getBooktitle());
		    System.out.println(" uniquebookassignnames ----  " + uniquebookassignnames);
		    int leftbook = bookrecord.getBookquantity() - 1;
		    if (leftbook <= -1) {
				msg= "There is no book " + bookrecord.getBooktitle();
				json.put("message", msg);
				return json.toString();
			}else if (uniquebookassignnames == null) {
				BookIssue bookissue = new BookIssue();
				bookissue.setBookfk(bookrecord.getPrimaryKey());
				bookissue.setStudentfk(student.getPrimaryKey());
				bookissue.setRollnumberid(student.getRollnumber());
				bookissue.setSelectStudent(student.getFullName());
				BookIssue boobj= this.bookissueRepository.getAssignbookname(bookrecord.getBooktitle() , "Book Issue",student.getRollnumber() );
				if(boobj != null) {
					msg = "Already Book Assigned " + bookrecord.getBooktitle();
					json.put("message", msg);
					return json.toString();
				}else {
					bookissue.setSelectbook(bookrecord.getBooktitle());
				}
				BookIssue samebookAssce= this.bookissueRepository.getSameBookAsscession("Book Issue",accessionNumber );
				if(samebookAssce != null){
					msg = "Already Book Assigned fot this " + accessionNumber;
					json.put("message", msg);
					return json.toString();
				}
				else {
					bookissue.setAccessionNumber(accessionNumber);
				}
				bookissue.setBookQuantityid(1);
				bookissue.setStatus("Book Issue");
				bookissue.setIssueBookDate(new Date());
				bookissue.setReturnDate(tendaysdate);
				bookissue.setBranch(student.getSelectBranch());
				bookissue.setSemester(student.getSelectSemester());
				bookissue.setDuebook("No Due");
				bookissue.setCreatedAt(new Date());
				bookissue.setModifiedOn(new Date());
				bookissue.setFine("0 Rs.");
				if(SelectBookBank == null) {
					msg= "Select book Bank !";
					json.put("message", msg);
					return json.toString();
				}else {
					bookissue.setBookBank(SelectBookBank);
				}
				System.out.println(bookissue.toString());
				this.registerRepository.save(bookissue);
				this.registerRepository.updateleftbookPK(leftbook, bookrecord.getPrimaryKey());
				msg= "Book Issued Successfully";
				json.put("message", msg);
				return json.toString();

				
			} else if (uniquebookassignnames.getSelectbook().equals(bookrecord.getBooktitle())&& uniquebookassignnames.getAccessionNumber().equals(accessionNumber)
				&& uniquebookassignnames.getSelectStudent().equals(student.getFullName())) {
				msg= "Please Return the book " + bookrecord.getBooktitle();
				json.put("message", msg);
				return json.toString();
		}
		   
			} catch (Exception e) {
				e.printStackTrace();
			}
		json.put("message", msg);
		return json.toString();
	}

	public void findAllBookIssue() {
	    List<BookIssue> allbookissue= this.bookissueRepository.findallbookissue("Book Issue");	    
	    Date cuurentdate = new Date();
		LocalDate currentdatebookreturn = cuurentdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		for(BookIssue obj : allbookissue) {
			if(obj.getBookBank().equals("no")) {
				Date returnbookissue = obj.getReturnDate();
				LocalDate actualreturnDate = returnbookissue.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				long daysDifferencebookreturn = ChronoUnit.DAYS.between(actualreturnDate, currentdatebookreturn);
				System.out.println(" daysDifferencebookreturn	" + daysDifferencebookreturn);
				if (daysDifferencebookreturn >= 0 && obj.getStatus().equals("Book Issue") ) {
					int multiplydaysfine = (int) (daysDifferencebookreturn * 5);
					String calculatefine = String.valueOf(multiplydaysfine);
					this.registerRepository.updatecharges(calculatefine+ " Rs.", "Due Book", obj.getPrimaryKey());
				}
			}
		}
	}
}
