package com.ds.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ds.domain.MemberVO;
import com.ds.domain.Room;
import com.ds.domain.userRoom;
import com.ds.service.MemberService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class ChatController {
	
	@Autowired
	private MemberService service;

	List<Room> roomList = new ArrayList<Room>();
	List<userRoom> userRoomList = new ArrayList<userRoom>();
	//HashSet<userRoom> setRoomList = new HashSet<userRoom>(userRoomList);
	static int roomNumber = 0;
	static int userRoomNumber = 0;
	
	//채팅방
	@RequestMapping("/chat")
	public ModelAndView chat(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		
		if(member == null) {
			mv.setViewName("member/memberlogin");
		}else {
			mv.setViewName("/chat/chat");
			mv.addObject("nickName", member.getNickname());
			mv.addObject("list", member);
			mv.addObject("login", session.getAttribute("login"));
		}
		return mv;
	}
	
	// 채팅 목록
	@RequestMapping("/room")
	public ModelAndView room(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		
		if(member == null) {
			mv.setViewName("member/memberlogin");
		}else {
			mv.setViewName("/chat/room");
			//session.getAttribute("member");
			mv.addObject("userid", member.getUserid());
			mv.addObject("list", member);
			mv.addObject("login", session.getAttribute("login"));
		}
		return mv;
	}
	
	//방 생성
	@RequestMapping("/createRoom")
	public @ResponseBody List<Room> createRoom(@RequestParam HashMap<Object, Object> params){
		String roomName = (String) params.get("roomName");
		if(roomName != null && !roomName.trim().equals("")) {
			Room room = new Room();
			room.setRoomNumber(++roomNumber);
			room.setRoomName(roomName);
			roomList.add(room);
		}
		return roomList;
	}
	
	
	 //채팅 목록 불러오기
	@RequestMapping("/getRoom")
	public @ResponseBody List<Room> getRoom(@RequestParam HashMap<Object, Object> params, HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		
		if(member == null) {
			mv.setViewName("member/memberlogin");
		}else {
			mv.setViewName("/chat/room");
			mv.addObject("list", member);
			mv.addObject("login", session.getAttribute("login"));
		}
		return roomList;
	}
	
	
	 //채팅방 이동
	@RequestMapping("/moveChating")
	public ModelAndView chating(@RequestParam HashMap<Object, Object> params, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		int roomNumber = Integer.parseInt((String) params.get("roomNumber"));
		
		List<Room> new_list = roomList.stream().filter(o->o.getRoomNumber()==roomNumber).collect(Collectors.toList());
		if(new_list != null && new_list.size() > 0) {
			mv.addObject("roomName", params.get("roomName"));
			mv.addObject("roomNumber", params.get("roomNumber"));
			mv.setViewName("/chat/chat");
		}else {
			mv.setViewName("/chat/room");
		}
		return mv;
	}
	
	/* ========================================================================================================= */
	/*                                            유저별 클릭시 채팅방 생성                                           */
	/* ========================================================================================================= */

	
	//유저 나오는 뷰
	@RequestMapping("/chatList")
	public ModelAndView chatList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		
		if(member == null) {
			mv.setViewName("member/memberlogin");
		}else {
			mv.setViewName("/chat/chatList");
			mv.addObject("userid", member.getUserid());
			mv.addObject("me", member.getUserid());
			mv.addObject("list", member);
			mv.addObject("login", session.getAttribute("login"));
		}
		return mv;
	}
	
	//채팅방 생성
	@RequestMapping("/createDmRoom")
	public @ResponseBody List<userRoom> createDmRoom(@RequestParam HashMap<Object, Object> params, HttpServletRequest request){
		HashSet<userRoom> setRoomList = new HashSet<userRoom>(userRoomList);

		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		//String userNickname = (String) params.get("userNickname");
		mv.addObject("userId", member.getUserid());
		
		if(member != null) {
			//if(상대방과 나의 아이디 정보가 userRoomList에 없다면){방 생성}else(있다면){존재하는 방으로 이동}
			userRoom userRoom = new userRoom();
			userRoom.setUserRoomNumber(++userRoomNumber);
			userRoom.setNickName(member.getNickname());
			userRoom.setUserId(member.getUserid());
			userRoom.setMe(member.getUserid());
			//room.setRoomName(username);
			userRoomList.add(userRoom);
		}
		return userRoomList;
	}
	
	
	//채팅방 생성 및 이동
	@RequestMapping("/moveDmChating")
	public ModelAndView chatingDm(@RequestParam HashMap<Object, Object> params, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		//int userRoomNumber = Integer.parseInt((String) params.get("userRoomNumber"));
		
		if(member != null) {
			userRoom userRoom = new userRoom();
			userRoom.setUserRoomNumber(++userRoomNumber);
			userRoom.setNickName(member.getNickname());
			userRoom.setUserId(member.getUserid());
			userRoom.setMe(member.getUserid());
			userRoomList.add(userRoom);
			
			mv.addObject("list", member);
			mv.addObject("nickName", params.get("nickName"));
			mv.addObject("userId", params.get("userId"));
			mv.addObject("me", params.get("me"));
			mv.addObject("userRoomNumber", params.get("userRoomNumber"));
			mv.setViewName("/chat/chat");
		}else {
			mv.setViewName("/chat/room");
		}
		return mv;
	}

}
