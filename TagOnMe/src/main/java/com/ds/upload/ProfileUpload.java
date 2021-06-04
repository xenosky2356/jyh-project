package com.ds.upload;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ds.domain.ProfileAttachVO;

public class ProfileUpload {
	
	public ProfileAttachVO requestSingleUpload(MultipartHttpServletRequest mtfRequest) {
		ProfileAttachVO attachVO = new ProfileAttachVO();
		MultipartFile mf = mtfRequest.getFile("file");
		String path = "C:\\profile-image";
		File uploadPath = new File(path);
		if(uploadPath.exists()==false) uploadPath.mkdir();
		
		String originFileName = mf.getOriginalFilename();
		originFileName = originFileName.substring(originFileName.lastIndexOf("\\")+1);
		
		attachVO.setFileName(originFileName);
		UUID uuid = UUID.randomUUID();
		originFileName = uuid.toString() + "_" + originFileName;
		
		try {
			mf.transferTo(new File(uploadPath, originFileName));
		} catch(IllegalStateException | IOException e1) {
			e1.printStackTrace();
		}
		attachVO.setUuid(uuid.toString());
		attachVO.setFilePath(path);
		
		String safeFile = path + System.currentTimeMillis() + originFileName;
		
		try {
			mf.transferTo(new File(safeFile));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return attachVO;
	}
}
