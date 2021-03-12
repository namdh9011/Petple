package com.pet.sns.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pet.sns.model.dto.Follow;
import com.pet.sns.model.dto.Member;
import com.pet.sns.model.dto.Result;
import com.pet.sns.service.FollowService;
import com.pet.sns.service.JwtService;
//import com.pet.sns.service.JwtService;
import com.pet.sns.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@RestController
@Api("Member Rest API")
public class MemeberRestController {
	@Autowired
	MemberService uservice;
	@Autowired
	FollowService folservice;
	
	@Autowired private JwtService jwtService;
	
	
	@RequestMapping(value = "/member/test", method = RequestMethod.GET)
	@ApiOperation("Frontend용 모든 DB를 볼 수 있는 test")
	public List<Member> testAll() {
		return uservice.testAll();
	}
	
	@RequestMapping(value = "/memberlogin", method = RequestMethod.POST)
	@ApiOperation("email과 password 입력으로 로그인 체크")
	public boolean loginCheck(@RequestBody Member u) {
		System.out.println(u);
		Member temp = uservice.loginCheck(u);
		System.out.println(temp);
		if(temp==null) {
			return false;
		}else {
			return true;
		}
	}
	
	@RequestMapping(value = "/loginemailcheck/{email}", method = RequestMethod.GET)
	@ApiOperation("email이 존재하는지 확인")
	public boolean emailCheck(@PathVariable String email) {
		System.out.println("email check OK");
		String tempemail = uservice.selectEmail(email);
		if(tempemail==null) {
			return false;
		}else {
			return true;
		}
	}
	
	@RequestMapping(value = "/loginsignin", method = RequestMethod.POST)
	@ApiOperation("email과 password 입력으로 로그인 체크")
    public Result signin(@RequestBody Member u, HttpServletResponse response){
    	Result result = Result.successInstance();
        Member user = uservice.loginCheck(u);
        user.setPassword(null);
        String token = jwtService.create(user.getUnum(),user,user.getName());
        user.setAuthorization(token);
        System.out.println(user);
        result.setData(user);
        return result;
    }

	
	@RequestMapping(value = "/member/{unum}", method = RequestMethod.GET)
	@ApiOperation("unum으로 회원정보 검색")
	public Member selectOne(@PathVariable String unum) {
		return uservice.selectOne(unum);
	}

	@RequestMapping(value = "/membersearchNick/{nickname}", method = RequestMethod.GET)
	@ApiOperation("nickname으로 회원정보 검색")
	public List<Member> selectNickname(@PathVariable String nickname) {
		return uservice.selectNickname(nickname);
	}
	
	@RequestMapping(value = "/loginmember", method = RequestMethod.POST)
	@ApiOperation("회원 추가(unum은 자동부여, interest는 추가 안됨.)")
	public Map<String, String> insert(@RequestBody Member u) {
		System.out.println(u);
		HashMap<String, String> map = new HashMap<String, String>();
		String tempemail = uservice.selectEmail(u.getEmail());
		if(tempemail==null) {
			uservice.insert(u);
			map.put("result", "추가성공");
			return map;
		}else {
			map.put("result", "존재하는 Email");
			return map;
		}
	}

	@RequestMapping(value = "/loginsearchUnum/{email}", method = RequestMethod.GET)
	@ApiOperation("Email을 입력하면 Unum을 출력")
	public String selectUnum(@PathVariable String email) {
		System.out.println(email);
		return uservice.selectUnum(email);
	}
	
	@RequestMapping(value = "/member/updatepassword", method = RequestMethod.PUT)
	@ApiOperation("unum을 입력받아서 password 수정")
	public Map<String, String> updatePassword(@RequestBody Member u) {
		HashMap<String, String> map = new HashMap<String, String>();
		uservice.updatePassword(u);
		map.put("result", "수정성공");
		return map;
	}

	@RequestMapping(value = "/member/update", method = RequestMethod.PUT)
	@ApiOperation("unum을 입력받아서 name, nickname, phone, interest 수정")
	public Map<String, String> update(@RequestBody Member u) {
		HashMap<String, String> map = new HashMap<String, String>();
		uservice.update(u);
		map.put("result", "수정성공");
		return map;
	}

	@RequestMapping(value = "/member/updateinterest", method = RequestMethod.PUT)
	@ApiOperation("unum을 입력받아서 interest 수정 (관심동물 수정)")
	public Map<String, String> updateInterest(@RequestBody Member u) {
		HashMap<String, String> map = new HashMap<String, String>();
		uservice.updateInterest(u);
		map.put("result", "수정성공");
		return map;
	}
	
	@RequestMapping(value = "/memberdelete/{unum}", method = RequestMethod.DELETE)
	@ApiOperation("unum을 입력 받아서 회원 삭제")
	public Map<String, String> delete(@PathVariable String unum) {
		//유저가 팔로우한 사용자의 follower DB 수정
		Follow f = folservice.selectOne(unum);
		System.out.println("fo=" + f);
		if(f!=null) {
		String deletenum = "0" + f.getFollowing();
		String[] fol = deletenum.split(" ");
			for(int i=1;i<fol.length;i++) {
				String unfollownum = fol[i];
				f = folservice.selectOne(unfollownum);
				String un = f.getFollower();
				un = un.replace(" "+unum+" ", " ");
				folservice.update(new Follow("", unfollownum, un, f.getFollowing()));
			}
		}
		//유저 삭제
		uservice.delete(unum);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("result", "삭제성공");
		return map;
	}
}