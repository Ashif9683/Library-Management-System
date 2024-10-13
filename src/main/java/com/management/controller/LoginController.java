package com.management.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import com.management.Dao.RegisterRepository;
import com.management.Dao.userDao;
import com.management.entities.Register;
import com.management.entities.Student;
import com.management.entities.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class LoginController {
	
	//@Autowired
	//private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	userDao userdao;
	
	@Autowired
	private RegisterRepository registerRepository;
	
	
	@RequestMapping("/sign")
	public  String sign() {
			
		return "sign";
	}
	
	@RequestMapping("/test")
	public  String test() {
		
		return "test";
	}
	
	/*
	 * @RequestMapping("/register_page") public String register_page() {
	 * System.out.println("run register page "); return "register_page"; }
	 */
	
	@PostMapping("/registerPage")	
	 @ResponseBody
	public String addProduct(@ModelAttribute Register register ,Model model) {
		String msg= null;
		try {
			
			List<String> getusernameList= this.registerRepository.finduserName(register.getUsername());
			if(!getusernameList.isEmpty()) {
				msg = "Username Already Exits";
				return msg;		
			}
			
			List<String> getemailnameList= this.registerRepository.findemailName(register.getEmail());
			if(!getemailnameList.isEmpty()) {
				msg = "Email Already Exits";
				return msg;		
			}
			if(getusernameList.isEmpty() && getemailnameList.isEmpty() ) {
				register.setUserrole("ADMIN");
				//	register.setPassword(bCryptPasswordEncoder.encode(register.getPassword()));
					register.setPassword(register.getPassword());
					register.setCreatedAt(new Date());
					register.setModifiedOn(new Date());
					Register register1 = this.registerRepository.save(register);
				msg = "Registered Successfully";
				return msg;		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return msg;		
			
	}
	
	
	@RequestMapping("/loginUser")
	public ModelAndView loginUser(@ModelAttribute user Usr ,Model model, HttpSession session) {
			try {
				user fectchUsernameAndPassword= this.userdao.finduserNameAndPassword(Usr.getUsername() , Usr.getPassword());
				System.out.println(" fectchUsernameAndPassword --" + fectchUsernameAndPassword);

				if(fectchUsernameAndPassword != null && fectchUsernameAndPassword.getUserrole().equals("ADMIN") ) {
					System.out.println("ADMIN LOGIN");
					session.setAttribute("fectchUsernameAndPassword", fectchUsernameAndPassword);
					return new ModelAndView("redirect:/AdminDashboard");
				}else if(fectchUsernameAndPassword != null && fectchUsernameAndPassword.getUserrole().equals("USER")) {
					System.out.println("USER LOGIN");
					session.setAttribute("fectchUsernameAndPassword", fectchUsernameAndPassword);
					return new ModelAndView("redirect:/dashboard");
				}else if(fectchUsernameAndPassword != null && fectchUsernameAndPassword.getUserrole().equals("STUDENT")) {
					System.out.println("STUDENT LOGIN");
					session.setAttribute("fectchUsernameAndPassword", fectchUsernameAndPassword);
					return new ModelAndView("redirect:/studentdashboard");
				}
				else {
					ModelAndView modelAndView = new ModelAndView("sign");
			        modelAndView.addObject("error", "Invalid username or password");
			        return modelAndView;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new ModelAndView("sign");	
			}		
	}
	
	 @GetMapping("/logoutUser")
	    public String logout(HttpServletRequest request) {
	        HttpSession session = request.getSession(false); // Get session, if exists
	        if (session != null) {
	            session.invalidate(); // Invalidate session
	        }
	        return "redirect:/sign";
	    }
	 
	 
}
