package com.iris.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.iris.daos.UserDao;
import com.iris.model.User;

@Controller
public class UserController {
	
@Autowired
 UserDao userdao;
	@RequestMapping(value="/ViewAllUser",method=RequestMethod.GET)
	
	public ModelAndView getAllUsers() {

		List<User> users=userdao.getAllUser();
		
		ModelAndView mv=new ModelAndView("UsersList");
		mv.addObject("usersList",users);
		return mv;
	}
	@RequestMapping(value="/deleteUser/{uId}",method=RequestMethod.GET)
	public ModelAndView deleteUser(@PathVariable int uId){
		
		System.out.println("User to delete = "+uId);
		User userObj=userdao.getUserById(uId);
		System.out.println("User Obj = "+userObj);
		
		userdao.deleteUser(userObj);
		
		ModelAndView mv=new ModelAndView("UsersList");
		mv.addObject("usersList",userdao.getAllUser());
		mv.addObject("msg","User deleted Succesfully...");
		return mv;
	}
	
	@RequestMapping(value="/updateUser/{uId}",method=RequestMethod.GET)
	public ModelAndView getUpdateUserForm(@PathVariable int uId){
		
		
		User userObj=userdao.getUserById(uId);
		
		ModelAndView mv=new ModelAndView("Signupform");
		mv.addObject("userObj",userObj);
		mv.addObject("btnLabel","Update");
		mv.addObject("formLabel", "Update Form");
		return mv;
	}
	}
	

