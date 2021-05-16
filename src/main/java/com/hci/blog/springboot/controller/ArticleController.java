package com.hci.blog.springboot.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hci.blog.springboot.Service.ArticleService;
import com.hci.blog.springboot.Util.Util;
import com.hci.blog.springboot.dto.Article;
import com.hci.blog.springboot.dto.ResultData;

@Controller
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("article/list")
	public String showList(Model model) {
		List<Article> articles = articleService.getArticles();
		
		model.addAttribute("articles", articles);
		return "usr/article/list";
	}// showList
	
	@RequestMapping("article/detail")
	public String showDetail(Model model, int id) {
		Article article = articleService.getArticle(id);
		
		model.addAttribute("article", article);
		return "usr/article/detail";
	}// showDetail
	
	@RequestMapping("article/write")
	public String showWrite() {
		
		return "usr/article/write";
	}// showWrite
	
	@RequestMapping("article/doWrite")
	public String doWrite(Model model, @RequestParam Map<String, Object> writeParam) {
		ResultData writeRs = articleService.write(writeParam);
		if(writeRs.isFail()) {
			model.addAttribute("msg", writeRs.getMsg());
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}
		
		model.addAttribute("replaceUri", String.format("/article/detail?id=%d", writeParam.get("id")));
		return "common/redirect";
	}// doWrtie
	
	@RequestMapping("article/modify")
	public String showModify(Model model, int id) {
		Article article = articleService.getArticle(id);
		
		model.addAttribute("article", article);
		return "usr/article/modify";
	}// showModify
	
	@RequestMapping("article/doModify")
	public String doModify(Model model, @RequestParam Map<String, Object> modifyParam) {
		ResultData modifyRs = articleService.modify(modifyParam);
		if(modifyRs.isFail()) {
			model.addAttribute("msg", modifyRs.getMsg());
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}
		
		model.addAttribute("replaceUri", String.format("/article/detail?id=%d", Util.getAsInt(modifyParam.get("id"))));
		return "common/redirect";
	}// doModify
}
