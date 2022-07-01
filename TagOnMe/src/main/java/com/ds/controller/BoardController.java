package com.ds.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ds.domain.*;
import com.ds.service.MemberService;
import com.ds.service.ReplyService;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ds.constant.Method;
import com.ds.service.BoardService;
import com.ds.service.CommonService;
import com.ds.service.TagBoardService;
import com.ds.util.UiUtils;

import lombok.extern.log4j.Log4j;
 
//dddd

@Controller
@RequestMapping("/board/*")
public class BoardController extends UiUtils {

	@Autowired
	private BoardService boardService;

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private TagBoardService tagBoardService;
	
	@Autowired
	private ReplyService replyService;

	@GetMapping(value = "/write.do")
	public String openBoardWrite(@ModelAttribute("params") BoardDTO params, @ModelAttribute("tagParams") TagBoardDTO tagParams, @ModelAttribute("member") @Validated MemberVO member,
			@RequestParam(value = "bno", required = false) Long bno, Model model, HttpServletRequest request) {
		
		member = commonService.getLoginUser(request);
		List<TagBoardDTO> searchTag = tagBoardService.getSearchTagList(bno);
		model.addAttribute("searchTag", searchTag );
		
		if (bno == null) {
			model.addAttribute("board", new BoardDTO());
			model.addAttribute("openTag", new TagBoardDTO());
			model.addAttribute("member", member);

		} else {
			BoardDTO board = boardService.getBoardDetail(bno);
			List<TagBoardDTO> openTag = tagBoardService.getTagDetail(bno);

			if (board == null) {
				return showMessageWithRedirect("없는 게시글이거나 이미 삭제된 게시글입니다.", "/board/list.do", Method.GET, null, model);
			}
			model.addAttribute("board", board);
			model.addAttribute("member", member);

			model.addAttribute("openTag", openTag);
			
			List<AttachDTO> fileList = boardService.getAttachFileList(bno);
			model.addAttribute("fileList", fileList);
		}

		return "write";
	}

	@PostMapping(value = "/register.do")
	public String registerBoard(final BoardDTO params,final TagBoardDTO tagParams , final MultipartFile[] files, Model model) {
		Map<String, Object> pagingParams = getPagingParams(params);
		try {
			boolean isRegistered = boardService.registerBoard(params, files);
			if (isRegistered == false) {
				return showMessageWithRedirect("게시글 등록에 실패하였습니다.", "/board/list.do", Method.GET, pagingParams, model);
			}

		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/board/list.do", Method.GET, pagingParams, model);

		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/board/list.do", Method.GET, pagingParams, model);
		}

		return showMessageWithRedirect("게시글 등록이 완료되었습니다.", "/board/list.do", Method.GET, pagingParams, model);
	}

	//존재하는 유저 태그 삽입 & 존재하지 않는 태그 삽입
	@PostMapping(value="/oldBoardTag.do")
	public ResponseEntity<TagBoardDTO> oldBoardTag(@RequestBody TagBoardDTO tagBoardDTO) throws Exception {
		tagBoardService.oldBoardTag(tagBoardDTO);

		return new ResponseEntity<TagBoardDTO>(tagBoardDTO,HttpStatus.OK);
	}

	@PostMapping(value= "/newTag.do")
	public String newTag(TagBoardDTO tagVO){

		tagBoardService.newTag(tagVO);

		return "redirect:/board/write.do";
	}

	//유저태그삭제(임시)
	@PostMapping(value="/delBoardTag.do")
	@ResponseBody
	public ResponseEntity<TagBoardDTO> delUserTag(@RequestBody TagBoardDTO tagVO) throws Exception {

		tagBoardService.delBoardTag(tagVO);

		return new ResponseEntity<TagBoardDTO>(tagVO,HttpStatus.OK);
	}


