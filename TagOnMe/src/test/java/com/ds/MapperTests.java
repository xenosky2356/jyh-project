package com.ds;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ds.domain.BoardDTO;
import com.ds.domain.TagBoardDTO;
import com.ds.mapper.BoardMapper;
import com.ds.mapper.TagBoardMapper;

@SpringBootTest
class MapperTests {

	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private TagBoardMapper TagboardMapper;

	@Test
	public void testInsert() throws Exception {
		for (int i = 2; i <= 50; i++) {
			BoardDTO params = new BoardDTO();
			params.setTitle(i + "번 게시글 제목");
			params.setContent(i + "번 게시글 내용");
			for(int j = 1; j < 6; j++) {
				params.setWriter("user"+j);			
			}
			boardMapper.insertBoard(params);
		}
	}
	
	/*@Test
	public void testSelectList() {
		BoardDTO params = new BoardDTO();		
		boardMapper.selectBoardList(params);
	}*/
}