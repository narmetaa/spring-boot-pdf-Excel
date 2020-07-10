package com.spring.services;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.stereotype.Service;

import com.spring.entity.Employee;

@Service
public class ExcelService {

	public boolean excelCreate(HttpServletRequest request, HttpServletResponse response, ServletContext context,
			Iterable<Employee> findAll) {

		
		System.err.println("starting for excel");
		String filepath = context.getRealPath("/resources/reports");
		File file = new File(filepath);
		boolean exits = new File(filepath).exists();
		if (!exits) {
			new File(filepath).mkdirs();
		}

		try {

			FileOutputStream outputstream = new FileOutputStream(file + "/" + "employess" + ".xls");
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet worksheet = workbook.createSheet("EMPLOYEES");
			worksheet.setDefaultColumnWidth(30);

			HSSFCellStyle headercellstyle = workbook.createCellStyle();
			headercellstyle.setFillForegroundColor(HSSFColor.BLUE.index);
			headercellstyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			HSSFRow headerRow = worksheet.createRow(0);

			HSSFCell id = headerRow.createCell(0);
			id.setCellValue("ID");
			id.setCellStyle(headercellstyle);

			HSSFCell designation = headerRow.createCell(1);
			designation.setCellValue("DESIGNATION");
			designation.setCellStyle(headercellstyle);

			HSSFCell email = headerRow.createCell(2);
			email.setCellValue("EMAIL");
			email.setCellStyle(headercellstyle);

			HSSFCell name = headerRow.createCell(3);
			name.setCellValue("NAME");
			name.setCellStyle(headercellstyle);

			HSSFCell salary = headerRow.createCell(4);
			salary.setCellValue("SALARY");
			salary.setCellStyle(headercellstyle);

			int i = 1;

			for (Employee e : findAll) {

				HSSFRow bodyRow = worksheet.createRow(i);

				HSSFCellStyle bodycellStyle = workbook.createCellStyle();
				bodycellStyle.setFillForegroundColor(HSSFColor.WHITE.index);

				HSSFCell idValue = bodyRow.createCell(0);
				idValue.setCellValue(e.getId());
				idValue.setCellStyle(bodycellStyle);

				HSSFCell desigValue = bodyRow.createCell(1);
				desigValue.setCellValue(e.getDesignation());
				desigValue.setCellStyle(bodycellStyle);

				HSSFCell emailValue = bodyRow.createCell(2);
				emailValue.setCellValue(e.getEmail());
				emailValue.setCellStyle(bodycellStyle);

				HSSFCell nameValue = bodyRow.createCell(3);
				nameValue.setCellValue(e.getName());
				nameValue.setCellStyle(bodycellStyle);

				HSSFCell salValue = bodyRow.createCell(4);
				salValue.setCellValue(e.getSalary());
				salValue.setCellStyle(bodycellStyle);
				i++;

			}

			workbook.write(outputstream);
			outputstream.flush();
			outputstream.close();

		} catch (Exception e) {

			System.out.println("work book generation Failed ");
			return false;
		}

		System.err.println("Ended for excel");
		return true;
	}

}
