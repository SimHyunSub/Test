package com.java.test.aop;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.xml.stream.events.Namespace;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class LoginAOPDao implements LoginAOPInterface {
	
	@Resource(name="sqlSession2")
	SqlSession session;
	HashMap<String, Object> result;
	
	public HashMap<String, Object> selectList(HashMap<String, Object> param) {
		
		result.put("result",session.selectList("user.select", param));
		
		return result;
	}
}
