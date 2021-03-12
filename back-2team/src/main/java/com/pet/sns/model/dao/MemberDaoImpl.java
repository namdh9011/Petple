package com.pet.sns.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pet.sns.model.dto.Member;

@Repository("mdao")
public class MemberDaoImpl implements MemberDao {

	@Autowired
	SqlSession session;

	@Override
	public List<Member> testAll() {
		return session.selectList("member.testAll");
	}

	@Override
	public Member loginCheck(Member u) {
		return session.selectOne("member.loginCheck", u);
	}

	@Override
	public Member selectOne(String unum) {
		return session.selectOne("member.selectOne", unum);
	}

	@Override
	public void insert(Member u) {
		session.insert("member.insert", u);
	}

	@Override
	public void update(Member u) {
		session.update("member.update", u);
	}

	@Override
	public void updatePassword(Member u) {
		session.update("member.updatepassword", u);
	}

	@Override
	public void updateInterest(Member u) {
		session.update("member.updateinterest", u);
	}

	@Override
	public void updateuserprofile(Member u) {
		session.update("member.updateuserprofile", u);
	}

	@Override
	public void updaterole(Member u) {
		session.update("member.updaterole", u);
	}
	@Override
	public void delete(String unum) {
		session.delete("member.delete", unum);
	}

	@Override
	public String selectEmail(String email) {
		return session.selectOne("member.selectEmail", email);
	}

	@Override
	public String selectUnum(String email) {
		return session.selectOne("member.selectUnum", email);
	}

	@Override
	public List<Member> selectnickName(String nickname) {
		return session.selectList("member.selectNickname", nickname);
	}

}
