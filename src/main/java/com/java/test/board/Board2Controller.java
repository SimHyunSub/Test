package com.java.test.board;

import java.util.Enumeration;
import java.util.HashMap;

import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.test.utill.HttpUtill;

@Controller
public class Board2Controller {
	

	@Autowired
	Board2ServiceInterface bsi; // BoardService 사용 할 수 있도록 주입 받기.
	
	@RequestMapping("/view/{menu}/{type}")
	public ModelAndView board(@PathVariable("menu") String menu,
							  @PathVariable("type") String type,
							  HttpServletRequest req) {
		
		return bsi.board(menu, type, req); // return type에 모델와 뷰가 올 수 있다. 
	}
	
	@RequestMapping("/json/{menu}/{type}")
	public ModelAndView json(@PathVariable("menu") String menu,
							  @PathVariable("type") String type,
							  HttpServletRequest req) {
		
		return bsi.json(menu, type, req);
	}
	
	@RequestMapping("/board/{type}")
	public String json(@PathVariable("type") String type, HttpServletRequest req, Model model) {
		String view = "";
		model.addAttribute("QueryString",HttpUtill.getParamMap(req));
		if("selectOne".equals(type)) {
			view = "json/boardOne";
			
		}else if("update".equals(type)) {
			view = "json/boardUpdate";
		}
		return view;
	}
	
}
