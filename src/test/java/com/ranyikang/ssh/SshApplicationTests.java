package com.ranyikang.ssh;

import com.ranyikang.ssh.service.EchartsServiceImpl;
import com.ranyikang.ssh.util.ArrayUtils;
import com.ranyikang.ssh.util.DataBaseUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

    @Test
    void test3() {
        echartsService.getData();
    }

}
