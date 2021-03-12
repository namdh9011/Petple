package com.pet.sns.model.dto;

public class Likepost {
	private int lpnum;
	private String unum;
	private String pnum;
	private String likepost;
	
	
	public Likepost() {
	}


	public Likepost(int lpnum, String unum, String pnum, String likepost) {
		this.lpnum = lpnum;
		this.unum = unum;
		this.pnum = pnum;
		this.likepost = likepost;
	}


	public int getLpnum() {
		return lpnum;
	}


	public void setLpnum(int lpnum) {
		this.lpnum = lpnum;
	}


	public String getUnum() {
		return unum;
	}


	public void setUnum(String unum) {
		this.unum = unum;
	}


	public String getPnum() {
		return pnum;
	}


	public void setPnum(String pnum) {
		this.pnum = pnum;
	}


	public String getLikepost() {
		return likepost;
	}


	public void setLikepost(String likepost) {
		this.likepost = likepost;
	}


	@Override
	public String toString() {
		return "Likepost [lpnum=" + lpnum + ", unum=" + unum + ", pnum=" + pnum + ", likepost=" + likepost + "]";
	}


}
