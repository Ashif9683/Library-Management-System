package com.management.mobileApi;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.management.Dao.userDao;
import com.management.entities.user;


@RestController
@RequestMapping("/mobileRest")
public class mobileApiRest {
	
	@Autowired
	userDao userdao;
	
	
	@GetMapping("/userListlogin")
	@ResponseBody
	public List<user> userListlogin() {
		List<user> fectchUsernameAndPassword= this.userdao.getAlluser();
		return fectchUsernameAndPassword;
	}
	
	@PostMapping("/loginVerfication")
	@ResponseBody
	public user loginVerfication(@RequestBody user Userlist) {
        String username = Userlist.getUsername();
        String password = Userlist.getPassword();
       
		user fectchUsernameAndPassword = null;
		try {
			 fectchUsernameAndPassword= this.userdao.finduserNameAndPasswordMobile(username , password);
			System.out.println(" fectchUsernameAndPassword --" + fectchUsernameAndPassword);
			
			if(fectchUsernameAndPassword != null && fectchUsernameAndPassword.getUserrole().equals("ADMIN") ) {
				
				System.out.println("ADMIN LOGIN");
				
				return fectchUsernameAndPassword;
				
			}else if(fectchUsernameAndPassword != null && fectchUsernameAndPassword.getUserrole().equals("USER")) {
				
				System.out.println("USER LOGIN");
				return fectchUsernameAndPassword;
				
			}else {
					
		        
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fectchUsernameAndPassword;
	}
}
