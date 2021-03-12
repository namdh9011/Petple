package com.pet.sns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.sns.model.dao.CommentDaoMgr;
import com.pet.sns.model.dao.MemberDao;
import com.pet.sns.model.dto.Comment;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDaoMgr dao;

	@Override
	public List<Comment> selectall(int postnum) {
		List<Comment> find = null;
		try {
			System.out.println("select all..find." + postnum);
			find = dao.selectall(postnum);

			if (find.size() == 0) {
				System.out.println("comment selectall find null");
				return null;
			}
			return find;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("comment select all error");

		}
		return find;
	}

	@Override
	public void insert(Comment comment) {
		try {
			dao.insert(comment);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("comment insert error");
		}
	}

	@Override
	public void delete(int no) {
		try {
			dao.delete(no);
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	@Override
	public void changeprofile(int num) {
		try {
			dao.changeprofile(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
