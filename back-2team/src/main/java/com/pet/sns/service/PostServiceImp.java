package com.pet.sns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.sns.model.dao.PostDaoMgr;
import com.pet.sns.model.dto.InfoNotFoundException;
import com.pet.sns.model.dto.PetsnsException;
import com.pet.sns.model.dto.Post;
import com.pet.sns.model.dto.Tag;

@Service
public class PostServiceImp implements PostService {

	@Autowired
	private PostDaoMgr dao;

	@Override
	public Post selectone(int num) {
		try {
			System.out.println("Post selectone ");
			Post find = dao.selectone(num);
			if (find == null) {
				throw new InfoNotFoundException();
			} else {
				return find;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new PetsnsException("Post selectone 오류 발생");
		}

	}

	@Override
	public int selectpnum(Post post) {

		try {
			return dao.selectpnum(post);
		} catch (Exception e) {
			e.printStackTrace();
			throw new PetsnsException("Post selectpnum 오류 발생");
		}
	}

	@Override
	public List<Post> selectten(int num) {
		try {
			List<Post> find = null;

			find = dao.selectten(num);
			System.out.println(find);
			return find;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public List<Post> selectall() {

		try {
			System.out.println("Post selectall ");
			List<Post> find = null;
			System.out.println(find);
			find = dao.selectall();
			System.out.println("find" + find);
			if (find == null) {
				System.out.println("null");
				return null;

			} else {
				System.out.println(find);
				return find;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new PetsnsException("Post selectall 오류 발생");
		}

	}

	@Override
	public List<Post> selectmine(int num) {
		try {
			System.out.println("Post selectmine ");
			List<Post> find = dao.selectmine(num);
			if (find == null) {
				throw new InfoNotFoundException();
			} else {
				return find;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new PetsnsException("Post selectmine 오류 발생");
		}
	}

	@Override
	public List<Post> selectfollower(String[] follower) {
		List<Post> find=null;
		try {
			System.out.println("select follower....... " + follower.toString());
			find = dao.selectfollower(follower);
			System.out.println(find.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return find;
	}

	@Override
	public void insert(Post post) {
		try {

			System.out.println("Post insert ");
			dao.insert(post);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Post insert 오류 발생");
			throw new PetsnsException("Post insert 오류 발생");
		}
	}

	@Override
	public void update(Post post) {
		try {
			System.out.println("Post update ");
			dao.update(post);

		} catch (Exception e) {
			e.printStackTrace();
			throw new PetsnsException("Post update 오류 발생");
		}
	}

	@Override
	public void delete(int num) {
		try {

			System.out.println("Post delete ");
			dao.delete(num);

		} catch (Exception e) {
			e.printStackTrace();
			throw new PetsnsException("Post delete 오류 발생");
		}

	}

	@Override
	public void hitup(int num) {
		try {

			System.out.println("Post hit up.......... " + num);
			Post find = dao.selectone(num);
			if (find == null) {
				throw new InfoNotFoundException("게시물 조회수 추가 중 오류발생");
			} else {
				dao.hitup(num);
				System.out.println("Post hit up success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new PetsnsException("Post hit up 중 오류 발생");
		}
	}

	@Override
	public void likeup(int num) {
		try {
			System.out.println("Post like up.......... " + num);
			Post find = dao.selectone(num);
			if (find == null) {
				throw new InfoNotFoundException("게시물 좋아요 추가 중 오류발생");
			} else {
				dao.likeup(num);
				System.out.println("Post likeup success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new PetsnsException("Postlikeup 중 오류 발생");
		}
	}

	@Override
	public void likedown(int num) {
		try {
			System.out.println("Post like down.......... " + num);
			Post find = dao.selectone(num);
			if (find == null) {
				throw new InfoNotFoundException("게시물 좋아요 다운 중 오류발생");
			} else {
				dao.likedown(num);
				System.out.println("Post likedown success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new PetsnsException("likedown 중 오류 발생");
		}
	}

	@Override
	public void commentsup(int num) {
		try {
			Post find = dao.selectone(num);
			if (find == null) {
				throw new InfoNotFoundException("댓글수 올리기 중 오류발생");
			} else {
				dao.commentsup(num);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void commentsdown(int num) {
		try {
			Post find = dao.selectone(num);
			if (find == null) {
				throw new InfoNotFoundException("댓글수 올리기 중 오류발생");
			} else {
				dao.commentsdown(num);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void inserttag(String word) {
		try {
			System.out.println("insert tag " + word);
			dao.inserttag(word);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("insert tag 중 오류 발생");
			throw new PetsnsException("insert tag 중 오류 발생");
		}

	}

	@Override
	public Integer selecttnum(String tag) {

		try {
			System.out.println("select num............");
			Integer tnum = -1;
			tnum = dao.selecttnum(tag);
			if (tnum == null) {
				dao.inserttag(tag);
				System.out.println("tnum 이 없어");
				tnum = dao.selecttnum(tag);
				System.out.println("new tnum = " + tnum);
				return tnum;
			} else {
				System.out.println("tnum 이 있을때");
				System.out.println(tnum);
				return tnum;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("select tnum 중 에러발생 ");
		}
		return -1;
	}

	@Override
	public List<String> selecttag(int num) {
		try {
			List<String> find = dao.selecttag(num);
			return find;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("select tag 중 오류발생   -게시물에 연결된 모든 태그 가져오기");
			return null;
		}

	}
	/*
	 * @Override public void updatetag() { // TODO Auto-generated method stub
	 * 
	 * }
	 */

	@Override
	public void deletetag(int num) {
		try {
			dao.deletetag(num);
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("deletetag  중 오류 발생");
		}

	}

	@Override
	public List<Post> tagsearch(String word) {
		// 태그 검색. 해당 태그가 연결된 모든 게시물 번호 가져오기
		List<Post> find = null;
		try {
			find = dao.tagsearch(word);
			return find;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("태그 검색중 오류 발생");
		}
		return find;
	}

	@Override
	public void posttag(Tag tag) {
		try {
			// int tnum = dao.selecttnum(word);
			dao.posttag(tag);
		} catch (Exception e) {
			e.printStackTrace();
			throw new PetsnsException("post_tag 입력중 에러발생");

		}
	}

}
