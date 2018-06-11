package com.java.test.aop;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.HashMap;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect 
public class TestAOP {
	
	@Autowired
	TestAOPInterface tai;
	
	@Pointcut("within(com.java.test..*)")
	public void pointcut(){}
	
	@Pointcut("bean(*Dao)")
	public void pointcutDAO(){}
	
	@Before("pointcutDAO()")
	
	public void before(JoinPoint jP) {
		String name = jP.getSignature().toShortString();	//호출되는 메서드의 대한 정보를 구함 
		System.out.println(name + " 시작 전");	//return을 기준으로 return이 되는시점에 brfore가 찍힌다.
		
		Object[] obj = jP.getArgs();	//파라미터의 목록을 구한다.
		System.out.println(name + "매개변수 :" + obj.length);
		
		for(int i=0; i < obj.length; i++) {
			System.out.println(obj[i]);
			if(obj[i] instanceof HashMap ) { //오른쪽 변의 타입을 비교하여 가져온다. 
				System.out.println("HashMap 타입: ");
				HashMap<String, Object> map =(HashMap<String, Object>) obj[i];
				for(String key : map.keySet()) {
					System.out.println(key + " : " + map.get(key));
				}
			}
		}
	}
	@After("pointcutDAO()")
	public void After(JoinPoint jP) {
		String name = jP.getSignature().toShortString();
		System.out.println(name + " 종료 후");
	}
	
	@Around("pointcut()") //실행 범위 지정 
	public Object callAOP(ProceedingJoinPoint joinPoint) throws Throwable{
		
		String name = joinPoint.getSignature().toShortString();
		System.out.println(name + "시작");
		/********************************************************************/
		
		if(name.contains("Controller")) {
			
			HashMap<String, Object> param = new HashMap<String, Object>();
			
			param.put("methodNm", joinPoint.getSignature().getName());
			
			Object[] args = joinPoint.getArgs();
			
			if(args[0] instanceof String) {
				param.put("menu",args[0]);
			}
			
			if(args[1] instanceof String) {
				param.put("type",args[1]);
			}
			
			tai.insert(param);
		}
		
		/********************************************************************/
		long st = System.currentTimeMillis();
		
		try {
			Object obj = joinPoint.proceed();
			return obj;
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			long et = System.currentTimeMillis();
			System.out.println(name + "종료 : " + ( et - st));
		}
		
		return null;
	}
	
}
