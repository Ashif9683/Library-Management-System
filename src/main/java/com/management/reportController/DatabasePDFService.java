package com.management.reportController;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.management.entities.BookIssue;

public class DatabasePDFService {
	
	public static ByteArrayInputStream employeePDFReport(List<BookIssue> employees , String reportname) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
 
        try {
 
            PdfWriter.getInstance(document, out);
            document.open();
 
			/*
			 * // Add Cover Page Font coverFont =
			 * FontFactory.getFont(FontFactory.TIMES_BOLD, 36); Paragraph cover = new
			 * Paragraph("Employee Structure Report", coverFont);
			 * cover.setAlignment(Element.ALIGN_CENTER); document.add(cover);
			 * 
			 * document.newPage(); // Start a new page
			 */            
            
            
            // Add Content to PDF file ->
            Font fontHeader = FontFactory.getFont(FontFactory.TIMES_BOLD, 22);
            Paragraph para = new Paragraph("Nitra Library Management " + reportname, fontHeader);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);
            
            
 
            PdfPTable table = new PdfPTable(11);
            // Add PDF Table Header ->
            Stream.of("Roll Number", "Student Name", "Book Name", "Accession No.", "Semester", "Branch", "Fine" ,"Status" , "Due Book" ,"Book Bank", "Return Date").forEach(headerTitle -> {
                PdfPCell header = new PdfPCell();
                Font headFont = FontFactory.getFont(FontFactory.TIMES_BOLD);
                header.setBackgroundColor(Color.CYAN);
                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                header.setBorderWidth(2);
                header.setPhrase(new Phrase(headerTitle, headFont));
                table.addCell(header);
            });
 
            for (BookIssue employee : employees) {
                PdfPCell idCell = new PdfPCell(new Phrase(employee.getRollnumberid().toString()));
                idCell.setPaddingLeft(4);
                idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(idCell);
 
                PdfPCell firstNameCell = new PdfPCell(new Phrase(employee.getSelectStudent()));
                firstNameCell.setPaddingLeft(4);
                firstNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                firstNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(firstNameCell);
 
                PdfPCell lastNameCell = new PdfPCell(new Phrase(String.valueOf(employee.getSelectbook())));
                lastNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                lastNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                lastNameCell.setPaddingRight(4);
                table.addCell(lastNameCell);
 
                PdfPCell deptCell = new PdfPCell(new Phrase(String.valueOf(employee.getAccessionNumber())));
                deptCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                deptCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                deptCell.setPaddingRight(4);
                table.addCell(deptCell);
                
                PdfPCell semeCell = new PdfPCell(new Phrase(String.valueOf(employee.getSemester())));
                semeCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                semeCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                semeCell.setPaddingRight(4);
                table.addCell(semeCell);
                
                PdfPCell branchCell = new PdfPCell(new Phrase(String.valueOf(employee.getBranch())));
                branchCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                branchCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                branchCell.setPaddingRight(4);
                table.addCell(branchCell);
 
                PdfPCell phoneNumCell = new PdfPCell(new Phrase(String.valueOf(employee.getFine())));
                phoneNumCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                phoneNumCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                phoneNumCell.setPaddingRight(4);
                table.addCell(phoneNumCell);
                
                PdfPCell status = new PdfPCell(new Phrase(String.valueOf(employee.getStatus())));
                status.setVerticalAlignment(Element.ALIGN_MIDDLE);
                status.setHorizontalAlignment(Element.ALIGN_CENTER);
                status.setPaddingRight(4);
                table.addCell(status);
                
                PdfPCell duebook = new PdfPCell(new Phrase(String.valueOf(employee.getDuebook())));
                duebook.setVerticalAlignment(Element.ALIGN_MIDDLE);
                duebook.setHorizontalAlignment(Element.ALIGN_CENTER);
                duebook.setPaddingRight(4);
                table.addCell(duebook);
                
                PdfPCell bookbank = new PdfPCell(new Phrase(String.valueOf(employee.getBookBank())));
                bookbank.setVerticalAlignment(Element.ALIGN_MIDDLE);
                bookbank.setHorizontalAlignment(Element.ALIGN_CENTER);
                bookbank.setPaddingRight(4);
                table.addCell(bookbank);
                
                PdfPCell returndate = new PdfPCell(new Phrase(String.valueOf(employee.getReturnDate())));
                returndate.setVerticalAlignment(Element.ALIGN_MIDDLE);
                returndate.setHorizontalAlignment(Element.ALIGN_CENTER);
                returndate.setPaddingRight(4);
                table.addCell(returndate);
                
            }
            document.add(table);
 
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
 
        return new ByteArrayInputStream(out.toByteArray());
    }
	
}
