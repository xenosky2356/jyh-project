package com.ds.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ds.domain.AttachDTO;
import com.ds.domain.BoardDTO;

public interface BoardService {

	public boolean registerBoard(BoardDTO params);

	public boolean registerBoard(BoardDTO params, MultipartFile[] files);

	public BoardDTO getBoardDetail(Long bno);

	public boolean deleteBoard(Long bno);

	public List<BoardDTO> getBoardList(BoardDTO params);
	
	public List<BoardDTO> getHitBoardList(BoardDTO params);

	public List<AttachDTO> getAttachFileList(Long bno);

	public AttachDTO getAttachDetail(Long bno);

}
