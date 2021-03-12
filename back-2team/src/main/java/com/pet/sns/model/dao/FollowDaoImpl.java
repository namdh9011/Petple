package com.pet.sns.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pet.sns.model.dto.Follow;

@Repository("foldao")
public class FollowDaoImpl implements FollowDao {

	@Autowired
	SqlSession session;

	@Override
	public List<Follow> testAll() {
		return session.selectList("follow.testAll");
	}

	@Override
	public Follow selectOne(String unum) {
		return session.selectOne("follow.selectOne", unum);
	}

	@Override
	public String selectFollower(String unum) {
		return session.selectOne("follow.selectFollower", unum);
	}

	@Override
	public String selectFollowing(String unum) {
		return session.selectOne("follow.selectFollowing", unum);
	}

	@Override
	public void insert(Follow f) {
		session.insert("follow.insert", f);
	}

	@Override
	public void update(Follow f) {
		session.update("follow.update", f);
	}

	@Override
	public void delete(String unum) {
		session.delete(unum);
	}

	@Override
	public Follow selectMyFollow(Follow f) {
		return session.selectOne("follow.selectMyFollow", f);
	}
}
