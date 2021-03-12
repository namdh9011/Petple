package com.pet.sns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.sns.model.dao.FollowDao;
import com.pet.sns.model.dto.Follow;

@Service("folservice")
public class FollowServiceImpl implements FollowService{
	
	@Autowired
	private FollowDao foldao;

	@Override
	public List<Follow> testAll() {
		return foldao.testAll();
	}

	@Override
	public Follow selectOne(String unum) {
		return foldao.selectOne(unum);
	}
	
	@Override
	public String selectFollower(String unum) {
		return foldao.selectFollower(unum);
	}
	
	@Override
	public String selectFollowing(String unum) {
		return foldao.selectFollowing(unum);
	}

	@Override
	public void insert(Follow f) {
		foldao.insert(f);
	}

	@Override
	public void update(Follow f) {
		foldao.update(f);
	}

	@Override
	public void delete(String unum) {
		foldao.delete(unum);
	}

	@Override
	public Follow selectMyFollow(Follow f) {
		return foldao.selectMyFollow(f);
	}

}
