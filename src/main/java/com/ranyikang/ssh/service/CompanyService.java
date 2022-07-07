package com.ranyikang.ssh.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.ranyikang.ssh.dao.CompanyDao;
import com.ranyikang.ssh.entity.Company;
import com.ranyikang.ssh.exception.BusinessException;
import com.ranyikang.ssh.vo.FillInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.*;

/**
 * CLASS_NAME: CompanyService.java <br/>
 *
 * @author ranyk           <br/>
 * @version V1.0           <br/>
 * @description: 公司业务处理类  <br/>
 * @date: 2022-07-05 <br/>
 */
@Slf4j
@Service
public class CompanyService {

    /**
     * CompanyDao 数据库操作对象
     */
    private CompanyDao companyDao;

    /**
     * Company_member 数据表数据库操作对象 set 注入方法
     *
     * @param companyDao CompanyDao 对象
     */
    @Autowired
    public void setCompanyDao(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    private final List<String> names = Arrays.asList("韩佳", "刘建国", "牟继攀", "冉意康", "宋敏", "唐智林", "杨博坤");
    /**
     * 旅居史正常值
     */
    @Value("${value.sojournHistory}")
    private String sojournHistory;
    /**
     * 健康状态正常值
     */
    @Value("${value.healthCondition}")
    private String healthCondition;
    /**
     * 绿码正常值
     */
    @Value("${value.greenCode}")
    private String greenCode;
    /**
     * 接种情况正常值
     */
    @Value("${value.vaccineSituation}")
    private String vaccineSituation;
    /**
     * 休息日/节假日在岗状态正常值
     */
    @Value("${value.restOnTheJobStatus}")
    private String restOnTheJobStatus;
    /**
     * 工作日在岗状态正常值
     */
    @Value("${value.normalOnTheJobStatus}")
    private String normalOnTheJobStatus;
    /**
     * 读取表格的起始行
     */
    @Value("${value.startIndex}")
    private int startIndex;
    /**
     * 周一常量值
     */
    private static final int MONDAY = 1;
    /**
     * 周六常量值
     */
    private static final int SATURDAY = 6;
    /**
     * 体温最小值
     */
    private static final int MIN_BODY_TEMPERATURE = 36;
    /**
     * 体温最大值
     */
    private static final int MAX_BODY_TEMPERATURE = 37;
    /**
     * 第一个工作薄索引
     */
    private static final int FIRST_SHEET_NO = 0;

    /**
     * 解析二维码检查 Excel
     *
     * @param file             excel 源文件, {@link MultipartFile} 对象
     * @param specialTreatment 是否特殊操作
     * @return 返回结果 List 集合
     */
    public List<Map<String, Object>> parseExcel(MultipartFile file, boolean specialTreatment) {
        List<Map<String, Object>> result = new ArrayList<>();
        List<FillInfoVo> dataList = new ArrayList<>();
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, FillInfoVo.class, new AnalysisEventListener<FillInfoVo>() {
                @Override
                public void invoke(FillInfoVo data, AnalysisContext context) {
                    int sheetNo = context.readSheetHolder().getSheetNo();
                    if (sheetNo == FIRST_SHEET_NO) {
                        int index = context.readRowHolder().getRowIndex();
                        if (index >= startIndex) {
                            dataList.add(data);
                        }
                    }
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext context) {

                }
            }).sheet().doRead();
            // 获取当天是周几
            int value = LocalDateTime.now().getDayOfWeek().getValue();
            // 打印获取的数据量
            log.info("本次读取的数据长度为: {}", dataList.size());
            // 遍历查询结果, 判断指定人员的填报内容中是否有误
            dataList.forEach(data -> {
                Map<String, Object> temp = new HashMap<>(1);
                // 当当前遍历数据在指定的数组中,进行填报信息判断
                if (names.contains(data.getName())) {
                    // 判断旅居史
                    if (!sojournHistory.equals(data.getSojournHistory())) {
                        log.error("{}  旅居史填报有异常!", data.getName());
                        temp.put(data.getName(), "旅居史填报有异常");
                    }
                    // 判断健康状态
                    if (!healthCondition.equals(data.getHealthCondition())) {
                        log.error("{} 健康情况填报有异常!", data.getName());
                        temp.put(data.getName(), "健康情况填报有异常");
                    }
                    // 判断体温
                    if (Double.parseDouble(data.getBodyTemperature()) < MIN_BODY_TEMPERATURE || Double.parseDouble(data.getBodyTemperature()) > MAX_BODY_TEMPERATURE) {
                        log.error("{} 体温填报有异常!", data.getName());
                        temp.put(data.getName(), "体温填报有异常");
                    }
                    // 判断绿码
                    if (!greenCode.equals(data.getGreenCode())) {
                        log.error("{} 绿码填报有异常!", data.getName());
                        temp.put(data.getName(), "绿码填报有异常");
                    }
                    // 判断接种情况
                    if (!vaccineSituation.equals(data.getVaccineSituation())) {
                        log.error("{} 接种情况填报有异常!", data.getName());
                        temp.put(data.getName(), "接种情况填报有异常");
                    }
                    checkOnTheJobStatus(temp, specialTreatment, value, data);
                    if (temp.size() > 0) {
                        result.add(temp);
                    }
                }
            });

        } catch (IOException e) {
            log.info("解析异常,异常信息为: {}", e.getMessage());
            throw new BusinessException("解析Excel数据异常!");
        }

