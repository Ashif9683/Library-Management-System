package com.management.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.management.Dao.RegisterCategoryRepository;
import com.management.entities.Branch;
import com.management.entities.Category;

@Controller
public class addBranchController {
	
	@Autowired
	RegisterCategoryRepository registercategoryrepository;
	
	@PostMapping("/addedBranch")	
	 @ResponseBody
	public String addedBranch(@ModelAttribute Branch branch ,Model model) {
		branch.setCreatedAt(new Date());
		 this.registercategoryrepository.save(branch);
		return "done";		
			
	}
	@PostMapping("/deleteBranchPrimary")
    @ResponseBody
    public String deleteBranchPrimary(@RequestBody String primaryKey) {
        String msg;
        int pk = Integer.parseInt(primaryKey);
        System.out.println("Primary Key: " + pk);

        try {
        	this.registercategoryrepository.deleteByBranchField(pk);
        	
        }catch (Exception e) {
        	e.printStackTrace();
			msg="error";
		}
        // Return an appropriate response (success, error, etc.)
        return "Done";
    }
	
	@PostMapping("/fetchallBranch")
    @ResponseBody
    public List<String> fetchallBranch(Model model) {
		List<String> branchname= this.registercategoryrepository.getbranchname();
		model.addAttribute("branchname" ,branchname );
		return branchname;
    }
}
