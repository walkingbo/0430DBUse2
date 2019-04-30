<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 반복문이나 조건문을 사용할 경우에 사용할 태그 라이브러리 설정 -->
<!-- c로 시작하는 태그는 "http://java.sun.com/jsp/jstl/core"로 해석 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체 데이터 가져오기</title>
</head>
<body>
	<!-- 여러 개의 데이터는 테이블로 출력하는 것이 일반적 -->
	<h3 align="center">아이템</h3>
	<table border="1" align="center">
		<thead>
			<tr>
				<th>코드</th>
				<th>제목</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${list}">
				<tr>
					<td>${item.code}</td>
					<td>${item.title}</td>
				</tr>
			</c:forEach>
		</tbody>		
	
	</table>

</body>
</html>