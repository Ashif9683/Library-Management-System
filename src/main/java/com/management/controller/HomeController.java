package com.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.management.Dao.BookDetailRepository;
import com.management.Dao.BookRegisterRespository;
import com.management.Dao.RegisterCategoryRepository;
import com.management.Dao.RegisterRepository;
import com.management.Dao.userDao;
import com.management.entities.AccessionCode;
import com.management.entities.Book;
import com.management.entities.BookIssue;
import com.management.entities.Branch;
import com.management.entities.Category;
import com.management.entities.Student;
import com.management.entities.user;
@Controller
public class HomeController {
	
	@Autowired
	RegisterRepository registerRepository;
	@Autowired
	userDao userdao;
	@Autowired
	RegisterCategoryRepository registercategoryRepository;
	@Autowired
	BookDetailRepository bookdetailRepository;
	
	@Autowired
	BookRegisterRespository bookRegisterRespository;
	
	@GetMapping("/adminhome")
	public  String adminhome() {
		
		return "adminhome";
	}
	
	@GetMapping("/userhome")
	public  String userhome() {
		
		return "userhome";
	}
	@GetMapping("/report") 
	public String report() {
		return "report";
	}  
	
	@GetMapping("/ReportStudent") 
	public String ReportStudent() {
		return "ReportStudent";
	}
	
	@GetMapping("/ReportBook") 
	public String ReportBook() {
		return "ReportBook";
	}
	
	@GetMapping("/BookIssueReport") 
	public String BookIssueReport() {
		return "BookIssueReport";
	}
	
	@RequestMapping("/StudentPerRecord.html")
	public String StudentPerRecord() {
		return "StudentPerRecord.html";
	}
	
	@RequestMapping("/BookPerRecord.html")
	public String BookPerRecord() {
		return "BookPerRecord.html";
	}
	
	@RequestMapping("/MigrateStudent")
	public String MigrateStudent() {
		return "MigrateStudent";
	}
	@RequestMapping("/addAccessionCode")
	public String addAccessionCode(Model model) {
		List<AccessionCode> fetchAccessionCode= this.registercategoryRepository.getAccessionCode();
		model.addAttribute("fetchAccessionCode" ,fetchAccessionCode );
		return "addAccessionCode";
	}
	@RequestMapping("/sidebar")
	public  String sidebar() {
		
		return "sidebar";
	}
	
	@RequestMapping("/dashboard")
	public  String dashboard(Model model) {
		long studentCount= this.registerRepository.countTotalStudent();
		long bookcount= this.bookdetailRepository.countTotalBooksdetails();
		long boookissueCount = this.registerRepository.countTotalBooksIssue();
		model.addAttribute("studentsize" ,studentCount );
		model.addAttribute("booksize" ,bookcount );
		model.addAttribute("bookissuesize" ,boookissueCount );
		return "dashboard";
	}
	@RequestMapping("/studentdashboard")
	public String StudentDashboard() {
		return "studentdashboard";
	}
	@RequestMapping("/studentbookissue")
	public String studentbookissue() {
		return "studentbookissue";
	}
	@RequestMapping("/AdminDashboard")
	public  String AdminDashboard(Model model) {
		long studentCount= this.registerRepository.countTotalStudent();
		long bookcount= this.bookdetailRepository.countTotalBooksdetails();
		long boookissueCount = this.registerRepository.countTotalBooksIssue();
		model.addAttribute("studentsize" ,studentCount );
		model.addAttribute("booksize" ,bookcount );
		model.addAttribute("bookissuesize" ,boookissueCount );
		return "AdminDashboard";
	}
	
	@RequestMapping("/add")
	public  String add(Model model) {
		String maxaccesion = this.bookdetailRepository.findMaxAccessionNumber();
		model.addAttribute("maxAccessionNumber" ,maxaccesion );
		return "add";
	}
	
	@RequestMapping("/manageBook")
	public  String manageBook(Model model) {
		return "manageBook";
	}
	
	@RequestMapping("/addstudent")
	public  String addstudent() {
		
		return "addstudent";
	}
	
	@RequestMapping("/userAdd")
	public  String userAdd() {
		
		return "userAdd";
	}
	
	@RequestMapping("/manageUser")
	public  String manageUser(Model model) {
		List<user> getUser= this.userdao.getAlluser();
		model.addAttribute("User" ,getUser );
		return "manageUser";
	}
	
	@RequestMapping("/manageStudent")
	public  String manageStudent() {
		return "manageStudent";
	}
	
	@RequestMapping("/editStudent")
	public  String editStudent() {
		return "editStudent";
	}
	
	@RequestMapping("/addlbookIssue")
	public  String addlbookIssue() {
		return "addlbookIssue";
	}
	@RequestMapping("/manageLoan")
	public  String manageLoan(Model model) {
		List<BookIssue> getBookissuelist= this.registerRepository.getAllBookissue();
		model.addAttribute("getBookissuelist" ,getBookissuelist );
		return "manageLoan";
	}
	@RequestMapping("/subscriptionsPlan")
	public  String subscriptionsPlan() {
		return "subscriptionsPlan";
	}
	@RequestMapping("/purchase-history")
	public  String purchasehistory() {
		return "purchase-history";
	}
	

	@RequestMapping("/ConfirmationDeleteStudentMdlCtrl")
	public  String ConfirmationDeleteStudentMdlCtrl() {
		return "ConfirmationDeleteStudentMdlCtrl";
	}
	
	@RequestMapping("/MyProfile")
	public  String MyProfile() {
		return "MyProfile";
	}
	
	@RequestMapping("/changePassword")
	public  String changePassword() {
		return "changePassword";
	}
	
	@RequestMapping("/forgetPassword")
	public  String forgetPassword() {
		return "forgetPassword";
	}
	
	
	@RequestMapping("/fileUploadStudent")
	public  String fileUploadStudent() {
		
		return "fileUploadStudent";
	}
	
	@RequestMapping("/fileUploadBook")
	public  String fileUploadBook() {
		
		return "fileUploadBook";
	}
	
	@RequestMapping("/addIssue")
	public  String addIssue() {
		
		return "addIssue";
	}
	
	@RequestMapping("/addBranch")
	public  String addBranch(Model model) {
		List<Branch> fetchbranchname= this.registercategoryRepository.getbranchnametable();
		model.addAttribute("fetchbranchname" ,fetchbranchname );
		return "addBranch";
	}

	@RequestMapping("/RetrunbookWithoutAccession")
	public String RetrunbookWithoutAccession() {
		return "RetrunbookWithoutAccession";
	}
	
	@RequestMapping("/confirmReturnBookModal.html")
	public  String confirmReturnBookModal() {
		
		return "confirmReturnBookModal.html";
	}
	
	@RequestMapping("/addCategory")
	public  String addCategory(Model model) {
		List<Category> fetchcatername= this.registercategoryRepository.getcategorynametable();
		model.addAttribute("fetchcatername" ,fetchcatername );
		return "addCategory";
	}
}
