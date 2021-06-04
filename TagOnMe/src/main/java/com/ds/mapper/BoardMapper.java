package com.ds.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ds.domain.BoardDTO;
import com.ds.domain.MemberVO;
import com.ds.domain.TagBoardDTO;

@Mapper
public interface BoardMapper {


	public int insertBoard(BoardDTO params);

	public BoardDTO selectBoardDetail(Long bno);

	public int updateBoard(BoardDTO params);

	public int deleteBoard(Long idx);

	public List<BoardDTO> selectBoardList(BoardDTO params);
	
	public List<BoardDTO> selectHitBoardList(BoardDTO params);

	public int selectBoardTotalCount(BoardDTO params);
	
	public int updateViewCnt(Long bno);
	
	public List<TagBoardDTO> selectBoardTagList (Long idx);
	
	public int updateReplyCount(Long idx);
	

}
