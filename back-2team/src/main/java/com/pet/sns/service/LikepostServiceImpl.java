package com.pet.sns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.sns.model.dao.LikepostDao;
import com.pet.sns.model.dto.Likepost;
import com.pet.sns.model.dto.Pet;

@Service("lpservice")
public class LikepostServiceImpl implements LikepostService {

	@Autowired
	private LikepostDao likepost;

	@Override
	public List<Likepost> selectAll() {
		return likepost.selectAll();
	}

	@Override
	public Likepost selectOne(Likepost lp) {
		return likepost.selectOne(lp);
	}

	@Override
	public String selectLikepost(Likepost lp) {
		return likepost.selectLikepost(lp);
	}

	@Override
	public void insert(Likepost lp) {
		likepost.insert(lp);
	}

	@Override
	public void update(Likepost lp) {
		likepost.update(lp);
		
	}

	@Override
	public void delete(Likepost lp) {
		likepost.delete(lp);
	}

}
