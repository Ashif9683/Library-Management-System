package com.management.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.management.Dao.RegisterRepository;
import com.management.entities.Book;
import com.management.entities.Register;
import com.management.entities.Student;


@Controller
public class AddStudentController {
	
	@Autowired
	RegisterRepository registerRepository;
	
	 @GetMapping("/students")
	    public List<Student> getallStudents() {
	        List<Student> getList= this.registerRepository.getAllStudent();
	        return getList;
	    }
	
	@PostMapping("registerStudent")	
	 @ResponseBody
	public String registerStudent(@ModelAttribute Student student ,Model model) {
		
		String msg= null;
		
		try {
			//int lastGeneratedmaxserailStud = getLastGeneratedSerialNumberStudent();
			List<String> getrollnumberList= this.registerRepository.getselectRollNumberexits(student.getRollnumber());
			if(!getrollnumberList.isEmpty()) {
				msg = "Roll Number Already Exits !!";
				return msg;		
			}
			List<String> getemailList= this.registerRepository.getselectemailexits(student.getEmail());
			if(!getemailList.isEmpty()) {
				msg = "Email Already Exits !!";
				return msg;		
			}
			if(getrollnumberList.isEmpty() && getemailList.isEmpty() ) {
			//	student.setSerailNo(lastGeneratedmaxserailStud++);
				student.setCreatedAt(new Date());
				student.setModifiedOn(new Date());
				System.out.println("student ---->>>" +student );
				Student register1 = this.registerRepository.save(student);
				msg="Student Added Successfully";
				return msg;
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
			msg="error";
		}
		return msg;		
			
	}
	
	@PostMapping("/deletePrimary")
    @ResponseBody
    public String handleDeleteRequest(@RequestBody String primaryKey) {
        String msg;
        int pk = Integer.parseInt(primaryKey);
        System.out.println("Primary Key: " + pk);

        try {
        	this.registerRepository.deleteBySomeField(pk);
        }catch (Exception e) {
        	e.printStackTrace();
			msg="error";
		}
        // Return an appropriate response (success, error, etc.)
        return "Done";
    }
	
	@PostMapping("/fetchStudent")
    @ResponseBody
    public Student fetchStudent(@RequestBody String primaryKey) {
        Student student= this.registerRepository.getAllStudentBYID1(primaryKey);
        return student;
    }
	
	@PostMapping("/fetchallStudentName")
    @ResponseBody
    public List<String> fetchallStudentNames(Model model) {
		List<String> studentnames= this.registerRepository.getStudentnames();
		model.addAttribute("studentnames" ,studentnames );
		return studentnames;
    }
	
	@GetMapping("/fetchfullName")
    @ResponseBody
    public List<String> fetchfullName(Model model) {
		List<String> studentnames= this.registerRepository.getStudentnames();
		return studentnames;
    }
	
	@PostMapping("/fetchallStudentNamewithrollnumber")
    @ResponseBody
    public List<String> fetchStudentNamewithrollnumber(@RequestBody String rollnumber) {
		
		System.out.println("rollnumber rollnumber  --->>>" + rollnumber);
		List<String>t= this.registerRepository.getstudentrollnumberwithName(rollnumber);
        return t;
    }
	
	@PostMapping("/fetchallStudentrollnumber")
    @ResponseBody
    public List<String> fetchallStudentRollnumber(Model model) {
		List<String> studentrollnumber= this.registerRepository.getStudentRollnumber();
		model.addAttribute("studentrollnumber" ,studentrollnumber );
		return studentrollnumber;
    }
	
	 @PostMapping("/updateStudentID")	
	 @ResponseBody
	public String updateStudentID(@ModelAttribute Student student) {
		
		 String msg = null;
		try {
			
			Optional<Student> findstudent = this.registerRepository.findById(student.getPrimaryKey());
			
			if(findstudent.isPresent()) {
				Student updateStudent= findstudent.get();
				updateStudent.setRollnumber(student.getRollnumber());
				updateStudent.setFullName(student.getFullName());
				updateStudent.setEmail(student.getEmail());
				updateStudent.setFatherName(student.getFatherName());
				updateStudent.setPhone(student.getPhone());
				updateStudent.setSelectSemester(student.getSelectSemester());
				updateStudent.setSelectYear(student.getSelectYear());
				updateStudent.setBookBank(student.getBookBank());
				updateStudent.setModifiedOn(new Date());
				Student save = this.registerRepository.save(updateStudent);
				msg="done";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg="error";
		}
		
		return msg;		
			
	}
	 
	 @PostMapping("/fetchallStudentRecord")
	 @ResponseBody
	    public List<Student> fetchallStudentRecord() {
			List<Student> getList= this.registerRepository.getAllStudent();
	        return getList;
	    }
	 @PostMapping("/addRemark")
	 @ResponseBody
	    public String addRemark(@RequestBody Map<String, String> requestData) {
		  String primaryKey = requestData.get("primaryKey");
		  String noDuess = requestData.get("noDuess");
		  System.out.println("primaryKey-- " + primaryKey);
		  System.out.println("noDuess-- " + noDuess);
		  
		  this.registerRepository.updateAddRemark(noDuess ,primaryKey );
		 
	        return "Added Remark";
	 }
	 
	 @PostMapping("/rollnumberfindObject")
	 @ResponseBody
	    public Student rollnumberfindObject(@RequestBody String rollnumber) {
	       Student student = this.registerRepository.getstudentrollnumberobject(rollnumber);
	        return student;
	    }
	/* private int getLastGeneratedSerialNumberStudent() throws IOException {
			Integer MaxSerailNoStudent = this.registerRepository.findMaxSerailNumberStudent();
			if (MaxSerailNoStudent == null) {
				return 1; // or any default value that makes sense in your context
			}
			return MaxSerailNoStudent;
		}  */
}
