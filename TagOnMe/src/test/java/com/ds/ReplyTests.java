package com.ds;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ds.domain.ReplyDTO;
import com.ds.service.ReplyService;

@SpringBootTest
public class ReplyTests {
	
	@Autowired
	private ReplyService replyService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	public void registerReply() {
		int number = 20;

		for (int i = 1; i <= number; i++) {
			ReplyDTO params = new ReplyDTO();
			params.setRno((long)2); // 댓글을 추가할 게시글 번호
			params.setReply(i + "번 댓글을 추가합니다!");
			params.setReplyer(i + "번 회원");
			replyService.registerReply(params);
		}

		logger.debug("댓글 " + number + "개가 등록되었습니다.");
	}

	@Test
	public void getReplyList() {
		ReplyDTO params = new ReplyDTO();

		replyService.getReplyList(params);
	}
	
	@Test
	public void deleteReply() {
		replyService.deleteReply((long) 7); // 삭제할 댓글 번호

		getReplyList();
	}
}
