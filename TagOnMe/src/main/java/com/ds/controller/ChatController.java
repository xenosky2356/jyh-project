package com.ds.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ds.domain.MemberVO;
import com.ds.domain.Room;
import com.ds.service.MemberService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class ChatController {
	
	@Autowired
	private MemberService service;

	List<Room> roomList = new ArrayList<Room>();
	//List<userRoom> userRoomList = new ArrayList<userRoom>();
	static int roomNumber = 0;
	//static int userRoomNumber = 0;
	
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
	
	//채팅방{writer}
		/* @RequestMapping("/chat/{writer}")
		public ModelAndView chat(@PathVariable(value="writer", required=false) String writer, HttpServletRequest request) {
			ModelAndView mv = new ModelAndView();
			HttpSession session = request.getSession(true);
			//MemberVO member = (MemberVO) session.getAttribute("member");
			MemberVO mvo = service.readBoardWriteMember(writer);
			session.getAttribute(writer);
			session.setAttribute("mvo", mvo);
			
			if(mvo != null) {
				mv.addObject("mvo", mvo);
				mv.addObject("nickName", mvo.getNickname());
				session.getAttribute("mvo");				
				mv.setViewName("/chat/chat");
			}else {
				mv.setViewName("redirect:/member/memberlogin");
			}
			return mv;
		}	*/
	
	//댓글 나오는 페이지
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
	@ResponseBody
	public Room createRoom(@RequestParam HashMap<Object, Object> params){
		String roomName = (String) params.get("roomName");
		log.info("roomName::" + roomName);
		Room room = null;
		if(roomName != null && !roomName.trim().equals("")) {
			for(int i = 0; i < roomList.size(); i++) {
				room  = roomList.get(i);
				if(room.getRoomName().equals(roomName)) {
					break;
				}else {
					room = null;
				}
			}
			if(room == null) {
				room = new Room();
				room.setRoomNumber(roomNumber++);
				room.setRoomName(roomName);
				log.info("createRoom::"+room);
				roomList.add(room);				
			}
		}
		log.info(roomList);
		return room;
	}
	
	
	 //모든 채팅방 정보가져오기
    @RequestMapping("/getAllRoom")
    public @ResponseBody
    List<Room> getAllRoom() {
        return roomList;
    }
	
	 //특정 채팅방 정보 가져오기
	@RequestMapping("/getRoom")
	public @ResponseBody Room getRoom(@RequestParam String roomName, HttpServletRequest request){
		Room room = null;
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		
		if(member != null) {	
			mv.setViewName("/chat/chatList");
			mv.addObject("list", member);
			mv.addObject("login", session.getAttribute("login"));
			for(int i = 0; i < roomList.size(); i++) {
				room = roomList.get(i);
				if(room.getRoomName().equals(roomName)) {
					break;
				}else {
					room = null;
				}
			}
		}else {
			mv.setViewName("member/memberlogin");
		}
        log.info("room::" + room);
		return room;
	}
	
	
	 //채팅방으로 이동
	@RequestMapping("/moveChatting")
	public ModelAndView chating(@RequestParam HashMap<Object, Object> params, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		int roomNumber = Integer.parseInt((String) params.get("roomNumber"));
		
		List<Room> new_list = roomList.stream().filter(o->o.getRoomNumber()==roomNumber).collect(Collectors.toList());
		if(new_list != null && new_list.size() > 0) {
			mv.setViewName("/chat/chat");
			mv.addObject("login", session.getAttribute("login"));
			mv.addObject("roomName", params.get("roomName"));
			mv.addObject("roomNumber", params.get("roomNumber"));
			mv.setViewName("/chat/chat");
		}else {
			mv.setViewName("/chat/chatList");
		}
		return mv;
	}
	
	/* ========================================================================================================= */
	/*                                            유저별 클릭시 채팅방 생성                                           */
	/* ========================================================================================================= */


}
