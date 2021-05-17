package com.hci.blog.springboot.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hci.blog.springboot.Util.Util;
import com.hci.blog.springboot.dto.Article;
import com.hci.blog.springboot.dto.ResultData;
import com.hci.blog.springboot.service.ArticleService;

@Controller
public class ArticleController {
	@Autowired
	private ArticleService articleService;

	@RequestMapping("article/list")
	public String showList(Model model, HttpServletRequest request, @RequestParam Map<String, Object> pageParam) {
		int loginedMemberId = (int) request.getAttribute("loginedMemberId");
		int page = Util.getAsInt(pageParam.get("page"), 1); // 현 페이지
		if (page < 1) page = 1;
		int articlesInAPage = 10; // 페이지당 게시물
		int articlesCnt = articleService.totalArticles(); // 총 게시물
		int pageCnt = (int) Math.ceil((double) articlesCnt / articlesInAPage); // 총 페이지

		int pageBlock = Math.abs((page - 1) / articlesInAPage) + 1; // 페이지 블럭
		int startPage = ((pageBlock - 1) * articlesInAPage) + 1; // 시작 페이지
		int lastPage = (startPage + articlesInAPage) - 1; // 끝 페이지
		if(lastPage > pageCnt) lastPage = pageCnt;
		
		pageParam.put("page", page);
		pageParam.put("articlesInAPage", articlesInAPage);
		List<Article> articles = articleService.getArticles(pageParam, loginedMemberId);

		System.out.println("articles : " + articles.toString());
		
		model.addAttribute("page", page);
		model.addAttribute("articlesCnt", articlesCnt);
		model.addAttribute("startPage", startPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("pageCnt", pageCnt);
		model.addAttribute("articles", articles);
		return "usr/article/list";
	}// showList

	@RequestMapping("article/detail")
	public String showDetail(Model model, HttpServletRequest request, int id) {
		int loginedMemberId = (int) request.getAttribute("loginedMemberId");
		Article article = articleService.getArticle(id, loginedMemberId);

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
		if (writeRs.isFail()) {
			model.addAttribute("msg", writeRs.getMsg());
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}

		model.addAttribute("replaceUri", String.format("/article/detail?id=%d", writeParam.get("id")));
		return "common/redirect";
	}// doWrtie

	@RequestMapping("article/modify")
	public String showModify(Model model, HttpServletRequest request, int id) {
		int loginedMemberId = (int) request.getAttribute("loginedMemberId");
		if(loginedMemberId != id) {
			model.addAttribute("msg", "권한이 없습니다.");
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}
		
		Article article = articleService.getArticle(id);
		model.addAttribute("article", article);
		return "usr/article/modify";
	}// showModify

	@RequestMapping("article/doModify")
	public String doModify(Model model, HttpServletRequest request, @RequestParam Map<String, Object> modifyParam) {
		int id = Util.getAsInt(modifyParam.get("id"));
		int loginedMemberId = (int) request.getAttribute("loginedMemberId");
		if(loginedMemberId != id) {
			model.addAttribute("msg", "권한이 없습니다.");
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}
		
		ResultData modifyRs = articleService.modify(modifyParam);
		if (modifyRs.isFail()) {
			model.addAttribute("msg", modifyRs.getMsg());
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}

		model.addAttribute("replaceUri", String.format("/article/detail?id=%d", Util.getAsInt(modifyParam.get("id"))));
		return "common/redirect";
	}// doModify

	@RequestMapping("article/doDelete")
	public String doDelete(Model model, HttpServletRequest request, int id) {
		int loginedMemberId = (int) request.getAttribute("loginedMemberId");
		if(loginedMemberId != id) {
			model.addAttribute("msg", "권한이 없습니다.");
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}
		articleService.doDelete(id);

		model.addAttribute("replaceUri", "/article/list");
		return "common/redirect";
	}// doDelete
}
