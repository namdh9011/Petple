package com.pet.sns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.sns.model.dao.MemberDao;
import com.pet.sns.model.dto.Member;

@Service("qservice")
public class MemberServiceImp implements MemberService{
	
	
	@Autowired
	private MemberDao mdao;

	@Override
	public List<Member> testAll() {
		return mdao.testAll();
	}

	@Override
	public Member loginCheck(Member u) {
		return mdao.loginCheck(u);
	}
	
	@Override
	public Member selectOne(String unum) {
		return mdao.selectOne(unum);
	}

	@Override
	public void insert(Member u) {
		mdao.insert(u);
	}

	@Override
	public void update(Member u) {
		mdao.update(u);
	}

	@Override
	public void updatePassword(Member u) {
		mdao.updatePassword(u);
	}
	@Override
	public void updateInterest(Member u) {
		mdao.updateInterest(u);
	}

	@Override
	public void delete(String unum) {
		mdao.delete(unum);
	}

	@Override
	public String selectEmail(String email) {
		return mdao.selectEmail(email);
	}

	@Override
	public void updateuserprofile(Member u) {
		mdao.updateuserprofile(u);		
	}

	@Override
	public void updaterole(Member u) {
		mdao.updaterole(u);
	}

	@Override
	public String selectUnum(String email) {
		return mdao.selectUnum(email);
	}

	@Override
	public List<Member> selectNickname(String nickname) {
		return mdao.selectnickName(nickname);
	}

}
