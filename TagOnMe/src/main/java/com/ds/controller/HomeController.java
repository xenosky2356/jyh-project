package com.ds.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ds.domain.HomeCountVO;
import com.ds.domain.HomeLinksVO;
import com.ds.domain.HomeTagList20VO;
import com.ds.domain.HomeVO;
import com.ds.service.HomeService;

@Controller
public class HomeController {	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private HomeService homeService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model,  HttpServletRequest request) throws Exception {
//		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		  List<HomeVO> list = homeService.getList();
			/* logger.info(">>>>list"+list); */
		  
		  List<HomeLinksVO> linksList = homeService.getLinksList();
			/* logger.info(">>>>linksList"+linksList); */
		  
		  model.addAttribute("tagMapNodes", list );
		  model.addAttribute("tagMapLinks", linksList );
		  
		  int userCount = homeService.getUserCount();
		  int userTagCount = homeService.getUserTagCount();
		  int boardTagCount = homeService.getBoardTagCount();
		  List<HomeTagList20VO> tagList20 = homeService.getTagList20();
		  
		  model.addAttribute("userCount", userCount );
		  model.addAttribute("userTagCount", userTagCount );
		  model.addAttribute("boardTagCount", boardTagCount );
		  model.addAttribute("tagList20", tagList20 );
			/* logger.info(">>>>linksList"+userCount); */
		  logger.info(">>>>tagList20"+tagList20); 
		  
		  HttpSession session = request.getSession(false);
		  
		  if(session != null) {
				/* logger.info(">>>>session"+"로그인 상태"); */
			  model.addAttribute("userLogin", true );
		  } else {
				/* logger.info(">>>>session"+"로그아웃 상태"); */
			  model.addAttribute("userLogin", false );
		  }
			/* logger.info(">>>>session"+session); */
		  
		return "index";
	}
	
}

