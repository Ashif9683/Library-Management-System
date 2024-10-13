package com.management.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.management.Dao.BookIssueRepository;
import com.management.Dao.RegisterRepository;
import com.management.entities.BookIssue;
import com.management.entities.Student;
import com.management.entities.user;

import jakarta.servlet.http.HttpSession;

@Controller
public class StudentWiseRollNumber {
			
	@Autowired
	BookIssueRepository bookissueRepository;
	
	@Autowired
	RegisterRepository registerRepository;
	
	@PostMapping("StudentcheckRollNumber")
	@ResponseBody
	public Student StudentcheckRollNumber(@RequestBody String rollnumber, Model model, HttpSession session) {
		user User = (user) session.getAttribute("fectchUsernameAndPassword");
		if (User != null) {
			Student t = this.registerRepository.getstudentrollnumberandNameobject(rollnumber, User.getEmployeename());
			if (t != null) {
				return t;
			} else {
				return null;
			}
		}
		return null;
	}
	
	
	@PostMapping("/diaplaydataStudentissuesintable")
	@ResponseBody
	public List<BookIssue> diaplaydataStudentissuesintable(@RequestBody String rollnumber , HttpSession session) {
		user User = (user) session.getAttribute("fectchUsernameAndPassword");
		if(User !=null ) {
			List<BookIssue> fndissuestudent = this.bookissueRepository.findrollnumberandBookIssuestudentname(rollnumber, "Book Issue" , User.getEmployeename());
			System.out.println(" fndstudent ---  " + fndissuestudent);
			return fndissuestudent;
		}
		return null;
	}
	
	@PostMapping("/diaplaydataStudentreturnintable")
	@ResponseBody
	public List<BookIssue> diaplaydataStudentreturnintable(@RequestBody String rollnumber , HttpSession session) {
		user User = (user) session.getAttribute("fectchUsernameAndPassword");
		if(User !=null ) {
		List<BookIssue> returnfndstudent = this.bookissueRepository.findrollnumberandBookIssuestudentname(rollnumber, "Book Return" , User.getEmployeename());
		System.out.println(" fndstudent ---  " + returnfndstudent);
		return returnfndstudent;
		}
		return null;
	}
}
