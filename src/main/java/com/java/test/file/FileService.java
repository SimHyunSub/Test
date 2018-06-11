package com.java.test.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service
public class FileService implements FileServiceInterface {

	@Override
	public HashMap<String, Object> fileUpload(MultipartFile[] files, String menu) { //여기서 files는 input에서 넘겨준 파일들.
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();

		for(int i=0; i<files.length; i ++) {
			
			HashMap<String, Object> fileMap = new HashMap<String, Object>();
			String fileNm = files[i].getOriginalFilename(); //파일의 이름을 보여준다. 사람이 알아볼수 잇게 함.
			try {
				byte[] bytes = files[i].getBytes(); //getbytes() 를 사용하면 이상하게 나와있는것을 바이트화 시킨다. 바이트화는 기계가 알아볼수있게 만들어주는 과정.
				
				String path = "D:/eclipse-jee-oxygen-3a-win32-x86_64/workspace/Test/src/main/webapp/resources/upload/" + menu + "/"; //local용
//				String path = req.getSession().getServletContext().getRealPath("/") // srever에 올릴때  
				
				File dir = new File(path);
				
				if(!dir.exists()) {
					dir.mkdirs(); //s가 붙으면 없으면 폴더가없더래도 다 만들어준다.
				}
					
					File f = new File(path + fileNm);
					OutputStream out = new FileOutputStream(f); //파이썬으로 치면 open
					out.write(bytes);
					out.close(); 					//이순간 업로드한 파일이 서버에 저장된다.
					
					fileMap.put("fileName", fileNm);
					fileMap.put("filePath",path);
					fileMap.put("fileUrl","/test/resources/upload/" + menu + "/" + fileNm);
					list.add(fileMap);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		map.put("upload", list);
		
		return map;
	}

}
