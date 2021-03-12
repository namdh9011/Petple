package com.pet.sns.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pet.sns.model.dto.Follow;
import com.pet.sns.model.dto.Member;
import com.pet.sns.service.FollowService;
import com.pet.sns.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@RestController
@Api("Follow Rest API")
public class FollowRestController {
	@Autowired
	FollowService folservice;
	@Autowired
	MemberService mservice;

	@RequestMapping(value = "/follow/test", method = RequestMethod.GET)
	@ApiOperation("Frontend용 모든 DB를 볼 수 있는 test")
	public List<Follow> testAll() {
		return folservice.testAll();
	}

	@RequestMapping(value = "/follower/{unum}", method = RequestMethod.GET)
	@ApiOperation("unum을 입력하면 나를 팔로우 하는 사람의 정보 출력")
	public List<Member> selectFollower(@PathVariable String unum) {
		String follower = "0" + folservice.selectFollower(unum);
		System.out.println(follower);
		String[] fol = follower.split(" ");
		List<Member> li = new ArrayList<Member>();
		for(int i=1;i<fol.length;i++) {
			Member temp = mservice.selectOne(fol[i]);
			temp.setPassword(null);
			li.add(temp);
		}
		return li;
	}

	@RequestMapping(value = "/following/{unum}", method = RequestMethod.GET)
	@ApiOperation("unum을 입력하면 내가 팔로잉 하는 사람의 정보 출력")
	public List<Member> selectFolling(@PathVariable String unum) {
		String following = "0" + folservice.selectFollowing(unum);
		System.out.println(following);
		String[] fol = following.split(" ");
		List<Member> li = new ArrayList<Member>();
		for(int i=1;i<fol.length;i++) {
			Member temp = mservice.selectOne(fol[i]);
			temp.setPassword(null);
			li.add(temp);
		}
		return li;
	}

	@RequestMapping(value = "/followingcheck", method = RequestMethod.POST)
	@ApiOperation("unum과 following를 입력하면 내가 팔로우 여부 출력")
	public boolean followcheck(@RequestBody Follow f) {
		Follow tempf = folservice.selectMyFollow(f);
		if(tempf!=null&&tempf.getUnum().equals(f.getUnum())) {
			return true;
		}else {
			return false;
		}
	}

	@RequestMapping(value = "/following/{unum}/{followingnum}", method = RequestMethod.POST)
	@ApiOperation("사용자의 unum과 팔로잉 할 사람의 followingnum을 입력하면 팔로잉 성공")
	public Map<String, String> following(@PathVariable String unum, @PathVariable String followingnum) {
		HashMap<String, String> map = new HashMap<String, String>();
		Follow f = folservice.selectOne(unum);
		if (f == null) {
			folservice.insert(new Follow("", unum, " ", " "));
			f = folservice.selectOne(unum);
		} else if (f.getFollowing().contains(" " + followingnum + " ")) {
			map.put("result", "이미 팔로잉한 유저");
			return map;
		}
		Follow tempf = new Follow("", unum, f.getFollower(), f.getFollowing() + followingnum + " ");
		folservice.update(tempf);

		f = null;
		f = folservice.selectOne(followingnum);
		if (f == null) {
			folservice.insert(new Follow("", followingnum, " ", " "));
			f = folservice.selectOne(followingnum);
		}

		tempf = new Follow("", followingnum, f.getFollower() + unum + " ", f.getFollowing());
		folservice.update(tempf);

		map.put("result", "팔로잉성공");
		return map;
	}

	@RequestMapping(value = "/unfollow/{unum}/{unfollownum}", method = RequestMethod.POST)
	@ApiOperation("사용자의 unum과 언팔 할 사람의 unfollownum을 입력하면 언팔 성공")
	public Map<String, String> unFollow(@PathVariable String unum, @PathVariable String unfollownum) {
		HashMap<String, String> map = new HashMap<String, String>();
		Follow f = folservice.selectOne(unum);
		if (!f.getFollowing().contains(" " + unfollownum + " ")) {
			map.put("result", "언팔 할 수 없습니다.");
			return map;
		}
		String un = f.getFollowing();
		un = un.replace(" " + unfollownum + " ", " ");
		Follow tempf = new Follow("", unum, f.getFollower(), un);
		folservice.update(tempf);

		f = folservice.selectOne(unfollownum);
		un = f.getFollower();
		un = un.replace(" " + unum + " ", " ");
		tempf = new Follow("", unfollownum, un, f.getFollowing());
		folservice.update(tempf);

		map.put("result", "언팔 성공");
		return map;
	}
}