        return result;
    }

    /**
     * 在职状态检查
     *
     * @param temp             用来存放检查结果 Map 集合
     * @param specialTreatment 特殊处理标志
     * @param value            周几值, 周一: 1; 周二: 2; 周三: 3; 周四: 4; 周五: 5; 周六: 6; 周天: 7;
     * @param data             每行 excel 数据
     */
    private void checkOnTheJobStatus(Map<String, Object> temp, boolean specialTreatment, int value, FillInfoVo data) {

        // 判断在岗状态, 周一到周五判断
        if (value >= MONDAY && value < SATURDAY) {
            // 特殊指定,即工作日放假
            if (specialTreatment) {
                if (normalOnTheJobStatus.equals(data.getOnTheJobStatus())) {
                    log.error("{} 工作日放假,工作状态填写错误!", data.getName());
                    temp.put(data.getName(), "工作日放假,工作状态填写错误");
                }
            }
            // 没特殊指定,即工作日正常上班
            else {
                // 正常上班,工作状态为休息,未填写备注
                if (restOnTheJobStatus.equals(data.getOnTheJobStatus()) && !StringUtils.hasText(data.getRemark())) {
                    log.error("{} 工作日工作状态填写为休息,但未填写备注!", data.getName());
                    temp.put(data.getName(), "工作日工作状态填写为休息,但未填写备注");
                }
                // 正常上班,工作状态为上班,填写了备注
                else {
                    if (normalOnTheJobStatus.equals(data.getOnTheJobStatus()) && StringUtils.hasText(data.getRemark())) {
                        log.error("{} 工作日正常上班,且填写了备注,备注为: {}!", data.getName(), data.getRemark());
                        temp.put(data.getName(), "工作日正常上班,且填写了备注,备注为: " + data.getRemark());
                    }
                }
            }
        }
        // 判断在岗状态, 周六到周天判断
        else {
            // 特殊指定,即休息日上班
            if (specialTreatment) {
                if (restOnTheJobStatus.equals(data.getOnTheJobStatus())) {
                    log.error("{} 休息日上班,工作状态填写错误!", data.getName());
                    temp.put(data.getName(), "休息日上班,工作状态填写错误");
                }
            }
            // 没特殊指定,即休息日正常休息
            else {
                // 休息休息,工作状态为上班,但未填写备注
                if (normalOnTheJobStatus.equals(data.getOnTheJobStatus()) && !StringUtils.hasText(data.getRemark())) {
                    log.error("{} 休息日工作状态填写为上班,但未填写备注!", data.getName());
                    temp.put(data.getName(), "休息日工作状态填写为上班,但未填写备注");
                }
                // 休息日休息,但填写了备注
                else {
                    if (restOnTheJobStatus.equals(data.getOnTheJobStatus()) && StringUtils.hasText(data.getRemark())) {
                        log.error("{} 休息日正常休息,且填写了备注,备注为: {}!", data.getName(), data.getRemark());
                        temp.put(data.getName(), "休息日正常休息,且填写了备注,备注为: " + data.getRemark());
                    }
                }
            }
        }
    }

    /**
     * 解析二维码填报信息字符串
     *
     * @param code 未填报人员信息字符串
     * @return 返回检查结果 List 集合
     */
    public List<Map<String, Object>> parseCode(String code) {
        String info = code.substring(code.indexOf("：") + 1);
        String[] split = info.split(",");

        List<Map<String, Object>> result = new ArrayList<>();
        for (String s : split) {
            String[] s1 = s.split(" ");
            for (String s2 : s1) {
                if (names.contains(s2)) {
                    Map<String, Object> temp = new HashMap<>(1);
                    log.info("{} 二维码未填写", s);
                    temp.put(s, "二维码未填写");
                    result.add(temp);
                }
            }
        }
        return result;
    }

    /**
     * 查询当前项目组所有人员信息
     *
     * @return 返回查询结果 List 集合
     */
    public List<Company> queryAll() {
        return companyDao.findByOnTheJobIs(true);
    }

    /**
     * 新增一个项目组成员
     *
     * @param company 项目组成员实体对象
     * @return 返回新增后的项目组成员对象
     */
    public Company addMember(Company company) {
        if (!StringUtils.hasText(company.getPosition())) {
            company.setPosition("1");
        }
        if (!company.isOnTheJob()) {
            company.setOnTheJob(true);
        }
        return companyDao.save(company);
    }
}
