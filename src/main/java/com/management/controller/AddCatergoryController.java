package com.management.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.management.Dao.RegisterCategoryRepository;
import com.management.entities.AccessionCode;
import com.management.entities.Category;
import com.management.entities.Student;

@Controller
public class AddCatergoryController {

	@Autowired
	RegisterCategoryRepository registercategoryrepository;
	
	@PostMapping("/addedcategory")	
	 @ResponseBody
	public String addcategory(@ModelAttribute Category category ,Model model) {
		System.out.println("category " + category);
		Category register1 = this.registercategoryrepository.save(category);
		return "done";		
	}
	
	@PostMapping("/addedAccessioncategory")	
	 @ResponseBody
	public String addedAccessioncategory(@ModelAttribute AccessionCode accessioncode ,Model model) {
		System.out.println("accessioncode " + accessioncode);
		 this.registercategoryrepository.save(accessioncode);
		return "done";		
	}
	
	@PostMapping("/deletecategoryPrimary")
    @ResponseBody
    public String deletecategoryPrimary(@RequestBody String primaryKey) {
        String msg;
        int pk = Integer.parseInt(primaryKey);
        System.out.println("Primary Key: " + pk);

        try {
        	this.registercategoryrepository.deleteById(pk);
        	
        }catch (Exception e) {
        	e.printStackTrace();
			msg="error";
		}
        // Return an appropriate response (success, error, etc.)
        return "Done";
    }
	
	@PostMapping("/deleteAccessionPrimaryKey")
    @ResponseBody
    public String deleteAccessionPrimaryKey(@RequestBody String primaryKey) {
        String msg;
        int pk = Integer.parseInt(primaryKey);
        System.out.println("Primary Key: " + pk);

        try {
        	this.registercategoryrepository.deleteByAccession(pk);
        	
        }catch (Exception e) {
        	e.printStackTrace();
			msg="error";
		}
        // Return an appropriate response (success, error, etc.)
        return "Done";
    }
	
	@PostMapping("/fetchallcategory")
    @ResponseBody
    public List<String> fetchallcategory(Model model) {
		List<String> categoryname= this.registercategoryrepository.getcategoryname();
		model.addAttribute("categoryname" ,categoryname );
		return categoryname;
    }
	
	@PostMapping("/fetchallAccessionCode")
    @ResponseBody
    public List<String> fetchallAccessionCode(Model model) {
		List<String> categoryname= this.registercategoryrepository.getallAccessionCode();
		return categoryname;
	}
	
}
