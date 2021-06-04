package com.ds.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.domain.ReplyDTO;
import com.ds.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyMapper replyMapper;

	@Override
	public boolean registerReply(ReplyDTO params) {
		int queryResult = 0;

		if (params.getRno() == null) {
			queryResult = replyMapper.insertReply(params);
		} else {
			queryResult = replyMapper.updateReply(params);
		}

		return (queryResult == 1) ? true : false;
	}

	@Override
	public boolean deleteReply(Long rno) {
		int queryResult = 0;

		ReplyDTO reply = replyMapper.selectReplyDetail(rno);

		if (reply != null) {
			queryResult = replyMapper.deleteReply(rno);
		}

		return (queryResult == 1) ? true : false;
	}

	@Override
	public List<ReplyDTO> getReplyList(ReplyDTO params) {
		List<ReplyDTO> replyList = Collections.emptyList();

		int ReplyTotalCount = replyMapper.selectReplyTotalCount(params);
		if (ReplyTotalCount > 0) {
			replyList = replyMapper.selectReplyList(params);
		}

		return replyList;
	}

}
