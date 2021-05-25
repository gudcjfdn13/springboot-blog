package com.hci.blog.springboot.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hci.blog.springboot.Util.Util;
import com.hci.blog.springboot.dao.ArticleDao;
import com.hci.blog.springboot.dto.Article;
import com.hci.blog.springboot.dto.Board;
import com.hci.blog.springboot.dto.ResultData;

@Service
public class ArticleService {
	@Autowired
	private ArticleDao articleDao;

	public int totalArticles(String searchKeyword, int boardId) {
		return articleDao.totalArticles(searchKeyword, boardId);
	}// totalArticles
	
	public List<Article> getArticles(Map<String, Object> pageParam) {
		String searchKeyword = Util.getAsString(pageParam.get("searchKeyword"));
		int boardId = Util.getAsInt(pageParam.get("boardId"));
		int articlesInAPage = Util.getAsInt(pageParam.get("articlesInAPage"));
		int page = Util.getAsInt(pageParam.get("page"), 1);
		
		int from = (page-1) * articlesInAPage;
		int until = articlesInAPage;
		
		List<Article> articles = articleDao.getArticles(from, until, searchKeyword, boardId);

		return articles;
	}// getArticles

	public Article getArticle(int id) {
		Article article = articleDao.getArticle(id);
		return article;
	}// getArticle

	public Article getArticleForPrint(int id, int loginedMemberId) {
		Article article = articleDao.getArticleForPrint(id);
		
		if(article.getExtra()==null) {
			article.setExtra(new HashMap<String, Object>());
		}
		boolean canModify = article.getMemberId() == loginedMemberId;
		boolean canDelete = canModify;
		
		article.getExtra().put("canModify", canModify);
		article.getExtra().put("canDelete", canDelete);
		
		return article;
	}// getArticleForPrint

	public ResultData write(Map<String, Object> writeParam) {
		if(writeParam.get("boardId").equals("none")) {
			return new ResultData("F-1", "카테고리를 적용해주세요.");
		}
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

	public Board getBoardByCode(String boardCode) {
		return articleDao.getBoardByCode(boardCode);
	}// getBoardByCode

	public Board getBoardById(int boardId) {
		return articleDao.getBoardById(boardId);
	}// getBoardById
	

}
