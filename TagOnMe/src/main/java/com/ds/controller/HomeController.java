package com.ds.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ds.domain.HomeLinksVO;
import com.ds.domain.HomeVO;
import com.ds.service.HomeService;

@Controller
public class HomeController {	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private HomeService homeService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws Exception {
//		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		  List<HomeVO> list = homeService.getList();
		  logger.info(">>>>list"+list);
		  
		  List<HomeLinksVO> linksList = homeService.getLinksList();
		  logger.info(">>>>linksList"+linksList);
		  
		  model.addAttribute("tagMapNodes", list );
		  model.addAttribute("tagMapLinks", linksList );
		  
		  model.addAttribute("memCount", linksList );
		  model.addAttribute("memCount", linksList );
		 
		return "index";
	}
	
}

