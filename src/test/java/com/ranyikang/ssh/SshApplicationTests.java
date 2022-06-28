package com.ranyikang.ssh;

import com.alibaba.fastjson.JSON;
import com.ranyikang.ssh.entity.Atest;
import com.ranyikang.ssh.service.EchartsServiceImpl;
import com.ranyikang.ssh.util.ArrayUtils;
import com.ranyikang.ssh.util.DataBaseUtils;
import com.ranyikang.ssh.util.EasyExcelUtils;
import com.ranyikang.ssh.vo.DemoData;
import com.ranyikang.ssh.vo.FcInterDtlSubVo;
import com.ranyikang.ssh.vo.FcInterDtlVo;
import com.ranyikang.ssh.vo.FillInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
@SuppressWarnings("all")
class SshApplicationTests {

    /**
     * 文件根目录
     */
    @Value("${file.root.path}")
    private String fileRootPath;
    /**
     * 自定义姓名数组
     */
    private List<String> names = Arrays.asList(new String[]{"韩佳", "刘建国", "牟继攀", "冉意康", "宋敏", "唐智林", "杨博坤"});

    private static final String SOJOURN_HISTORY = "否";

    private static final String HEALTH_CONDITION = "否";

    private static final String GREEN_CODE = "是";
    private static final String VACCINE_SITUATION = "第三针";
    private static final String REST_ON_THE_JOB_STATUS = "复工后:年假、节假日休假、病事假等正常请休假";
    private static final String NORMAL_ON_THE_JOB_STATUS = "复工后:今日正常上班";


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
    void test10() {
        int a = 1;
        int b = 2;
        log.error("a: {}, b: {}", a, b);
        a += 1;
        b = +1;
        log.error("a: {}, b: {}", a, b);

    }

    @Test
    void test11() {
        Date date = new Date();
        log.info("aaaaaaaaaaaaaaaaaaa");
    }


    /**
     * EasyExcel 简单读取 Excel 数据方式一
     */
    @Test
    void test12() {
        List<DemoData> dataList = EasyExcelUtils.simpleRead(fileRootPath, "file1.xlsx", new DemoData());
        dataList.forEach(data -> {
            log.info("读取到一条数据{}", JSON.toJSONString(data));
        });
    }

    /**
     * EasyExcel 简单读取 Excel 数据方式二
     */
    @Test
    void test13() {
        List<DemoData> dataList = EasyExcelUtils.simpleRead2(fileRootPath, "file1.xlsx", new DemoData());
        dataList.forEach(data -> {
            log.info("读取到一条数据{}", JSON.toJSONString(data));
        });
    }

    @Test
    void test14() {
        int value = LocalDateTime.now().getDayOfWeek().getValue();
        List<FillInfoVo> dataList = EasyExcelUtils.complexRead(fileRootPath, "填报信息2022-06-28-外包.xlsx", 7, new FillInfoVo());
        log.info("本次读取的数据长度为: {}", dataList.size());
        dataList.forEach(data -> {
            if (names.contains(data.getName())) {
                if (!SOJOURN_HISTORY.equals(data.getSojournHistory())) {
                    log.error("{}  旅居史填报有异常!", data.getName());
                }
                if (!HEALTH_CONDITION.equals(data.getHealthCondition())) {
                    log.error("{} 健康情况填报有异常!", data.getName());
                }
                if (Double.valueOf(data.getBodyTemperature()) < 36 || Double.valueOf(data.getBodyTemperature()) > 37) {
                    log.error("{} 体温填报有异常!", data.getName());
                }
                if (!GREEN_CODE.equals(data.getGreenCode())) {
                    log.error("{} 绿码填报有异常!", data.getName());
                }
                if (!VACCINE_SITUATION.equals(data.getVaccineSituation())) {
                    log.error("{} 接种情况填报有异常!", data.getName());
                }
                if (value >= 1 && value <= 6) {
                    if (!NORMAL_ON_THE_JOB_STATUS.equals(data.getOnTheJobStatus()) && !StringUtils.hasText(data.getRemark())) {
                        log.error("{} 工作日工作状态填写为休息,但未填写备注!", data.getName());
                    }
                } else if (value < 1 || value > 6) {
                    if (!REST_ON_THE_JOB_STATUS.equals(data.getOnTheJobStatus()) && !StringUtils.hasText(data.getRemark())) {
                        log.error("{} 休息日工作状态填写为上班,但未填写备注!", data.getName());
                    }
                }

            }
        });
    }

