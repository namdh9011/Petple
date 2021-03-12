package com.pet.sns.model.dto;

public class Comment {
	/*
	 * create table comment( no int primary key auto_increment, postnum int, date
	 * datetime, contents varchar(150) not null, constraint fk_pnum foreign
	 * key(postnum) references post(pnum) on delete cascade );
	 */
	private int no;
	private int postnum;
	private int usernum;
	private String nickname;
	private String userprofile;
	private String date;
	private String contents;

	public Comment() {
	}

	public Comment(int no, int postnum, int usernum, String nickname, String userprofile, String date,
			String contents) {
		super();
		this.no = no;
		this.postnum = postnum;
		this.usernum = usernum;
		this.nickname = nickname;
		this.userprofile = userprofile;
		this.date = date;
		this.contents = contents;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getPostnum() {
		return postnum;
	}

	public void setPostnum(int postnum) {
		this.postnum = postnum;
	}

	public int getUsernum() {
		return usernum;
	}

	public void setUsernum(int usernum) {
		this.usernum = usernum;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUserprofile() {
		return userprofile;
	}

	public void setUserprofile(String userprofile) {
		this.userprofile = userprofile;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	@Override
	public String toString() {
		return "Comment [no=" + no + ", postnum=" + postnum + ", usernum=" + usernum + ", nickname=" + nickname
				+ ", userprofile=" + userprofile + ", date=" + date + ", contents=" + contents + "]";
	}
}