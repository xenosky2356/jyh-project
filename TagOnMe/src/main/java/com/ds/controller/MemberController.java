package com.ds.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ds.domain.MemberVO;
import com.ds.domain.ProfileAttachVO;
import com.ds.domain.TagVO;
import com.ds.service.CommonService;
import com.ds.service.MemberService;
import com.ds.service.ProfileAttachService;
import com.ds.upload.ProfileUpload;
import com.ds.util.MediaUtils;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/member/*") // url을 받아옴//경로)http://localhost:8080/member/memberinfo
@Log4j
//회원정보
public class MemberController {

	@Autowired
	private MemberService service;
	
	@Autowired
	private ProfileAttachService profileAttachService;
	
	@Autowired
	private CommonService commonService;
	
	private ProfileUpload profileUpload = new ProfileUpload();
	private final Logger log = LoggerFactory.getLogger(this.getClass());


	@GetMapping("/memberinfo") // 사이트에 보이는 주소
	public ModelAndView openMemberList(MemberVO vo, HttpServletRequest request) {
		log.info("GET");
		ModelAndView mv = new ModelAndView("member/memberinfo");// 파일의 위치url//1,4//실제로 가는 주소
		HttpSession session = request.getSession(true);

		MemberVO member = (MemberVO) session.getAttribute("member"); // "member"가 key인 value를 가져옴(vo)
		if (member == null) {
			mv.setViewName("member/memberlogin");
			mv.addObject("alarm", 0);

		} else {
			mv.addObject("list", member);// 2//키와 벨류
			mv.addObject("login", session.getAttribute("login"));
		}
		return mv;// 3
	}

	// 비밀번호 수정
	@PostMapping("/memberinfo")
	public String pwmodify(@RequestParam String oldpw, Model model, MemberVO vo, HttpServletRequest request) {
		log.info("POST");
		// String result="";

		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(-1);// 세션 무한
		MemberVO member = (MemberVO) session.getAttribute("member");
		String pw = member.getUserpw();

		log.info("post db의 비밀번호>>>>>" + pw);
		log.info("post memberinfo에서 입력한 비밀번호>>>>" + oldpw);

		// post 로 비밀번호를 변경
		service.updatePw(vo);// 문제점 : 비밀번호 변경 1회 후 바로 2회째 비밀번호 변경시 변경했던 비밀번호가 아닌 기존의 로그인한 비밀번호로 변경이 가능한
								// 오류//세션의문제인듯
		session.invalidate();// 세션 초기화 코드//비밀번호 변경을 하면 다시 로그인창으로가서 다시 로그인 해야됨

		return "redirect:/member/memberinfo";
	}

	@GetMapping("/memberjoin") // 회원가입
	public String join(MemberVO vo,TagVO tagVO, Model model) {
		/* String result = ""; */
		/*
		 * if(vo==null) return; else model.addAttribute(vo);
		 */
		log.info("JOINGET");
		
		List<TagVO> tagvo = service.readTag();		 
		
		
		if(tagvo != null) {
			
			model.addAttribute("tagvo", tagvo);
			
		}
		return null;	

	}

	@PostMapping("/memberjoin") // 회원가입 버튼 누르면 insert
	public String joinForm(MemberVO vo, Errors errors, RedirectAttributes ra) {

		log.info(vo.toString());
		/*
		 * if(errors.hasErrors()) {
		 * 
		 * ra.addFlashAttribute("join", vo); ra.addFlashAttribute("errorMsg", "fail");//
		 * 뷰에보내는정보// 이메일 양식이 아닐 경우, 에러 메시지 보내줌.
		 * 
		 * System.out.println("===hasErrors===" + errors.hasErrors());
		 * 
		 * System.out.println("===getFieldErrors===" + errors.getFieldErrors());
		 * 
		 * for(FieldError value : errors.getFieldErrors()) {
		 * System.out.println("===defaultMessage===" + value.getDefaultMessage());
		 * 
		 * }
		 * 
		 * 
		 * return "redirect:/member/memberjoin";
		 * 
		 * } else { }
		 */
		service.insert(vo);

		// }
		return "redirect:/member/memberlogin";

	}

	// 아이디 중복 체크
	@ResponseBody
	@PostMapping("/idChk/{id}") // 페이지를 가져오지 않고 바로 그자리에서 체크할 수 있으니까 POST 만 쓴다
	public int idChk(@PathVariable String id) {
		log.info("id:::" + id);
		int result = service.idChk(id);
		log.info("결과:::" + result);
		return result;
	}

	/*
	 * // 태그 중복 체크
	 * 
	 * @ResponseBody
	 * 
	 * @PostMapping("/tagChk/{tno}")//페이지를 가져오지 않고 바로 그자리에서 체크할 수 있으니까 POST 만 쓴다
	 * public int tagChk(@PathVariable String tno) { log.info("tno:::"+tno); int
	 * result = service.tagChk(tno); log.info("결과:::"+result); return result; }
	 */
	
	@GetMapping("/memberlogin") // 로그인
	public void login() {
	}

	@PostMapping("/memberlogin") // 로그인버튼 누르면 insert
	public String loginForm(MemberVO vo, Model model, RedirectAttributes ra, HttpServletRequest request) {
		String result = "";
		log.info(vo.toString());
		HttpSession session = request.getSession(true); // 세션가져오기 (true : 세션 생성)
		session.setMaxInactiveInterval(-1);// 세션 무한
		MemberVO member = service.login(vo);

		if (member != null) {// getter,setter의 유저아이디
			// login 되었을 경우. 세션에 값을 넣어줘야됨.
			session.setAttribute("member", member); // vo를 세션에 넣음
			session.setAttribute("login", "success"); // login 키와 success 값
			result = "redirect:/";// 메인페이지 주소해야됨
		} else {
			// login 실패했을 경우, 에러 메시지 보내줌.
			ra.addFlashAttribute("errorMsg", "fail");// 뷰에보내는정보
			result = "redirect:/member/memberlogin";
		}

		return result;
	}


    // 임시 로그아웃
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        MemberVO member = (MemberVO) session.getAttribute("member");
        String result = "";

        if (member != null) {// getter,setter의 유저아이디
            // login 되었을 경우. 세션에 값을 넣어줘야됨.
            session.setAttribute("member", member); // vo를 세션에 넣음
            session.setAttribute("login", "success"); // login 키와 success 값
            session.invalidate();// 세션 초기화 코드

            result = "redirect:/";
        } else {
            result = "redirect:/";
        }
        return result;

    }


    //프로필 오픈
    @GetMapping("/openProfileList.do")
    public ModelAndView openProfile(MemberVO vo, HttpServletRequest request, Model model) throws Exception {

        ModelAndView mv = new ModelAndView("member/readProfile");
        model = commonService.loginUser(model, request);
        HttpSession session = request.getSession(true); // 세션가져오기 (true : 세션 생성)
        MemberVO member = (MemberVO) session.getAttribute("member");
        if (member == null) {
            mv.setViewName("redirect:/member/memberlogin");
        } else {

            MemberVO mvo = service.readAllMember(member.getUno());
            session.getAttribute("member");
            session.setAttribute("mvo", mvo);

            if (mvo != null) {

                mv.addObject("mvo", mvo);
                mv.addObject("tagList", mvo.getTagList());
                session.getAttribute("mvo");

                mv.setViewName("member/readProfile");
            } else {
                mv.setViewName("redirect:/member/memberlogin");
            }

        }

        return mv;
    }
  //게시물 작성자의 프로필 오픈
    @RequestMapping("/openProfileList.do/{writer}")
    public ModelAndView openWriterProfile(MemberVO vo, @PathVariable(value = "writer", required = false) String writer, Model model ,HttpServletRequest request) throws Exception {

        ModelAndView mv = new ModelAndView("member/readProfile");
        HttpSession session = request.getSession(true); // 세션가져오기 (true : 세션 생성)
        
        model = commonService.loginUser(model, request);
        
        MemberVO mvo = service.readBoardWriteMember(writer);
        session.getAttribute(writer);
        session.setAttribute("mvo", mvo);

        if (mvo != null) {

            mv.addObject("mvo", mvo);
            mv.addObject("tagList", mvo.getTagList());
            session.getAttribute("mvo");

            mv.setViewName("member/readProfile");
        } else {
            mv.setViewName("redirect:/member/memberlogin");
        }

        return mv;
    }



    //프로필 이미지 보이기 다시 하기?
    @RequestMapping(value = "/display", method = RequestMethod.GET)
    public ResponseEntity<byte[]> displayImage() throws IOException {
        MediaUtils mediaUtils = new MediaUtils();
        InputStream in = null;
        ResponseEntity<byte[]> entity = null;
        List<ProfileAttachVO> attachList = profileAttachService.selectFileName();
        for (ProfileAttachVO attachVO : attachList) {
            try {
                String fileName = attachVO.getFileName();
                String uuid = attachVO.getUuid();
                String filePath = attachVO.getFilePath();
                String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
                MediaType mType = mediaUtils.getMediaType(formatName);
                HttpHeaders headers = new HttpHeaders();
                in = new FileInputStream(filePath + "\\" + uuid + "_" + fileName);
                headers.setContentType(mType);
                entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
            } catch (IOException e) {
                e.printStackTrace();
                entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
            } finally {
                in.close();
            }
        }
        return entity;
    }

    //프로필 수정 오픈
    @GetMapping(value = "/updateProfile.do")
    public ModelAndView goUpdateProfile(MemberVO vo, TagVO tagVO, HttpServletRequest request) {
        log.debug("goUpdateProfile");

        ModelAndView mv = new ModelAndView("member/updateProfile");
        HttpSession session = request.getSession(true); // 세션가져오기 (true : 세션 생성)
        MemberVO member = (MemberVO) session.getAttribute("member");
        MemberVO mvo = (MemberVO) session.getAttribute("mvo");

        List<TagVO> tvo = service.searchTag(member.getUno());
        session.setAttribute("tvo", tvo);

        if (mvo != null) {

            mv.addObject("mvo", mvo);
            mv.addObject("tvo", tvo);
            mv.addObject("tagList", mvo.getTagList());
            session.getAttribute("tvo");

            mv.setViewName("member/updateProfile");
        } else {
            mv.setViewName("/");
        }
        return mv;
    }

    //프로필 이미지 수정하기
    @PostMapping(value = "updateProfile.do")
    public ModelAndView openUpdateProfile(MemberVO vo, MultipartHttpServletRequest multi) throws Exception {
        log.debug("openUpdateProfile");

        ProfileAttachVO attachVO = profileUpload.requestSingleUpload(multi);
        //log.debug(vo.getUno());
        attachVO.setUno(vo.getUno());

        List<ProfileAttachVO> attachList = profileAttachService.selectFileName();
        deleteFiles(attachList);

        profileAttachService.insert(attachVO);

        ModelAndView mv = new ModelAndView("member/updateProfile");
        MemberVO mvo = service.readAllMember(vo.getUno());

        mv.addObject("mvo", mvo);
        mv.addObject("tagList", mvo.getTagList());

        return mv;
    }

    //프로필 닉네임 소개 수정
    @RequestMapping(value = "/goUpdateProfile.do", method = RequestMethod.POST)
    public String editProfile(MemberVO vo) throws Exception {
        log.info("editProfile: " + vo);

        service.editProfile(vo);

        return "redirect:openProfileList.do";
    }


    private void deleteFiles(List<ProfileAttachVO> attachList) {
        if (attachList == null || attachList.size() == 0) {
            return;
        }
        attachList.forEach(attachVO -> {
            try {
                Path file = Paths.get(attachVO.getFilePath() + "\\" + attachVO.getUuid() + "_" + attachVO.getFileName());

                Files.deleteIfExists(file);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        });
    }

    //존재하는 유저 태그 삽입 & 존재하지 않는 태그 삽입
    @PostMapping(value = "oldUserTag.do")
    @ResponseBody
    public ResponseEntity<TagVO> oldUserTag(@RequestBody TagVO tagVO) throws Exception {
        service.oldUserTag(tagVO);

       // log.info("getTags>>>" + tagVO.toString());
        return new ResponseEntity<TagVO>(tagVO, HttpStatus.OK);
    }

    @PostMapping(value = "newTag.do")
    @ResponseBody
    public ResponseEntity<TagVO> newTag(@RequestBody TagVO tagVO) {
        log.info("tagVO::" + tagVO);

        service.newTag(tagVO);

        //log.info("tagVO::" + tagVO);
        return new ResponseEntity<TagVO>(tagVO, HttpStatus.OK);
    }

    //유저태그삭제(임시)
    @PostMapping(value = "delUserTag.do")
    @ResponseBody
    public ResponseEntity<TagVO> delUserTag(@RequestBody TagVO tagVO) throws Exception {

        service.delUserTag(tagVO);

        //log.info("getTags>>>" + tagVO.toString());
        return new ResponseEntity<TagVO>(tagVO, HttpStatus.OK);
    }
    //리더태그
    @PostMapping(value = "leaderTag.do")
    @ResponseBody
    public ResponseEntity<TagVO> leaderTag(@RequestBody TagVO tagVO) throws Exception {
    	service.leaderTag(tagVO);
    	
    	log.info("leader>>>" + tagVO.toString());
    	
    	return new ResponseEntity<TagVO>(tagVO, HttpStatus.OK);
    }
}
