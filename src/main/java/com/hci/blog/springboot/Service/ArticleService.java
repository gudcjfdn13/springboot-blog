package com.hci.blog.springboot.Service;

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

	public List<Article> getArticles() {
		List<Article> articles = articleDao.getArticles();
		return articles;
	}// getArticles

	public Article getArticle(int id) {
		Article article = articleDao.getArticle(id);
		return article;
	}// getArticle

	public ResultData write(Map<String, Object> writeParam) {
		String title = Util.getAsString(writeParam.get("title"));
		String body = Util.getAsString(writeParam.get("body"));

		if (title.length() == 0)
			return new ResultData("F-1", "제목을 입력해주세요.");
		if (body.length() == 0)
			return new ResultData("F-1", "내용을 입력해주세요.");

		articleDao.doWrite(writeParam);

		return new ResultData("S-1", "글 작성 성공");
	}// write

}
