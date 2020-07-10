package com.spring.services;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.spring.entity.Employee;
import com.spring.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository emprepo;

	ArrayList<Employee> data = new ArrayList<>();

	@PostConstruct
	public ArrayList<Employee> employeeData() {

		Employee emp = new Employee("ashok", "ashok@gmail.com", "50k", "developer");
		Employee emp2 = new Employee("Raju", "raju@gmail.com", "50k", "tester");
		Employee emp3 = new Employee("Roja", "Roja12@gmail.com", "80K", "TeamLead");
		Employee emp4 = new Employee("Savitri", "Savitri@gmail.com", "50L", "Maneger");
		Employee emp5 = new Employee("JhonCena", "JohnCena@gmail.com", "50L", "Maneger");

		data.add(emp);
		data.add(emp2);
		data.add(emp3);
		data.add(emp4);
		data.add(emp5);
		emprepo.saveAll(data);
		return data;

	}

	public boolean CreatePdf(HttpServletRequest request, HttpServletResponse response, Iterable<Employee> findAll,
			ServletContext context) {

		Document document = new Document(PageSize.A4, 15, 15, 45, 30);
		try {

			String filepath = context.getRealPath("/resources/reports");
			File file = new File(filepath);
			boolean exits = new File(filepath).exists();
			if (!exits) {
				new File(filepath).mkdirs();
			}

			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file + "/" + "employees" + ".pdf"));
			document.open();

			Font mainfont = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
			Paragraph paragraph = new Paragraph("All Employess", mainfont);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.setIndentationLeft(50);
			paragraph.setIndentationRight(50);
			paragraph.setSpacingAfter(10);
			document.add(paragraph);

			PdfPTable table = new PdfPTable(5);
			table.setWidthPercentage(100);
			table.setSpacingBefore(10f);
			table.setSpacingAfter(10f);

			Font tableHeader = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
			Font tableBody = FontFactory.getFont("Arial", 10, BaseColor.BLACK);

			float[] columWidth = { 2f, 2f, 2f, 2f,2f };
			table.setWidths(columWidth);

			PdfPCell id = new PdfPCell(new Paragraph("ID", tableHeader));
			id.setBorderColor(BaseColor.BLACK);
			id.setPaddingLeft(10);
			id.setHorizontalAlignment(Element.ALIGN_CENTER);
			id.setVerticalAlignment(Element.ALIGN_CENTER);
			id.setBackgroundColor(BaseColor.GRAY);
			id.setExtraParagraphSpace(5f);
			table.addCell(id);

			PdfPCell designation = new PdfPCell(new Paragraph("DESIGNATION", tableHeader));
			designation.setBorderColor(BaseColor.BLACK);
			designation.setPaddingLeft(10);
			designation.setHorizontalAlignment(Element.ALIGN_CENTER);
			designation.setVerticalAlignment(Element.ALIGN_CENTER);
			designation.setBackgroundColor(BaseColor.GRAY);
			designation.setExtraParagraphSpace(5f);
			table.addCell(designation);

			PdfPCell email = new PdfPCell(new Paragraph("EMAIL", tableHeader));
			email.setBorderColor(BaseColor.BLACK);
			email.setPaddingLeft(10);
			email.setHorizontalAlignment(Element.ALIGN_CENTER);
			email.setVerticalAlignment(Element.ALIGN_CENTER);
			email.setBackgroundColor(BaseColor.GRAY);
			email.setExtraParagraphSpace(5f);
			table.addCell(email);

			PdfPCell name = new PdfPCell(new Paragraph("NAME", tableHeader));
			name.setBorderColor(BaseColor.BLACK);
			name.setPaddingLeft(10);
			name.setHorizontalAlignment(Element.ALIGN_CENTER);
			name.setVerticalAlignment(Element.ALIGN_CENTER);
			name.setBackgroundColor(BaseColor.GRAY);
			name.setExtraParagraphSpace(5f);
			table.addCell(name);

			PdfPCell salary = new PdfPCell(new Paragraph("SALARY", tableHeader));
			salary.setBorderColor(BaseColor.BLACK);
			salary.setPaddingLeft(10);
			salary.setHorizontalAlignment(Element.ALIGN_CENTER);
			salary.setVerticalAlignment(Element.ALIGN_CENTER);
			salary.setBackgroundColor(BaseColor.GRAY);
			salary.setExtraParagraphSpace(5f);
			table.addCell(salary);

			for (Employee e : findAll) {

				System.out.println(e.getDesignation() +"--"+e.getEmail());
				PdfPCell idValue = new PdfPCell(new Paragraph(String.valueOf(e.getId()), tableBody));
				idValue.setBackgroundColor(BaseColor.BLACK);
				idValue.setPaddingLeft(10);
				idValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				idValue.setVerticalAlignment(Element.ALIGN_CENTER);
				idValue.setBackgroundColor(BaseColor.GRAY);
				idValue.setExtraParagraphSpace(5f);
				table.addCell(idValue);

				PdfPCell designationValue = new PdfPCell(new Paragraph(e.getDesignation(), tableBody));
				designationValue.setBackgroundColor(BaseColor.BLACK);
				designationValue.setPaddingLeft(10);
				designationValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				designationValue.setVerticalAlignment(Element.ALIGN_CENTER);
				designationValue.setBackgroundColor(BaseColor.GRAY);
				designationValue.setExtraParagraphSpace(5f);
				table.addCell(designationValue);

				PdfPCell emailValue = new PdfPCell(new Paragraph(e.getEmail(), tableBody));
				emailValue.setBackgroundColor(BaseColor.BLACK);
				emailValue.setPaddingLeft(10);
				emailValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				emailValue.setVerticalAlignment(Element.ALIGN_CENTER);
				emailValue.setBackgroundColor(BaseColor.GRAY);
				emailValue.setExtraParagraphSpace(5f);
				table.addCell(emailValue);

				PdfPCell nameValue = new PdfPCell(new Paragraph(e.getName(), tableBody));
				nameValue.setBackgroundColor(BaseColor.BLACK);
				nameValue.setPaddingLeft(10);
				nameValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				nameValue.setVerticalAlignment(Element.ALIGN_CENTER);
				nameValue.setBackgroundColor(BaseColor.GRAY);
				nameValue.setExtraParagraphSpace(5f);
				table.addCell(nameValue);

				PdfPCell salValue = new PdfPCell(new Paragraph(e.getSalary(), tableBody));
				salValue.setBackgroundColor(BaseColor.BLACK);
				salValue.setPaddingLeft(10);
				salValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				salValue.setVerticalAlignment(Element.ALIGN_CENTER);
				salValue.setBackgroundColor(BaseColor.GRAY);
				salValue.setExtraParagraphSpace(5f);
				table.addCell(salValue);

			}

			document.add(table);
			document.close();
			writer.close();

		} catch (Exception e) {

			System.out.println("pdf executing failed");
			e.printStackTrace();
			return false;

		}
		return true;
	}
}
