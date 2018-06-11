package com.java.test.json;

import java.util.HashMap;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.test.utill.HttpUtill;


@Controller
public class JsonController {
	
	@RequestMapping("/json")
	public ModelAndView getJson(Model model) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("key", "json");

		return HttpUtill.makeJsonView(map); //return되는것이 foward와 같다.
	}
	
	@RequestMapping("/json2")
	public void getJson2(HttpServletResponse res) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("key", "value2");
		
		HttpUtill.makeJsonWriter(res, map); //return없이 화면에 바로.
		
	}
}
