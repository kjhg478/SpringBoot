package com.icia.member.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.icia.member.dto.MemberDTO;
import com.icia.member.service.MemberService;

import lombok.AllArgsConstructor;

@SessionAttributes("loginId") 
@Controller
@AllArgsConstructor
public class MemberController {
	
	// Spring Autowired가 없어도 실행 된 이유?
	// AllArgsConstructor 덕분에 가능
	private MemberService ms;
	
	// 회원가입 폼 출력
	@GetMapping("/member/join")
	public String memberJoinForm() {
		return "memberJoin";
	}
	// 회원가입 폼 출력
	@GetMapping("/member/login")
	public String memberloginForm() {
		return "memberLogin";
	}

	
	// 회원가입 처리
	@PostMapping("/member/join")
	public String memberJoin(MemberDTO member) {
		String result = ms.memberJoin(member);
		if(result != null)
			return "memberlogin";
		else
			return "joinfail";
	}
	
	// 로그인 처리
	@PostMapping("/member/login")
	public String memberLogin(MemberDTO member, Model model) {
		MemberDTO loginMember = ms.memberLogin(member);
		if(loginMember != null) {
			model.addAttribute("loginMember", loginMember);
			model.addAttribute("loginId", loginMember.getMemail());
			return "mypage";
		} else {
			return "loginfail";
		}
	}
	
	// 목록 처리
	@GetMapping("/member/list")
	public String memberList(Model model) {
		List<MemberDTO> memberList = ms.memberList();
		model.addAttribute("memberList", memberList);
		return "memberlist";
	}
}
