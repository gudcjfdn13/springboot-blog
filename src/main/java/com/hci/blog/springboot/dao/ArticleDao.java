package com.hci.blog.springboot.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hci.blog.springboot.dto.Article;

@Mapper
public interface ArticleDao {

	List<Article> getArticles();

	Article getArticle(@Param("id") int id);

	void doWrite(Map<String, Object> writeParam);

}
