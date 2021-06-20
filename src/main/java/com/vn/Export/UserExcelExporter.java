package com.vn.Export;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import com.vn.entity.Account;
import com.vn.entity.Order;
import com.vn.entity.OrderDetail;
import com.vn.service.ShopCartService;
import com.vn.service.impl.ShopCartServiceImpl;

public class UserExcelExporter {
	@Autowired 
	ShopCartService shopCartServiceImpl;
	
	@Autowired
	HttpSession session;
	
	  private XSSFWorkbook workbook;
	    private XSSFSheet sheet;
	    private List<OrderDetail> orderDetails;
	    private String total;
	    private Account account;
	    private Order order;
	    static String[] HEADERs = { "Id", "Name", "Số lượng", "Đơn giá","Thành tiền" };
	     
	    public UserExcelExporter(List<OrderDetail> orderDetails,String total,Account account,Order order) {
	        this.orderDetails = orderDetails;
	        this.total = total;
	        this.account= account;
	        this.order = order;
	        workbook = new XSSFWorkbook();
	    }
	 
	 
	    private void writeHeaderLine() {
	    	CellStyle style = workbook.createCellStyle();
	        XSSFFont font = workbook.createFont();
	        font.setBold(true);
	        font.setFontHeight(16);
	        font.setFontName("Times New Roman");
	        style.setFont(font);
	        
	        //style2
	        CellStyle style2 = workbook.createCellStyle();
	        XSSFFont font2 = workbook.createFont();
	        font2.setBold(false);
	        font2.setFontHeight(16);
	        font2.setFontName("Times New Roman");
	        style2.setFont(font2);
	        //--------------------
	        
	        sheet = workbook.createSheet("Users");

	        Row row00 = sheet.createRow(0);
	        createCell(row00, 2, "Hóa Đơn Thanh Toán", style);      
	        sheet.autoSizeColumn(0);

	       
	        Row row0 = sheet.createRow(2);
	        createCell(row0, 0, "Account", style);      
	        sheet.autoSizeColumn(0);
	        createCell(row0, 1,account.getEmail(), style2);      
	        sheet.autoSizeColumn(1);
	        
	        Row row2 = sheet.createRow(4);
	        createCell(row2, 0,"Address", style);      
	        sheet.autoSizeColumn(0);
	        createCell(row2, 1,order.getAddress(), style2);      
	        sheet.autoSizeColumn(1);
	        
	        Row row4 = sheet.createRow(6);
	        createCell(row4, 0,"Total", style);      
	        sheet.autoSizeColumn(0);
	        createCell(row4, 1,total, style2);      
	        sheet.autoSizeColumn(1);
	        
	        Row row = sheet.createRow(8);
	        
	        createCell(row, 0, "ID", style);      
	        sheet.autoSizeColumn(0);
	        createCell(row, 1, "Name", style);    
	        sheet.autoSizeColumn(1);
	        createCell(row, 2, "Số lượng", style);  
	        sheet.autoSizeColumn(2);
	        createCell(row, 3, "Đơn giá", style);
	        sheet.autoSizeColumn(3);
	        createCell(row, 4, "Thành tiền", style);
	        sheet.autoSizeColumn(4);
	    }
	     
	    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
	        sheet.autoSizeColumn(columnCount);
	        Cell cell = row.createCell(columnCount);
	        if (value instanceof Integer) {
	            cell.setCellValue((Integer) value);
	        } else if (value instanceof Boolean) {
	            cell.setCellValue((Boolean) value);
	        }else {
	            cell.setCellValue((String) value);
	        }
	        cell.setCellStyle(style);
	    }
	     
	    private void writeDataLines() {
	        int rowCount = 9;
	 
	        CellStyle style = workbook.createCellStyle();
	        XSSFFont font = workbook.createFont();
	        font.setFontHeight(14);
	        font.setFontName("Times New Roman");
	        style.setFont(font);
	       
	                 
	        for (OrderDetail user : orderDetails) {
	            Row row = sheet.createRow(rowCount++);
	            int columnCount = 0;
	             
	            createCell(row, columnCount++, user.getId(), style);
	            createCell(row, columnCount++, user.getName(), style);
	            createCell(row, columnCount++, user.getQuantity(), style);
	            createCell(row, columnCount++, user.getPrice().toString(), style);
	            createCell(row, columnCount++, (user.getQuantity() * user.getPrice())+"", style);
	             
	        }
	        
	    }
	     
	    public void export(HttpServletResponse response) throws IOException {
	        writeHeaderLine();
	        writeDataLines();
	         
	        ServletOutputStream outputStream = response.getOutputStream();
	        workbook.write(outputStream);
	        workbook.close();
	         
	        outputStream.close();
	         
	    }
}
