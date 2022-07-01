package com.ds.service;

import java.util.List;

import com.ds.domain.TagVO;
import org.springframework.ui.Model;

import com.ds.domain.MemberVO;
import com.ds.domain.TagBoardDTO;
import com.ds.domain.TagDTO;

public interface TagBoardService {
	
	public List<TagBoardDTO> getTagList(TagBoardDTO params);
	
	public List<TagBoardDTO> getTagHitList(TagBoardDTO params);
	
	public List<TagBoardDTO> getTagDetail(Long bno);
	
	public List<TagBoardDTO> getSearchTagList(Long bno);

	public void oldBoardTag(TagBoardDTO tagBoardDTO);

	public void newTag(TagBoardDTO tagDTO);//존재하지 않는 태그 만들기

	public void newBoardTag(TagBoardDTO tagBoardDTO);//존재하지 않는 유저태그 삽입

	public void delBoardTag(TagBoardDTO tagBoardDTO);//유저 태그 삭제(임시)
}
