package com.ds.domain;

import java.util.List;

import lombok.Data;


@Data
public class BoardDTO extends CommonDTO {

	/** 번호 (PK) */
	private Long bno;

	/** 제목 */
	private String title;

	/** 내용 */
	private String content;

	/** 작성자 */
	private String writer;

	/** 조회 수 */
	private int hitCnt;
	
	/** 댓글 수 */
	private int replyCnt;
	
	/** 추천 수 */
	private int boardUp;
	
	/** 비추천 수 */
	private int boardDown;	

	/** 파일 변경 여부 */
	private String changeYn;

	/** 파일 인덱스 리스트 */
	private List<Long> fileIdxs;
	
}