    /**
     * 获取文件路径测试
     */
    @Test
    void test15() throws IOException {
        // 使用 getResource("").getPath()
        String path1 = this.getClass().getClassLoader().getResource("").getPath();
        String path2 = this.getClass().getClassLoader().getResource("填报信息2022-06-25--外包.xlsx").getPath();
        InputStream in1 = this.getClass().getClassLoader().getResourceAsStream("填报信息2022-06-25--外包.xlsx");
        InputStream in2 = this.getClass().getResourceAsStream("/" + "填报信息2022-06-25--外包.xlsx");
        ClassPathResource classPathResource = new ClassPathResource("填报信息2022-06-25--外包.xlsx");
        InputStream inputStream = classPathResource.getInputStream();

        log.info("path1 ==> {}", path1);
        log.info("path2 ==> {}", path2);
    }

    @Test
    void test16(){
        List<FcInterDtlSubVo> list = new ArrayList<>();
        AtomicReference<List<FcInterDtlSubVo>> result = new AtomicReference<>();
        result.set(list);
        List<FcInterDtlVo> dataList = EasyExcelUtils.complexRead(fileRootPath, "查看数据.xlsx", 3, 93, new FcInterDtlVo());
        log.info("查询的数据总量为: {}", dataList.size());
        dataList.forEach(data -> {
            // 当前数据: ==> {"accName":"成都锦江绿道建设投资集团有限公司（二级本部）","account":"1000012120000004","curName":"人民币","interest":"118,416.67","interestRatePercent":"2.10%","interestTime":"2022-04-17","sigma":"2,030,000,000.00"}
            // log.info("当前数据: ==> {}", JSON.toJSONString(data));
            FcInterDtlSubVo fcInterDtlSubVo = new FcInterDtlSubVo();
            fcInterDtlSubVo.setAccount(data.getAccount());
            fcInterDtlSubVo.setAccName(data.getAccName());
            fcInterDtlSubVo.setBigSigma(new BigDecimal(data.getSigma().replace(",","")));
            try {
            fcInterDtlSubVo.setBigInterestRatePercent(new BigDecimal(data.getInterestRatePercent().replace("%","")).divide(new BigDecimal(100)));
            }catch (Exception e){
                log.info("当前异常数据的时间为 {}", data.getInterestTime());
                throw e;
            }
            result.get().add(fcInterDtlSubVo);
        });
        List<FcInterDtlSubVo> subVoList = result.get();
        FcInterDtlSubVo subVo = new FcInterDtlSubVo();
        subVo.setBigInterest(BigDecimal.ZERO);
        subVo.setBigSigma(BigDecimal.ZERO);
        AtomicReference<FcInterDtlSubVo> summary = new AtomicReference<>();
        summary.set(subVo);
        subVoList.forEach(data -> {
            data.setBigInterest(data.getBigSigma().multiply(data.getBigInterestRatePercent()).divide(new BigDecimal(360), 4 , BigDecimal.ROUND_HALF_UP));
            log.info("当前账户利息数据为 ==> 账户: {} , 户名: {} , 积数: {} , 利率: {} , 利息: {}", data.getAccount(), data.getAccName(), data.getBigSigma().toString(), data.getBigInterestRatePercent().toString(), data.getBigInterest().toString());
            if (summary.get().getBigInterestRatePercent() == null){
                summary.get().setBigInterestRatePercent(data.getBigInterestRatePercent());
            }
            summary.get().setBigSigma(summary.get().getBigSigma().add(data.getBigSigma()));
            summary.get().setBigInterest(summary.get().getBigInterest().add(data.getBigInterest()));
        });
        log.info("当前汇总利息数据为 ==>  积数: {} , 利率: {} , 利息: {}", summary.get().getBigSigma().toString(), summary.get().getBigInterestRatePercent().toString(), summary.get().getBigInterest().toString());
    }
}
