package com.ds.domain;

import java.time.LocalDateTime;

import com.ds.paging.Criteria;
import com.ds.paging.PaginationInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonDTO extends Criteria {

	/** 페이징 정보 */
	private PaginationInfo paginationInfo;

	/** 삭제 여부 */
	private String deletedYn;

	/** 등록일 */
	private LocalDateTime regDate;

}
