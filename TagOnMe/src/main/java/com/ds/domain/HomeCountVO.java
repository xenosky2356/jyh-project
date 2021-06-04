package com.ds.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HomeCountVO {

	public int userCount;
	public int userTagCount;
	public int boardTagCount;
}