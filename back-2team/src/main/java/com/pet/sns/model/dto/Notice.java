package com.pet.sns.model.dto;

public class Notice {

	// num,unum_from,unum_to,action,pnum,create_at,read_at
	private int num;
	private int unum_from;
	private int unum_to;
	private String action;
	private int pnum;
	private String create_at;
	private String read_at;

	public Notice() {
	};

	public Notice(int num, int unum_from, int unum_to, String action, int pnum, String create_at, String read_at) {
		super();
		this.num = num;
		this.unum_from = unum_from;
		this.unum_to = unum_to;
		this.action = action;
		this.pnum = pnum;
		this.create_at = create_at;
		this.read_at = read_at;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getUnum_from() {
		return unum_from;
	}

	public void setUnum_from(int unum_from) {
		this.unum_from = unum_from;
	}

	public int getUnum_to() {
		return unum_to;
	}

	public void setUnum_to(int unum_to) {
		this.unum_to = unum_to;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public String getCreate_at() {
		return create_at;
	}

	public void setCreate_at(String create_at) {
		this.create_at = create_at;
	}

	public String getRead_at() {
		return read_at;
	}

	public void setRead_at(String read_at) {
		this.read_at = read_at;
	}

	@Override
	public String toString() {
		return "Notice [num=" + num + ", unum_from=" + unum_from + ", unum_to=" + unum_to + ", action=" + action
				+ ", pnum=" + pnum + ", create_at=" + create_at + ", read_at=" + read_at + "]";
	}

}
