package com.hci.blog.springboot.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hci.blog.springboot.Util.Util;
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
	}
}
