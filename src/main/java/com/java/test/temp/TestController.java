package com.java.test.temp;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller //어노테이션으로 컨트롤러 선언
public class TestController {
	
	@RequestMapping("/test")
	public String test() {
		return "test";
	}
	//bean객체 =컨테이너  factory 창고같은데 저장해놓았다가 생성자와 다르게 복제 개념이 아니라 가져다 쓴다 (재사용)
	
	// 만들어져있는것을 주입시킨다. 만들어져있는 클래스의 메소드를 가져다 쓸 수 있다. 생성자를 만들 필요가 없어진다.
	
	@Resource(name="testService") //autowired는 spring에서 만든 주입법이고 Resource는 javax에서 만들어놓은것. 명시적으로 아이디를 지정할 수 있다.
	TestServiceInterface ts;
	
	@RequestMapping("/test2")
	public String test2(String result) {
		
		//TestService ts = new TestService(); 스프링에서는 이렇게 사용하지 않는다. 디펜더시 인젝션 
		
		result = ts.test();
		return result;
	}
	@RequestMapping("/test3")
	public String test3(String result, Model model) {
		
		//TestService ts = new TestService(); 스프링에서는 이렇게 사용하지 않는다. 디펜더시 인젝션 
		HashMap<String, Object> map  = ts.test3();
		result = map.get("view").toString(); //
		model.addAttribute("result", map);
		
		return result;
	}
	
	@RequestMapping("/test4")
	public String test4(String result) {
		result = ts.test4();
		return result;
		
	}
	@RequestMapping("/test5")
	public String test5(Model model) {
		
		model.addAttribute("result", ts.test5());
		return "test";
	}
	
	HashMap<String, Object> param;
	
	@RequestMapping("/selectList")
	public String selectList(Model model) {
		model.addAttribute("data",ts.selectList());
		return "boardList";
	}
	@RequestMapping("/selectOne")
	public String selectOne(Model model, HttpServletRequest req) {
		param = new HashMap<String, Object>();
		param.put("boardNo", req.getParameter("boardNo"));
		model.addAttribute("data",ts.selectOne(param));
		return "boardOne";
	}
	@RequestMapping("/insert")
	public String insert(Model model, HttpServletRequest req) {
		param = new HashMap<String, Object>();
		param.put("title", req.getParameter("title"));
		param.put("content", req.getParameter("content"));
		param.put("regUser", Integer.parseInt(req.getParameter("regUser")));
		model.addAttribute("data",ts.insert(param));
		return "redirect:selectList";
	}
	@RequestMapping("/updateView")
	public String updateView(Model model, HttpServletRequest req) {
		param = new HashMap<String, Object>();
		param.put("boardNo", req.getParameter("boardNo"));
		model.addAttribute("data",ts.selectOne(param));
		return "updateView";
	}
	
	@RequestMapping("/update")
	public String update(Model model, HttpServletRequest req) {
		param = new HashMap<String, Object>();
		param.put("title", req.getParameter("title"));
		param.put("content", req.getParameter("content"));
		param.put("boardNo", req.getParameter("boardNo"));
		model.addAttribute("data",ts.update(param));
		return "redirect:selectList";
	}
	@RequestMapping("/delete")
	public String delete(Model model, HttpServletRequest req) {
		param = new HashMap<String, Object>();
		param.put("boardNo", req.getParameter("boardNo"));
		model.addAttribute("data",ts.delete(param));
		return "redirect:selectList";
	}
	
	
	
	
	
	
}
