package com.kh.web.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.web.board.model.dto.BoardDto;
import com.kh.web.board.model.service.BoardService;
import com.kh.web.member.model.dto.MemberDto;


@WebServlet("/delete.bo")
public class BoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BoardDeleteController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Long boardNo = Long.parseLong(request.getParameter("boardNo"));
			HttpSession session = request.getSession();
			MemberDto member = (MemberDto)session.getAttribute("userInfo");
			if(member == null) {
				session.setAttribute("message", "비 정상적인 접근입니다.");
				response.sendRedirect(request.getContextPath()+"/fail.do");
				return;
			}
			Long userNo = member.getUserNo();
			
			BoardDto board = new BoardDto();
			board.setUserNo(userNo);
			board.setBoardNo(boardNo);
			member.setUserRole(member.getUserRole());
			
			int result = new BoardService().deleteBoard(board);
			
			if(result > 0) {
				session.setAttribute("alertMsg", "삭제에 성공하셨습니다.");
				response.sendRedirect(request.getContextPath()+"/boards.do?page=1");
			}else {
				
				response.sendRedirect(request.getContextPath()+"detail.bo?boardNo="+ boardNo);
			}
			
			
			
			
			
			
			
		}catch(NumberFormatException e){
			request.getSession().setAttribute("message", "넘버 포멧오류 발생");
			//System.out.println(request.getContextPath());
			response.sendRedirect(request.getContextPath()+"/fail.do");
			return;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
