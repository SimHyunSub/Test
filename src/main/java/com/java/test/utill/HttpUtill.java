package com.java.test.utill;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class HttpUtill {
	
	public static HashMap<String, Object> getParamMap(HttpServletRequest req){
    	HashMap<String, Object> result = new HashMap<String, Object>();
    	
    	Enumeration<?> enums = req.getParameterNames();	//request할 때 받은 파라미터들의 이름들이니까 리스트이다. enums 넣었다.
    	
    	while(enums.hasMoreElements()) { 		// while안의 조건문 enums가 가진 요소들의 갯수까지 .
    		String paramName = enums.nextElement().toString(); 	// enums while문이 도는동안 뽑혀져 나오는것들을 string형변환 시켜서 paramnames에 넣겠다.
    		
    		if("".equals(req.getParameter(paramName))) {
    			
    			System.out.println("값이 비어 있습니당");
    			result = null;
    			break;
    		}
    		
    		result.put(paramName, req.getParameter(paramName)); // result에 hashmap타입으로 아까 넣은 paramname이름으로 아까 뽑아놓은거 넣기.
    	}
    	
    	return result;
    }
	
	public static ModelAndView makeJsonView(HashMap<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		
		JSONObject j = new JSONObject();
		j = JSONObject.fromObject(JSONSerializer.toJSON(map));
		mav.addObject("json",j);
		mav.setViewName("json");
		
		return mav; 
	}
	
	public static void makeJsonWriter( HttpServletResponse res, HashMap<String, Object> map) {
		
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=utf8");
		
		JSONObject j = new JSONObject();
		j = JSONObject.fromObject(JSONSerializer.toJSON(map));
		
		try {
			res.getWriter().write(j.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
