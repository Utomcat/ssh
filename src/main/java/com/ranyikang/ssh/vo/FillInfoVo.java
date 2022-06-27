package com.ranyikang.ssh.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 填报信息实体类
 *
 * @author ranYk
 * @version V1.0
 * @date 2022-06-27
 */
@Data
@EqualsAndHashCode
public class FillInfoVo {

    /**
     * 序号
     */
    private String id;
    /**
     * 日期
     */
    private String date;
    /**
     * 部门
     */
    private String department;
    /**
     * 姓名
     */
    private String name;
    /**
     * 身份证后四位
     */
    private String idCard;
    /**
     * 人员类别
     */
    private String type;
    /**
     * 目前居住地址（精确到门牌号）
     */
    private String address;
    /**
     * 旅居史
     */
    private String sojournHistory;
    /**
     * 健康情况
     */
    private String healthCondition;
    /**
     * 体温
     */
    private String bodyTemperature;
    /**
     * 绿码
     */
    private String greenCode;
    /**
     * 接种情况
     */
    private String vaccineSituation;
    /**
     * 不能接种说明
     */
    private String illustrate;
    /**
     * 在岗状态
     */
    private String onTheJobStatus;
    /**
     * 备注
     */
    private String remark;


}
