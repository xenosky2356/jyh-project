package com.ds.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyDTO extends CommonDTO {

	private Long rno;

	private Long bno;

	private String reply;

	private String replyer;
	
	private Long replyUp;
	
	private Long replyDown;

}
