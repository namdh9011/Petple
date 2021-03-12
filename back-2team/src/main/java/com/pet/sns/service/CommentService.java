package com.pet.sns.service;

import java.util.List;

import com.pet.sns.model.dto.Comment;

public interface CommentService {

	public List<Comment> selectall(int postnum);

	public void insert(Comment comment);

	public void delete(int num);

	public void changeprofile(int num);

}
