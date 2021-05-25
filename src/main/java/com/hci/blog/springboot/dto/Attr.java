package com.hci.blog.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Attr extends Dto{
	private String expireDate, relTypeCode, typeCode, type2Code, value;
	private int relId;
}