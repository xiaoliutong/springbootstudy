package reflect;

import annotation.ZrarExcelProperty;
import annotation.ZrarExcelTitle;
import dto.TestOneVo;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/*This Style does not belong to the supplied Workbook Stlyes Source.
Are you trying to assign a style from one workbook to the cell of a differnt workbook?
*/
public class SuperExcelUtils {
    private final static Logger log = LoggerFactory.getLogger(SuperExcelUtils.class);
    private final static DateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final static Pattern pattern = Pattern.compile("^//d+(//.//d+)?$");



    public static <T> void exportExcel(Class<T> reflectClass, List<T> dataList, String beginDate, String endDate, OutputStream outputStream) {
        Workbook initWorkbook = new XSSFWorkbook();
        CellStyle initTitleStyle = initWorkbook.createCellStyle();
        Font initFont = initWorkbook.createFont();
        CellStyle initContentStyle1 = initWorkbook.createCellStyle();
        Font initContentFont1 = initWorkbook.createFont();
        CellStyle initContentStyle2 = initWorkbook.createCellStyle();
        Font initContentFont2 = initWorkbook.createFont();
        //初始化标题样式
        initTitleStyle(initTitleStyle, initFont);
        //初始化表头样式
        initContentStyle1(initContentStyle1, initContentFont1);
        //初始化正文样式
        initContentStyle2(initContentStyle2, initContentFont2);
        Sheet tempSheet = initWorkbook.createSheet();
        tempSheet.setDefaultColumnWidth(300);
        try {
            if (!reflectClass.isAnnotationPresent(ZrarExcelTitle.class)) {
                throw new Exception("请在此类" + reflectClass.getSimpleName() + "加上注解ZrarExcelTitle");
            }
            //得到类上的注解
            ZrarExcelTitle zrarExcelTitle = reflectClass.getAnnotation(ZrarExcelTitle.class);
            //得到类的属性columnCount，title
            String titleName = zrarExcelTitle.title();
            //得到实体类上的各个字段
            Field[] headFields = reflectClass.getDeclaredFields();
            //得到总列数之后合并第一行和第二行的单元格
            tempSheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headFields.length));
            tempSheet.addMergedRegion(new CellRangeAddress(1, 1, 0, headFields.length));
            setTitle(tempSheet, titleName, initTitleStyle);
            //设置统计时间
            String dateRecord = "统计时间:" + beginDate + "------" + endDate;
            Cell dateCell = tempSheet.createRow(1).createCell(0);
            dateCell.setCellValue(dateRecord);
            dateCell.setCellStyle(initContentStyle1);
            //设置头部
            setHead(tempSheet, headFields, initContentStyle1);
            //填充数据
            fillData(tempSheet, dataList, initContentStyle2);
            //输出到页面上
            initWorkbook.write(outputStream);

        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                log.error(e.getMessage());
                e.printStackTrace();
            }
        }

    }


    private static <E> void fillData(Sheet tempSheet, List<E> dataList, CellStyle initContentStyle2) {
        E e;
        Iterator<E> tIterator = dataList.iterator();
        int index = 0;
        ZrarExcelProperty zrarExcelProperty = null;
        Field commonField = null;
        String fieldName = null;
        String getMethodName = null;
        Class eCls = null;
        Method getMethod = null;
        Object fieldValue = null;
        String returnV = null;
        Row row = null;
        Cell cell = null;
        while (tIterator.hasNext()) {
            row = tempSheet.createRow(index + 3);
            //创建序号列
            Cell sortC = row.createCell(0);
            sortC.setCellValue(index+1);
            sortC.setCellStyle(initContentStyle2);
            e = tIterator.next();
            Field[] commonFields = e.getClass().getDeclaredFields();

            try {
                for (int i = 0; i < commonFields.length; i++) {
                    commonField = commonFields[i];
                    zrarExcelProperty= commonField.getAnnotation(ZrarExcelProperty.class);
                    validate(zrarExcelProperty, commonField);
                    //获得该字段在第几列
                    cell = row.createCell(zrarExcelProperty.columnIndex());
                    //通过反射get方法拿到字段的值
                    fieldName = commonField.getName();
                    getMethodName = "get"
                            + fieldName.substring(0, 1).toUpperCase()
                            + fieldName.substring(1);
                    eCls = e.getClass();
                    getMethod = eCls.getMethod(getMethodName,
                            new Class[]{});
                    fieldValue = getMethod.invoke(e, new Object[]{});
                    returnV = switchType(fieldValue);
                    //对转换后的字符串查看是否是全数字
                    if (pattern.matcher(returnV).matches()) {
                        cell.setCellValue(Double.parseDouble(returnV));
                    } else {
                        cell.setCellValue(returnV);
                    }
                    cell.setCellStyle(initContentStyle2);
                }
            } catch (Exception e1) {
                log.error(e1.getMessage());
            }
            index++;
        }
    }


    private static String switchType(Object value) {
        //如果都不满足代表该字段没有值，赋值为空
        String rString = "";
        if (value == null) {
            return rString;
        } else if (value instanceof Date) {
            rString = dataFormat.format(value);
        } else {
            rString = value.toString();
        }

        return rString;

    }

    /**
     * 方法实现说明
     *
     * @author LiuZhuangZhuang
     * @description 对标题进行设置
     * @date 2020/9/8 14:30
     */
    private static void setTitle(Sheet tempSheet, String titleName, CellStyle initTitleStyle) {
        Cell cell = tempSheet.createRow(0).createCell(0);
        cell.setCellValue(titleName);
        cell.setCellStyle(initTitleStyle);
    }

    /**
     * 方法实现说明
     *
     * @author LiuZhuangZhuang
     * @description 设置表头
     * @date 2020/9/8 16:16
     */
    private static void setHead(Sheet tempSheet, Field[] headFields, CellStyle initContentStyle1) {
        Field commonField = null;
        ZrarExcelProperty zrarExcelProperty = null;
        Row headRow = tempSheet.createRow(2);
        Cell oneCell = headRow.createCell(0);
        Cell commonCell = null;
        oneCell.setCellValue("序号");
        oneCell.setCellStyle(initContentStyle1);
        try {
            for (int i = 0; i < headFields.length; i++) {
                commonField = headFields[i];
                zrarExcelProperty= commonField.getAnnotation(ZrarExcelProperty.class);
                validate(zrarExcelProperty, commonField);
                commonCell = headRow.createCell(zrarExcelProperty.columnIndex());
                commonCell.setCellValue(zrarExcelProperty.columnName());
                commonCell.setCellStyle(initContentStyle1);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    private static void validate(ZrarExcelProperty zrarExcelProperty, Field commonField) throws Exception {
        if (!commonField.isAnnotationPresent(ZrarExcelProperty.class)) {
            throw new Exception("请在字段" + commonField.getName() + "上加上ZrarExcelProperty注解");
        }
        if (zrarExcelProperty.columnIndex() == 0) {
            throw new Exception("列值不可为0,请修改字段" + commonField.getName() + "上注解的列值");
        }
    }

    /**
     * 方法实现说明
     *
     * @author LiuZhuangZhuang
     * @description 设置标题的样式
     * @date 2020/9/8 13:56
     */
    private static void initTitleStyle(CellStyle initTitleStyle, Font initFont) {

        //设置居中
        initTitleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置背景色
        initTitleStyle.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        //设置填充格式
        initTitleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        //字体为粗体
        initFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //设置字体类别
        initFont.setFontName(HSSFFont.FONT_ARIAL);
        //没有下划线
        initFont.setUnderline(FontUnderline.NONE.getByteValue());
        //字体颜色
        initFont.setColor(HSSFColor.BLACK.index);
        //字体字号
        initFont.setFontHeightInPoints((short) 17);
        //设置到样式中
        initTitleStyle.setFont(initFont);
    }

    /**
     * 方法实现说明
     *
     * @author LiuZhuangZhuang
     * @description 设置内容第一行样式
     * @date 2020/9/8 14:04
     */
    private static void initContentStyle1(CellStyle initContentStyle1, Font initContentFont1) {
        //设置内容第一行的样式,加背景
        initContentStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        initContentStyle1.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
        initContentStyle1.setFillPattern(CellStyle.SOLID_FOREGROUND);
        //设置字体
        initContentFont1.setFontName("Times New Roman");
        initContentFont1.setFontHeightInPoints((short) 12);
        initContentFont1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        initContentStyle1.setFont(initContentFont1);
    }

    /**
     * 方法实现说明
     *
     * @author LiuZhuangZhuang
     * @description 设置正文的样式
     * @date 2020/9/8 16:15
     */
    private static void initContentStyle2(CellStyle initContentStyle2, Font initContentFont2) {
        //设置正文样式,居中就可
        initContentStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置字体
        initContentFont2.setFontName("Times New Roman");
        initContentFont2.setFontHeightInPoints((short) 12);
        initContentFont2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        initContentStyle2.setFont(initContentFont2);
    }

   public static void main(String[] args) throws Exception {
/* @ZrarExcelProperty(columnIndex = 1, columnName = "信访日期")
    private String date;
    @ZrarExcelProperty(columnIndex = 2, columnName = "信访结束日期")
    private String endDate;
    @ZrarExcelProperty(columnIndex = 3, columnName = "反映内容")
    private String reflectContent;
    @ZrarExcelProperty(columnIndex = 4, columnName = "反迎人姓名")
    private String name;
    @ZrarExcelProperty(columnIndex = 5, columnName = "年龄")
    private String age;*/
        //SuperExcelUtils.exportExcel(TestOneVo.class, null, outputStream);
         List<TestOneVo> voList = new ArrayList<>(4);
       for (int i = 0; i < 60; i++) {
           voList.add(new TestOneVo("2020","2020","污水","小刘","22"));
       }
        for (int i = 0; i < 1; i++) {

            OutputStream outputStream = new
                    FileOutputStream(new File("E:\\springbootstudy\\spring-boot-poi\\target\\" +
                    UUID.randomUUID().toString().substring(0, 5) + ".xlsx"));
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            SuperExcelUtils.exportExcel(TestOneVo.class, voList,"2020-08-09","2020-09-09", outputStream);
                        }
                    }

            ).start();
        }
    }

}
