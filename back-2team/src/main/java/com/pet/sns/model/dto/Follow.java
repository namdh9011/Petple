package com.pet.sns.model.dto;

public class Follow {
	private String folnum;
	private String unum;
	private String follower;
	private String following;
	
	public Follow() {
	}

	public Follow(String folnum, String unum, String follower, String following) {
		this.folnum = folnum;
		this.unum = unum;
		this.follower = follower;
		this.following = following;
	}

	@Override
	public String toString() {
		return "Follow [folnum=" + folnum + ", unum=" + unum + ", follower=" + follower + ", following=" + following
				+ "]";
	}

	public String getFolnum() {
		return folnum;
	}

	public void setFolnum(String folnum) {
		this.folnum = folnum;
	}

	public String getUnum() {
		return unum;
	}

	public void setUnum(String unum) {
		this.unum = unum;
	}

	public String getFollower() {
		return follower;
	}

	public void setFollower(String follower) {
		this.follower = follower;
	}

	public String getFollowing() {
		return following;
	}

	public void setFollowing(String following) {
		this.following = following;
	}

}
