package com.kh.web.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.web.board.model.dto.BoardDto;
import com.kh.web.board.model.service.BoardService;


@WebServlet("/notice-detail.bo")
public class NoticeBoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public NoticeBoardDetailController() {
        super();
        
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Long boardNo = Long.parseLong(request.getParameter("boardNo"));	
			
			BoardDto board = new BoardService().selectNoticeBoard(boardNo);
			
			if(board != null) {
				request.setAttribute("board",board);
				request.getRequestDispatcher("/WEB-INF/views/notice_board/notice_detail.jsp").forward(request, response);
			}else {
				request.setAttribute("message", "조회결과가 없습니다");
				response.sendRedirect(request.getContextPath()+"/fail.do");
			}
		}catch(NumberFormatException e) {
			//System.out.println("이상하다");
			request.setAttribute("message", "너 웹 url에 장난질했지 조심해");
			response.sendRedirect(request.getContextPath()+"/fail.do");
			e.printStackTrace();
		}
		
		//.out.println("여기로 왔다");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
