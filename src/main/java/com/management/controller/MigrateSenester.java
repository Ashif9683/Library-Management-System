package com.management.controller;



import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.management.Dao.RegisterRepository;
import com.management.entities.Student;

@RestController
public class MigrateSenester {
	
	@Autowired
	RegisterRepository registerRepository;
	
	/*
	 * @PostMapping("/migrateStudent")
	 * 
	 * @ResponseBody public String migrateStudentSemester() {
	 * 
	 * Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
	 * LocalDateTime currentDateTime = currentTimestamp.toLocalDateTime();
	 * 
	 * LocalDateTime sixMonthsAgoDateTime = currentDateTime.minusMonths(6);
	 * System.out.println("sixMonthsAgoDateTime ---  " + sixMonthsAgoDateTime);
	 * 
	 * Timestamp sixMonthsAgoTimestamp = Timestamp.valueOf(sixMonthsAgoDateTime);
	 * 
	 * List<Student> allbackstudent =
	 * this.registerRepository.allgetstudentsixmonth(sixMonthsAgoTimestamp);
	 * 
	 * for(Student e: allbackstudent) { if(e.getSelectSemester() == null ) {
	 * continue; } firstsemester(e); secondsemester(e); thirdsemester(e);
	 * fourthsemester(e); fifthsemester(e); sixthsemester(e); seventhsemester(e); }
	 * return "All Student prometed"; }
	 */
	
	@PostMapping("/migrateSemeesterStudent")
	@ResponseBody
    public List<Student> migrateSemeesterStudent(@RequestBody String semesterName) {
		List<Student> studentnames= this.registerRepository.getStudentSemesternames(semesterName);
		return studentnames;
	}
	
	@PostMapping("/migrateSemester")
	@ResponseBody
    public String migrateSemester(@RequestBody List<Student> students) {
		JSONObject json = new JSONObject();
        for (Student e : students) {
            if(e.getSelectSemester() == null ) {
	    		continue;
	    	}
	    	firstsemester(e);
	    	secondsemester(e);
	    	thirdsemester(e);
	    	fourthsemester(e);
	    	fifthsemester(e);
	    	sixthsemester(e);
	    	seventhsemester(e);
	    	eighthsemester(e);
         
        }
        String msg = "Students Migrated Successfully";
		json.put("message", msg);
		return json.toString();
    }
	
	 void eighthsemester(Student e) {
			Student  studentupdate = new Student();
			if(e.getSelectSemester().equals("eighth semester")) {
	    		studentupdate.setSelectSemester("Pass Out year");
	    		studentupdate.setBookBank("no");
	    		studentupdate.setSelectYear("None");
	    		studentupdate.setModifiedOn(new Date());
	    		this.registerRepository.updateSemester(studentupdate.getBookBank(),studentupdate.getSelectSemester(), studentupdate.getModifiedOn(), studentupdate.getSelectYear(), e.getPrimaryKey());
	    	}
			
		}
	
	 void seventhsemester(Student e) {
		Student  studentupdate = new Student();
		if(e.getSelectSemester().equals("seventh semester")) {
    		studentupdate.setSelectSemester("eighth semester");
    		studentupdate.setBookBank("no");
    		studentupdate.setSelectYear("fourth year");
    		studentupdate.setModifiedOn(new Date());
    		this.registerRepository.updateSemester(studentupdate.getBookBank(),studentupdate.getSelectSemester(), studentupdate.getModifiedOn(), studentupdate.getSelectYear(), e.getPrimaryKey());
    	}
		
	}

	 void sixthsemester(Student e) {
		Student  studentupdate = new Student();
		if(e.getSelectSemester().equals("sixth semester")) {
    		System.out.println("sixth semester run method with primary key " + e.getPrimaryKey());
    		studentupdate.setSelectSemester("seventh semester");
    		studentupdate.setBookBank("no");
    		studentupdate.setModifiedOn(new Date());
    		studentupdate.setSelectYear("fourth year");
    		this.registerRepository.updateSemester(studentupdate.getBookBank(),studentupdate.getSelectSemester(), studentupdate.getModifiedOn(), studentupdate.getSelectYear(), e.getPrimaryKey());
    	}
	}

	 void fifthsemester(Student e) {
		Student  studentupdate = new Student();
		if(e.getSelectSemester().equals("fifth semester")) {
    		studentupdate.setSelectSemester("sixth semester");
    		studentupdate.setBookBank("no");
    		studentupdate.setSelectYear("third year");
    		studentupdate.setModifiedOn(new Date());
    		this.registerRepository.updateSemester(studentupdate.getBookBank(),studentupdate.getSelectSemester(), studentupdate.getModifiedOn(), studentupdate.getSelectYear(), e.getPrimaryKey());
    	}
	}

	 void fourthsemester(Student e) {
		Student  studentupdate = new Student();
		if(e.getSelectSemester().equals("fourth semester")) {
    		studentupdate.setSelectSemester("fifth semester");
    		studentupdate.setBookBank("no");
    		studentupdate.setModifiedOn(new Date());
    		studentupdate.setSelectYear("third year");
    		this.registerRepository.updateSemester(studentupdate.getBookBank(),studentupdate.getSelectSemester(), studentupdate.getModifiedOn(), studentupdate.getSelectYear(), e.getPrimaryKey());
    	}
	}

	 void thirdsemester(Student e) {
		Student  studentupdate = new Student();
		if(e.getSelectSemester().equals("third semester")) {
    		studentupdate.setSelectSemester("fourth semester");
    		studentupdate.setBookBank("no");
    		studentupdate.setSelectYear("second year");
    		studentupdate.setModifiedOn(new Date());
    		this.registerRepository.updateSemester(studentupdate.getBookBank(),studentupdate.getSelectSemester(), studentupdate.getModifiedOn(), studentupdate.getSelectYear(), e.getPrimaryKey());
    	}
	}

	 void secondsemester(Student e) {
		Student  studentupdate = new Student();
		if(e.getSelectSemester().equals("second semester")) {
    		studentupdate.setSelectSemester("third semester");
    		studentupdate.setBookBank("no");
    		studentupdate.setSelectYear("second year");
    		studentupdate.setModifiedOn(new Date());
    		this.registerRepository.updateSemester(studentupdate.getBookBank(),studentupdate.getSelectSemester(), studentupdate.getModifiedOn(), studentupdate.getSelectYear(), e.getPrimaryKey());
    	}
	}

	 void firstsemester(Student e) {
		 Student  studentupdate = new Student();
		if(e.getSelectSemester().equals("first semester")) {
    		studentupdate.setSelectSemester("second semester");
    		studentupdate.setBookBank("no");
    		studentupdate.setSelectYear("first year");
    		studentupdate.setModifiedOn(new Date());
    		this.registerRepository.updateSemester(studentupdate.getBookBank(),studentupdate.getSelectSemester(), studentupdate.getModifiedOn(), studentupdate.getSelectYear(), e.getPrimaryKey());
    	}
	}
}
