package com.ds.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ds.domain.MemberVO;
import com.ds.domain.TagVO;

@Mapper
public interface MemberMapper {
	MemberVO read(MemberVO vo); 	
	void insert(MemberVO vo); 
	
	MemberVO login(MemberVO vo);
	
	public void updatePw(MemberVO vo);

	public int idChk(String id);
	
	int update(MemberVO vo); 
	int delete(int mno);
	
	List<TagVO> readTag();
	
	MemberVO readAllMember(int uno);//프로필 오픈
	MemberVO readBoardWriteMember(String userid); // 게시물 작성자의 프로필 오픈
	
	void editProfile(MemberVO vo) throws Exception;//프로필 수정
	List<TagVO> searchTag(int uno);
	void oldUserTag(TagVO tagVO);//존재하는 유저태그 삽입
	
	void newTag(TagVO tagVO);//존재하지 않는 태그 만들기
	void newUserTag(TagVO tagVO);//존재하지 않는 유저태그 삽입
	
	void delUserTag(TagVO tagVO);//유저태그 삭제
	
	void leaderTag(TagVO tagVO);//리더태그(임시)
	
	TagVO getNewTag(TagVO tagVO);//트랜잭션으로 인서트한 태그 바로 가져오려고(임시)
}

	

