<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삭제 결과</title>
</head>
<body>
	<p>삭제에 성공하였습니다.<br/>5 초 뒤에 목록보기로 이동합니다.</p>
<script>
	//5초 후에 동작하는 타이머
	setTimeout(function(){
		location.href ="list.do"
	},5000)
	
</script>	
</body>
</html>