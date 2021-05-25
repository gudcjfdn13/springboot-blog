package com.hci.blog.springboot.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hci.blog.springboot.Util.Util;
import com.hci.blog.springboot.dao.MemberDao;
import com.hci.blog.springboot.dto.Member;
import com.hci.blog.springboot.dto.ResultData;

@Service
public class MemberService {
	@Value("${custom.siteName}")
	private String siteName;
	@Value("${custom.siteUri}")
	private String siteUri;
	@Value("${custom.siteLoginUri}")
	private String siteLoginUri;
	
	@Autowired
	MailService mailService;
	@Autowired
	AttrService attrService;
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

		return new ResultData("S-1", "로그인 성공", member);
	}// login

	public ResultData join(Map<String, Object> joinParam) {
		String loginId = Util.getAsString(joinParam.get("loginId"));
		String loginPw = Util.getAsString(joinParam.get("loginPw"));
		String loginPwCf = Util.getAsString(joinParam.get("loginPwCf"));
		String name = Util.getAsString(joinParam.get("name"));
		String email = Util.getAsString(joinParam.get("email"));

		if (loginId.length() == 0)
			return new ResultData("F-1", "아이디를 입력해주세요.");
		if (email.length() == 0)
			return new ResultData("F-1", "이메일을 입력해주세요.");
		if (loginPw.length() == 0)
			return new ResultData("F-1", "비밀번호를 입력해주세요.");
		if (loginPw.equals(loginPwCf) == false)
			return new ResultData("F-1", "비밀번호가 일치하지 않습니다.");
		if (name.length() == 0)
			return new ResultData("F-1", "이름을 입력해주세요.");
		if (memberDao.getMemberByLoginId(loginId) != null) {
			return new ResultData("F-1", "이미 존재하는 아이디 입니다.");
		}
		if (memberDao.getMemberByEmail(email) != null) {
			return new ResultData("F-1", "이미 사용중인 이메일 입니다.");
		}
		loginPw = Util.sha256(loginPw);
		joinParam.put("loginPw", loginPw);
		memberDao.joinMember(joinParam);
		
		int id = Util.getAsInt(joinParam.get("id"));
		
		String authCode = genEmailAuthCode(id);

		sendJoinCompleteMail(id, (String) joinParam.get("email"), authCode);

		return new ResultData("S-1", "로그인 성공");
	}// join

	public Member getMemberById(int loginedMemberId) {

		return memberDao.getMemberById(loginedMemberId);
	}// getMemberById

	public ResultData modify(Map<String, Object> modifyParam) {
		String loginPw = Util.getAsString(modifyParam.get("loginPw"));
		String loginPwCf = Util.getAsString(modifyParam.get("loginPwCf"));
		
		if (loginPw.length() == 0)
			return new ResultData("F-1", "비밀번호를 입력해주세요.");
		if (loginPw.equals(loginPwCf) == false)
			return new ResultData("F-1", "비밀번호가 일치하지 않습니다.");
		loginPw = Util.sha256(loginPw);
		modifyParam.put("loginPw", loginPw);
		
		memberDao.modifyUserInfo(modifyParam);
		
		return new ResultData("S-1", "정보수정 성공");
	}// modify

	public ResultData findLoginId(String email, String name) {
		if (email.length() == 0)
			return new ResultData("F-1", "이메일을 입력해주세요.");
		if (name.length() == 0)
			return new ResultData("F-1", "이름을 입력해주세요");
		
		Member member = memberDao.getMemberByEmailAndName(email, name);
		if(member == null) {
			return new ResultData("F-1", "존재하지 않는 회원입니다.");
		}
		
		return new ResultData("S-1", "아이디 찾기 성공", member.getLoginId());
	}// findLoginId
	
	public ResultData findLoginPw(String loginId, String email) {
		if (loginId.length() == 0)
			return new ResultData("F-1", "아이디를 입력해주세요.");
		if (email.length() == 0)
			return new ResultData("F-1", "이메일을 입력해주세요");
		
		Member member = memberDao.getMemberByLoginIdAndEmail(loginId, email);
		if(member == null) {
			return new ResultData("F-1", "존재하지 않는 회원입니다.");
		}
		
		return new ResultData("S-1", "비밀번호 찾기 성공", member);
	}// findLoginPw

	public ResultData setTempPwAndNotify(Member member) {
		Random r = new Random();
		String tempLoginPw = (10000 + r.nextInt(90000)) + "";
		String mailTitle = String.format("[%s] 임시 비밀번호", siteName);
		String mailBody = String.format("임시 비밀번호 : %s", tempLoginPw);
		mailBody += "<br>";
		mailBody += String.format("<a href=\"%s\" target=\"_blank\">인증하기</a>", siteLoginUri);

		mailService.send(member.getEmail(), mailTitle, mailBody);

		Map<String, Object> param = new HashMap<>();
		param.put("id", member.getId());
		param.put("loginPw", Util.sha256(tempLoginPw));
		memberDao.modifyUserInfo(param);

		return new ResultData("S-1", "해당 이메일로 임시 패스워드를 발송했습니다.");
	}// setTempPwAndNotify

	private String genEmailAuthCode(int actorId) {
		String authCode = UUID.randomUUID().toString();
		attrService.setValue("member__" + actorId + "__extra__emailAuthCode", authCode);

		return authCode;
	}// genEmailAuthCode
	
	private void sendJoinCompleteMail(int actorId, String email, String authCode) {
		String mailTitle = String.format("[%s] 가입이 완료되었습니다. 이메일인증을 진행해주세요.", siteName);

		StringBuilder mailBodySb = new StringBuilder();
		mailBodySb.append("<h1>가입이 완료되었습니다.</h1>");
		mailBodySb.append("<div>아래 인증코드를 클릭하여 이메일인증을 마무리 해주세요.</div>");

		String doAuthEmailUrl = siteUri + "/member/doAuthEmail?authCode=" + authCode + "&email=" + email
				+ "&actorId=" + actorId;
		mailBodySb.append(String.format("<p><a href=\"%s\" target=\"_blank\">인증하기</a></p>", doAuthEmailUrl));

		mailService.send(email, mailTitle, mailBodySb.toString());
	}// sendJoinCompleteMail
	
	public String genCheckLoginPwAuthCode(int actorId) {
		String authCode = UUID.randomUUID().toString();
		attrService.setValue("member__" + actorId + "__extra__modifyPrivateAuthCode", authCode,
				Util.getDateStrLater(60 * 60));

		return authCode;
	}
	
	public String getEmailAuthCode(int actorId) {
		return attrService.getValue("member__" + actorId + "__extra__emailAuthCode");
	}
	
	public void saveAuthedEmail(int actorId, String email) {
		attrService.setValue("member__" + actorId + "__extra__authedEmail", email);
	}
	
	public String getAuthedEmail(int actorId) {
		return attrService.getValue("member__" + actorId + "__extra__authedEmail");
	}

	public ResultData checkValidCheckLoginPwAuthCode(int id, String authCode) {
		if (attrService.getValue("member__" + id + "__extra__modifyPrivateAuthCode")
				.equals(authCode)) {
			return new ResultData("S-1", "유효한 키 입니다.");
		}

		return new ResultData("F-1", "유효하지 않은 키 입니다.");
	}
}
