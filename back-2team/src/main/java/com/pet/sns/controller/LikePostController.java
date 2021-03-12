package com.pet.sns.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pet.sns.model.dto.Likepost;
import com.pet.sns.service.LikepostService;
import com.pet.sns.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@RestController
@Api("LikePost Rest API")
public class LikePostController {
	
	@Autowired
	MemberService uservice;
	@Autowired
	LikepostService lpservice;
	
	
	@RequestMapping(value = "/likepost/All", method = RequestMethod.GET)
	@ApiOperation("Frontend용 모든 DB를 볼 수 있는 test")
	public List<Likepost> testAll() {
		return lpservice.selectAll();
	}
	
	@RequestMapping(value = "/likepostConfirm", method = RequestMethod.POST)
	@ApiOperation("unum과 pnum을 입력하면 좋아요 여부 출력")
	public boolean likepostConfirm(@RequestBody Likepost lp) {
//		System.out.println(lp);
		String st = lpservice.selectLikepost(lp);
//		System.out.println(st);
		if(st==null) {
			return false;
		}else if(st.equals("Y")) {
			return true;
		}else {
			return false;
		}
	}
	
	@RequestMapping(value = "/likepostclick", method = RequestMethod.POST)
	@ApiOperation("unum과 pnum을 입력하면 좋아요 클릭으로 변경")
	public Map<String, String> likepostclick(@RequestBody Likepost lp) {
		HashMap<String, String> map = new HashMap<String, String>();
		System.out.println(lp);
		Likepost templp = lpservice.selectOne(lp);
		if(templp==null) {
			lpservice.insert(lp);
			templp = lpservice.selectOne(lp);
			templp.setLikepost("Y");
			lpservice.update(templp);
			System.out.println(templp);
			map.put("result", "좋아요 성공");
		}else {
			map.put("result", "이미 좋아요한 게시물 입니다.");
		}
		return map;
	}
	
	@RequestMapping(value = "/likepostunclick", method = RequestMethod.POST)
	@ApiOperation("unum과 pnum을 입력하면 좋아요 취소로 변경")
	public Map<String, String> likepostunclick(@RequestBody Likepost lp) {
		HashMap<String, String> map = new HashMap<String, String>();
		Likepost templp = lpservice.selectOne(lp);
		if(templp!=null) {
			lpservice.delete(lp);
			map.put("result", "좋아요 취소 성공");
		}else {
			map.put("result", "좋아요 취소된 게시물 입니다.");
		}
		return map;
	}
}