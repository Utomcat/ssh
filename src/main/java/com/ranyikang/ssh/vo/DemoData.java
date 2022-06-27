package com.ranyikang.ssh.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * EasyExcel 测试实体类
 *
 * @author ranYk
 * @version V1.0
 * @date 2022-06-25
 */
@Data
@EqualsAndHashCode
public class DemoData {

    @ExcelProperty("字符串标题")
    private String string;
    @ExcelProperty("日期标题")
    private Date date;
    @ExcelProperty("数字标题")
    private Double doubleData;
}
