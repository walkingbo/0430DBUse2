<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세보기</title>
</head>
<body>
	<p>코드:${item.code}</p>
	<p>제목:${item.title}</p>
	<p>종류:${item.category}</p>
	<p>설명:${item.description}</p>
	<input type="button" value="목록보기" id="listbtn"/>
	<input type="button" value="수정하기" id="updatebtn"/>
	<input type="button" value="삭제하기" id="deletebtn"/>

	<script>
		document.getElementById("listbtn").addEventListener(
				"click",function(e){
					//자바스크립트로 요청 만들기
					location.href = 'list.do';
				})
		
		document.getElementById("updatebtn").addEventListener(
				"click",function(e){
					//자바스크립트로 요청 만들기
					location.href = 'update.do?code=${item.code}';
				})		
				
		document.getElementById("deletebtn").addEventListener(
				"click",function(e){
					
					var x = confirm("정말로 삭제하겠습니까")
					if(true){
					//자바스크립트로 요청 만들기
					location.href = 'delete.do?code=${item.code}';
					}
				})				
	</script>
	
</body>
</html>