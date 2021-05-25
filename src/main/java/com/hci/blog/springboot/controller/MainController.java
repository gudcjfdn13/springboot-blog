package com.hci.blog.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@RequestMapping("/")
	public String showHome_() {
		return "redirect:main/home";
	}
	
	@RequestMapping("main/home")
	public String showHome() {
		
		return "usr/main/home";
	}
}
