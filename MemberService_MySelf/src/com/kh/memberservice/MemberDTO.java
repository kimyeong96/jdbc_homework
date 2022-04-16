package com.kh.memberservice;

public class MemberDTO {
	private String id;
	private String pw;
	private String nickname;
	
	public MemberDTO() {}

	public MemberDTO(String id) {
		this.id = id;
	}
	
	
	public MemberDTO(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}

	public MemberDTO(String id, String pw, String nickname) {
		this.id = id;
		this.pw = pw;
		this.nickname = nickname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "MemberDTO [id=" + id + "]";
	}
	
	
	
	
}
