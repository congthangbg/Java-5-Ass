package com.vn.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ParamService {
	
	@Autowired
	HttpServletRequest request;
	
	//Đọc chuỗi giá trị tham số
	public String getString(String name,String defaultValue) {
		String value=request.getParameter(name);
		return value != null ? value : defaultValue;
	}
	//Đọc số nguyên giá trị của tham số
	public int getInt(String name,int defaultValue) {
		String value=getString(name, String.valueOf(defaultValue));
		return Integer.parseInt(value);
	}
	//Đọc số thực  giá trị của tham số
	public double getDouble(String name,double defaultValue) {
		String value=getString(name, String.valueOf(defaultValue));
		return Double.parseDouble(value);
	}
	//Đọc giá trị boolean của tham số
	public boolean getBoolean(String name,boolean defaultValue) {
		String value=getString(name, String.valueOf(defaultValue));
		return Boolean.parseBoolean(value);
	}
	
	public Date getDate(String name,String pattern) {
		String value=getString(name, "");
		try {
			return new SimpleDateFormat(pattern).parse(value);
		} catch (Exception e) {
			throw new RuntimeException(e);
			// TODO: handle exception
		}
	}
	//Lưu file upload
	public File save(MultipartFile mFile,String path) {
		if(!mFile.isEmpty()) {
			File dir=new File(request.getServletContext().getRealPath(path));
			if(!dir.exists()) {
				dir.mkdir();
			}
			try {
				File saveFile=new File(dir,mFile.getOriginalFilename());
				mFile.transferTo(saveFile);
				return saveFile;
				
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}
}
