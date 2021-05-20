package com.hci.blog.springboot.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hci.blog.springboot.dto.Article;

@Mapper
public interface ArticleDao {

	int totalArticles(@Param("searchKeyword") String searchKeyword);

	List<Article> getArticles(@Param("from") int from, @Param("until")  int until, @Param("searchKeyword") String searchKeyword);

	Article getArticle(@Param("id") int id);

	Article getArticleForPrint(@Param("id") int id);

	void doWrite(Map<String, Object> writeParam);

	void doModify(Map<String, Object> modifyParam);

	void doDelete(@Param("id") int id);


}
