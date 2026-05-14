package com.kh.web.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.web.board.model.dto.BoardDto;
import com.kh.web.board.model.service.BoardService;
import com.kh.web.common.model.dto.PageInfo;

@WebServlet("/notice.do")
public class NoticeBoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public NoticeBoardListController() {
        super();
     
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//-페이징 처리
		// 필요한 변수들
		int listCount; // 현재 게시판의 총 게시글 개수
		// => WEB_BOARD 테이블에서 COUNT(*) (SATUS='N') 조회
		int currentPage; // 현재 사용자가 요청한 페이지 
		// => request.getParameter("page");로 뽑아서 씀 
		int pageLimit = 3; // 페이지 하단에 버튼을 몇개 보여줄 것인지 => 5개
		int boardLimit = 3; // 한 페이지당 board를 몇개 보여줄 것인지 => 3개
		
		int maxPage; // 가장 마지막 페이지(총 페이지의 개수)
		int startPage; // 페이지 하단에 보여질 페이징바의 시작값
		int endPage; // 페이지 하단에 보여질 페이징바의 끝 값
		
		listCount = new BoardService().selectNoticeBoardCount();
		
		currentPage = Integer.parseInt(request.getParameter("page"));

		maxPage = (int)Math.ceil((double)listCount / boardLimit);
	
		startPage = (currentPage -1 ) / pageLimit * pageLimit +1;

		endPage = startPage + pageLimit -1;
		if(endPage > maxPage) {
			endPage = maxPage;
		}
	
		int offset = (currentPage -1) * boardLimit;
		
		PageInfo noticePi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, startPage, endPage, maxPage, offset);
		
		List<BoardDto> noticeBoards = new BoardService().selectNoticeBoardList(noticePi);
		
		request.setAttribute("noticePi", noticePi);
		request.setAttribute("noticeBoards", noticeBoards);
		
		
		//System.out.println(listCount);
		
		
		
		request.getRequestDispatcher("/WEB-INF/views/notice_board/notice_board.jsp").forward(request, response);
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
