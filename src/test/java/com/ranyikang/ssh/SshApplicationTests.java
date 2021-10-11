package com.ranyikang.ssh;

import com.ranyikang.ssh.service.EchartsServiceImpl;
import com.ranyikang.ssh.util.ArrayUtils;
import com.ranyikang.ssh.util.DataBaseUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@SpringBootTest
class SshApplicationTests {

    @Autowired
    private EchartsServiceImpl echartsService;

    @Test
    void contextLoads() {
        log.error("BigDecimal.ZERO.intValue() ==> " + BigDecimal.ZERO.intValue() );
    }

    @Test
    void test0() {
        List<String> tableNames = DataBaseUtils.getTableNames();
        tableNames.forEach(item -> {
            if ("sys_user".equals(item)) {
                log.error("tableNames: " + item);
                List<String> columnNames = DataBaseUtils.getColumnNames(item);
                List<String> columnTypes = DataBaseUtils.getColumnTypes(item);
                List<String> columnComments = DataBaseUtils.getColumnComments(item);
                log.error("columnNames: " + columnNames);
                log.error("columnTypes: " + columnTypes);
                log.error("columnComments: " + columnComments);
            }
        });
    }

    @Test
    void test1(){
        boolean aBoolean = Boolean.getBoolean("1");
        log.error(" aBoolean ==> " + aBoolean);
    }

    @Test
    @SuppressWarnings("all")
    void test2() {
        String[] strings = new String[4];
        if (strings == null){
            log.error("数组为空!");
        }else if (ArrayUtils.isAllBank(strings)){
            log.error("数组元素为空!");
        }else {
            log.error("数组不为空!");
        }
    }

    /**
     * ECharts 数据获取内容
     */
    @Test
    void test3() {
        echartsService.getData();
    }

    /**
     * 换行符字符串输出结果验证
     */
    @Test
    void test4() {
        String str = "Alcohol/\nFermented";
        String str1 = "Alcohol/\\nFermented";
        // 输出结果为 Alcohol/
        //Fermented
        log.error(str);
        // 输出结果为 Alcohol/\nFermented
        log.error(str1);

    }

    /**
     * org.springframework.util.StringUtils 类 hasText 和  hasLength 方法测试
     */
    @Test
    @SuppressWarnings("all")
    void test5() {
        String str0 = null;
        String str1 = "";
        String str2 = " ";
        String str3 = "      ";
        String str4 = "a %";
        // false
        log.error("str0 使用 StringUtils.hasText() 方法结果为   {}", StringUtils.hasText(str0));
        // false
        log.error("str0 使用 StringUtils.hasLength() 方法结果为   {}", StringUtils.hasLength(str0));
        // false
        log.error("str1 使用 StringUtils.hasText() 方法结果为   {}", StringUtils.hasText(str1));
        // false
        log.error("str1 使用 StringUtils.hasLength() 方法结果为   {}", StringUtils.hasLength(str1));
        // false
        log.error("str2 使用 StringUtils.hasText() 方法结果为   {}", StringUtils.hasText(str2));
        // true
        log.error("str2 使用 StringUtils.hasLength() 方法结果为   {}", StringUtils.hasLength(str2));
        // false
        log.error("str3 使用 StringUtils.hasText() 方法结果为   {}", StringUtils.hasText(str3));
        // true
        log.error("str3 使用 StringUtils.hasLength() 方法结果为   {}", StringUtils.hasLength(str3));
        // true
        log.error("str4 使用 StringUtils.hasText() 方法结果为   {}", StringUtils.hasText(str4));
        // true
        log.error("str4 使用 StringUtils.hasLength() 方法结果为   {}", StringUtils.hasLength(str4));
    }

}
