package com.hci.blog.springboot.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.hci.blog.springboot.dto.Member;

@Mapper
public interface MemberDao {
	Member getMember(Map<String, Object> loginParam);

}
