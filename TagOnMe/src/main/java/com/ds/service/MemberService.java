package com.ds.service;

import java.util.List;

import com.ds.domain.MemberVO;
import com.ds.domain.TagVO;

public interface MemberService {
	
	MemberVO readMember(MemberVO vo); 
	
	MemberVO login(MemberVO vo);//로그인
		
	public void insert(MemberVO vo);//회원가입시 회원정보를 넣음
	
	public void insertTagUser(TagVO tagvo);//회원가입시 TagUser를 넣음

	//public void insertTags(TagVO tagvo);//회원가입시 태그를 넣음
	
	public void updatePw(MemberVO vo);//회원정보수정)비밀번호만수정
	// public void 로 타입 변경


	public int idChk(String id);
	
	
	List<TagVO> readTag();//태그를 읽어옴
	
	
	MemberVO readAllMember(int uno);//프로필 오픈
	MemberVO readBoardWriteMember(String userid);//프로필 오픈
	void editProfile(MemberVO vo) throws Exception;//프로필 수정
	List<TagVO> searchTag(int uno);//태그 검색
	void oldUserTag(TagVO tagVO);//존재하는 유저태그 삽입
	
	//void newTagInsert(TagVO tagVO);//트랜잭션 쓸 뉴인서트 유저태그
	void newTag(TagVO tagVO);//존재하지 않는 태그 만들기
	void newUserTag(TagVO tagVO);//존재하지 않는 유저태그 삽입
	
	void delUserTag(TagVO tagVO);//유저 태그 삭제

	void leaderTag(TagVO tagVO);//리더 태그(임시)
	
	TagVO getNewTag(TagVO tagVO);//트랜잭션으로 인서트한 태그 바로 가져오려고(임시)
	
	TagVO tagCheck(TagVO tagVO);//중복검사
	
}
