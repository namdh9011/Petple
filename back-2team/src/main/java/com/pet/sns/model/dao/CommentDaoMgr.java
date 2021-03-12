package com.pet.sns.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pet.sns.model.dto.Comment;

@Mapper
public interface CommentDaoMgr {

	public List<Comment> selectall(int postnum);

	public void insert(Comment comment);

	public void update(Comment comment);

	public void delete(int num);

	public void changeprofile(int num);

}
