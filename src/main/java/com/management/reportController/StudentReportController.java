package com.management.reportController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.management.Dao.BookDetailRepository;
import com.management.Dao.BookIssueRepository;
import com.management.Dao.RegisterRepository;
import com.management.entities.Book;
import com.management.entities.BookIssue;
import com.management.entities.Student;
import com.management.entities.bookDetail;

@Controller
public class StudentReportController {

	@Autowired
	RegisterRepository registerRepository;
	@Autowired
	BookDetailRepository bookDetailRepository;
	
	@Autowired
	BookIssueRepository bookIssueRepository;
	
	@PostMapping("/findBranchStudent")
	@ResponseBody
	public List<Student> findBranchStudent(@RequestBody String branch) {
		List<Student> studentnames = this.registerRepository.getStudentbranchnames(branch);
		return studentnames;
	}

	@PostMapping("/findbookCategory")
	@ResponseBody
	public List<Book> findbookCategory(@RequestBody Map<String, Object> requestData) {
		String accessionCategory = (String) requestData.get("accessionCategory");
		String accesspercent= accessionCategory + "-%";
		List<Book> resultBooks = new ArrayList<>();
		List<bookDetail> detail = null;
		int count = 1;
		if (accessionCategory.equals("All")) { 
			   detail =this.bookDetailRepository.ALLfetchbookdetail(); 
		}else if (accessionCategory.equals("Numeric")) { 
			  detail =this.bookDetailRepository.findNumericRecords(); 
		} else { 
			  detail = this.bookDetailRepository.findByAccessionNumberLike(accesspercent);	
		}
		for(bookDetail de : detail) {
			Book originalBook = this.registerRepository.getobjectBookBYID1(de.getBook().getPrimaryKey());
		    Book newbooknames = new Book(originalBook); 
		    if (newbooknames != null) {
					newbooknames.setAccessionNumber(de.getAccessionNumber());
					newbooknames.setSerailNo(count++);
					newbooknames.setBooktitle(originalBook.getBooktitle()); 
					newbooknames.setAbstracts(originalBook.getAbstracts());
					newbooknames.setAuthorMark(originalBook.getAuthorMark());
					newbooknames.setAuthorName(originalBook.getAuthorName());
					newbooknames.setAuthorName2(originalBook.getAuthorName2());
					newbooknames.setAuthorName3(originalBook.getAuthorName3());
					newbooknames.setAuthorName4(originalBook.getAuthorName4());
					newbooknames.setAuthorName5(originalBook.getAuthorName5());
					newbooknames.setBillDate(originalBook.getBillDate());
					newbooknames.setBillNumber(originalBook.getBillNumber());
					newbooknames.setBookAddedIn(originalBook.getBookAddedIn());
					newbooknames.setBookCategory(originalBook.getBookCategory());
					newbooknames.setBookPublisherName(originalBook.getBookPublisherName());
					newbooknames.setBookPublisherPlace(originalBook.getBookPublisherPlace());
					newbooknames.setBookquantity(1);
					newbooknames.setCallNo(originalBook.getCallNo());
					newbooknames.setCompiler(originalBook.getCompiler());
					newbooknames.setCostOfItem(originalBook.getCostOfItem());
					newbooknames.setCreatedAt(originalBook.getCreatedAt());
					newbooknames.setCurrencyName(originalBook.getCurrencyName());
					newbooknames.setCurrentStatus(originalBook.getCurrentStatus());
					newbooknames.setDiscountAmount(originalBook.getDiscountAmount());
					newbooknames.setDiscountInPercantage(originalBook.getDiscountInPercantage());
					newbooknames.setEdition(originalBook.getEdition());
					newbooknames.setEditor(originalBook.getEditor());
					newbooknames.setEmployeeName(originalBook.getEmployeeName());
					newbooknames.setEntryDate(originalBook.getEntryDate());
					newbooknames.setIsbnNumber(originalBook.getIsbnNumber());
					newbooknames.setItemStatus(originalBook.getItemStatus());
					newbooknames.setKeywords(originalBook.getKeywords());
					newbooknames.setLanguageName(originalBook.getLanguageName());
					newbooknames.setLibraryItemType(originalBook.getLibraryItemType());
					newbooknames.setLibraryName(originalBook.getLibraryName());
					newbooknames.setLocation(originalBook.getLocation());
					newbooknames.setModifiedOn(originalBook.getModifiedOn());
					newbooknames.setOrderDate(originalBook.getOrderDate());
					newbooknames.setOrderNumber(originalBook.getOrderNumber());
					newbooknames.setPagesNumber(originalBook.getPagesNumber());
					newbooknames.setPrePages(originalBook.getPrePages());
					newbooknames.setPublicationDate(originalBook.getPublicationDate());
					newbooknames.setPublicationYear(originalBook.getPublicationYear());
					newbooknames.setRackNumber(originalBook.getRackNumber());
					newbooknames.setSeriesNo(originalBook.getSeriesNo());
					newbooknames.setShelfNumber(originalBook.getShelfNumber());
					newbooknames.setSize(originalBook.getSize());
					newbooknames.setSubjectTitle(originalBook.getSubjectTitle());
					newbooknames.setSubTitle(originalBook.getSubTitle());
					newbooknames.setTranslator(originalBook.getTranslator());
					newbooknames.setTypeOfBinding(originalBook.getTypeOfBinding());
					newbooknames.setUserName(originalBook.getUserName());
					newbooknames.setVendorName(originalBook.getVendorName());
					newbooknames.setVendorPlace(originalBook.getVendorPlace());
					newbooknames.setVerified(originalBook.getVerified());
					newbooknames.setVolume(originalBook.getVolume());
					newbooknames.setSeriesTitle(originalBook.getSeriesTitle());
			 }
			  resultBooks.add(newbooknames);
	    }
	    return resultBooks;
	}
	

/*@PostMapping("/findbookCategory")
@ResponseBody
public List<BookWithDetails> findbookCategory(@RequestBody String category) {
    System.out.println("category -- " + category);
    List<BookWithDetails> response = new ArrayList<>();

    List<Book> booknames;
    if (category.equalsIgnoreCase("All")) {
        booknames = this.registerRepository.getAllBook();
    } else {
        booknames = this.registerRepository.getbookCategorynames(category);
    }

    for (Book b : booknames) {
        List<bookDetail> bookdetail = this.bookDetailRepository.getdetailbook(b.getPrimaryKey());
        System.out.println("bookdetail-  " + bookdetail);

        List<String> newbook = new ArrayList<>();
        for (bookDetail bokde : bookdetail) {
            newbook.add(bokde.getAccessionNumber());
            newbook.add(bokde.getBookAccessionCode());
        }

        System.out.println("newbook - only accession  " + newbook);
        response.add(new BookWithDetails(b, newbook));
    }

    return response;
} */
	
	@PostMapping("/findbookissueDue")
	@ResponseBody
	public List<BookIssue> findbookissueDue(@RequestBody String nodue) {
		List<BookIssue> duebook = this.bookIssueRepository.getnodueebook(nodue);
		return duebook;
	}
	
	@PostMapping(value = "/openpdf/employees", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> employeeReport(@RequestBody String nodue)  throws IOException {
		System.out.println(" nodue pdf  -- " + nodue);
		List<BookIssue> duebook = this.bookIssueRepository.getnodueebook(nodue);
		System.out.println(" nodue pdf  -- " + duebook);
        ByteArrayInputStream bis = DatabasePDFService.employeePDFReport(duebook , nodue);
 
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=employees.pdf");
    	System.out.println(" nodue pdf  -- " + nodue);
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
	}
}