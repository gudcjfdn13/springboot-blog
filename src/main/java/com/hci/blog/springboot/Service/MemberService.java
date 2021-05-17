package com.hci.blog.springboot.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hci.blog.springboot.Util.Util;
import com.hci.blog.springboot.dao.MemberDao;
import com.hci.blog.springboot.dto.Member;
import com.hci.blog.springboot.dto.ResultData;

@Service
public class MemberService {
	@Autowired
	MemberDao memberDao;
	
	public ResultData login(Map<String, Object> loginParam) {
		String loginId = Util.getAsString(loginParam.get("loginId"));
		String loginPw = Util.getAsString(loginParam.get("loginPw"));
		
		if (loginId.length() == 0)
			return new ResultData("F-1", "아이디를 입력해주세요.");
		if (loginPw.length() == 0)
			return new ResultData("F-1", "비밀번호를 입력해주세요.");
		
		Member member = memberDao.getMember(loginParam); 
		
		return new ResultData("S-1", "로그인 성공", member.getId());
	}// login

	public void join(Map<String, Object> joinParam) {


		memberDao.joinMember(joinParam);
		
	}


}
