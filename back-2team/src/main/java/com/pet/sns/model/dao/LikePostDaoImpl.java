package com.pet.sns.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pet.sns.model.dto.Likepost;

@Repository("lpdao")
public class LikePostDaoImpl implements LikepostDao{

	@Autowired
	SqlSession session;

	@Override
	public List<Likepost> selectAll() {
		return session.selectList("likepost.selectAll");
	}

	@Override
	public Likepost selectOne(Likepost lp) {
		return session.selectOne("likepost.selectOne", lp);
	}

	@Override
	public String selectLikepost(Likepost lp) {
		return session.selectOne("likepost.selectLikepost", lp);
	}

	@Override
	public void insert(Likepost lp) {
		session.insert("likepost.insert", lp);
		
	}

	@Override
	public void update(Likepost lp) {
		session.update("likepost.update", lp);
	}

	@Override
	public void delete(Likepost lp) {
		session.delete("likepost.delete", lp);
	}
	
}
