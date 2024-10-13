package com.management.controller;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class ExcelHeaderObject {
	
	 @GetMapping("/download")
	    public ResponseEntity<byte[]> downloadSampleExcel() {
	    	// Create a new workbook
	        try (Workbook workbook = new XSSFWorkbook()) {
	            // Create a new sheet
	            Sheet sheet = workbook.createSheet("Books");

	            // Create header row
	            Row headerRow = sheet.createRow(0);

	            // Create a cell style with centered alignment
	            CellStyle centeredStyle = workbook.createCellStyle();
	            centeredStyle.setAlignment(HorizontalAlignment.CENTER);
	            
	            // Merge cells for the object
	            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));

	            // Create a cell for the object
	            Cell objectCell = headerRow.createCell(0);
	            objectCell.setCellValue("Books Object");
	            objectCell.setCellStyle(centeredStyle);

	            // Create header row for book details
	            Row detailsHeaderRow = sheet.createRow(1);
	            String[] headers = {"ID", "Title", "Author", "ISBN", "Genre"};
	            for (int i = 0; i < headers.length; i++) {
	                Cell cell = detailsHeaderRow.createCell(i);
	                cell.setCellValue(headers[i]);
	            }

	            // Save the workbook to a file
	            try (FileOutputStream fileOut = new FileOutputStream("C:\\Users\\sk913\\Downloads\books_with_object4.xlsx")) {
	                workbook.write(fileOut);
	                System.out.println("Excel file with header object created successfully.");
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			return null;
	    }
/*    public static void main(String[] args) {
        // Create a new workbook
        try (Workbook workbook = new XSSFWorkbook()) {
            // Create a new sheet
            Sheet sheet = workbook.createSheet("Books");

            // Create header row
            Row headerRow = sheet.createRow(0);

            // Create a cell style with centered alignment
            CellStyle centeredStyle = workbook.createCellStyle();
            centeredStyle.setAlignment(HorizontalAlignment.CENTER);
            
            // Merge cells for the object
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));

            // Create a cell for the object
            Cell objectCell = headerRow.createCell(0);
            objectCell.setCellValue("Books Object");
            objectCell.setCellStyle(centeredStyle);

            // Create header row for book details
            Row detailsHeaderRow = sheet.createRow(1);
            String[] headers = {"ID", "Title", "Author", "ISBN", "Genre"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = detailsHeaderRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Save the workbook to a file
            try (FileOutputStream fileOut = new FileOutputStream("books_with_object.xlsx")) {
                workbook.write(fileOut);
                System.out.println("Excel file with header object created successfully.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    } */
}
