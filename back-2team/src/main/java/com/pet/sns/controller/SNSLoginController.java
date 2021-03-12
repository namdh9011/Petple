package com.pet.sns.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.pet.sns.auth.config.LoginUser;
import com.pet.sns.auth.dto.SessionUser;
import com.pet.sns.model.dto.Member;
import com.pet.sns.service.JwtService;
import com.pet.sns.service.MemberService;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RequiredArgsConstructor
@Controller
public class SNSLoginController {
	@Autowired
	MemberService mservice;
	@Autowired
	JwtService jwtService;
	@GetMapping("/")
	public String index(Model model, @LoginUser SessionUser user,HttpServletRequest request) {
		System.out.println(user);
		if(user!=null) {
			String email = user.getEmail();
			String name = user.getName();
			System.out.println(name);
			System.out.println(email);
			model.addAttribute("userName", name);
			model.addAttribute("userEmail", email);
			String tempemail = mservice.selectEmail(email);
			if(tempemail==null) {
				mservice.insert(new Member(name, email, "1q2w3e4r","닉네임을 수정해 주세요."));
			}
		}
		return "index";
	}

	
	
//	@GetMapping("/")
//	public String logingoogle(Model model, @LoginUser SessionUser user,HttpServletRequest request) {
//		System.out.println("logingooglesuccess");
//		System.out.println(user);
//		if(user!=null) {
//			String email = user.getEmail();
//			String name = user.getName();
//			System.out.println(name);
//			System.out.println(email);
//			model.addAttribute("userName", name);
//			model.addAttribute("userEmail", email);
//			String tempemail = mservice.selectEmail(email);
//			if(tempemail==null) {
//				mservice.insert(new Member(name, email, "1q2w3e4r","닉네임을 수정해 주세요."));
//			}
//			String unum = mservice.selectUnum(email);
//			
//		}
//		return "index";
//	}
//	
//	@GetMapping("/loginnaver")
//	public String loginnaver(Model model, @LoginUser SessionUser user,HttpServletRequest request) {
//		System.out.println("logingooglesuccess");
//		System.out.println(user);
//		if(user!=null) {
//			String email = user.getEmail();
//			String name = user.getName();
//			System.out.println(name);
//			System.out.println(email);
//			model.addAttribute("userName", name);
//			model.addAttribute("userEmail", email);
//			String tempemail = mservice.selectEmail(email);
//			if(tempemail==null) {
//				mservice.insert(new Member(name, email, "1q2w3e4r","닉네임을 수정해 주세요."));
//			}
//			String unum = mservice.selectUnum(email);
//			
//		}
//		return "index";
//	}
	
//	@RequestMapping(value = "/loginsignin", method = RequestMethod.POST)
//	@ApiOperation("email과 password 입력으로 로그인 체크")
//    public Member signin(@RequestBody Member u, HttpServletResponse response){
////    	Result result = Result.successInstance();
//        Member user = mservice.loginCheck(u);
//        user.setPassword(null);
//        user.setAuthorization(jwtService.create(user.getUnum(),user,user.getName()));
////        response.setHeader("Authorization", token);
////        result.setData(user);
//        return user;
//    }
	
}
