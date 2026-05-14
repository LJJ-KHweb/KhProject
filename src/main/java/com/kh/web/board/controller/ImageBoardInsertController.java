package com.kh.web.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.kh.web.board.model.dto.AttachmentDto;
import com.kh.web.board.model.dto.BoardDto;
import com.kh.web.board.model.service.BoardService;
import com.kh.web.common.MyRenamePolicy;
import com.kh.web.member.model.dto.MemberDto;
import com.oreilly.servlet.MultipartRequest;


@WebServlet("/insert.im")
public class ImageBoardInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ImageBoardInsertController() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(ServletFileUpload.isMultipartContent(request)) {
			
			int maxSize = 100000000;
			String savePath = request.getServletContext().getRealPath("/resources/image_upfiles");
			
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyRenamePolicy());
			
			
			//파일 업로드 끝!
			
			String boardTitle = multiRequest.getParameter("title");
			String boardContent = multiRequest.getParameter("content");
			
			HttpSession session = request.getSession();
			MemberDto member = (MemberDto)session.getAttribute("userInfo");  //널체크는 생략 
			Long userNo = member.getUserNo();
			//디비에서 테이블을 만들때 dto새로 만들기 귀찮아서 boardDto에 담을수 있게끔 테이블 구성 
			BoardDto board = new BoardDto();
			board.setBoardTitle(boardTitle);
			board.setBoardContent(boardContent);
			board.setUserNo(userNo);
			
			//Attachment !
			// => 사진 게시글 작성 form required
			// 게시글 당 최소 한개의 첨부파일이 존재
			/*
			AttachmentDto at1 = null;
			AttachmentDto at2 = null;
			AttachmentDto at3 = null;
			AttachmentDto at4 = null;
			*/
			
			/*
			if(multiRequest.getOriginalFileName("file1")!= null) {
				at1= new AttachmentDto();
			}
			if(multiRequest.getOriginalFileName("file2")!= null) {
				at2= new AttachmentDto();
			}
			if(multiRequest.getOriginalFileName("file3")!= null) {
				at3= new AttachmentDto();
			}
			if(multiRequest.getOriginalFileName("file4")!= null) {
				at4= new AttachmentDto();
			}
			*/
			List<AttachmentDto> files = new ArrayList();
			
			for(int i = 0; i < 5; i ++) {
				String key="file" + i;
				if(multiRequest.getOriginalFileName(key)!= null) {
					// 파일이 존재한다
					AttachmentDto at = new AttachmentDto();
					at.setOriginName(multiRequest.getOriginalFileName(key));
					at.setChangeName(multiRequest.getFilesystemName(key));
					at.setFilePath("resources/image_upfiles");
					at.setBoardType("I");
					/*
					if( i == 1) {
						at.setFileLevel(1);
					}else {
						at.setFileLevel(2);
					}
					*/
					at.setFileLevel(i == 1 ? 1 : 2);
					
					files.add(at);
					
				}
			}
			
			int result = new BoardService().insertImage(board,files);
			
			if(result > 0) {
				session.setAttribute("alretMsg", "게시글 등록 성공");
				response.sendRedirect(request.getContextPath()+"/boards.im");
			}else {
				session.setAttribute("message", " 뭔가 실패함");
				response.sendRedirect(request.getContextPath()+"/fail.do");
			}
			
			
			
			
			
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
