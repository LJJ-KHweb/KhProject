package com.kh.web.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

// FileRenamePolicy라는 인터페이스를 구현해서
// 이름 바꾸기 정책 만들어주기
public class MyRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File origin) {
		
		// 우리 입맛대로 바꾸기
		String originName = origin.getName();
		
		// 최대한 안곂치게
		// KH_년월일시분초_랜덤값 + 원본파일의 확장자
		
		String ext = originName.substring(originName.lastIndexOf("."));
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		int randomNum = (int)(Math.random() * 900) +100;
		
		String changeName = "KH_" + currentTime + "_" + randomNum + ext; 
		
		
		
		
		return new File(origin.getParent(),changeName);
	}

}
