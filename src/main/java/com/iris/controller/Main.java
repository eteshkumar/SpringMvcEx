package com.iris.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.iris.daos.UserDao;
import com.iris.model.User;

@Controller
public class Main {
	
	
	@Autowired
	UserDao userDao;
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String Homepage(ModelMap map) {
		map.addAttribute("msg","Hello Etesh Welcome to Spring");
		return "Homepage";
	}
	
	@RequestMapping(value="/Signupform",method=RequestMethod.GET)
	public String displaysignupform(ModelMap map) {
		map.addAttribute("uObj",new User());
		return "Signupform";
	}
	@RequestMapping(value="/registerUser",method=RequestMethod.POST)
	public ModelAndView registerUser(@Valid @ModelAttribute("userObj") User userobj ,BindingResult result) {
		
		if(result.hasErrors()) {
			ModelAndView mv=new ModelAndView("Signupform");
			mv.addObject("btnLabel","Sign Up");
			mv.addObject("formLabel", "SignUp Form");	
			return mv;
		}
		
		
		
		else {
		userobj.setRole("user");
		userDao.registerUser(userobj);
		ModelAndView mv=new ModelAndView("Signinform");
		mv.addObject("msg1","User has been registered succesfully!!");
		return mv;
		}
	}
	@RequestMapping(value="/Signinform",method=RequestMethod.GET)
	public String Signinform() {
		return "Signinform";
		
	}
	@Autowired
	HttpSession session;
	@RequestMapping(value="/SignIn",method=RequestMethod.POST)
	public String validateUser(@RequestParam int userId,@RequestParam String password) {
		User uobj=userDao.validateUser(userId,password);
		if(uobj==null) {
			return "Signinform";
		}
		else {
			String role=uobj.getRole();
			session.setAttribute("uobj", uobj);
			if(role.equals("admin")) {
				return "adminLogin";
			}
			else {
				return "userLogin";
				
			}
		
		
	}
}
}