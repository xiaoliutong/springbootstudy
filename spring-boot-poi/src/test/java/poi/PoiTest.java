package poi;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.jam.internal.elements.CommentImpl;
import org.junit.Test;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class PoiTest {

    @Test
    public void test03() throws Exception{
        String path = "E:\\springbootstudy\\spring-boot-poi\\target\\test.xlsx";
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        //Row row = sheet.createRow(0);
       workbook.createFont();
      byte a= Byte.MAX_VALUE;
        CellStyle cellStyle =  workbook.createCellStyle();
        cellStyle.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
        cellStyle.setFillPattern((short) 4);
        for (int i = 0; i <10000 ; i++) {
            Row  row = sheet.createRow(i);
            Cell cell = row.createCell(0);
            cell.setCellValue("a");
            cell.setCellStyle(cellStyle);
            row.createCell(1).setCellValue("b");
            row.createCell(2).setCellValue("c");
        }

        workbook.write(new FileOutputStream(new File(path)));
    }
}
