package com.hci.blog.springboot.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hci.blog.springboot.Util.Util;
import com.hci.blog.springboot.dto.Reply;
import com.hci.blog.springboot.service.ReplyService;

@Controller
public class ReplyController {
	@Autowired
	private ReplyService replyService;
	
	@RequestMapping("reply/doWrite")
	public String doWrite(Model model, @RequestParam Map<String, Object> writeParam) {
		int articleId = Util.getAsInt(writeParam.get("articleId"));
		replyService.write(writeParam);
		
		model.addAttribute("replaceUri", String.format("/article/detail?id=%d", articleId));
		return "common/redirect";
	}// doWrite
	
	@RequestMapping("reply/doDelete")
	public String doDelete(Model model, HttpServletRequest request, int id) {
		int loginedMemberId = (int) request.getAttribute("loginedMemberId");
		Reply reply = replyService.getReply(id);
		int memberId = reply.getMemberId();
		int articleId = reply.getArticleId();
		if(loginedMemberId != memberId) {
			model.addAttribute("msg", "권한이 없습니다.");
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}
		
		replyService.doDelete(id);
		
		model.addAttribute("replaceUri", String.format("/article/detail?id=%d", articleId));
		return "common/redirect";
	}// doDelete
}
