package com.ranyikang.ssh.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.ranyikang.ssh.dao.CompanyDao;
import com.ranyikang.ssh.entity.Company;
import com.ranyikang.ssh.exception.BusinessException;
import com.ranyikang.ssh.vo.CompanyVO;
import com.ranyikang.ssh.vo.FillInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
        // 解析的返回结果 List 集合
        List<Map<String, Object>> result = new ArrayList<>();
        // 获取 excel 表格中的数据 List 集合
        List<FillInfoVo> dataList = new ArrayList<>();
        // 解析 Excel 数据表格,同时将解析后的数据放进 dataList 中
        getExcelData(dataList, file);
        // 获取全公司的人员姓名
        List<String> names = queryAllName();
        // 定义并初始化用来存放地址的 Map 集合,其中 key 为 姓名, value 为对应人员的住址
        Map<String, String> addressMap = new HashedMap<>(names.size());
        // 获取全公司人员信息
        List<Company> companies = queryAll();
        // 打印获取的数据量
        log.info("本次读取的数据长度为: {}", dataList.size());
        // 做数据检查,同时获取最新的住址,将其添加进住址 Map 中
        doCheck(dataList,addressMap,names, result, specialTreatment);
        // 更新当前人员信息的地址
        companies.forEach(company -> company.setAddress(addressMap.get(company.getName())));
        // 执行更新地址操作
        companyDao.saveAll(companies);
        // 返回响应结果
        return result;
    }

    private void doCheck(List<FillInfoVo> dataList, Map<String, String> addressMap, List<String> names, List<Map<String, Object>> result, boolean specialTreatment) {
        // 获取当天是周几
        int value = LocalDateTime.now().getDayOfWeek().getValue();
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
                addressMap.put(data.getName(), data.getAddress());
            }
        });
    }

    /**
     * 获取传入的 Excel 数据
     *
     * @param dataList 用来存放 Excel 每一行数据的 List 集合
     * @param file     传入的 Excel 文件对象
     */
    private void getExcelData(List<FillInfoVo> dataList, MultipartFile file) {
        try {
            // 获取文件输入流
            InputStream inputStream = file.getInputStream();
            // 读取文件流中的内容
            EasyExcel.read(inputStream, FillInfoVo.class, new AnalysisEventListener<FillInfoVo>() {
                @Override
                public void invoke(FillInfoVo data, AnalysisContext context) {
                    // 获取工作薄编号
                    int sheetNo = context.readSheetHolder().getSheetNo();
                    // 当工作簿是指定的工作薄则获取数据
                    if (sheetNo == FIRST_SHEET_NO) {
                        // 获取行索引
                        int index = context.readRowHolder().getRowIndex();
                        // 当指定行数为指定的数据时,将当前数据对象添加进 dataList 集合中
                        if (index >= startIndex) {
                            dataList.add(data);
                        }
                    }
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext context) {

                }
            }).sheet().doRead();
        } catch (Exception e) {
            log.error("获取 Excel 数据时发生异常!");
            throw new BusinessException("获取 Excel 数据发生错误,请检查 Excel 数据或获取 Excel 的配置!");
        }
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
        List<String> names = queryAllName();
        for (String s : split) {
            String[] s1 = s.split(" ");
            for (String s2 : s1) {
                if (names.contains(s2)) {
                    Map<String, Object> temp = new HashMap<>(1);
                    log.info("{} 二维码未填写", s);
                    temp.put("name", s);
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

    /**
     * 查询所有在职状态的人员姓名
     *
     * @return 返回查询结果 List 集合
     */
    public List<String> queryAllName() {
        return companyDao.findNameByOnTheJobIs(true);
    }

    /**
     * 根据条件查询对应项目组人员信息
     *
     * @param condition 查询条件封装对象
     * @return 返回查询结果 List 集合
     */
    public List<Company> queryAllOfMeetConditions(Company condition) {

        Specification<Company> queryCondition = (root, query, criteriaBuilder) -> {

            // 查询条件 List
            List<Predicate> predicateList = new ArrayList<>();
            addCondition(predicateList, condition, criteriaBuilder, root);
            // 将查询条件添加进 where 子句中
            query.where(criteriaBuilder.and(predicateList.toArray(new Predicate[0])));
            // 增加排序条件
            List<Order> orderList = new ArrayList<>();
            // 根据数据ID 降序排序
            orderList.add(criteriaBuilder.desc(root.get("id")));
            // 返回查询子句
            return query.orderBy(orderList).getRestriction();
        };
        // 执行查询返回查询结果
        return companyDao.findAll(queryCondition);
    }

    /**
     * 添加条件
     *
     * @param predicateList   查询条件 List 集合
     * @param condition       传入的查询条件对象
     * @param criteriaBuilder 条件对象构造对象
     * @param root            根对象
     */
    private void addCondition(List<Predicate> predicateList, Company condition, CriteriaBuilder criteriaBuilder, Root<Company> root) {
        // id 不为空,增加查询条件 id
        if (null != condition.getId()) {
            Predicate id = criteriaBuilder.equal(root.get("id").as(Integer.class), condition.getId());
            predicateList.add(id);
        }
        // name 不为空,增加查询条件 name
        if (StringUtils.hasText(condition.getName())) {
            Predicate name = criteriaBuilder.like(root.get("name").as(String.class), "%" + condition.getName() + "%");
            predicateList.add(name);
        }
        // phone 不为空,增加查询条件 phone
        if (StringUtils.hasText(condition.getPhone())) {
            Predicate phone = criteriaBuilder.like(root.get("phone").as(String.class), "%" + condition.getPhone() + "%");
            predicateList.add(phone);
        }
        // email 不为空,增加查询条件 email
        if (StringUtils.hasText(condition.getEmail())) {
            Predicate email = criteriaBuilder.like(root.get("email").as(String.class), "%" + condition.getEmail() + "%");
            predicateList.add(email);
        }
        // group 不为空,增加查条件 group
        if (null != condition.getGroup()) {
            Predicate group = criteriaBuilder.equal(root.get("group").as(Integer.class), condition.getGroup());
            predicateList.add(group);
        }
        // position 不为空,增加查询条件 position
        if (StringUtils.hasText(condition.getPosition())) {
            Predicate position = criteriaBuilder.like(root.get("position").as(String.class), "%" + condition.getPosition() + "%");
            predicateList.add(position);
        }
        // onTheJob 不为空,增加查询条件 onTheJob
        Predicate onTheJob = criteriaBuilder.equal(root.get("onTheJob").as(Boolean.class), condition.isOnTheJob() ? 1 : 0);
        predicateList.add(onTheJob);
        // address 不为空,增加查询条件 address
        if (StringUtils.hasText(condition.getAddress())) {
            Predicate address = criteriaBuilder.like(root.get("address").as(String.class), "%" + condition.getAddress() + "%");
            predicateList.add(address);
        }
    }

    /**
     * 更新公司成员在职状态
     *
     * @param company 需要更新公司成员在职状态的封装对象,其中仅存在 ID 值,以及需要修改的状态
     */
    public void updateMemberStatus(Company company) {
        if (company.getId() == null || company.getId() <= 0) {
            throw new BusinessException("传入的修改人员ID不正常,当前传入的ID为: " + company.getId());
        }
        Optional<Company> memberOptional = companyDao.findById(company.getId());
        if (memberOptional.isPresent()) {
            Company member = memberOptional.get();
            member.setOnTheJob(company.isOnTheJob());
            companyDao.save(member);
            log.info("执行更新人员完成");
        } else {
            throw new BusinessException("未查询到指定ID的成员,传入的人员ID为: " + company.getId());
        }
    }

    /**
     * 按条件分页查询公司全部人员信息
     *
     * @param company 查询条件分页封装对象
     * @return 返回查询结果 Map&lt;String,Object&gt; 集合 ,<br/>
     * 结果中包含: <br/>
     * 1. tableData: 存放查询结果 List 集合; <br/>
     * 2. tableTotal: 存放满足查询条件的数据条数; <br/>
     * 3. pageSize: 存放每页查询数量;<br/>
     * 4. currentPage: 存放当前页数;<br/>
     * 5. totalPage: 存放总页数;
     */
    public Map<String, Object> queryAll(CompanyVO company) {
        Page<Company> companies = companyDao.findAll((Specification<Company>) (root, query, criteriaBuilder) -> {
            // 查询条件 List
            List<Predicate> predicateList = new ArrayList<>();
            addCondition(predicateList, company.getCompany(), criteriaBuilder, root);
            query.where(criteriaBuilder.and(predicateList.toArray(new Predicate[0])));
            return null;
        }, company.getPageable());
        Map<String, Object> result = new HashedMap<>(6);
        result.put("tableData", companies.getContent());
        result.put("tableTotal", companies.getTotalElements());
        result.put("pageSize", companies.getSize());
        result.put("currentPage", companies.getNumber() + 1);
        result.put("totalPage", companies.getTotalPages());
        return result;
    }

    /**
     * 更新人员信息
     *
     * @param company 传入的需要更新的人员信息封装实体对象
     * @return 返回更新后的实体对象
     */
    public Company updateMember(Company company) {
        // 更新前判断需要更新的人员信息 ID 是否存在
        if (company.getId() == null || company.getId() <= 0) {
            throw new BusinessException("传入的人员ID不正确,请排查传入信息!");
        }
        // 更新前判断需要更新的人员信息 name 是否存在
        if (!StringUtils.hasText(company.getName())) {
            throw new BusinessException("传入的人员姓名不存在!");
        }
        // 更新前判断需要更新的人员信息 phone 是否存在
        if (!StringUtils.hasText(company.getPhone())) {
            throw new BusinessException("传入的人员电话不存在!");
        }
        // 更新前判断需要更新的人员信息 email 是否存在,不存在则设置为 1
        if (!StringUtils.hasText(company.getEmail())) {
            company.setEmail("1");
        }
        // 更新前判断需要更新的人员信息 position 是否存在,不存在则设置为 1
        if (!StringUtils.hasText(company.getPosition())) {
            company.setPosition("1");
        }
        return companyDao.save(company);
    }
}
