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
		if (member == null)
			return new ResultData("F-1", "존재하지 않는 회원입니다.");

		return new ResultData("S-1", "로그인 성공", member.getId());
	}// login

	public ResultData join(Map<String, Object> joinParam) {
		String loginId = Util.getAsString(joinParam.get("loginId"));
		String loginPw = Util.getAsString(joinParam.get("loginPw"));
		String loginPwCf = Util.getAsString(joinParam.get("loginPwCf"));
		String name = Util.getAsString(joinParam.get("name"));

		if (loginId.length() == 0)
			return new ResultData("F-1", "아이디를 입력해주세요.");
		if (loginPw.length() == 0)
			return new ResultData("F-1", "비밀번호를 입력해주세요.");
		if (loginPw.equals(loginPwCf) == false)
			return new ResultData("F-1", "비밀번호가 일치하지 않습니다.");
		if (name.length() == 0)
			return new ResultData("F-1", "이름을 입력해주세요.");
		if (memberDao.getMemberByLoginId(loginId) != null) {
			return new ResultData("F-1", "이미 존재하는 아이디 입니다.");
		}
		memberDao.joinMember(joinParam);

		return new ResultData("S-1", "로그인 성공");
	}// join

	public Member getMemberById(int loginedMemberId) {

		return memberDao.getMemberById(loginedMemberId);
	}// getMemberById

}
