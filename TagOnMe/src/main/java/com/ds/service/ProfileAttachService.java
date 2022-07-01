package com.ds.service;

import java.util.List;

import com.ds.domain.ProfileAttachVO;

public interface ProfileAttachService {
	
	public void insert(ProfileAttachVO vo);
	//public boolean delProImage(String uuid);
	public List<ProfileAttachVO> selectFileName();
}
