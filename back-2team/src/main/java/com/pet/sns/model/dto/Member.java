package com.pet.sns.model.dto;

public class Member {
	private String unum;
	private String name;
	private String email;
	private String password;
	private String nickname;
	private String phone;
	private String interest;
	private String userprofile;
	private String role;
	private String authorization;
	
	public Member() {
	}

	public Member(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public Member(String name, String email, String password, String nickname) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.nickname = nickname;
	}

	public Member(String email, String password, String nickname, String phone, String interest) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.phone = phone;
		this.interest = interest;
	}

	public Member(String unum, String name, String email, String password, String nickname, String phone,
			String interest, String userprofile, String role, String authorization) {
		this.unum = unum;
		this.name = name;
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.phone = phone;
		this.interest = interest;
		this.userprofile = userprofile;
		this.role = role;
		this.authorization = authorization;
	}

	@Override
	public String toString() {
		return "Member [unum=" + unum + ", name=" + name + ", email=" + email + ", password=" + password + ", nickname="
				+ nickname + ", phone=" + phone + ", interest=" + interest + ", userprofile=" + userprofile + ", role="
				+ role + ", authorization=" + authorization + "]";
	}

	public String getUnum() {
		return unum;
	}

	public void setUnum(String unum) {
		this.unum = unum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getUserprofile() {
		return userprofile;
	}

	public void setUserprofile(String userprofile) {
		this.userprofile = userprofile;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}
	

}
