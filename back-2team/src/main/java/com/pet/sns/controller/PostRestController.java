package com.pet.sns.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pet.sns.model.dto.Post;
import com.pet.sns.model.dto.Tag;
import com.pet.sns.service.FollowService;
import com.pet.sns.service.PostService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@Api("Post Rest API")
public class PostRestController {

	@Autowired
	private PostService postservice;
	@Autowired
	FollowService folservice;

	@ExceptionHandler
	public ResponseEntity<Map<String, Object>> handle(Exception e) {
		return handleFail(e.getMessage(), HttpStatus.OK);
	}

	public ResponseEntity<Map<String, Object>> handleSuccess(Object data) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("state", "ok");
		resultMap.put("data", data);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	public ResponseEntity<Map<String, Object>> handleFail(Object data, HttpStatus state) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("state", "fail");
		resultMap.put("data", data);
		return new ResponseEntity<Map<String, Object>>(resultMap, state);
	}

	@GetMapping("/post/selectone/{num}")
	@ApiOperation("선택한 게시물의 정보  return / 게시물 상세")
	public ResponseEntity<Map<String, Object>> selectone(@PathVariable int num) {
		System.out.println("selectone..........." + num);
		return handleSuccess(postservice.selectone(num));

	}

	@GetMapping("/post/selectmine/{num}")
	@ApiOperation("나의 모든 게시물 리스트 return / 내피드")
	public ResponseEntity<Map<String, Object>> selectmine(@PathVariable int num) {
		System.out.println("selectmine...........");
		return handleSuccess(postservice.selectmine(num));

	}

	@GetMapping("/post/selectten/{num}")
	@ApiOperation("select ten ")
	public ResponseEntity<Map<String, Object>> selectten(@PathVariable int num) {
		System.out.println("selectten..........." + num);
		return handleSuccess(postservice.selectten(num));

	}

	@GetMapping("/post/selectall")
	@ApiOperation("모든 게시물 리스트 받아오기 ")
	public ResponseEntity<Map<String, Object>> selectall() {
		System.out.println("Search All...........");
		List<Post> find = postservice.selectall();
		return handleSuccess(find);

	}

	@GetMapping("/post/selectfollower/{num}")
	@ApiOperation("내가 팔로잉 하는 사람의 게시물만 출력 ")
	public ResponseEntity<Map<String, Object>> selectfollower(@PathVariable int num) {
		System.out.println("Search selectfollower...........");
		String follower = folservice.selectFollowing(Integer.toString(num));
		String[] fol = follower.split(" ");
		System.out.println(fol.toString());
		for (int i = 0; i < fol.length; i++) {
			System.out.println(fol[i]);
		}
		List<Post> find = postservice.selectfollower(fol);
		return handleSuccess(find);

	}

	@PostMapping("post/insert")
	@ApiOperation("게시물 등록")
	public ResponseEntity<Map<String, Object>> insert(@RequestBody Post post) {
		System.out.println(post);

		System.out.println("unum..........." + post.getUnum());
		postservice.insert(post);
		System.out.println("게시물 등록 완료.............");
		Integer pnum = post.getPnum();
		pnum = postservice.selectpnum(post);
		System.out.println("pnum............" + pnum);

		// 해시태그 null아닌지 검사후 추가
		String[] taglist = null;
		String tags = post.getTags();
		if (tags != null) {
			tags = tags.replace(" ", "");
			taglist = tags.split("#");
			System.out.println(Arrays.toString(taglist));
			for (int i = 1; i < taglist.length; i++) {
				System.out.println("태크리스트" + taglist[i]);
				int tnum = postservice.selecttnum(taglist[i]);
				postservice.posttag(new Tag(pnum, tnum));
				System.out.println(taglist[i] + " 태그 추가 완료");
			}
		}

		return handleSuccess(pnum);
	}

	@PutMapping("post/update")
	@ApiOperation("게시물 수정")
	public ResponseEntity<Map<String, Object>> update(@RequestBody Post post) {
		System.out.println("post update...........");
		// 해시태그 삭제 + 추가
		postservice.update(post);
		int pnum = post.getPnum();
		postservice.deletetag(pnum);
		// 해시 태그 추가
		String[] taglist = null;
		String tags = post.getTags();
		if (tags != null) {
			tags = tags.replace(" ", "");
			taglist = tags.split("#");
			System.out.println(Arrays.toString(taglist));
			for (int i = 1; i < taglist.length; i++) {
				System.out.println(i + "     " + taglist[i]);
				int tnum = postservice.selecttnum(taglist[i]);
				postservice.posttag(new Tag(pnum, tnum));
				System.out.println(taglist[i] + " 태그 추가 완료");
			}
		}

		return handleSuccess("게시물 수정 완료");
	}

	@PutMapping("post/hitup/{num}")
	@ApiOperation("게시물 조회수 증가")
	public ResponseEntity<Map<String, Object>> hitup(@PathVariable int num) {
		System.out.println("post hitup...........");
		postservice.hitup(num);
		return handleSuccess("조회수 올리기 성공");
	}

	@PutMapping("post/likeup/{num}")
	@ApiOperation("게시물 댓글 갯수 증가")
	public ResponseEntity<Map<String, Object>> likeup(@PathVariable int num) {
		System.out.println("post hitup...........");

		postservice.likeup(num);
		Post post = postservice.selectone(num);
		int like = post.getLikes();

		return handleSuccess(like);
	}

	@PutMapping("post/likedown/{num}")
	@ApiOperation("게시물 좋아요 감소")
	public ResponseEntity<Map<String, Object>> likedown(@PathVariable int num) {
		System.out.println("post hitup...........");
		postservice.likedown(num);
		Post post = postservice.selectone(num);
		int like = post.getLikes();
		if (like < 0) {
			like = 0;
			post.setLikes(0);
		}

		return handleSuccess(like);
	}

	@PutMapping("post/commentsup/{num}")
	@ApiOperation("게시물 조회수 증가")
	public ResponseEntity<Map<String, Object>> commentsup(@PathVariable int num) {
		System.out.println("post commentup...........");

		postservice.commentsup(num);
		Post post = postservice.selectone(num);
		int comment = post.getComments();

		return handleSuccess(comment);
	}

	@PutMapping("post/commentsdown/{num}")
	@ApiOperation("게시물 댓글 갯수 감소")
	public ResponseEntity<Map<String, Object>> commentsdown(@PathVariable int num) {
		System.out.println("post hitup...........");

		postservice.commentsdown(num);
		Post post = postservice.selectone(num);
		int comment = post.getComments();

		if (comment < 0) {
			comment = 0;
			post.setComments(0);
		}
		return handleSuccess(comment);
	}

	@DeleteMapping("post/delete/{num}")
	@ApiOperation("게시물 삭제")
	public ResponseEntity<Map<String, Object>> delete(@PathVariable int num) {
		System.out.println("post delete...........");
		// 해시태그도 같이 삭제
		postservice.delete(num);
		postservice.deletetag(num);
		return handleSuccess("게시물 삭제 완료");
	}

	@GetMapping("post/tagsearch/{word}")
	@ApiOperation("태그 검색")
	public ResponseEntity<Map<String, Object>> tagsearch(@PathVariable String word) {
		List<Post> find = null;
		find = postservice.tagsearch(word);
		return handleSuccess(find);
	}

}
