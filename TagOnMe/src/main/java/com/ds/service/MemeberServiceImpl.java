package com.ds.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ds.domain.MemberVO;
import com.ds.domain.TagVO;
import com.ds.mapper.MemberMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class MemeberServiceImpl implements MemberService {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MemberMapper membermapper;

	@Override
	public MemberVO readMember(MemberVO vo) {
		return membermapper.read(vo);
	}
	
	@Override
	public void insert(MemberVO vo) {//회원가입시 회원정보추가
		membermapper.insert(vo);
		
	}
	
	
	
	@Override
	public void insertTagUser(TagVO tagvo) {
		membermapper.insertTagUser(tagvo);
	}

//	@Override
//	public void insertTags(TagVO tagvo) {
//		membermapper.insertTags(tagvo);
//	}
//	
	
	
	


	@Override
	public MemberVO login(MemberVO vo) {
		return membermapper.login(vo);
	}

	// 비밀번호 변경
	@Override
	public void updatePw(MemberVO vo) { // public void 로 타입 변경
		membermapper.updatePw(vo);

	}

	// 아이디 중복 체크
	@Override
	public int idChk(String id) {
		return membermapper.idChk(id);
	}

	//태그를 읽어옴
	 @Override 
	 public List<TagVO> readTag() { 
		 return membermapper.readTag(); }
	@Override//프로필 오픈
	public MemberVO readAllMember(int uno) {
		return membermapper.readAllMember(uno);
	}

	@Override
	public MemberVO readBoardWriteMember(String userid) {
		return membermapper.readBoardWriteMember(userid);
	}

	@Override//프로필 수정
	public void editProfile(MemberVO vo) throws Exception {
		membermapper.editProfile(vo);
	}

	@Override//태그 검색
	public List<TagVO> searchTag(int uno) {
		return membermapper.searchTag(uno);
	}

	@Override//존재하는 유저태그 삽입
	public void oldUserTag(TagVO tagVO) {
		membermapper.oldUserTag(tagVO);
	}

	@Override//유저 태그 삭제
	public void delUserTag(TagVO tagVO) {
		membermapper.delUserTag(tagVO);	
	}

	
	/*
	 * @Override
	 * 
	 * @Transactional public void newTagInsert(TagVO tagVO) {
	 * membermapper.newTag(tagVO.getTags());
	 * membermapper.newUserTag(tagVO.getUno()); }
	 */
	
	
	@Override
	public void newUserTag(TagVO tagVO) {
		membermapper.newUserTag(tagVO);
	}
	

	@Override
	@Transactional
	public void newTag(TagVO tagVO) {
		membermapper.newTag(tagVO);
		membermapper.newUserTag(tagVO);	
		membermapper.getNewTag(tagVO);
	}

	@Override
	public void leaderTag(TagVO tagVO) {
		log.info("leaderTag of service>>>"+tagVO.toString());
		membermapper.leaderTag(tagVO);
	}

	@Override
	public TagVO getNewTag(TagVO tagVO) {
		return membermapper.getNewTag(tagVO);
	}
	
	@Override
	public TagVO tagCheck(TagVO tagVO) {
		return membermapper.tagCheck(tagVO);
	}

}