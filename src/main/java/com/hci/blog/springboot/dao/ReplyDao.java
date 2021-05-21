package com.hci.blog.springboot.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hci.blog.springboot.dto.Reply;

@Mapper
public interface ReplyDao {

	void doWrite(Map<String, Object> writeParam);

	List<Reply> getReplies(@Param("articleId") int articleId);

	Reply getReply(@Param("id") int id);

	void doDelete(@Param("id") int id);

}
