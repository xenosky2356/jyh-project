package com.ds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ds.domain.HomeLinksVO;
import com.ds.domain.HomeVO;
import com.ds.mapper.HomeMapper;

@Component
@Service
//@Transactional
public class HomeServiceImpl implements HomeService {

	@Autowired
	HomeMapper homeMapper;
	
	@Override
	public List<HomeVO> getList() throws Exception {
		List<HomeVO> list = homeMapper.homeTagList(); 
		return list;
	}

	@Override
	public List<HomeLinksVO> getLinksList() throws Exception {
		List<HomeLinksVO> linksList = homeMapper.homeLinksList(); 
		
		return linksList;
	}	
}
