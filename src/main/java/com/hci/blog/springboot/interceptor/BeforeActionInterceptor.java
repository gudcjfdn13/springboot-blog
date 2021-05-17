package com.hci.blog.springboot.interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.hci.blog.springboot.Util.Util;
import com.hci.blog.springboot.dto.Member;
import com.hci.blog.springboot.service.MemberService;

@Component("beforeActionInterceptor") // 컴포넌트 이름 설정
public class BeforeActionInterceptor implements HandlerInterceptor {
	@Autowired
	MemberService memberService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();

		boolean isAjax = false;
		boolean isLogined = false;
		int loginedMemberId = 0;
		Member loginedMember = null;

		if (session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = Util.getAsInt(session.getAttribute("loginedMemberId"));
			loginedMember = memberService.getMemberById(loginedMemberId);
		} else {
			isLogined = false;
		}

		String currentUri = request.getRequestURI();

		if (request.getQueryString() != null) {
			currentUri += "?" + request.getQueryString();
		}

		String encodedCurrentUri = Util.getUriEncoded(currentUri);

		request.setAttribute("currentUri", currentUri);
		request.setAttribute("encodedCurrentUri", encodedCurrentUri);
		
		
		request.setAttribute("isAjax", isAjax);
		request.setAttribute("isLogined", isLogined);
		request.setAttribute("loginedMemberId", loginedMemberId);
		request.setAttribute("loginedMember", loginedMember);
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}