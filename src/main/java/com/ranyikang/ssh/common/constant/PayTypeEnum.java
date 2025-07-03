package com.ranyikang.ssh.common.constant;

import lombok.Getter;

import java.util.Objects;

/**
 * CLASS_NAME: PayTypeEnum.java
 *
 * @author ranyk
 * @version V1.0
 * @description: 支付方式枚举对象
 * @date: 2025-05-24
 */
@Getter
public enum PayTypeEnum {

    /**
     * 支付宝支付方式
     */
    ALI_PAY("支付宝", 1),
    /**
     * 微信支付方式
     */
    WECHAT_PAY("微信", 2),
    /**
     * 银联支付方式
     */
    UNION_PAY("银联", 3),
    /**
     * 现金支付方式
     */
    CASH("现金", 4),
    /**
     * 银行转账支付方式
     */
    BANK_TRANSFER("银行转账", 5),
    /**
     * 其他支付方式
     */
    OTHER("其他", 0),
    /**
     * 未知支付方式
     */
    UNKNOWN("未知", -1);

    /**
     * 支付方式名称
     */
    private final String name;
    /**
     * 支付方式类型值
     */
    private final Integer value;

    /**
     * 构造方法
     *
     * @param name  支付方式名称
     * @param value 支付方式类型值
     */
    PayTypeEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    /**
     * 根据支付方式类型值获取支付方式枚举对象
     *
     * @param value 支付方式类型值
     * @return 支付方式枚举对象, {@link PayTypeEnum} ; 默认为 UNKNOWN {@link PayTypeEnum#UNKNOWN}
     */
    public static PayTypeEnum getName(Integer value) {
        for (PayTypeEnum payTypeEnum : PayTypeEnum.values()) {
            if (Objects.equals(payTypeEnum.getValue(), value)) {
                return payTypeEnum;
            }
        }
        return UNKNOWN;
    }
}
