package com.ranyikang.ssh;

import com.ranyikang.ssh.entity.Atest;
import com.ranyikang.ssh.service.EchartsServiceImpl;
import com.ranyikang.ssh.util.ArrayUtils;
import com.ranyikang.ssh.util.DataBaseUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
@SuppressWarnings("all")
class SshApplicationTests {

    @Autowired
    private EchartsServiceImpl echartsService;

    @Test
    void contextLoads() {
        log.error("BigDecimal.ZERO.intValue() ==> " + BigDecimal.ZERO.intValue());
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
    void test1() {
        boolean aBoolean = Boolean.getBoolean("1");
        log.error(" aBoolean ==> " + aBoolean);
    }

    @Test
    void test2() {
        String[] strings = new String[4];
        if (strings == null) {
            log.error("数组为空!");
        } else if (ArrayUtils.isAllBank(strings)) {
            log.error("数组元素为空!");
        } else {
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

    @Test
    void test6() {
        Atest atest = new Atest();
    }

    @Test
    void test7() {
        String a = null;
        if (a == null) {
            System.out.println("a");
        }

    }

    @Test

    void test8() {
        String a = "aaAAAAAAf";
        System.out.println(a.substring(a.length() - 4));
    }

    @Test

    void test9() {
        //log.info("结果值 ==> {}", lengthOfLongestSubstring("abcabcbb"));
        //log.info("结果值 ==> {}", lengthOfLongestSubstring("bbbbb"));
        //log.info("结果值 ==> {}", lengthOfLongestSubstring("pwwkew"));
        //log.info("结果值 ==> {}", lengthOfLongestSubstring(" "));
        //log.info("结果值 ==> {}", lengthOfLongestSubstring(""));
        //log.info("结果值 ==> {}", lengthOfLongestSubstring("dvdf"));
        //log.info("结果值 ==> {}", lengthOfLongestSubstring("qrsvbspk"));
        //log.info("结果值 ==> {}", lengthOfLongestSubstring("werqwetwetqwerqqwerewr"));
        //log.info("结果值 ==> {}", findMedianSortedArrays(new int[]{1,2,3},new int[]{4,5,6}));
        //log.info("结果值 ==> {}", findMedianSortedArrays(new int[]{1,2,3},new int[]{}));
        //log.info("结果值 ==> {}", findMedianSortedArrays(new int[]{1,2,3},new int[]{7,9}));
        //log.info("结果值 ==> {}", findMedianSortedArrays(new int[]{1,3},new int[]{2}));
        //log.info("结果值 ==> {}", findMedianSortedArrays(new int[]{},new int[]{}));
        //log.info("结果值 ==> {}",longestPalindrome("bbbbb"));
        int a = 10;
        int b = 5;
        log.info("当前 a 的值为 {}, 当前 b 的值为 {}", a, b);
        a ^= b;
        b ^= a;
        a ^= b;
        log.info("当前 a 的值为 {}, 当前 b 的值为 {}", a, b);

    }

    /**
     * 无重复字符的最长子串
     *
     * @param s 传入需要确认的字符串
     * @return 返回传入字符串中无重复字符的最长子串长度
     */
    int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        int maxLen = 0;
        for (char curChar : chars) {
            if (sb.length() <= 0) {
                sb.append(curChar);
            } else {
                if (sb.toString().contains(String.valueOf(curChar))) {
                    maxLen = Math.max(maxLen, sb.length());
                    sb.delete(0, Math.max((sb.indexOf(String.valueOf(curChar)) + 1), 0));
                    sb.append(curChar);
                } else {
                    sb.append(curChar);
                }
            }
        }
        return Math.max(maxLen, sb.length());
    }

    /**
     * 寻找两个正序数组的中位数
     *
     * @param nums1 需要判断的数组一
     * @param nums2 需要判断的数组二
     * @return 返回两个数组的中位数
     */
    double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>(nums1.length + nums2.length);
        for (int num : nums1) {
            list.add(num);
        }
        for (int num : nums2) {
            list.add(num);
        }
        if (list.size() > 0) {
            list = list.stream().sorted().collect(Collectors.toList());
            int size = list.size();
            if (size % 2 == 0) {
                return (list.get(size / 2 - 1) + list.get(size / 2)) / 2.0D;
            } else {
                return list.get(size / 2);
            }
        } else {
            return 0D;
        }

    }

    /**
     * 寻找最长地回文字符串,如 abac 最长地回文字符串为 aba
     *
     * @param s 需要检测的字符串
     * @return 返回最长地回文字符串
     */
    public String longestPalindrome(String s) {

        return null;
    }

    @Test
    void test10(){
        int a = 1;
        int b = 2;
        log.error("a: {}, b: {}", a, b);
        a += 1;
        b =+ 1;
        log.error("a: {}, b: {}", a, b);

    }
}
