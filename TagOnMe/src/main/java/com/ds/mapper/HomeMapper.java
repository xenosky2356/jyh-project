package com.ds.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ds.domain.HomeLinksVO;
import com.ds.domain.HomeVO;

@Mapper
public interface HomeMapper {

	List<HomeVO> homeTagList() throws Exception;
	List<HomeLinksVO> homeLinksList() throws Exception;
}