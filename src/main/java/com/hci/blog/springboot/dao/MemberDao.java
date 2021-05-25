package com.hci.blog.springboot.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hci.blog.springboot.dto.Member;

@Mapper
public interface MemberDao {
	Member getMember(Map<String, Object> loginParam);

	Member getMemberById(@Param("id") int loginedMemberId);

	Member getMemberByLoginId(@Param("loginId") String loginId);

	Object getMemberByEmail(@Param("email") String email);

	Member getMemberByLoginIdAndEmail(@Param("loginId") String loginId, @Param("email") String email);

	Member getMemberByEmailAndName(@Param("email") String email, @Param("name") String name);

	void joinMember(Map<String, Object> joinParam);

	void modifyUserInfo(Map<String, Object> modifyParam);

}
