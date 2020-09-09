package dto;

import annotation.ZrarExcelProperty;
import annotation.ZrarExcelTitle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ZrarExcelTitle(title = "综合查询")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestOneVo {
    @ZrarExcelProperty(columnIndex = 1, columnName = "信访日期")
    private String date;
    @ZrarExcelProperty(columnIndex = 2, columnName = "信访结束日期")
    private String endDate;
    @ZrarExcelProperty(columnIndex = 3, columnName = "反映内容")
    private String reflectContent;
    @ZrarExcelProperty(columnIndex = 4, columnName = "反迎人姓名")
    private String name;
    @ZrarExcelProperty(columnIndex = 5, columnName = "身高")
    private String age;
}
