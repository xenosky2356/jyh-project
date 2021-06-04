package com.ds.service;

import java.util.List;

import com.ds.domain.ReplyDTO;

public interface ReplyService {

	public boolean registerReply(ReplyDTO params);

	public boolean deleteReply(Long rno);

	public List<ReplyDTO> getReplyList(ReplyDTO params);

}
