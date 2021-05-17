package com.hci.blog.springboot.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article extends Dto{
	private String title, body;
	private int memberId;
	
	private Map<String, Object> extra;
}
