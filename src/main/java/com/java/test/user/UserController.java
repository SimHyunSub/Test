package com.java.test.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.test.utill.HttpUtill;

@Controller
public class UserController {
	
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest req){
		
		return HttpUtill.makeJsonView(HttpUtill.getParamMap(req));
	}
	
}
