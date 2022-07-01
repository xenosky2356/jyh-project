package com.ds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.domain.ProfileAttachVO;
import com.ds.mapper.ProfileAttachMapper;

import lombok.extern.log4j.Log4j;

@Service
public class ProfileAttachServiceImpl implements ProfileAttachService {
	
	@Autowired
	ProfileAttachMapper profileAttachMapper;
	
	@Override
	public void insert(ProfileAttachVO vo) {
		profileAttachMapper.insert(vo);
	}

//	@Override
//	public boolean delProImage(String uuid) {
//		return profileAttachMapper.delProImage(uuid) > 0 ? true : false;
//	}

	@Override
	public List<ProfileAttachVO> selectFileName() {
		return profileAttachMapper.selectFileName();
	}
	
}
