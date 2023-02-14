package com.douzone.mysite.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.exception.FileuploadServiceException;

@Service
@PropertySource("classpath:com/douzone/mysite/web/fileupload.properties")
public class FileUploadService {
	
	@Autowired
	private Environment env;
	
	public String restore(MultipartFile file) {
		String url = null;
		
		try {
			File uploadDirectory = new File(env.getProperty("fileupload.uploadLocation"));
			if(!uploadDirectory.exists()) {
				uploadDirectory.mkdirs();
			}
			
			if(file.isEmpty()) {
				return url;
			}
			
			String originfilename = file.getOriginalFilename();
			String extName = originfilename.substring(originfilename.lastIndexOf(".")+1);
			String saveFilename = generateSaveFilename(extName);
			Long filesize = file.getSize();
			
			
			System.out.println("#############" + originfilename);
			System.out.println("#############" + filesize);
			System.out.println("#############" + saveFilename);
			
			byte[] data = file.getBytes();
			OutputStream os = new FileOutputStream(env.getProperty("fileupload.uploadLocation") + "/" + saveFilename);
			os.write(data);
			os.close();
			
			url = env.getProperty("fileupload.resourceUrl") + "/" + saveFilename;
		} catch(IOException ex) {
			throw new FileuploadServiceException(ex.toString());
		}
		
		return url;
	}

	private String generateSaveFilename(String extName) {
		String filename = "";
		
		Calendar calenda = Calendar.getInstance();
		filename += calenda.get(Calendar.YEAR);
		filename += calenda.get(Calendar.MONTH);
		filename += calenda.get(Calendar.DATE);
		filename += calenda.get(Calendar.HOUR);
		filename += calenda.get(Calendar.MINUTE);
		filename += calenda.get(Calendar.MILLISECOND);
		filename += ("." + extName);
		
		return filename;
	}

}
