package com.ds.service;

import java.util.List;

import com.ds.domain.TagVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.domain.TagBoardDTO;
import com.ds.domain.TagDTO;
import com.ds.mapper.TagBoardMapper;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TagBoardServiceImpl implements TagBoardService {

	@Autowired
	private TagBoardMapper tagBoardMapper;

	@Override
	public List<TagBoardDTO> getTagList(TagBoardDTO params) {
		return tagBoardMapper.selectTagBoardList(params);
	}

	@Override
	public List<TagBoardDTO> getTagDetail(Long bno) {
		return tagBoardMapper.selectTagDetail(bno);
	}

	@Override//존재하는 유저태그 삽입
	public void oldBoardTag(TagBoardDTO tagBoardDTO) {
		tagBoardMapper.oldBoardTag(tagBoardDTO);
	}

	@Override//유저 태그 삭제
	public void delBoardTag(TagBoardDTO tagBoardDTO) {
		tagBoardMapper.delBoardTag(tagBoardDTO);
	}

	@Override
	public void newBoardTag(TagBoardDTO tagBoardDTO) {
		tagBoardMapper.newBoardTag(tagBoardDTO);
	}


	@Override
	@Transactional
	public void newTag(TagBoardDTO tagBoardDTO) {
		tagBoardMapper.newTag(tagBoardDTO);
		tagBoardMapper.newBoardTag(tagBoardDTO);
	}


	@Override
	public List<TagBoardDTO> getTagHitList(TagBoardDTO params) {
		return tagBoardMapper.selectTagCount(params);
	}

	@Override
	public List<TagBoardDTO> getSearchTagList(Long bno) {
		return tagBoardMapper.selectSearchTagList(bno);
	}
}
