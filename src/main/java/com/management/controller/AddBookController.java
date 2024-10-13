package com.management.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.management.Dao.BookDetailRepository;
import com.management.Dao.RegisterBookRepository;
import com.management.Dao.RegisterRepository;
import com.management.entities.Book;
import com.management.entities.Student;
import com.management.entities.bookDetail;
import com.management.entities.user;

import jakarta.servlet.http.HttpSession;

@Controller
public class AddBookController {

	@Autowired
	RegisterRepository registerRepository;

	@Autowired
	BookDetailRepository bookdetailRepository;

	@Autowired
	RegisterBookRepository registerbookRepository;
	
	@Autowired
	FileUploadController fileuploadcontroller;
	@PostMapping("addBook")
	@ResponseBody
	public String addBook(@ModelAttribute Book book, @ModelAttribute bookDetail bookdetailUI, Model model , HttpSession session) {
		String msg = null;
		List<bookDetail> bookDetails = new ArrayList<>();
		user User = (user) session.getAttribute("fectchUsernameAndPassword");
		try {
			//int lastMaxSerailNoBookDetail = getLastGeneratedSerialNumberbookdetail();
			//int lastMaxSerailNoBook = getLastGeneratedSerialNumberbook();
		if (User != null) {
			 String username = User.getUsername();
			 int lastGeneratedNumber = 0;
			    int lastGeneratedCode = 0;
			    if(bookdetailUI.getBookAccessionCode().isEmpty()) {
			    	  lastGeneratedNumber = getLastGeneratedNumber();
			    }else {
			    	lastGeneratedCode = getLastGeneratedCode(bookdetailUI.getBookAccessionCode());
			    }
				book.setCreatedAt(new Date());
				book.setModifiedOn(new Date());
				book.setUserName(User.getUsername());
				book.setEmployeeName(User.getEmployeename());
				book.setEntryDate(new Date());
				//book.setSerailNo(lastMaxSerailNoBook++);
				System.out.println(" bookdetailUI.getBookAccessionCode()  -------   " + bookdetailUI.getBookAccessionCode());
				for (int i = 1; i <= book.getBookquantity(); i++) {
					bookDetail bookDetail = new bookDetail(); 
					if(bookdetailUI.getBookAccessionCode().isEmpty()) {
						System.out.println("lastGeneratedNumber ---  "  + lastGeneratedNumber);
						String s =String.valueOf(lastGeneratedNumber);   
				    	bookDetail.setAccessionNumber(s);
				    	lastGeneratedNumber++;
				    }else {
				    	String changestring = String.valueOf(lastGeneratedCode); 
				    	System.out.println(" changestring String -------   " +changestring);
				    	String append = bookdetailUI.getBookAccessionCode().concat("-"+changestring);
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
				registerbookRepository.save(book);
				msg = "Book Added Successfully";
		} else {
		  
		    System.out.println("Username: " + User);

		}
		// List<String> getbooknameList=
		// this.registerRepository.getselectBookNamexits(book.getBookname());
		
			// if(getbooknameList.isEmpty()) {
			
			// }else {
			//msg = "Book Already Exits";
			// }
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		return msg;
	}

	@PostMapping("/deletebookPrimary")
	@ResponseBody
	public String deletebookPrimary(@RequestBody String primaryKey) {	
		String msg;
	        try {
	            int pk = Integer.parseInt(primaryKey);
	            Optional<Book> optionalBook = registerbookRepository.findById(pk);
	            System.out.println("optionalBook " + optionalBook);
	            if (optionalBook.isPresent()) {
	                Book book = optionalBook.get();
	                List<bookDetail> bookDetails = book.getBooklist();
	                for (bookDetail bookDetail : bookDetails) {
	    	            System.out.println("bookDetail " + bookDetail);
	                    bookdetailRepository.delete(bookDetail);
	                }
	                // Now delete the book itself
	                registerbookRepository.delete(book);
	                msg = "Done";
	            } else {
	                msg = "Book not found.";
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            msg = "An error occurred while deleting the book.";
	        }
	        return msg;
	}

	@PostMapping("/fetchBook")
	@ResponseBody
	public Book fetchBook(@RequestBody String primaryKey) {
		Book book = this.registerRepository.getBookBYID1(primaryKey);
		return book;
	}

	@PostMapping("/fetchallBook")
	@ResponseBody
	public List<String> fetchallBook(Model model) {
		List<String> book = this.registerRepository.getBooknames();
		model.addAttribute("booknames", book);
		return book;
	}

	@PostMapping("/updateBookID")
	@ResponseBody
	public String updateBookID(@ModelAttribute Book book) {
		String msg = null;
		System.out.println("edit hit ");
		try {
			Optional<Book> findbook = this.registerRepository.getBookBYIDint(book.getPrimaryKey());
			if (findbook.isPresent()) {
				Book updateBook = findbook.get();
				updateBook.setBooktitle(book.getBooktitle());
				updateBook.setAuthorName(book.getAuthorName());
				updateBook.setIsbnNumber(book.getIsbnNumber());
				updateBook.setBookquantity(book.getBookquantity());
				updateBook.setPublicationYear(book.getPublicationYear());
				updateBook.setModifiedOn(new Date());
				Book save = this.registerRepository.save(updateBook);
				msg = "done";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		return msg;
	}

//	@PostMapping("/addfilterform")
//	@ResponseBody
//	public List<Book> addfilter(@RequestParam("selectcategory") String selectcategory,@RequestParam("selectbook") String selectbook, Model model) {
//		List<Book> getfilter = this.registerRepository.getfilterbyname(selectcategory, selectbook);
//		model.addAttribute("getfilter", getfilter);
//		return getfilter;
//
//	}
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
/*	private int getLastGeneratedSerialNumberbookdetail() throws IOException {
		Integer MaxSerailNo = this.bookdetailRepository.findMaxSerailNumber();
		if (MaxSerailNo == null) {
			return 1; // or any default value that makes sense in your context
		}
		return MaxSerailNo;
	}  */

	/*private int getLastGeneratedSerialNumberbook() throws IOException {
		Integer MaxSerailNo = this.registerbookRepository.findMaxSerailNumber(); 
		if (MaxSerailNo == null) {
			return 1; // or any default value that makes sense in your context
		}
		return MaxSerailNo;
	}*/
	 @PostMapping("/fetchbookdetails")
	 @ResponseBody
	    public Book redirectToBookDetails(@RequestBody String primaryKey) {
		 int pk = Integer.parseInt(primaryKey);
		 Book booDet = this.registerRepository.gebookDetailsByPk(pk);			
	        return booDet;
	    }
	 
	 @PostMapping("/fetchallBookRecord")
	 @ResponseBody
	    public List<Book> fetchallStudentRecord() {
			List<Book> getbookList= this.registerRepository.getAllBook();
	        return getbookList;
	    }
	 
	 @PostMapping("/fetchallAsscessionRecord")
	 @ResponseBody
	    public List<bookDetail> fetchallAsscessionRecord() {
			List<bookDetail> getbookList= this.bookdetailRepository.fetchAccession();
	        return getbookList;
	    }
	 
	   
	 
	    @PostMapping("/Assessionrecordone")
		 @ResponseBody
		    public Book fetchallAsscessionOnebyone(@RequestBody String AsscessionRecords) {
				bookDetail findassrec= this.bookdetailRepository.fetchAccessionNumber(AsscessionRecords);
		        return findassrec.getBook();
		    }
	
}
