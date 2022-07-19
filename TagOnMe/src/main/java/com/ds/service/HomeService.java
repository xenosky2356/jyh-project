package com.ds.service;

import java.util.List;

import com.ds.domain.HomeCountVO;
import com.ds.domain.HomeLinksVO;
import com.ds.domain.HomeTagList20VO;
import com.ds.domain.HomeVO;

public interface HomeService {
	public List<HomeVO> getList() throws Exception;
	public List<HomeLinksVO> getLinksList() throws Exception;
	public int getUserCount();
	public int getUserTagCount();
	public int getBoardTagCount();
	public List<HomeTagList20VO> getTagList20() throws Exception;
}

