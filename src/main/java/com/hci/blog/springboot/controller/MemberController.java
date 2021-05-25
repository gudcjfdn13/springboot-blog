package com.hci.blog.springboot.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hci.blog.springboot.Util.Util;
import com.hci.blog.springboot.dto.Member;
import com.hci.blog.springboot.dto.ResultData;
import com.hci.blog.springboot.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	@Value("${custom.mainUri}")
	private String mainUri;
	
	@RequestMapping("member/showCheckPw")
	public String showCheckPw() {
		return "usr/member/checkPw";
	}
	
	@RequestMapping("member/doCheckPw")
	public String doCheckPw(Model model, HttpServletRequest request, String redirectUri, String loginPw) {
		Member loginedMember = (Member) request.getAttribute("loginedMember");

		if (loginedMember.getLoginPw().equals(loginPw) == false) {
			model.addAttribute("historyBack", true);
			model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
			return "common/redirect";
		}

		String authCode = memberService.genCheckLoginPwAuthCode(loginedMember.getId());

		if (redirectUri == null || redirectUri.length() == 0) {
			redirectUri = "/main/home";
		}

		redirectUri = Util.getNewUri(redirectUri, "authCode", authCode);

		model.addAttribute("replaceUri", redirectUri);

		return "common/redirect";
	}

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
	
	@RequestMapping("member/doAuthEmail")
	public String doAuthEmail(Model model, int actorId, String email, String authCode) {
		Member member = memberService.getMemberById(actorId);

		if (member == null) {
			model.addAttribute("historyBack", true);
			model.addAttribute("msg", "존재하지 않는 회원입니다.");
			return "common/redirect";
		}

		if (member.getEmail().equals(email) == false) {
			model.addAttribute("historyBack", true);
			model.addAttribute("msg", "이메일이 일치하지 않습니다.");
			return "common/redirect";
		}

		String emailAuthCodeOnDb = memberService.getEmailAuthCode(actorId);

		if (authCode.equals(emailAuthCodeOnDb) == false) {
			model.addAttribute("historyBack", true);
			model.addAttribute("msg", "인증코드가 일치하지 않거나 만료되었습니다. 관리자에게 문의해주세요.");
			return "common/redirect";
		}

		memberService.saveAuthedEmail(actorId, email);

		model.addAttribute("msg", "이메일 인증에 성공하였습니다.");
		model.addAttribute("replaceUri", "/");
		return "common/redirect";
	}

	@RequestMapping("member/login")
	public String showLogin(Model model, HttpServletRequest request) {
		boolean isLogined = (boolean) request.getAttribute("isLogined");
		if (isLogined) {
			model.addAttribute("msg", "이미 로그인중 입니다.");
			model.addAttribute("replaceUri", mainUri);
		}

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
		Member member = (Member)loginRs.getBody();
		
		String authedEmail = memberService.getAuthedEmail(member.getId());

		if (authedEmail.equals(member.getEmail()) == false) {
			model.addAttribute("msg", String.format("이메일 인증 후 시도해주세요."));
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}

		session.setAttribute("loginedMemberId", member.getId());

		model.addAttribute("replaceUri", mainUri);
		return "common/redirect";
	}// doLogin

	@RequestMapping("member/doLogout")
	public String doLogout(Model model, HttpSession session, HttpServletRequest request) {
		boolean isLogined = (boolean) request.getAttribute("isLogined");
		if (!isLogined) {
			model.addAttribute("msg", "로그인 상태가 아닙니다.");
			model.addAttribute("replaceUri", mainUri);
		}

		session.removeAttribute("loginedMemberId");

		model.addAttribute("replaceUri", mainUri);
		return "common/redirect";
	}// doLogout

	@RequestMapping("member/modify")
	public String showModify(Model model, HttpServletRequest request, String authCode) {
		Member member = (Member) request.getAttribute("loginedMember");
		
		if(authCode == null || authCode.length() == 0) {
			model.addAttribute("msg", "정상적인 경로를 이용해주세요.");
			model.addAttribute("replaceUri", mainUri);
			return "common/redirect";
		}
		
		ResultData checkValidCheckPasswordAuthCodeResultData = memberService
				.checkValidCheckLoginPwAuthCode(member.getId(), authCode);

		if (checkValidCheckPasswordAuthCodeResultData.isFail()) {
			model.addAttribute("historyBack", true);
			model.addAttribute("msg", checkValidCheckPasswordAuthCodeResultData.getMsg());
			return "common/redirect";
		}


		model.addAttribute("member", member);
		return "usr/member/modify";
	}// showModify

	@RequestMapping("member/doModify")
	public String doModify(Model model, @RequestParam Map<String, Object> modifyParam) {
		ResultData modifyRs = memberService.modify(modifyParam);
		if (modifyRs.isFail()) {
			model.addAttribute("msg", modifyRs.getMsg());
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}

		model.addAttribute("msg", modifyRs.getMsg());
		model.addAttribute("historyBack", true);
		return "common/redirect";
	}// doModify

	@RequestMapping("member/showFindLoginId")
	public String showFindLoginId() {

		return "usr/member/findLoginId";
	}// showFindLoginId

	@RequestMapping("member/doFindLoginId")
	public String doFindLoginId(Model model, String email, String name) {
		ResultData findRs = memberService.findLoginId(email, name);
		if (findRs.isFail()) {
			model.addAttribute("msg", findRs.getMsg());
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}

		model.addAttribute("loginId", findRs.getBody());
		return "usr/member/findLoginId_rs";
	}// doFindLoginId

	@RequestMapping("member/showFindLoginPw")
	public String showFindLoginPw() {

		return "usr/member/findLoginPw";
	}// showFindLoginPw

	@RequestMapping("member/doFindLoginPw")
	public String doFindLoginPw(Model model, String loginId, String email) {
		ResultData findRs = memberService.findLoginPw(loginId, email);
		if (findRs.isFail()) {
			model.addAttribute("msg", findRs.getMsg());
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}
		
		Member member = (Member)findRs.getBody();
		
		ResultData setTempPwAndNotify = memberService.setTempPwAndNotify(member);

		model.addAttribute("msg", setTempPwAndNotify.getMsg());
		model.addAttribute("replaceUri", "/member/login");
		return "common/redirect";
	}// doFindLoginId
}