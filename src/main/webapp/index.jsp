<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 
		WEB 환경에서의 CRUD
		
		* 회원 서비스
		로그인/로그아웃, 회원가입, 내정보조회, 내정보변경, 비밀번호변경, 회원탈퇴

		* 일반 게시판 서비스
		게시글목록조회(페이징처리), 상세조회, 게시글작성(첨부파일 1개 업로드), 게시글 수정, 게시글삭제		
		댓글서비스, 게시글검색
		
		* 사진게시판 서비스
		사진게시글목록조회(썸네일), 상세조회, 게시글작성(다중파일업로드)
		
		공지사항 작성(첨부파일 X, 사용자 검증), 상세조회, 삭제 구현
	
	 -->
	<jsp:include page="WEB-INF/views/include/header.jsp" />
	<jsp:include page="WEB-INF/views/include/main.jsp" />
	<jsp:include page="WEB-INF/views/include/footer.jsp" />
	<!-- 
		Java, Oracle, JDBC, MyBatis
									+ Network(Web) -> 클라이언트, 서버, DB
		HTML, CSS, JS
		문제가 발생하면 캡쳐를해서 정리를 하자
		tailwindcss -> bootstrap같은 사이트
	 -->
	 <!--  예고편 -->
	 <a href="insert.notice">노티스 인서트</a>
	 <a href="select notice">노티스 셀렉트</a>
</body>
</html>