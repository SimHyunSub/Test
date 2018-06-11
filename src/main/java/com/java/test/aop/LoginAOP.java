package com.java.test.aop;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import com.java.test.utill.HttpUtill;

@Aspect
public class LoginAOP {
	
	@Autowired
	LoginAOPInterface lai;
	
	@Around("within(com.java.test.user.UserController)")
	public Object loginCheck(ProceedingJoinPoint jP) throws Throwable {
		try {
			String name = jP.getSignature().getName();
			System.out.println(name + "호출됨");
			
			/***************************************************************************************/
			
			Object[] args = jP.getArgs();
			for(int i = 0; i < args.length; i++) {
				if(args[i] instanceof HttpServletRequest) {
					HttpServletRequest req = (HttpServletRequest) args[i];
					HashMap<String, Object> params = HttpUtill.getParamMap(req);
					
					String id = params.get("id").toString();
					String pwd = params.get("pwd").toString();
					
					boolean check = true;
					
					if(!"admin".equals(id)) {
						check = false;
					}
					
					if(!"1234".equals(pwd)) {
						check = false;
					}
					
					if(check) {
						return jP.proceed(); //userController 의 login메소드를 뜻한다. 
					}else {
						HashMap<String, Object> map = new HashMap<String, Object>();
						map.put("status", 0);
						map.put("msg", "누구세요? 뮟췬떽기아니야");
						return HttpUtill.makeJsonView(map);
					}
				}
			}
			
			/***************************************************************************************/
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}
