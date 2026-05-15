<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>실패용 페이지</title>
<style>
h1 {
	color: red;
	font-size: 64px;
	text-align: center;
	height: 600px;
	line-height: 600px;
}
</style>
</head>
<body>

	<jsp:include page="../include/header.jsp" />

	<h1>아주 매우 중요 ★★★★★★★★★★★★★★★★</h1>

	<h2>AJAX(진짜 중요함)</h2>

	<pre>
		AJAX == Asynchronous JavaSrcipt And XML
		"페이지를 새로고침하지 않고 서버와 데이터르 주고 받을 수 있는 기술"
		"비동기 통신 기술"	
		
		우리가 앞에서 개발 했던 방식은 동기 방식
		
		동기 방식:
		1. 사용자가 요청을 보냄
		2. 서버가 요청을 받아서 전체 HTML데이터를 응답
		3. 브라우저는 응답받은 HTML데이터를 처음부터 끝까지 핸더링 -> 전체페이지를 다시 로딩 -> 화면이 한 번 깜빡 (SSR)
		
		비동기방식:
		1. 사용자가 요청을 보냄
		2. JavaScript 기술을 이용해서 데이터만 서버로 전공
		3. 서버는 JSON / XML데이터만 응답
		4. JavaScript를 이용해서 필요한 부분만 갱신 -> 부르럽고, 빠름 (CSR)
	</pre>

	<hr>

	<h3>AJAX 장단점</h3>
	<pre>
		장점 : 사용자 경험(U.X) 향상
			: 서버의 부하가 감소
			: 네트워크 트래픽 절약
	
		단점	: SEO(검색엔진)취약
			: 브라우저 히스토리 관리 복잡
			: JavaScript 의존성
			: 보안 취약점 증가(XSS)
	
		SPA(Single page Application) 전성시대
		React, Vue, Anglar => JavaSrcipt / AJAX 기반 라이브러리 및 프레임워크
	</pre>
	<pre>
	<h2>JSON</h2>
		김예찬
		kyc****
		태준이형 600화 축하하고 수고했으면 개추 ㅋㅋㅋ
		<reply>
			<name>김예찬</name>
			<maskedId>kyc****</maskedId>
			<content>태준이형 600화 축하하고 수고했으면 개추 ㅋㅋㅋ</content>		
		</reply>
		(구시대 방식)
		"reply" : {
			"nickName" : "김예찬",
			"id" : "kyc0****"
			"content" : "태준이형 600화 축하하고 수고했으면 개추 ㅋㅋㅋ"
		}
		(표춘 방식)
		
		JSON == JavaScipt Object Notation
		
		사람이 읽기 쉽고, 기계가 파싱하기 쉬운 데이터 교환형식 텍스트기반이라 아주 가볍다
		* 진짜 자바스크립트 객체 X / 자바스크립트 객체 모양으로 문자열을 만든거
		
		문법이 아주 엄격함!
		
		자바스크립트 객체
		{
			name : "김예찬",				// 키에 따음표 안적어도됨
			id : 'kyc0****'				// 작음 따음표 사용가능
			content : '태준이형 개추 ㅋㅋㅋ'	// 마지막 속성에 컴마 가능
		}
		
		JSON형식
		{
			"name" : "김예찬",
			"id" : "kyc0****",
			"content" : "태준이형 개추 ㅋㅋㅋ"
		}
		
		장점 : 가독성이 좋음(XML과 비교ㅛ해서 훨씬 읽기 편함)
			: 데이터 자체가 가볍다 (XML대피해서 30% 더 가벼움)
			: 파싱 속도 빠름
			: 언어 독립적
			: JavaScript 네이티브 지원
			
		단점 : 주석 불가
			: 날짜 타입 없음(문자열로 처리)
			: 함수 불가능
			
		웹 개발의 표준!! 데이터 형식
		REST API의 기본 포멧!!
		설정파일 XML -> JSON(설정파일은 YAML이 인기 훨씬 많음)
	</pre>	
		
	<pre>
		우리는 AJAX를 이용해서 댓글 기능을 구현해볼 예정
		
		AJAX 사용방법
		1. XMLHttpRequest 객체를 생성해서 사용하는 방법			-
		2. jQuery를 사용해서 ajax메소드를 호출하는 방법			+
		3. fetch API 를 활용해서 fetch 호출하는 방법			+
		4. React 배울 때 Axios 라이브러리 설치해서 사용하는 방법	+
	
	</pre>
	
	<h4>jQuery를 이용한 ajax활용</h4>
	
	<h5>요청을 보내고 응답받아오기</h5>

	<div class="form-group">
		<div class="form-control">
		<button class="btn btn-sm " onclick="fn1();">요청보내기!</button>
		</div>
	</div>
	
	응답 : <label id="output1">아직 응답 없음</label>
	
	<script>
		function fn1(){
			// 동기식 요청
			//location.href ="http://localhost:8088/kh/ajax1.do"
			
			// 비동기식 요청
			$.ajax({
				url : 'http://localhost:8088/kh/ajax1.do',
				type : 'get',
				success : result => {
					console.log(result);
					document.querySelector('#output1').innerHTML = result;
				},
				error : result => {
					console.log('ajax요청 실패!');
					document.querySelector('#output1').innerHTML = '통신에 실패했습니다';
				},
				complete : () => {
					console.log('성공실패 무조건');
				}
			});
		}
	</script>
	
	<hr>
	
	<h3>게시글 번호를 보내서 게시글 정보를 받아오기</h3>	
	
	게시글 번호 : <input type="number" id="boardNo" /> <br>
	
	<button onclick="inforBoard();">게시글 주세요</button>
	
	<hr>
	
	게시글 제목 : <label id="title">현재 응답없음</label> <br>
	게시글 내용 : <label id="content">현재 응답없음</label> <br>
	
	<script>
		function inforBoard(){
			$.ajax({
				url : 'http://localhost:8088/kh/ajax2.do',
				type : 'get',
				data :	{
					boardNo : document.querySelector('#boardNo').value
				},
				success : result => {
					console.log(result);
					document.querySelector('#title').innerHTML = result.boardTitle;
					document.querySelector('#content').innerHTML = result.boardContent;
				},
				error : e => {
					console.log(e);
				}
			});
		
		}
	</script>
	
	<hr>
	
	<h3>사진게시글 목록 조회</h3>


	
	<div id="result" style="width: 80% height : 300px margin: auto;">
	
	</div>
	
	<button class="btn btn-lg btn-danger" onclick="img();">사진게시글 조회하기</button>

	<script>
		function img(){
			$.ajax({
				url : 'http://localhost:8088/kh/ajax3.do',
				type : 'get',
				success : result => {
					console.log(result);
					
					const el = result.map(e => 
						`
							<div>
								<label>글 번혼 : \${e.boardNo}</label>
								<div><img src="\${e.src}" width="120" height="70"/></div>
								<p>제목 : \${e.boardTitle}</p>
							</div>
						`
					).join('');
					
					document.querySelector('#result').innerHTML = el;
				}
			});
		
		}
	</script>

	<jsp:include page="../include/footer.jsp" />
	
	
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

</body>
</html>