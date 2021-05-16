package com.hci.blog.springboot.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hci.blog.springboot.dto.ResultData;
import com.hci.blog.springboot.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;

	@RequestMapping("member/login")
	public String showLogin() {
		
		return "usr/member/login";
	}// showLogin
	
	@RequestMapping("member/doLogin")
	public String doLogin(Model model, @RequestParam Map<String, Object> loginParam) {
		ResultData memberRs = memberService.login(loginParam);
		if(memberRs.isFail()) {
			model.addAttribute("msg", memberRs.getMsg());
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}
		
		int loginedMemberId = (int) memberRs.getBody();
		
		model.addAttribute("replaceUri", "/article/list");
		return "common/redirect";
	}// doLogin
}
