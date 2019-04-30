<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이템 삽입</title>
<!-- jquery 링크 설정 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script>
window.onload = function(){
	//중복검사 통과 여부를 저장할 변수
	var codecheck = false
	//code에서 포커스가 떠날 때 이벤트 처리 
	document.getElementById("code").addEventListener(
		'focusout', function(e){
			//code에 입력한 내용이 없으면 중복검사 안함
			if(document.getElementById("code").value.length == 0){
				return 
			}
			//codecheck.do 라는 요청을 전송
			//파라미터는 code라는 이름으로 code에 입력된 값을 전송
			//전송받을 데이터 타입은 json
			//데이터를 가져오면 가져온 데이터를 출력
			$.ajax({
				url:'codecheck.do',
				data:{'code':document.getElementById("code").value},
				dataType:'json',
				success:function(d){
					//==는 값만 같으면 true === 자료형까지 일치
					if(d.result === 'success'){
						document.getElementById("codemsg").innerHTML = 
							'사용 가능한 코드 번호입니다.'
						document.getElementById("codemsg").style.color='green'
						codecheck = true
					}else{
						document.getElementById("codemsg").innerHTML = 
							'사용 불가능한 코드 번호입니다.'
						document.getElementById("codemsg").style.color='red'	
						codecheck = false
					}
				}
			});
	})
	//폼에서 submit 버튼을 눌렀을 때
	//기본이벤트를 제거 : 매개변수.preventDefault()
	document.getElementById("myform").addEventListener("submit",function(e){
		//코드 중복 체크를 하지 않은 경우 중복 검사를 하도록 설정
		if(codecheck ==false){
			document.getElementById("codemsg").innerHTML = 
				'중복체크를 해야 합니다.'
				e.preventDefault()
		}
		
	});
	
	
	
	
}

</script>



</head>
<body>
	<!-- form의 속성
	id - javascript에서 이벤트 처리를 하기 위해서 설정
	action - 폼의 데이터를 전송할 서버 URL : 생략하면 현재 URL
	method - 서버에게 요청할 때 요청방식 : get 또는 post - 생략하면 get
	enctype - 파일을 업로드 할  multipart/form-data 로 설정
	post를 설정해야하는 경 file, textarea, password	가 있는 경우 -->
	<form action="create.do" method="post" id="myform">
	
		<label for="code">코드</label>
		<input type="number" name="code" id="code" size="10" required="required"/><span id="codemsg"></span><br/>
		
		<label for="title">제목</label>
		<input type="text" name="title" id="title" size="20" required="required"/><br/>
		
		<label for="category">분류</label>
		<input type="text" name="category" id="category" size="20" required="required"/><br/>
		
		<label for="description">설명</label>
		<textarea rows="10" cols="20" name="description" id="description"></textarea><br/>
		
		<input type="submit" value="데이터 삽입"/><br/>
			
	</form>
</body>
</html>