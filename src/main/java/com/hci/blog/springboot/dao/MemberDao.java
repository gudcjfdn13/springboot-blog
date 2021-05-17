package com.hci.blog.springboot.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hci.blog.springboot.dto.Member;

@Mapper
public interface MemberDao {
	Member getMember(Map<String, Object> loginParam);

	Member getMemberByLoginId(@Param("loginId") String loginId);

	Member getMemberById(@Param("id") int loginedMemberId);

	void joinMember(Map<String, Object> joinParam);

}
