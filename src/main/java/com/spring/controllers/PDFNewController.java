package com.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PDFNewController {

	
	
	
	
	
	
	@GetMapping("/export")
	public String newpdf() {
		System.out.println("printing pdf");
		
		
		return "pdf generated succssfully ..."; 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
