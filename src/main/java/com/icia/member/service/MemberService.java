package com.icia.member.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.icia.member.domain.entity.MemberEntity;
import com.icia.member.domain.repository.MemberRepository;
import com.icia.member.dto.MemberDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberService {

	private MemberRepository mr;
	
	public String memberJoin(MemberDTO member) {
		// member에 담아온 내용을 DB에 insert
		return mr.save(member.toEntity()).getMemail();
	}

	public MemberDTO memberLogin(MemberDTO member) {
		// Select * from bootmember where mnumber=?
		// Select * from bootmember where memail=?
		MemberEntity memberEntity = mr.findByMemail(member.getMemail());
		// DB에서 조회된 내용은 ENTITY 타입이기 때문에 DTO에 옮겨 담음
		MemberDTO loginMember = MemberDTO.builder()
										.mnumber(memberEntity.getMnumber())
										.memail(memberEntity.getMemail())
										.mpassword(memberEntity.getMpassword())
										.mname(memberEntity.getMname())
										.build();
		if(member.getMpassword().equals(loginMember.getMpassword()))
			return loginMember;
		else
			return null;
	}

	public List<MemberDTO> memberList() {
		List<MemberEntity> memberEntity = mr.findAll();
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		// Entity 타입의 리스트를 DTO 타입의 리스트로 옮겨 담기
		for(MemberEntity entity : memberEntity) {
			MemberDTO member = MemberDTO.builder()
									.mnumber(entity.getMnumber())
									.memail(entity.getMemail())
									.mpassword(entity.getMpassword())
									.mname(entity.getMname())
									.build();
			memberList.add(member);
		}

		return memberList;
	}

	public MemberDTO memberView(String memail) {
		MemberEntity memberEntity = mr.findByMemail(memail);
		MemberDTO memberView = MemberDTO.builder()
										.mnumber(memberEntity.getMnumber())
										.memail(memberEntity.getMemail())
										.mpassword(memberEntity.getMpassword())
										.mname(memberEntity.getMname())
										.build();
		return memberView;
	}

	public String memberDelete(int mnumber) {
		mr.deleteById(mnumber);
		return "ok";
	}

}
