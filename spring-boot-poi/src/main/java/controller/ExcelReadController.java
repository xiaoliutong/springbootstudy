package controller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;

public class ExcelReadController {
    public static void main(String[] args) throws Exception {
  /*      //创建工作簿
        Workbook workbook = new XSSFWorkbook();
        //通过工作簿创建表
        Sheet sheet = workbook.createSheet();
        for (int i = 0; i < 10000; i++) {
            //循环创建第几行
            Row row = sheet.createRow(i);
            //创建第几行的第1列
            Cell cell = row.createCell(0);
            cell.setCellValue("a");
            //创建第几行的第2列并设置值为b
            row.createCell(1).setCellValue("b");
            //创建第几行的第3列并设置值为c
            row.createCell(2).setCellValue("c");
        }
        OutputStream outputStream = new
                FileOutputStream("E:\\springbootstudy\\spring-boot-poi\\target\\testRead.xlsx");
        workbook.write(outputStream);*/

       //读取这个文件做为流对象
        InputStream inputStream =
                new FileInputStream("E:\\springbootstudy\\spring-boot-poi\\target\\testRead.xlsx");
        //将流放入工作簿中，工作簿会自动解析的
        Workbook workbook1 = new XSSFWorkbook(inputStream);
        //获得工作表的名字
        String sheet = workbook1.getSheetName(0);
        Row row = null;
        Cell cell = null;
        String cellValue = "";
        //根据工作表的名字来获得工作表并且得到行的迭代器
        Iterator<Row> rowIterator = workbook1.getSheet(sheet).rowIterator();
        //遍历获得每一行
        while (rowIterator.hasNext()) {
            //得到当前行
            row = rowIterator.next();
            //打印当前行数
            System.out.println(row.getRowNum());
            //通过当前行获得单元格的迭代器
            Iterator<Cell> cellIterator = row.cellIterator();
            //遍历单元格迭代器获得每一个单元格
            while (cellIterator.hasNext()) {
                //得到当前单元格
                cell = cellIterator.next();
                //打印当前单元格的值
                System.out.println(cell.getStringCellValue());
            }
        }
    }

}
