package com.ds.domain;

import java.util.List;

import lombok.Data;

@Data
public class TagBoardDTO{
	private Long tbno;
	private Long bno;
	private Long tno;
	
	private String otherTags;
	private String tags;	
	
	private List<TagDTO> tagNameList;
}
