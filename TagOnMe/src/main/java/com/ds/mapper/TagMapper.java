package com.ds.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ds.domain.MemberVO;
import com.ds.domain.TagVO;

@Mapper
public interface TagMapper {
	List<TagVO> list(); 
	
	public int insert(TagVO dto);
		
	public int delete (Long tno);
	
	public int updateLeader(Long tno);
	public int cancelupdateLeader(TagVO dto);

	TagVO read(Long tno);
	

}
