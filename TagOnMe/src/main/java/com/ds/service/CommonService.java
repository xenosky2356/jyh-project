package com.ds.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.ds.domain.MemberVO;

@Service
public class CommonService {
	public Model loginUser(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		MemberVO member = (MemberVO) session.getAttribute("member");
		if(member ==null) {
			model.addAttribute("member", "null");
		} else {
			model.addAttribute("member", member);
		}
		return model;
	}
	
	public MemberVO getLoginUser(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		MemberVO member = (MemberVO) session.getAttribute("member");
		return member;
	}
}
