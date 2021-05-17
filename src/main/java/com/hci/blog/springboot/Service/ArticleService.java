package com.hci.blog.springboot.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hci.blog.springboot.Util.Util;
import com.hci.blog.springboot.dao.ArticleDao;
import com.hci.blog.springboot.dto.Article;
import com.hci.blog.springboot.dto.ResultData;

@Service
public class ArticleService {
	@Autowired
	private ArticleDao articleDao;

	public int totalArticles() {
		return articleDao.totalArticles();
	}// totalArticles
	
	public List<Article> getArticles(Map<String, Object> pageParam) {
		int articlesInAPage = Util.getAsInt(pageParam.get("articlesInAPage"));
		int page = Util.getAsInt(pageParam.get("page"), 1);
		int from = (page-1) * articlesInAPage;
		int until = articlesInAPage;
		
		List<Article> articles = articleDao.getArticles(from, until);
		return articles;
	}// getArticles

	public Article getArticle(int id) {
		Article article = articleDao.getArticle(id);
		return article;
	}// getArticle

	public ResultData write(Map<String, Object> writeParam) {
		ResultData cf = confirm(writeParam);
		if(cf.isFail())
			return new ResultData(cf.getResultCode(), cf.getMsg());
		
		articleDao.doWrite(writeParam);
		return new ResultData(cf.getResultCode(), cf.getMsg());
	}// writeConfirm

	public ResultData modify(Map<String, Object> modifyParam) {
		ResultData cf = confirm(modifyParam);
		if(cf.isFail())
			return new ResultData(cf.getResultCode(), cf.getMsg());
		
		articleDao.doModify(modifyParam);
		return new ResultData(cf.getResultCode(), cf.getMsg());
	}// modifyConfirm
	
	private ResultData confirm(Map<String, Object> param) {
		String title = Util.getAsString(param.get("title"));
		String body = Util.getAsString(param.get("body"));

		if (title.length() == 0)
			return new ResultData("F-1", "제목을 입력해주세요.");
		if (body.length() == 0)
			return new ResultData("F-1", "내용을 입력해주세요.");
		
		return new ResultData("S-1", "글 작성 성공");
	}// confirm

	
	public void doDelete(int id) {
		articleDao.doDelete(id);
	}// doDelete
	

}
