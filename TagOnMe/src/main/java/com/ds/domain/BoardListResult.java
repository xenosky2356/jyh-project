package com.ds.domain;

import lombok.Data;

@Data
public class BoardListResult {
	private BoardDTO board;
	private TagBoardDTO tag;
}
