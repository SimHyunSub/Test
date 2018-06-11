<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/test/webjars/jquery/3.3.1/dist/jquery.min.js"></script>
<script type="text/javascript" src="/test/resources/js/common.js"></script>
<title>json boardOne</title>

	<script>
	$(document).ready(function(){
		var boardNo = '${QueryString.boardNo}';
		var data = {"boardNo" : boardNo};
		
		console.log(data);
		$.ajax({
			 url :  "/test/json/board/selectOne",
			 data : data  /* json 형식 */ //data가 키값이다. 
 		})
		.done(function(data) {
			var d = JSON.parse(data)
			var dd = d.result;
			console.log(dd);
			$("#title").text(dd.title);
			$("#content").text(dd.content);
		});
		
		$("#delete").on("click",function(){
			console.log("deletet선택");
			
			$.ajax({
				 url:"/test/json/board/delete",
				 data:data  
	 		}).done(function(data) {
	 			i_d(data);
			});
		});
	});
	</script>
</head>

<body>
	<h1 id="title"></h1>
	<p id="content"></p>
	<button type="botton" id="delete">삭제</button>
	<a href="/test/board/update?boardNo=${QueryString.boardNo}">수정</a>
</body>
</html>