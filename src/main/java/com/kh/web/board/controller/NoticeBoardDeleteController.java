package com.kh.web.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.web.board.model.dto.BoardDto;
import com.kh.web.board.model.service.BoardService;
import com.kh.web.member.model.dto.MemberDto;

/**
 * Servlet implementation class NoticeBoardDeleteController
 */
@WebServlet("/delete-notice.bo")
public class NoticeBoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public NoticeBoardDeleteController() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			Long boardNo = Long.parseLong(request.getParameter("boardNo"));
			MemberDto member = (MemberDto)request.getSession().getAttribute("userInfo");
			if(member==null) {
				System.out.println("멤버가 널임");
			}
			Long userNo = member.getUserNo();
			String userRole = member.getUserRole();
			BoardDto board = new BoardDto();
			board.setUserNo(userNo);
			board.setBoardNo(boardNo);
			board.setUserRole(userRole);
			
			int result = new BoardService().deleteNoticeBoard(board);
			if(result >0) {
				request.getSession().setAttribute("alertMsg", "삭제 성공");
				response.sendRedirect(request.getContextPath()+"/notice.do?page=1");
				System.out.println("test2222");
			}else {
				System.out.println("test");
				response.sendRedirect(request.getContextPath()+"notice-detail.bo?boardno="+boardNo);
			}
			
		}catch(NumberFormatException e) {
			request.setAttribute("message", "url문제");
			System.out.println("넘버포멧");
			response.sendRedirect(request.getContextPath()+"/fail.do");
		}catch(NullPointerException e) {
			request.setAttribute("message", "로그인안된상태임");
			System.out.println("널포인트");
			response.sendRedirect(request.getContextPath()+"/fail.do");
		}
		
		
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
