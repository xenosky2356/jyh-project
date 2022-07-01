package com.ds.domain;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("memberVO")
public class MemberVO {
	private int uno;
	private String userid;
	private String userpw;
	
	private String nickname;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date regdate;
	
	private String email;
	private String introduce;
	
	private List<TagVO> tagList;
}
