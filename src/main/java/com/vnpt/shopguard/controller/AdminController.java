package com.vnpt.shopguard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
    @GetMapping("")
	public String dashboard(){
    	return "admin/dashboard";
    }
    
    @GetMapping("/login")
   	public String login(){
       	return "admin/login";
       }
}
