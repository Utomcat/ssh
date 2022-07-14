package com.ranyikang.ssh.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * CLASS_NAME: TestService.java <br/>
 *
 * @author ranyk           <br/>
 * @version V1.0           <br/>
 * @description: 测试业务接口类  <br/>
 * @date: 2022-07-14 <br/>
 */
@Slf4j
@Service
public class TestService {

    /**
     * 定义全局变量,并初始化为 0
     */
    BigDecimal globalOne = BigDecimal.ZERO;
    /**
     * 最大循环次数
     */
    private static final int MAX_CYCLES = 10;

    /**
     * 测试方法0,用来测试全局变量
     */
    public void testMethodZero() {
        for (int i = 0; i < MAX_CYCLES; i++) {
            globalOne = globalOne.add(BigDecimal.valueOf(i));
        }
        log.info("本次循环结果 {}", globalOne.intValue());
    }
}
