package controller;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

//https://blog.csdn.net/qq_38593865/article/details/80888229 //poi小白教程
@Controller
public class ExcelWriteController {
    @RequestMapping("/getExcel")
    public void getExcel(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws Exception {



        //获得工作簿,这里做个说明XSSFWorkbook是支持xlsx的。HSSFWorkBook是支持xls的。
        //XSSFWorkbook的缺点是一次写出完可能会造成内存溢出，SXSSFWorkbook是为了解决内存溢出的，它会先写出临时文件
        //最后合并
        Workbook workbook = new XSSFWorkbook();
        //通过工作簿创建表
        Sheet sheet = workbook.createSheet();
        //通过工作簿创建单元格风格
        CellStyle cellStyle = workbook.createCellStyle();
        //设置单元格的填充背景
        cellStyle.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
        //设置单元格的填充模式
        cellStyle.setFillPattern((short) FillPatternType.THICK_HORZ_BANDS.ordinal());
        for (int i = 0; i < 10000; i++) {
            //循环创建第几行
            Row row = sheet.createRow(i);
            //创建第几行的第1列
            Cell cell = row.createCell(0);
            cell.setCellValue("a");
            //把样式设置给第一列的单元格
            cell.setCellStyle(cellStyle);
            //创建第几行的第2列并设置值为b
            row.createCell(1).setCellValue("b");
            //创建第几行的第3列并设置值为c
            row.createCell(2).setCellValue("c");
        }
        //通过响应获得输出流
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        //设置响应头，响应头的目的是告诉浏览器允许服务器写出，attachment为附件的意思，设置返回文件的名字
        httpServletResponse.setHeader("Content-disposition", "attachment;filename=user.xlsx");//默认Excel名称
        workbook.write(servletOutputStream);
        //记住要关闭流
        servletOutputStream.close();
        InputStream inputStream = httpServletRequest.getInputStream();
        inputStream.read();

    }
}
