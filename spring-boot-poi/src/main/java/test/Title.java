package test;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @Description: java类作用描述
 * @Author: LiuZhuangZhuang
 * @CreateDate: 2020/9/8 9:48
 * @UpdateUser: LiuZhuangZhuang
 * @UpdateDate: 2020/9/8 9:48
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class Title {
    public static void main(String[] args) throws Exception {
        /**/

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        sheet.setColumnWidth(1,300);
        Font contentFont1 = workbook.createFont();
        CellStyle contentStyle1 = workbook.createCellStyle();
        Font contentFont2 = workbook.createFont();
        CellStyle contentStyle2 = workbook.createCellStyle();
        Font titleFont = workbook.createFont();
        CellStyle titleStyle = workbook.createCellStyle();
        //合并第一行和第二行的单元格
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 13));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 13));

        //创建第一行的样式
        //设置居中
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        /*style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cell = row4.createCell((short) 2);
        cell.setCellValue("X17");
        cell.setCellStyle(style);*/
        //设置填充背景颜色和格式 参考:https://blog.csdn.net/seven_tao/article/details/25112389?locationNum=3
        titleStyle.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        titleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        //设置标题的样式
        //粗体
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //设置字体
        titleFont.setFontName(HSSFFont.FONT_ARIAL);
        //没有下划线
        titleFont.setUnderline(FontUnderline.NONE.getByteValue());
        //字体颜色
        titleFont.setColor(HSSFColor.BLACK.index);
        //字体字号
        titleFont.setFontHeightInPoints((short) 17);
        //设置到样式中
        titleStyle.setFont(titleFont);

        //设置内容第一行的样式,需要加粗,加背景
        contentStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        contentStyle1.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
        contentStyle1.setFillPattern(CellStyle.SOLID_FOREGROUND);
        //设置字体
        contentFont1.setFontName("Times New Roman");
        contentFont1.setFontHeightInPoints((short) 12);
        contentFont1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
         contentStyle1.setFont(contentFont1);
        //设置正文内容背景和居中
        contentStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
      /*  contentStyle2.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
        contentStyle2.setFillPattern(CellStyle.SOLID_FOREGROUND);*/
        //设置正文内容字体
        contentFont2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        contentFont2.setFontHeightInPoints((short) 12);
        contentFont2.setFontName("Times New Roman");
        contentStyle2.setFont(contentFont2);
        //创建标题单元格
        Row titleRow = sheet.createRow(0);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("综合查询");
        titleCell.setCellStyle(titleStyle);
        Cell contentCell1 = sheet.createRow(1).createCell(0);
        contentCell1.setCellStyle(contentStyle1);
        contentCell1.setCellValue("开始时间 2020-08-06");
        String[] contentValues =
                {"开始时间", "开s时间", "开时间", "始时间",
                        "时间", "开5时间", "开6时间", "开7时间",
                        "开8时间", "开9时间"};
       Row row =  sheet.createRow( 2);
        for (int i = 0; i < 10; i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(contentStyle2);
            cell.setCellValue(contentValues[i]);
            cell.setCellStyle(contentStyle2);
        }
        OutputStream outputStream = new
                FileOutputStream(new File("E:\\springbootstudy\\spring-boot-poi\\target\\style.xlsx"));
        workbook.write(outputStream);
/*
* POI 设置字体内容和位置，以下是实例：

HSSFFont f  = wb.createFont();
f.setFontHeightInPoints((short) 11);//字号
f.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);//加粗

HSSFCellStyle style = wb.createCellStyle();
style.setFont(f);
style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//左右居中
style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中
style.setRotation(short rotation);//单元格内容的旋转的角度

HSSFDataFormat df = wb.createDataFormat();
style1.setDataFormat(df.getFormat("0.00%"));//设置单元格数据格式
cell.setCellFormula(string);//给单元格设公式
style.setRotation(short rotation);//单元格内容的旋转的角度
* */
/*//设置内容第一行的样式,需要加粗,加背景
        WritableFont font1 = new WritableFont(WritableFont.TIMES, 12,
                WritableFont.BOLD);//设置内容字体大小及样式
        WritableCellFormat fmt1 = new WritableCellFormat(font1);
        fmt1.setBackground(Colour.PALE_BLUE);// 设置单元格的背景颜色
        fmt1.setAlignment(Alignment.CENTRE);//设置内容居中
        //设置余下内容的样式
        WritableFont font2 = new WritableFont(WritableFont.TIMES, 12,
                WritableFont.NO_BOLD);//设置内容字体大小及样式
        WritableCellFormat fmt2 = new WritableCellFormat(font2);
        fmt2.setAlignment(Alignment.CENTRE);//设置内容居中*/

        //cellView.setAutosize(true); //设置自动大小
        //        WritableFont wf = new WritableFont(WritableFont.ARIAL, 16,
        //                WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
        //                Colour.BLACK);//设置标题字体大小及样式
        //        WritableCellFormat wcf = new WritableCellFormat(wf);
        //        wcf.setAlignment(Alignment.CENTRE);//设置标题居中
        //        wcf.setBackground(Colour.GRAY_25);
      /*  sheet.se
        sheet.mergeCells(0, 0, 13, 0); //设置标题的列合并
        sheet.mergeCells(0, 1, 13, 1); //设置标题的列合并*/
        /*int col1, int row1, int col2, int row2*/
        /*
        * sheet.addMergedRegion(new CellRangeAddress(


        1, //first row (0-based)


        1, //last row  (0-based)


        1, //first column (0-based)


        2  //last column  (0-based)


));*/


    }
}
