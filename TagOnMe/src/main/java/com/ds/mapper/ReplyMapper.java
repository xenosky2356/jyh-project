package com.ds.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ds.domain.ReplyDTO;

@Mapper
public interface ReplyMapper {

	public int insertReply(ReplyDTO params);

	public ReplyDTO selectReplyDetail(Long rno);

	public int updateReply(ReplyDTO params);

	public int deleteReply(Long rno);

	public List<ReplyDTO> selectReplyList(ReplyDTO params);

	public int selectReplyTotalCount(ReplyDTO params);

}
