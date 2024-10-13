package com.management;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class DefaultController {
	
	 @GetMapping("/")
	    public RedirectView redirectToDefaultPage() {
	        return new RedirectView("/home", true); // Redirect to "/home" URL
	    }
}
