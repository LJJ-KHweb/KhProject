package com.kh.web.member.model.dto;

import java.sql.Date;

public class MemberDto {
	private Long userNo;
	// int vs long
	// long vs Long
	/*
	 * userNo : 0  
	 * 
	 * userNo : null 
	 * 
	 * Wrapper클래스를 사용시 0이 돌아욌을때 조회가 0이 왔다고 알고있고 
	 * null 체크로 조회가 안되었을 때 판단을 할수 있다.
	 */
	
	private String userId;
	private String userPwd;
	private String userName;
	private String email;
	private Date enrollDate;
	private Date modifyDate;
	private String status;
	private String userRole;
	


	public MemberDto() {
		super();
	}

	public MemberDto(String userId, String userPwd, String userName, String email) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.email = email;
	}

	public MemberDto(Long userNo, String userId, String userPwd, String userName, String email, Date enrollDate,
			Date modifyDate, String status) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.email = email;
		this.enrollDate = enrollDate;
		this.modifyDate = modifyDate;
		this.status = status;
	}
	

	public MemberDto(Long userNo, String userId, String userPwd, String userName, String email, Date enrollDate,
			Date modifyDate, String status, String userRole) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.email = email;
		this.enrollDate = enrollDate;
		this.modifyDate = modifyDate;
		this.status = status;
		this.userRole = userRole;
	}

	public Long getUserNo() {
		return userNo;
	}

	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "MemberDto [userNo=" + userNo + ", userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName
				+ ", email=" + email + ", enrollDate=" + enrollDate + ", modifyDate=" + modifyDate + ", status="
				+ status + ", userRole=" + userRole + "]";
	}

	
	
	
	

}
