package com.ds.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ds.adapter.GsonLocalDateTimeAdapter;
import com.ds.domain.ReplyDTO;
import com.ds.service.CommonService;
import com.ds.service.ReplyService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
public class ReplyController {

	@Autowired
	private ReplyService replyService;
	
	@Autowired
	private CommonService commonService;

	@RequestMapping(value = { "/replys", "/replys/{rno}" }, method = { RequestMethod.POST, RequestMethod.PATCH })
	public JsonObject registerReply(@PathVariable(value = "rno", required = false) Long rno, @RequestBody final ReplyDTO params) {
		log.info("rno>>>"+rno);
		JsonObject jsonObj = new JsonObject();

		try {
			boolean isRegistered = replyService.registerReply(params);
			jsonObj.addProperty("result", isRegistered);

		} catch (DataAccessException e) {
			jsonObj.addProperty("message", "데이터베이스 처리 과정에 문제가 발생하였습니다.");

		} catch (Exception e) {
			jsonObj.addProperty("message", "시스템에 문제가 발생하였습니다.");
		}

		return jsonObj;
	}

	@GetMapping(value = "/replys/{bno}")
	public JsonObject getReplyList(@PathVariable("bno") Long bno, @ModelAttribute("params") ReplyDTO params, Model model, HttpServletRequest request) {

		JsonObject jsonObj = new JsonObject();

		model = commonService.loginUser(model, request);
		
		List<ReplyDTO> replyList = replyService.getReplyList(params);
		if (CollectionUtils.isEmpty(replyList) == false) {
			Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();
			JsonArray jsonArr = gson.toJsonTree(replyList).getAsJsonArray();
			jsonObj.add("replyList", jsonArr);
		}

		return jsonObj;
	}

	@DeleteMapping(value = "/replys/{rno}")
	public JsonObject deleteReply(@PathVariable("rno") final Long rno) {

		JsonObject jsonObj = new JsonObject();

		try {
			boolean isDeleted = replyService.deleteReply(rno);
			jsonObj.addProperty("result", isDeleted);

		} catch (DataAccessException e) {
			jsonObj.addProperty("message", "데이터베이스 처리 과정에 문제가 발생하였습니다.");

		} catch (Exception e) {
			jsonObj.addProperty("message", "시스템에 문제가 발생하였습니다.");
		}

		return jsonObj;
	}

}
