package com.hci.blog.springboot.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hci.blog.springboot.Util.Util;
import com.hci.blog.springboot.dao.ReplyDao;
import com.hci.blog.springboot.dto.Reply;
import com.hci.blog.springboot.dto.ResultData;

@Service
public class ReplyService {
	@Autowired
	private ReplyDao replyDao;

	public ResultData write(Map<String, Object> writeParam) {
		String body = Util.getAsString(writeParam.get("body"));

		if (body.length() == 0)
			return new ResultData("F-1", "내용을 입력해주세요.");
		
		replyDao.doWrite(writeParam);
		return new ResultData("S-1", "댓글 작성 성공");
	}// write

	public List<Reply> getReplies(int articleId, int loginedMemberId) {
		List<Reply> replies = replyDao.getReplies(articleId);
		for(Reply reply : replies) {
			if(reply.getExtra()==null) {
				reply.setExtra(new HashMap<String, Object>());
			}
			boolean canModify = reply.getMemberId() == loginedMemberId;
			boolean canDelete = canModify;
			
			reply.getExtra().put("canModify", canModify);
			reply.getExtra().put("canDelete", canDelete);
	
		}
		return replies;
	}// getReplies

	public Reply getReply(int id) {
		return replyDao.getReply(id);
	}// getReply

	public void doDelete(int id) {
		replyDao.doDelete(id);
		
	}// doDelete
}
