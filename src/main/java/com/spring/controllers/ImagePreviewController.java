package com.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ImagePreviewController {

	
	
	
	
	
	
	public ImagePreviewController(String[] java) {
		super();
		this.java = java;
	}








	public String java[];
	
	
	
	
	



	public String[] getJava() {
		return java;
	}








	public void setJava(String[] java) {
		this.java = java;
	}








	@RequestMapping("/getpreview")
	public String imagepreview() {

		
		
		try {
		int a=5, b=11;
		b=0;
		a=5/b;
		}finally {
			
		}
		return null;
		
		
	}

}
