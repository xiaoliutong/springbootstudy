package controller;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ExcelWriteAndCommentController {

    public static void main(String[] args) throws Exception{
        Workbook wb = new XSSFWorkbook(); //or new HSSFWorkbook();


        CreationHelper factory = wb.getCreationHelper();


        Sheet sheet = wb.createSheet();

        Row row = sheet.createRow(3);


        Cell cell = row.createCell(5);


        cell.setCellValue("F4");


        Drawing drawing = sheet.createDrawingPatriarch();


// When the comment box is visible, have it show in a 1x3 space


        ClientAnchor anchor = factory.createClientAnchor();

        System.out.println("行数->"+cell.getColumnIndex());
        anchor.setCol1(cell.getColumnIndex());


        //anchor.setCol2(cell.getColumnIndex() + 1);


        anchor.setRow1(row.getRowNum());


        //anchor.setRow2(row.getRowNum() + 3);


// Create the comment and set the text+author


        Comment comment = drawing.createCellComment(anchor);


        RichTextString str = factory.createRichTextString("Hello, World!");


        comment.setString(str);


        comment.setAuthor("Apache POI");


// Assign the comment to the cell


        cell.setCellComment(comment);
        OutputStream outputStream = new
                FileOutputStream(new File("E:\\springbootstudy\\spring-boot-poi\\target\\comment.xlsx"));
        wb.write(outputStream);
    }

}
