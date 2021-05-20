package com.hci.blog.springboot.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hci.blog.springboot.dao.ReplyDao;
import com.hci.blog.springboot.dto.Reply;

@Service
public class ReplyService {
	@Autowired
	private ReplyDao replyDao;

	public void write(Map<String, Object> writeParam) {
		replyDao.doWrite(writeParam);
	}

	public List<Reply> getReplies(int articleId) {
		return replyDao.getReplies(articleId);
	}
}
