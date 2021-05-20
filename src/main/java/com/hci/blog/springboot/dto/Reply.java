package com.hci.blog.springboot.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply extends Dto{
	private int articleId, memberId;
	private String body;
	
	private Map<String, Object> extra;
}
