package com.management.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.management.Dao.userDao;
import com.management.entities.Book;
import com.management.entities.Register;
import com.management.entities.user;

@RestController
public class addUserController {
	
	@Autowired
	userDao userdao;
	
	
	@PostMapping("/addUser")	
	 @ResponseBody
	public String addUser(@ModelAttribute user Usr ,Model model) {
		String msg= null;
		try {
			
			List<String> getusernameList= this.userdao.checkusr(Usr.getUsername());
			if(!getusernameList.isEmpty()) {
				msg = "Username Already Exits";
				return msg;		
			}
			
			List<String> getemailnameList= this.userdao.checkemail(Usr.getEmail());
			if(!getemailnameList.isEmpty()) {
				msg = "Email Already Exits";
				return msg;		
			}
			if(getusernameList.isEmpty() && getemailnameList.isEmpty() ) {
				//	register.setPassword(bCryptPasswordEncoder.encode(register.getPassword()));
				Usr.setCreatedAt(new Date());
				Usr.setModifiedOn(new Date());				
				this.userdao.save(Usr);
				msg = "Added User";
				return msg;		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return msg;		
			
	}
	
	@PostMapping("/deleteUserPrimary")
	@ResponseBody
	public String deleteUserPrimary(@RequestBody String primaryKey) {
		String msg;
		int pk = Integer.parseInt(primaryKey);
		try {
			this.userdao.deleteByUserField(pk);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		// Return an appropriate response (success, error, etc.)
		return "Done";
	}
	
	@PostMapping("/fetchUser")
	@ResponseBody
	public user fetchUser(@RequestBody String primaryKey) {
		user User = this.userdao.getUserbyID(primaryKey);
		return User;
	}
	
	@PostMapping("/updateUserID")
	@ResponseBody
	public String updateUserID(@ModelAttribute user us) {
		System.out.println("us--" + us);
		String msg = null;
		try {
			Optional<user> findUser = this.userdao.getupdateUserbyID(us.getPrimarykey());
			if (findUser.isPresent()) {
				user updateUser = findUser.get();
				updateUser.setUsername(us.getUsername());
				updateUser.setEmployeename(us.getEmployeename());
				updateUser.setPassword(us.getPassword());
				updateUser.setEmail(us.getEmail());
				updateUser.setUserrole(us.getUserrole());
				updateUser.setModifiedOn(new Date());
				user save = this.userdao.save(updateUser);
				msg = "done";
			} 
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		return msg;
	}
	
}
