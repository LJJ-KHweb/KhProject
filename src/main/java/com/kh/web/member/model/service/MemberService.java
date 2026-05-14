package com.kh.web.member.model.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.kh.web.common.Template;
import com.kh.web.member.model.dao.MemberDao;
import com.kh.web.member.model.dto.MemberDto;
import com.kh.web.member.model.dto.UpdatePwdDto;

public class MemberService {
	private MemberDao md = new MemberDao();
	
	/*
	 * public void vaildate(MemberDto member) { String pattern =
	 * "^[a-zA-Z0-9]{5,30}"; }
	 */
	
	public int insertMember(MemberDto member) {
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = md.insertMember(sqlSession, member);
		
		if(result > 0 ) {
			sqlSession.commit();
		}
		sqlSession.close();
		
		return result;
		
	}

	public MemberDto login(MemberDto member) {
		//로그인 처리 -> DAO로 전달값을 전달해서 SELECT 해보기 -> 결과값반환
		// 전통적인 session방식 로그인은 조회된 행의 정보를 객체의 필드에 담아서 반환
		// 원래는 비즈니스 로직으로 검증을 하고 디비에 보내야됨 디비 자원은 소중하니까 불필요한 요청을 줄여야함
		SqlSession sqlSession = Template.getSqlSession();
		
		MemberDto loginMember =md.login(sqlSession, member);
		
		sqlSession.close();
		
		return loginMember;
	}

	public MemberDto updateMember(Map<String, String> map) {
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = md.updateMember(sqlSession, map);
		MemberDto member = null;
		
		if(result > 0) {
			sqlSession.commit();
			member = md.selectMember(sqlSession, Long.parseLong(map.get("userNo")));
		}
		
		
		
		sqlSession.close();
		
		return member;
		
	}

	public int updatePassword(UpdatePwdDto upd) {
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = md.updatePassword(sqlSession, upd);
		
		if(result > 0) {
			sqlSession.commit();
		}
		
		sqlSession.close();

		return result;
	}

	public int deleteMember(MemberDto member) {
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = md.deleteMember(sqlSession, member);
		
		if(result> 0) {
			sqlSession.commit();
		}
		sqlSession.close();
		
		return result;
	}
	
	
}
