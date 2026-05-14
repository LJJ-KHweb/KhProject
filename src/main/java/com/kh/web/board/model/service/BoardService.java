package com.kh.web.board.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.web.board.model.dao.BoardDao;
import com.kh.web.board.model.dto.AttachmentDto;
import com.kh.web.board.model.dto.BoardDto;
import com.kh.web.board.model.dto.BoardResponse;
import com.kh.web.common.Template;
import com.kh.web.common.model.dto.PageInfo;

public class BoardService {
	private BoardDao bd = new BoardDao();

	public int selectBoardCount() {
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = bd.selectBoardCount(sqlSession);
		
		if(result > 0) {
			sqlSession.commit();
		}
		sqlSession.close();
		
		
		return result;
	}

	public List<BoardDto> selectBoardList(PageInfo pi) {
		SqlSession sqlSession = Template.getSqlSession();
		
		List<BoardDto> boards = bd.selectBoardList(sqlSession, pi);
		
		sqlSession.close();
		
		return boards;
	}

	public int selectNoticeBoardCount() {
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = bd.selectNoticeBoardCount(sqlSession);
		
		sqlSession.close();
		
		return result;
	
	}

	public List<BoardDto> selectNoticeBoardList(PageInfo pi) {
		SqlSession sqlSession = Template.getSqlSession();
		List<BoardDto> noticeBoards = bd.selectNoticeBoardList(sqlSession, pi);
		
		sqlSession.close();
		
		return noticeBoards;
	}

	public int insertBoard(BoardDto board, AttachmentDto at) {
		SqlSession sqlSession = Template.getSqlSession();
		
		String newTitle = board.getBoardTitle().replaceAll("<", "&lt");
		board.setBoardTitle(newTitle);
		board.setBoardContent(board.getBoardContent().replace("<", "&lt"));
		// INSERT 두번
		// BOARD 테이블에 한번 => 무조건 (Attachment테이블보다 선행되어야함 why-> attachment 테이블에 boardNo가 필요하기 때문 )
		
		int result = bd.insertBoard(sqlSession, board);
		int atResult = 1;
		// ATTACHMENT테이블에 한 번 => 파일이 존재할 때만
		
		if(at != null) {
			at.setRefBno(board.getBoardNo());
			atResult = bd.insertAttachment(sqlSession,at);
		}
		if(result * atResult > 0) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		sqlSession.close();

		return (result * atResult);
	}

	public BoardResponse selectBoard(Long boardNo) {
		SqlSession sqlSession = Template.getSqlSession();
		
		// 총 DB에 세 번 가야함
		// 처음 UPDATE => 조회수 증가 후 커밋
		// SELECT => BOARD
		// SELECT => ATTACHMENT
		
		int result = bd.increaseCount(sqlSession, boardNo);
		BoardResponse br = null;
	
		if(result > 0) {
			sqlSession.commit();
			BoardDto board = bd.selectBoard(sqlSession, boardNo);
			AttachmentDto attachment = bd.selectAttachment(sqlSession, boardNo);
			br = new BoardResponse();
			br.setBoard(board);
			br.setAttachment(attachment);
		}
		sqlSession.close();
		
		
		
		return br;
	}

	public int deleteBoard(BoardDto board) {
		SqlSession sqlSession = Template.getSqlSession();
		
		// 삭제요청을 보낸 사용자가 로그인도 안하고 요청을 보냈네? => Servlet에서 처리
		// 삭제 요청을 보낸 사용자가 BOARD의 작성자랑 다르네? => 요청보낸 사용자의 유저NO가 게시글의 작성자 유저NO랑 동일한가?
		
		BoardDto boardResult = bd.selectBoard(sqlSession, board.getBoardNo());
		
		if(boardResult.getUserNo().longValue() != board.getUserNo().longValue()) {
			return 0;
			// throw new Exception~~
		}
		
		int result = bd.deleteBoard(sqlSession, board);
		
		AttachmentDto attachment = bd.selectAttachment(sqlSession, board.getBoardNo());
		
		if(attachment != null) {
			result *= bd.deleteAttachment(sqlSession, board.getBoardNo());
		}
		if(result > 0) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		
		
		return result;
	}

	public BoardDto selectNoticeBoard(Long boardNo) {
		SqlSession sqlSession = Template.getSqlSession();
		BoardDto board = null;
		int result  = bd.increaseCount(sqlSession, boardNo);
		
		if(result > 0) {
			sqlSession.commit();
			board = bd.selectNoticeBoard(sqlSession, boardNo);
		}
		sqlSession.close();
		return board;
	}

	public int deleteNoticeBoard(BoardDto board) {
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = bd.deleteBoard(sqlSession, board);
		
		if(result > 0) {
			sqlSession.commit();
		}
		sqlSession.close();
		
		return result;
	}

	public int updateBoard(BoardDto board, AttachmentDto at) {
		SqlSession sqlSession = Template.getSqlSession();
		
		// 1. WEN_BOAR => UPDATE
		// 2. WEB_ATTACHMENT => UPDATE
		// 3. WEB_ATTACHMENT => INSERT
		
		int result = bd.updateBoard(sqlSession, board);
		
		// 새 첨부파일이 존재할 경우
		if(at != null) {
			if(at.getFileNo() != null) {
				result *= bd.updateAttachment(sqlSession, at);
			}else {
				result *= bd.insertAttachment(sqlSession, at);
			}
		}
		if(result > 0) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		sqlSession.close();
		
		return result;
	} 
}
