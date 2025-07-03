package com.ranyikang.ssh;

import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import com.alibaba.fastjson.JSON;
import com.ranyikang.ssh.common.constant.PayTypeEnum;
import com.ranyikang.ssh.design.patterns.strategy.PayStrategyFactory;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.ranyikang.ssh.entity.Atest;
import com.ranyikang.ssh.entity.User;
import com.ranyikang.ssh.service.EchartsServiceImpl;
import com.ranyikang.ssh.service.UserServiceImpl;
import com.ranyikang.ssh.util.ArrayUtils;
import com.ranyikang.ssh.util.DataBaseUtils;
import com.ranyikang.ssh.util.EasyExcelUtils;
import com.ranyikang.ssh.util.JdomUtils;
import com.ranyikang.ssh.vo.DemoData;
import com.ranyikang.ssh.vo.FcInterDtlSubVo;
import com.ranyikang.ssh.vo.FcInterDtlVo;
import com.ranyikang.ssh.vo.FillInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.jdom2.JDOMException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
@SuppressWarnings("all")
class SshApplicationTests {

    /**
     * 文件根目录路径一
     */
    @Value("${file.root.path1}")
    private String fileRootPath;
    /**
     * 文件根目录路径二
     */
    @Value("${file.root.path2}")
    private String fileRootPath2;
    /**
     * redis ip 配置
     */
    @Value("${redis.ip}")
    private String redisIP;
    /**
     * redis prot 配置
     */
    @Value("${redis.port}")
    private Integer redisPort;
    /**
     * redis database 配置
     */
    @Value("${redis.database}")
    private String redisDatabase;
    /**
     * redis 字符串操作 Template 对象
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    /**
     * 表 sys_user 业务操作接口实现类
     */
    @Autowired
    private UserServiceImpl userService;
    /**
     * 支付策略工厂对象
     */
    @Autowired
    private PayStrategyFactory payStrategyFactory;


