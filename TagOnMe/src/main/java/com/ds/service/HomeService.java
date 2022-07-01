package com.ds.service;

import java.util.List;

import com.ds.domain.HomeLinksVO;
import com.ds.domain.HomeVO;

public interface HomeService {
	public List<HomeVO> getList() throws Exception;
	public List<HomeLinksVO> getLinksList() throws Exception;
}

