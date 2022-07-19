package com.ds.service;

import java.util.List;

import com.ds.domain.TagVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.domain.TagBoardDTO;
import com.ds.domain.TagDTO;
import com.ds.mapper.TagBoardMapper;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TagBoardServiceImpl implements TagBoardService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
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
	
	@Override
	public List<TagBoardDTO> getTagHitList(TagBoardDTO params) {
		return tagBoardMapper.selectTagCount(params);
	}
	
	@Override
	public List<TagBoardDTO> getSearchTagList(Long bno) {
		return tagBoardMapper.selectSearchTagList(bno);
	}
	
	@Override
	public List<TagBoardDTO> getSearchTagListForNull(TagBoardDTO params) {
		return tagBoardMapper.selectSearchTagListForNull(params);
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
	public void oldBoardWriteTag(TagBoardDTO tagBoardDTO) {	
		log.info(""+tagBoardDTO);
		tagBoardMapper.oldBoardWriteTag(tagBoardDTO);
	}

	@Override
	@Transactional
	public void newBoardWriteTag(TagBoardDTO tagBoardDTO) {
		tagBoardMapper.newTag(tagBoardDTO);
		tagBoardMapper.newBoardWriteTag(tagBoardDTO);
	}

	@Override
	public void delBoardWriteTag(TagBoardDTO tagBoardDTO) {
		tagBoardMapper.delBoardWriteTag(tagBoardDTO);
		
	}
}
