package com.java.test;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;

import com.java.test.temp.TestService;

public class Main {
	/***********************************************************************************8
	 * Ioc (inversion of control) 컨테이너  
	 * 컨테이너 
	 */
	
	public static void main(String[] args) {
		
		String config = "file:src/main/webapp/WEB-INF/spring/root-context.xml";
		
		AbstractApplicationContext ac = new GenericXmlApplicationContext(config); // 컨테이너를 생성한다. 톰켓서버를 켜면 발생하는것과 똑같다.
		SqlSessionTemplate session = ac.getBean("session",SqlSessionTemplate.class);
		
		List<HashMap<String, Object>>list = session.selectList("test.select");
		System.out.println(list.size());
		
		for(int i = 0; i<list.size(); i++ ) {
			System.out.println(list.get(i));  // null값이면 필드 자체를 안가져온다. 그래서 있는것만 나옴. 
		}
		
//		TestService ts = ac.getBean("testService",TestService.class); //컨테이너에 있는 bean을 가져와서 주입한다. @autowired와 같은 역할. 
		//String result = ts.test();
//		HashMap<String, Object> result = ts.test3();
		
		TestService ts = ac.getBean("testService",TestService.class);
		
	}

}
