package controller;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
//https://blog.csdn.net/qq_38593865/article/details/80888229 //poi小白教程
@Controller
public class ExcelController {
    @RequestMapping("/getExcel")
    public void getExcel(HttpServletResponse httpServletResponse) throws Exception{
       ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        //Row row = sheet.createRow(0);
        workbook.createFont();
        byte a= Byte.MAX_VALUE;
        CellStyle cellStyle =  workbook.createCellStyle();
        cellStyle.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
        cellStyle.setFillPattern((short) 4);
        for (int i = 0; i <10000 ; i++) {
            Row row = sheet.createRow(i);
            Cell cell = row.createCell(0);
            cell.setCellValue("a");
            cell.setCellStyle(cellStyle);
            row.createCell(1).setCellValue("b");
            row.createCell(2).setCellValue("c");
        }
        httpServletResponse.setHeader("Content-disposition", "attachment;filename=user.xlsx");//默认Excel名称
        workbook.write(servletOutputStream);
        //记住要关闭流
        servletOutputStream.close();

    }
}
