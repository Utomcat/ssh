package com.ranyikang.ssh.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 初始容量
     */
    private static final int INITIAL_CAPACITY = 16;

    /**
     * 测试方法0,用来测试全局变量
     */
    public void testMethodZero() {
        for (int i = 0; i < MAX_CYCLES; i++) {
            globalOne = globalOne.add(BigDecimal.valueOf(i));
        }
        log.info("本次循环结果 {}", globalOne.intValue());
    }

    /**
     * 形实参数测试方法
     *
     * @return 返回 Map&lt;String,Object&gt; 集合
     */
    public Map<String, Object> testFormAndRealParam() {
        List<String> list = new ArrayList<>(16);
        List<String> result = listHandle(list);
        Map<String, Object> map = new HashMap<>(4);
        map.put("list", list);
        map.put("list 地址",System.identityHashCode(list));
        map.put("result", result);
        map.put("result 地址",System.identityHashCode(result));
        return map;
    }

    /**
     * 处理 List 集合
     *
     * @param list 需要处理的 List 集合
     * @return 返回处理好的 List 集合
     */
    private List<String> listHandle(List<String> list) {
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }
}
