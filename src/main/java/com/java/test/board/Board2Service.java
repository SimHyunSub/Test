package com.java.test.board;

import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.java.test.utill.HttpUtill;

@Service
public class Board2Service implements Board2ServiceInterface {

	@Autowired
	BoardDaoInterface bdi; // BoardDao 사용할수 있도록 주입 받기 
		
	HashMap<String, Object> param; // 파라메터 사용할 전역 변수 선언 : param => type, param, sql
	ModelAndView mav;	
		
	@Override
	public ModelAndView board(String menu,String type, HttpServletRequest req) {
		
		mav = new ModelAndView();
		param = new HashMap<String, Object>();
		
		param.put("menu", menu);
		param.put("type", type);
		param.put("param", HttpUtill.getParamMap(req)); // map에 담는다. 파라미터 3개를 
			
		if("selectList".equals(type)) {
			mav.setViewName("boardList"); 	//jsp화면을 지정해준다 set 
			
		}else if("selectOne".equals(type)) {
			mav.setViewName("boardOne");
			
		}else if("insert".equals(type)) {
			mav.setViewName("redirect:selectList");
			
		}else if("updateView".equals(type)) {
			param.put("type", "selectOne");
			mav.setViewName("updateView");
			
		}else if("update".equals(type)) {
			mav.setViewName("redirect:selectList");
			
		}else if("delete".equals(type)) {
			mav.setViewName("redirect:selectList");
			
		}

		mav.addObject("data",bdi.board(param)); //{"data" : result} result는 dao에서 쿼리문 처리하고난 리턴값
		return mav;
	}

	@Override
	public ModelAndView json(String menu, String type, HttpServletRequest req) {
		
		param = new HashMap<String, Object>();
		param.put("menu", menu);
		param.put("type", type);
		if("updateView".equals(type)) {
			param.put("type", "selectOne");
		}
		
		HashMap<String, Object> map = HttpUtill.getParamMap(req);
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		if(map == null) {
			System.out.println("null값이 옴");
			result.put("status",0);
			result.put("msg","입력값이 없습니다.");
		}else {
			param.put("param",map);
			result = bdi.board(param);
			result.put("status",1);
		}
		
		return HttpUtill.makeJsonView(result);
	}
}
