package com.icia.member.dto;

import com.icia.member.domain.entity.MemberEntity;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDTO {
	private int mnumber;
	private String memail;
	private String mpassword;
	private String mname;
	
	public MemberEntity toEntity() {
		MemberEntity entity = MemberEntity.builder()
											.mnumber(mnumber)
											.memail(memail)
											.mpassword(mpassword)
											.mname(mname)
											.build();
		return entity;
	}
	
	@Builder
	public MemberDTO(int mnumber, String memail, String mpassword, String mname) {
		this.mnumber = mnumber;
		this.memail = memail;
		this.mpassword = mpassword;
		this.mname = mname;
	}
}
