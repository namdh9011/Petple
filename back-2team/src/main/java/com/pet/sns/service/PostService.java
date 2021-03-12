package com.pet.sns.service;

import java.util.List;

import com.pet.sns.model.dto.Post;
import com.pet.sns.model.dto.Tag;

public interface PostService {
	public int selectpnum(Post post);

	public Post selectone(int num);

	public List<Post> selectall();

	public List<Post> selectmine(int num);

	public List<Post> selectten(int num);

	public List<Post> selectfollower(String[] follower);

	public void insert(Post post);

	public void update(Post post);

	public void delete(int num);

	public void hitup(int num);

	public void likeup(int num);

	public void likedown(int num);

	public void commentsup(int num);

	public void commentsdown(int num);

	/* tag */

	public void inserttag(String tags);

	public Integer selecttnum(String tag); // 해당 단어의 tnum return

	public List<String> selecttag(int num); // 게시물에 연결된 모든 태그 가져오기

	// public void updatetag();

	public void deletetag(int num);

	public List<Post> tagsearch(String word); // 태그 검색. 해당 태그가 연결된 모든 게시물 번호 가져오기

	public void posttag(Tag tag);
}
