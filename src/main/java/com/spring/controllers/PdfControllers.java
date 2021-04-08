package com.spring.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.entity.Employee;
import com.spring.repository.EmployeeRepository;
import com.spring.services.EmployeeService;
import com.spring.services.ExcelService;

@Controller
public class PdfControllers {

	@Autowired
	private EmployeeRepository emprepo;

	@Autowired
	private ExcelService excelservice;

	@Autowired
	private EmployeeService service;
	@Autowired
	private ServletContext context;

	@GetMapping("/")
	public String homePage(Model model) {
		System.out.println("user triggred welcome page");
		Iterable<Employee> findAll = emprepo.findAll();

		model.addAttribute("empdata", findAll);
		return "welcome";
	}
	
	
	@GetMapping("/pdf")
	public String pdfprint(Model model) {
		System.out.println("user triggred welcome page");
		Iterable<Employee> findAll = emprepo.findAll();

		model.addAttribute("empdata", findAll);
		return "pdfexport";
	}

	@GetMapping("//createPdf")
	public void createPdf(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("user triggred welcome page");
		Iterable<Employee> findAll = emprepo.findAll();

		boolean isFlag = service.CreatePdf(request, response, findAll, context);

		if (isFlag) {
			String fullPath = request.getServletContext().getRealPath("/resources/reports/" + "employees" + ".pdf");
			filedownload(fullPath, response, "employees.pdf");
		}

	}

	@GetMapping("/createExcel")
	public void createExcel(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("req for create Excel");

		Iterable<Employee> findAll = emprepo.findAll();

		boolean isFlag = excelservice.excelCreate(request, response, context, findAll);
		if (isFlag) {
			System.out.println("downloading file");
			String fullPath = request.getServletContext().getRealPath("/resources/reports/" + "employess" + ".xls");
			filedownload(fullPath, response, "employess.xls");

		}

	}

	private void filedownload(String fullPath, HttpServletResponse response, String string) {

		File file = new File(fullPath);

		final int Buffer_Size = 4096;

		if (file.exists()) {
			try {

				FileInputStream fis = new FileInputStream(file);
				String mimeType = context.getMimeType(fullPath);
				response.setContentType(mimeType);
				response.setHeader("content-disposition", "attachment; filename=" + fullPath);

				OutputStream outstream = response.getOutputStream();
				byte[] buffer = new byte[Buffer_Size];

				int byte_Read = -1;
				while ((byte_Read = fis.read(buffer)) != -1) {

					outstream.write(buffer, 0, byte_Read);
				}

				fis.close();
				outstream.close();
				file.delete();

			} catch (Exception e) {
				System.out.println("downloading for catch block");
			}

		}

	}

}
