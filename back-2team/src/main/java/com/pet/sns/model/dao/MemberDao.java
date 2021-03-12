package com.pet.sns.model.dao;

import java.util.List;

import com.pet.sns.model.dto.Member;

public interface MemberDao {
	public List<Member> testAll();
	public Member loginCheck(Member u);
	public Member selectOne(String unum);
	public String selectEmail(String email);
	public List<Member> selectnickName(String nickname);
	public String selectUnum(String email);
	public void insert(Member u);
	public void update(Member u);
	public void updatePassword(Member u);
	public void updateInterest(Member u);
	public void updateuserprofile(Member u);
	public void updaterole(Member u);
	public void delete(String unum);
}
