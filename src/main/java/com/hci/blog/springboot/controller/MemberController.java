package com.hci.blog.springboot.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hci.blog.springboot.dto.Member;
import com.hci.blog.springboot.dto.ResultData;
import com.hci.blog.springboot.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;

	@RequestMapping("member/join")
	public String showJoin() {

		return "usr/member/join";
	}// showJoin

	@RequestMapping("member/doJoin")
	public String doJoin(Model model, @RequestParam Map<String, Object> joinParam) {
		ResultData joinRs = memberService.join(joinParam);
		if (joinRs.isFail()) {
			model.addAttribute("msg", joinRs.getMsg());
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}

		model.addAttribute("replaceUri", "/member/login");
		return "common/redirect";
	}// doJoin

	@RequestMapping("member/login")
	public String showLogin() {

		return "usr/member/login";
	}// showLogin

	@RequestMapping("member/doLogin")
	public String doLogin(Model model, HttpSession session, @RequestParam Map<String, Object> loginParam) {
		ResultData loginRs = memberService.login(loginParam);
		if (loginRs.isFail()) {
			model.addAttribute("msg", loginRs.getMsg());
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}

		int loginedMemberId = (int) loginRs.getBody();

		session.setAttribute("loginedMemberId", loginedMemberId);

		model.addAttribute("replaceUri", "/article/list");
		return "common/redirect";
	}// doLogin

	@RequestMapping("member/doLogout")
	public String doLogout(Model model, HttpSession session) {
		session.removeAttribute("loginedMemberId");

		model.addAttribute("replaceUri", "/article/list");
		return "common/redirect";
	}// doLogout
	
	@RequestMapping("member/modify")
	public String showModify(Model model, HttpServletRequest request) {
		Member member = (Member) request.getAttribute("loginedMember");
		
		model.addAttribute("member", member);
		return "usr/member/modify";
	}// showModify
	
	@RequestMapping("member/doModify")
	public String doModify(Model model, @RequestParam Map<String, Object> modifyParam) {
		ResultData modifyRs = memberService.modify(modifyParam);
		if(modifyRs.isFail()) {
			model.addAttribute("msg", modifyRs.getMsg());
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}
		
		model.addAttribute("msg", modifyRs.getMsg());
		model.addAttribute("historyBack", true);
		return "common/redirect";
	}// doModify
}