    /**
     * 自定义姓名数组
     */
    private List<String> names = Arrays.asList(new String[]{"韩佳", "刘建国", "牟继攀", "冉意康", "宋敏", "唐智林", "杨博坤"});
    /**
     * 旅居史正常值
     */
    private static final String SOJOURN_HISTORY = "否";
    /**
     * 健康状态正常值
     */
    private static final String HEALTH_CONDITION = "否";
    /**
     * 绿码正常值
     */
    private static final String GREEN_CODE = "是";
    /**
     * 接种情况正常值
     */
    private static final String VACCINE_SITUATION = "第三针";
    /**
     * 休息日/节假日在岗状态正常值
     */
    private static final String REST_ON_THE_JOB_STATUS = "复工后:年假、节假日休假、病事假等正常请休假";
    /**
     * 工作日在岗状态正常值
     */
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
        log.error("str0 使用 StringUtils.hasText() 方法结果为   {}", Optional.of(StringUtils.hasText(str0)));
        // false
        log.error("str0 使用 StringUtils.hasLength() 方法结果为   {}", Optional.of(StringUtils.hasLength(str0)));
        // false
        log.error("str1 使用 StringUtils.hasText() 方法结果为   {}", Optional.of(StringUtils.hasText(str1)));
        // false
        log.error("str1 使用 StringUtils.hasLength() 方法结果为   {}", Optional.of(StringUtils.hasLength(str1)));
        // false
        log.error("str2 使用 StringUtils.hasText() 方法结果为   {}", Optional.of(StringUtils.hasText(str2)));
        // true
        log.error("str2 使用 StringUtils.hasLength() 方法结果为   {}", Optional.of(StringUtils.hasLength(str2)));
        // false
        log.error("str3 使用 StringUtils.hasText() 方法结果为   {}", Optional.of(StringUtils.hasText(str3)));
        // true
        log.error("str3 使用 StringUtils.hasLength() 方法结果为   {}", Optional.of(StringUtils.hasLength(str3)));
        // true
        log.error("str4 使用 StringUtils.hasText() 方法结果为   {}", Optional.of(StringUtils.hasText(str4)));
        // true
        log.error("str4 使用 StringUtils.hasLength() 方法结果为   {}", Optional.of(StringUtils.hasLength(str4)));
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
        log.info("当前 a 的值为 {}, 当前 b 的值为 {}", Optional.of(a), Optional.of(b));
        a ^= b;
        b ^= a;
        a ^= b;
        log.info("当前 a 的值为 {}, 当前 b 的值为 {}", Optional.of(a), Optional.of(b));

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
            list.add(Integer.valueOf(num));
        }
        for (int num : nums2) {
            list.add(Integer.valueOf(num));
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
        log.error("a: {}, b: {}", Optional.of(a), Optional.of(b));
        a += 1;
        b = +1;
        log.error("a: {}, b: {}", Optional.of(a), Optional.of(b));

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

    /**
     * 公司二维码填报信息下午检查单元测试
     */
    @Test
    void test14() {
        // 获取当天是周几
        int value = LocalDateTime.now().getDayOfWeek().getValue();
        // 获取文件路径 默认使用路径一
        String path = fileRootPath;
        // 判断路径一是否存在, 不存在则使用路径二
        if (!new File(path).exists()) {
            path = fileRootPath2;
        }
        // 读取指定路径中的 Excel 数据
        List<FillInfoVo> dataList = EasyExcelUtils.complexRead(path, "填报信息2022-07-05-外包.xlsx", 7, new FillInfoVo());
        // 打印获取的数据量
        log.info("本次读取的数据长度为: {}", Optional.of(dataList.size()));
        // 遍历查询结果, 判断指定人员的填报内容中是否有误
        dataList.forEach(data -> {
            // 当当前遍历数据在指定的数组中,进行填报信息判断
            if (names.contains(data.getName())) {
                // 判断旅居史
                if (!SOJOURN_HISTORY.equals(data.getSojournHistory())) {
                    log.error("{}  旅居史填报有异常!", data.getName());
                }
                // 判断健康状态
                if (!HEALTH_CONDITION.equals(data.getHealthCondition())) {
                    log.error("{} 健康情况填报有异常!", data.getName());
                }
                // 判断体温
                if (Double.valueOf(data.getBodyTemperature()) < 36 || Double.valueOf(data.getBodyTemperature()) > 37) {
                    log.error("{} 体温填报有异常!", data.getName());
                }
                // 判断绿码
                if (!GREEN_CODE.equals(data.getGreenCode())) {
                    log.error("{} 绿码填报有异常!", data.getName());
                }
                // 判断接种情况
                if (!VACCINE_SITUATION.equals(data.getVaccineSituation())) {
                    log.error("{} 接种情况填报有异常!", data.getName());
                }
                // 判断在岗状态
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
        String path2 = this.getClass().getClassLoader().getResource("填报信息2022-06-29-外包.xlsx").getPath();
        InputStream in1 = this.getClass().getClassLoader().getResourceAsStream("填报信息2022-06-29-外包.xlsx");
        InputStream in2 = this.getClass().getResourceAsStream("/" + "填报信息2022-06-29-外包.xlsx");
        ClassPathResource classPathResource = new ClassPathResource("填报信息2022-06-29-外包.xlsx");
        InputStream inputStream = classPathResource.getInputStream();

        log.info("path1 ==> {}", path1);
        log.info("path2 ==> {}", path2);
    }

    /**
     * 利息计算单元测试
     */
    @Test
    void test16() {
        List<FcInterDtlSubVo> list = new ArrayList<>();
        AtomicReference<List<FcInterDtlSubVo>> result = new AtomicReference<>(list);
        String path = fileRootPath;
        if (!new File(path).exists()) {
            path = fileRootPath2;
        }
        List<FcInterDtlVo> dataList = EasyExcelUtils.complexRead(path, "查看数据.xlsx", 2, 93, new FcInterDtlVo());
        log.info("查询的数据总量为: {}", Optional.of(dataList.size()));
        dataList.forEach(data -> {
            // 当前数据: ==> {"accName":"成都锦江绿道建设投资集团有限公司（二级本部）","account":"1000012120000004","curName":"人民币","interest":"118,416.67","interestRatePercent":"2.10%","interestTime":"2022-04-17","sigma":"2,030,000,000.00"}
            // log.info("当前数据: ==> {}", JSON.toJSONString(data));
            FcInterDtlSubVo fcInterDtlSubVo = new FcInterDtlSubVo();
            fcInterDtlSubVo.setAccount(data.getAccount());
            fcInterDtlSubVo.setAccName(data.getAccName());
            fcInterDtlSubVo.setBigSigma(new BigDecimal(data.getSigma().replace(",", "")));
            try {
                fcInterDtlSubVo.setBigInterestRatePercent(new BigDecimal(data.getInterestRatePercent().replace("%", "")).divide(new BigDecimal(100)));
            } catch (Exception e) {
                log.info("当前异常数据的时间为 {}", data.getInterestTime());
                throw e;
            }
            result.get().add(fcInterDtlSubVo);
        });
        List<FcInterDtlSubVo> subVoList = result.get();
        FcInterDtlSubVo subVo = new FcInterDtlSubVo();
        subVo.setBigInterest(BigDecimal.ZERO);
        subVo.setBigSigma(BigDecimal.ZERO);
        AtomicReference<FcInterDtlSubVo> summary = new AtomicReference<>(subVo);
        subVoList.forEach(data -> {
            data.setBigInterest(data.getBigSigma().multiply(data.getBigInterestRatePercent()).divide(new BigDecimal(360), 6, BigDecimal.ROUND_HALF_UP));
            log.info("当前账户利息数据为 ==> 账户: {} , 户名: {} , 积数: {} , 利率: {} , 利息: {}", data.getAccount(), data.getAccName(), data.getBigSigma().toString(), data.getBigInterestRatePercent().toString(), data.getBigInterest().toString());
            if (summary.get().getBigInterestRatePercent() == null) {
                summary.get().setBigInterestRatePercent(data.getBigInterestRatePercent());
            }
            summary.get().setBigSigma(summary.get().getBigSigma().add(data.getBigSigma()).setScale(6, BigDecimal.ROUND_HALF_UP));
            summary.get().setBigInterest(summary.get().getBigInterest().add(data.getBigInterest()).setScale(6, BigDecimal.ROUND_HALF_UP));
        });
        log.info("当前汇总利息数据为 ==>  积数: {} , 利率: {} , 利息: {}", summary.get().getBigSigma().toString(), summary.get().getBigInterestRatePercent().toString(), summary.get().getBigInterest().toString());
    }

    @Test
    void test17() {
        String path = fileRootPath;
        if (!new File(path).exists()) {
            path = fileRootPath2;
        }
        path += File.separator + "QRcode.txt";
        File file = new File(path);
        StringBuffer buffer = new StringBuffer();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String info = buffer.toString().substring(buffer.toString().indexOf("：") + 1);
        String[] split = info.split(",");
        for (String s : split) {
            if (names.contains(s)) {
                log.info("{} 二维码未填写", s);
            }
        }


    }

    /**
     * <p>
     * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
     * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
     * <p>
     * P   A   H   N <br/>
     * A P L S I I G <br/>
     * Y   I   R <br/>
     * <p>
     * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
     * </p>
     * <p>
     * 规则:
     * </p>
     */
    @Test
    void test18() {
        // 定义字符串 S
        String str = "";
        // 定义行数 numRows
        int numRows = 3;


    }

    /**
     * BigDecimal 类型 减法测试;
     */
    @Test
    void test19() {
        BigDecimal a = BigDecimal.ZERO;
        BigDecimal b = new BigDecimal("1");
        log.info("a-b = {}", a.subtract(b));
    }

    /**
     * JDOM xml 文件处理工具类测试,获取xml 文件, 设置节点值,获取xml文件内容测试
     */
    @Test
    void test20() {
        String xmlFileName = "001004.xml";
        String xmlPath = this.getClass().getClassLoader().getResource(xmlFileName).getPath();
        File xmlFile = new File(xmlPath);
        try {
            JdomUtils jdomUtils = new JdomUtils(xmlFile);
            jdomUtils.setNodeText("/BCDL/CST_FLOW", "aaaaaa");
            String docText = jdomUtils.getDocText().replace(" ", "").replace("\r", "").replace("\n", "");
            log.info("节点值为 ==> {}", docText);
        } catch (JDOMException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * DecimalFormat 格式化数字,并将其转换为字符串方法测试,测试结果:
     * - 2023-03-01 测试结果:
     * 格式字符串中小数点后有多少个0 则表示需要保留几位小数,多余位数四舍五入,不够位数用0 补位
     * 格式字符串中小数点前的内容表示 按某种格式用指定的记号分割,如 #,###.00 代表小数点前没三位用 , 分割一下,记号前不用和分割位数一样,用一个也可
     * DecimalFormat 格式化数字,并将其转换为字符串方法测试,测试结果:<br/>
     * - 2023-03-01 测试结果:<br/>
     * 格式字符串中小数点后有多少个0 则表示需要保留几位小数,多余位数四舍五入,不够位数用0 补位<br/>
     * 格式字符串中小数点前的内容表示 按某种格式用指定的记号分割,如 #,###.00 代表小数点前没三位用 , 分割一下,记号前不用和分割位数一样,用一个也可<br/>
     */
    @Test
    void test21() {
        BigDecimal decimal0 = new BigDecimal(1000000000000000000L);
        BigDecimal decimal1 = BigDecimal.valueOf(12.0457);
        BigDecimal decimal2 = BigDecimal.valueOf(12.0457899098);
        BigDecimal decimal3 = BigDecimal.valueOf(12.0457894098);
        //DecimalFormat format = new DecimalFormat("###.00");
        //DecimalFormat format = new DecimalFormat("###,###.00");
        //DecimalFormat format = new DecimalFormat("#,###.00");
        DecimalFormat format = new DecimalFormat("#,###.000000");
        String number0 = format.format(decimal0);
        String number1 = format.format(decimal1);
        String number2 = format.format(decimal2);
        String number3 = format.format(decimal3);
        log.info("原数字值为: {} ,格式化后值为: {}", decimal0, number0);
        log.info("原数字值为: {} ,格式化后值为: {}", decimal1, number1);
        log.info("原数字值为: {} ,格式化后值为: {}", decimal2, number2);
        log.info("原数字值为: {} ,格式化后值为: {}", decimal3, number3);
    }


    /**
     * jedis 测试操作 redis
     */
    @Test
    void test22() {
        Jedis jedis = new Jedis(redisIP, redisPort);
        long name = jedis.del("name");
        log.info("删除 key = name 的值, 结果为: {}", Optional.of(name));
        log.info("删除 key = name 的值, 结果为: {}", name);
        String result = jedis.set("name", "张三");
        String setexResult = jedis.setex("key", 60, "李四");
        log.info("操作 redis 数据库的结果为: {} , {}", result, setexResult);
        jedis.close();
    }

    @Test
    void test23() {
        stringRedisTemplate.opsForValue().set("name", "李四");
        String s = stringRedisTemplate.opsForValue().get("name");
        log.info("redis 存放的 key 为 name 的值为 {} ", s);

    }

    @Test
    void test24() {
        User user = userService.queryUserById(Integer.valueOf(4));
        Boolean deleted = Boolean.FALSE;
        for (int i = 0; i < 5; i++) {
            user.setDeleted(deleted);

        }
    }

    /**
     * Optional 类 ofNullable orElse 方法测试, 当判定值为空时取默认值
     */
    @Test
    void test25() {
        String a = null;
        switch (Optional.ofNullable(a).orElse("default")) {
            case "a":
                log.info("a");
                break;
            default:
                log.info("异常值");
        }
    }

    /**
     * 策略模式 + 自动注入方式测试
     */
    @Test
    void test26() {
        Boolean result = payStrategyFactory.getPayStrategy(PayTypeEnum.UNKNOWN).pay(Double.valueOf(100D));
        log.info("支付结果为: {}", result);
    }
    @Test
    void test27() {
        try {
            String url = "http://182.140.146.105:8082/uapws/rest/unifiedPaymentSettlement/paymentCompleted";

            JSONArray jsonarray = new JSONArray();
            JSONObject test1 = new JSONObject();
            test1.put("paystatus", "0");
            test1.put("vdef48", "9999");
            test1.put("paydate", "2024-01-18 11:30:40");
            test1.put("vdef50", "");
            test1.put("pkPaysettledetail", "10011T100000001QE3UO");
            jsonarray.add(test1);

            //加密
            // String request = CDHttpClientUtil.getEncryptionTest(jsonarray.toJSONString());
            JSONObject requestjson = new JSONObject();
            requestjson.put("data", jsonarray.toJSONString());
            log.info("请求参数  ==> {}", requestjson.toJSONString());
            //请求接口
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Content-Type", "application/json");
            String resultStr = HttpUtil.createRequest(Method.POST, url).addHeaders(headers).body(requestjson.toString()).execute().body();

            //解密
            // String checkSignAndDecrypt = CDHttpClientUtil.getDecodeTest(resultStr);
            log.info(resultStr);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 字符串字符集判断
     *
     * @throws UnsupportedEncodingException 不支持的字符集编码异常
     */
    @Test
    void test28() throws UnsupportedEncodingException {
        String a = "这就是";
        if (a.equals(new String(a.getBytes(), "GBK"))) {
            log.info("字符集是 {}", "GBK");
        } else if (a.equals(new String(a.getBytes(), "UTF8"))) {
            log.info("字符集是 {}", "UTF8");
        } else if (a.equals(new String(a.getBytes(), "GB2312"))) {
            log.info("字符集是 {}", "GB2312");
        } else if (a.equals(new String(a.getBytes(), "ISO-8859-1"))) {
            log.info("字符集是 {}", "ISO-8859-1");
        } else {
            log.info("未知字符集");
        }
    }


    @Test
    void test29(){
        try {
            String a = "这就是";
            String b = new String(a.getBytes("GBK"), "GBK");
            log.info("字符集转化完成,其结果为 {} ==> {}", a, b);
        }catch (Exception e){
            log.error("不支持的字符集编码!");
        }

    }

}
