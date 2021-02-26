package com.icia.member.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	// 조회 처리 (주소값으로)
//	@GetMapping("/member/{memail}")
//	// 경로상에 있는 값을 가져올 때
//	public String memberView(@PathVariable("memail") String memail, Model model) {
//		MemberDTO memberView = ms.memberView(memail);
//		model.addAttribute("memberView", memberView);
//		return "memberview";
//	}
	
	@GetMapping("/member/{memail}")
	public ResponseEntity<MemberDTO> memberView(@PathVariable("memail") String memail) {
		MemberDTO memberView = ms.memberView(memail);
		return new ResponseEntity<MemberDTO>(memberView, HttpStatus.OK);
	}
	
	// 삭제 처리
	@DeleteMapping("/member/{mnumber}")
	public @ResponseBody String memberDelete(@PathVariable("mnumber")int mnumber) {
		String result = ms.memberDelete(mnumber);
		return result;
	}
	
	// 수정 화면 처리
	@GetMapping("/member/update")
	public String memberUpdate(HttpSession session, Model model) {
		String loginId = (String)session.getAttribute("loginId");
		MemberDTO updateView = ms.memberView(loginId);
		model.addAttribute("updateView", updateView);
		return "memberUpdate";
	}
	
	// 수정 처리
	@PutMapping("/member/update")
	public @ResponseBody String memberUpdateProcess(@RequestBody MemberDTO member) {
		// 기존 데이터가 존재하는 경우 join을 호출하면 알아서 update 쿼리가 실행 됨
		// 조건은 pk가 전달되어야 함
		ms.memberJoin(member);
		return "ok";
	}
	
}
