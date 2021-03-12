package com.pet.sns.model.dao;


import java.util.List;

import com.pet.sns.model.dto.Likepost;

public interface LikepostDao {
	public List<Likepost> selectAll();
	public Likepost selectOne(Likepost lp);
	public String selectLikepost(Likepost lp);
	public void insert(Likepost lp);
	public void update(Likepost lp);
	public void delete(Likepost lp);
}