	@GetMapping(value = "/list.do")
	public String openBoardList(@ModelAttribute("params") BoardDTO params, @ModelAttribute("tags") TagBoardDTO tags, Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession(false);
		  
		  if(session != null) {
			  model.addAttribute("userLogin", true );
		  } else {
			  model.addAttribute("userLogin", false );
		  }
		
		List<BoardDTO> boardList = boardService.getBoardList(params);
		List<TagBoardDTO> tagTitle = tagBoardService.getTagList(tags);
		List<BoardDTO> hitList = boardService.getHitBoardList(params);
		List<TagBoardDTO> hitTagList = tagBoardService.getTagHitList(tags);

		model = commonService.loginUser(model, request);

		model.addAttribute("boardList", boardList);
		model.addAttribute("tagTitle", tagTitle);
		model.addAttribute("hitList", hitList);
		model.addAttribute("hitTagList", hitTagList);

		return "board";
	}

	@GetMapping(value = "/view.do")
	public String openBoardDetail(BoardDTO params, TagBoardDTO tags, @RequestParam(value = "bno", required = false) Long bno, Model model, HttpServletRequest request) {
		
		model = commonService.loginUser(model, request);
		if (bno == null) {
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "/board/list.do", Method.GET, null, model);
		}

		BoardDTO board = boardService.getBoardDetail(bno);
		List<TagBoardDTO> openTagList = tagBoardService.getTagDetail(bno);
		
		if (board == null) {
			return showMessageWithRedirect("없는 게시글이거나 이미 삭제된 게시글입니다.", "/board/list.do", Method.GET, null, model);
		}
		model.addAttribute("board" , board);
		model.addAttribute("openTagList",openTagList);

		List<AttachDTO> fileList = boardService.getAttachFileList(bno); // 추가된 로직
		model.addAttribute("fileList", fileList); // 추가된 로직

		return "board-detail";
	}
	
	@PostMapping(value = "/delete.do")
	public String deleteBoard(@ModelAttribute("params") BoardDTO params,
			@RequestParam(value = "bno", required = false) Long bno, Model model) {
		if (bno == null) {
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "/board/list.do", Method.GET, null, model);
		}

		Map<String, Object> pagingParams = getPagingParams(params);
		try {
			boolean isDeleted = boardService.deleteBoard(bno);
			if (isDeleted == false) {
				return showMessageWithRedirect("게시글 삭제에 실패하였습니다.", "/board/list.do", Method.GET, pagingParams, model);
			}
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/board/list.do", Method.GET, pagingParams, model);

		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/board/list.do", Method.GET, pagingParams, model);
		}

		return showMessageWithRedirect("게시글 삭제가 완료되었습니다.", "/board/list.do", Method.GET, pagingParams, model);
	}
	
	@GetMapping("/download.do")
	public void downloadAttachFile(@RequestParam(value = "bno", required = false) final Long bno, Model model,
			HttpServletResponse response) {

		if (bno == null)
			throw new RuntimeException("올바르지 않은 접근입니다.");

		AttachDTO fileInfo = boardService.getAttachDetail(bno);
		if (fileInfo == null) {
			throw new RuntimeException("파일 정보를 찾을 수 없습니다.");
		}

		String uploadDate = fileInfo.getRegDate().format(DateTimeFormatter.ofPattern("yyMMdd"));
		String uploadPath = Paths.get("C:", "develop", "upload", uploadDate).toString();

		String filename = fileInfo.getOriginalName();
		File file = new File(uploadPath, fileInfo.getSaveName());

		try {
			byte[] data = FileUtils.readFileToByteArray(file);
			response.setContentType("application/octet-stream");
			response.setContentLength(data.length);
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Content-Disposition",
					"attachment; fileName=\"" + URLEncoder.encode(filename, "UTF-8") + "\";");

			response.getOutputStream().write(data);
			response.getOutputStream().flush();
			response.getOutputStream().close();

		} catch (IOException e) {
			throw new RuntimeException("파일 다운로드에 실패하였습니다.");

		} catch (Exception e) {
			throw new RuntimeException("시스템에 문제가 발생하였습니다.");
		}
	}

}
