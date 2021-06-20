package com.vn.Export;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.vn.entity.OrderDetail;


public class ExcelHelper {
	 public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	  static String[] HEADERs = { "Id", "Name", "Số lượng", "Đơn giá","Thành tiền" };
	  static String SHEET = "Tutorials";

	  public static ByteArrayInputStream tutorialsToExcel(List<OrderDetail> listEntity) {
		  
	    try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
	      Sheet sheet = workbook.createSheet(SHEET);

	      // Header
	      Row headerRow = sheet.createRow(0);

	      for (int col = 0; col < HEADERs.length; col++) {
	        Cell cell = headerRow.createCell(col);
	        cell.setCellValue(HEADERs[col]);
	      }

	      int rowIdx =1;
	      for (OrderDetail tutorial : listEntity) {
	        Row row = sheet.createRow(rowIdx);
	        System.out.println(tutorial.toString());
	        row.createCell(0).setCellValue(tutorial.getId());
	        row.createCell(1).setCellValue(tutorial.getName());
	        row.createCell(2).setCellValue(tutorial.getQuantity());
	        row.createCell(3).setCellValue(tutorial.getPrice());
	        row.createCell(4).setCellValue(tutorial.getQuantity() * tutorial.getPrice());
	        rowIdx++;
	      }

	      
	      workbook.write(out);
	      return new ByteArrayInputStream(out.toByteArray());
	    } catch (IOException e) {
	      throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
	    }
	  }

}
