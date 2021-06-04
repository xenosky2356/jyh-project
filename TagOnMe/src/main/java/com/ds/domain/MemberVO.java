package com.ds.domain;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("memberVO")
public class MemberVO {
	private Integer uno;
	private String userid;
	private String userpw;
	
	private String nickname;
	
	private int idChk;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date regdate;
	
	//@Email(message = "이메일 형식이 아닙니다.")
	private String email;
	private String introduce;
	
	private List<TagVO> tagList;
}
