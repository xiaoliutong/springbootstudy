/*
package test;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

public class TzExcel {
    public static void main(String[] args) {
        String titleUTF= URLEncoder.encode(title,"UTF8");
        File file = new File(folder, titleUTF  + ".xls");

        WritableWorkbook book = Workbook.createWorkbook(file);
        WritableSheet sheet = book.createSheet("Sheet1", 0);
        sheet.mergeCells(0, 0, 13, 0); //设置标题的列合并
        sheet.mergeCells(0, 1, 13, 1); //设置标题的列合并
        sheet.setRowView(1, 300); //设置行宽度
        CellView cellView = new CellView();
        cellView.setAutosize(true); //设置自动大小
        WritableFont wf = new WritableFont(WritableFont.ARIAL, 16,
                WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
                Colour.BLACK);//设置标题字体大小及样式
        WritableCellFormat wcf = new WritableCellFormat(wf);
        wcf.setAlignment(Alignment.CENTRE);//设置标题居中
        wcf.setBackground(Colour.GRAY_25);
        //设置内容第一行的样式,需要加粗,加背景
        WritableFont font1 = new WritableFont(WritableFont.TIMES, 12,
                WritableFont.BOLD);//设置内容字体大小及样式
        WritableCellFormat fmt1 = new WritableCellFormat(font1);
        fmt1.setBackground(Colour.PALE_BLUE);// 设置单元格的背景颜色
        fmt1.setAlignment(Alignment.CENTRE);//设置内容居中
        //设置余下内容的样式
        WritableFont font2 = new WritableFont(WritableFont.TIMES, 12,
                WritableFont.NO_BOLD);//设置内容字体大小及样式
        WritableCellFormat fmt2 = new WritableCellFormat(font2);
        fmt2.setAlignment(Alignment.CENTRE);//设置内容居中
        //标题
        sheet.addCell(new Label(0, 0, title, wcf));
        sheet.addCell(new Label(0, 1, date, fmt1));
        sheet.addCell(new Label(0, 2, "序号", fmt1));
        sheet.addCell(new Label(1, 2, "省信访件编号", fmt1));
        sheet.addCell(new Label(2, 2, "市受理编号", fmt1));
        sheet.addCell(new Label(3, 2, "区县受理编号", fmt1));
        sheet.addCell(new Label(4, 2, "反映日期", fmt1));
        sheet.addCell(new Label(5, 2, "姓名", fmt1));
        sheet.addCell(new Label(6, 2, "来电号码", fmt1));
        sheet.addCell(new Label(7, 2, "联系电话", fmt1));
        sheet.addCell(new Label(8, 2, "发生地", fmt1));
        sheet.addCell(new Label(9, 2, "反映内容", fmt1));
        sheet.addCell(new Label(10, 2, "内容分类", fmt1));
        sheet.addCell(new Label(11, 2, "紧急程度", fmt1));
        sheet.addCell(new Label(12, 2, "杭州处理人", fmt1));
        sheet.addCell(new Label(13, 2, "上导区县", fmt1));

        List<SlcxVO> list = (List) pagination.getPageObj();
        int i = 0;
        for (SlcxVO slvo : list) {
            sheet.addCell(new Label(0, i + 3, Integer.toString(i + 1), fmt2));
            sheet.setColumnView(1, 24);
            sheet.addCell(new Label(1, i + 3, slvo.getSxfjbh(), fmt2));
            sheet.setColumnView(2, 24);
            sheet.addCell(new Label(2, i + 3, slvo.getXfjbh(), fmt2));
            sheet.setColumnView(3, 24);
            sheet.addCell(new Label(3, i + 3, slvo.getQxxfjbh(), fmt2));
            sheet.setColumnView(4, 20);
            sheet.addCell(new Label(4, i + 3, slvo.getXfrqCN(), fmt2));
            sheet.setColumnView(5, 15);
            sheet.addCell(new Label(5, i + 3, slvo.getXm(), fmt2));
            sheet.setColumnView(6, 18);
            sheet.addCell(new Label(6, i + 3, slvo.getLdhm(), fmt2));
            sheet.setColumnView(7, 18);
            sheet.addCell(new Label(7, i + 3, slvo.getLxdh(), fmt2));
            sheet.setColumnView(8, 18);
            sheet.addCell(new Label(8, i + 3, slvo.getFsdMC(), fmt2));
            sheet.setColumnView(9, 15);
            sheet.addCell(new Label(9, i + 3, slvo.getFynr(), fmt2));
            sheet.setColumnView(10, 18);
            sheet.addCell(new Label(10, i + 3, slvo.getNrflMc(), fmt2));
            sheet.setColumnView(11, 20);
            sheet.addCell(new Label(11, i + 3, slvo.getJjcd(), fmt2));
            sheet.setColumnView(12, 26);
            sheet.addCell(new Label(12, i + 3, slvo.getDjr(), fmt2));
            sheet.setColumnView(13, 18);
            sheet.addCell(new Label(13, i + 3, slvo.getLyfs(), fmt2));
            i++;
        }
    }
}
*/
