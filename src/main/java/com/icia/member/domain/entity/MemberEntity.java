package com.icia.member.domain.entity;

import javax.persistence.*;

import lombok.*;

// Entity 클래스 객체를 다른 패키지 소속 클래스가 만들지 못하게 함
// MemberEntitly me = new MemberEntity();
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name="bootmember") 
public class MemberEntity {
	
	@Id // PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) // sequence 
	private int mnumber;
	
	@Column(length=100, unique=true, nullable=false)
	private String memail;

	@Column(length=50, nullable=false)
	private String mpassword;
	
	@Column(length=30, nullable=false)
	private String mname;
	
	// Setter의 역할을 함
	// 필드 하나하나에 대한 setter를 쓰지 않고 builder 패턴 사용
	@Builder
	public MemberEntity(int mnumber, String memail, 
			String mpassword, String mname) {
		this.mnumber = mnumber;
		this.memail = memail;
		this.mpassword = mpassword;
		this.mname = mname;
	}



	
}
