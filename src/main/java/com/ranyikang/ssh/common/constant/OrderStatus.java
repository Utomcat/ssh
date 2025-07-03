package com.ranyikang.ssh.common.constant;

import lombok.Getter;

/**
 * CLASS_NAME: OrderStatus.java
 *
 * @author ranyk
 * @version V1.0
 * @description: 订单状态枚举类
 * @date: 2025-05-27
 */
@Getter
public enum OrderStatus {

    /**
     * 订单状态 - 待付款
     */
    WAIT_PAYMENT(0, "待付款"),
    /**
     * 订单状态 - 待发货
     */
    WAIT_DELIVER(1, "待发货"),
    /**
     * 订单状态 - 待收货
     */
    WAIT_RECEIVE(2, "待收货"),
    /**
     * 订单状态 - 已完成
     */
    FINISHED(3, "已完成"),
    /**
     * 订单状态 - 未知
     */
    UNKNOWN(-1, "未知");

    /**
     * 订单状态key
     */
    private final Integer key;
    /**
     * 订单状态描述
     */
    private final String desc;

    /**
     * 构造方法
     *
     * @param key  订单状态key
     * @param desc 订单状态描述
     */
    OrderStatus(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    /**
     * 根据key获取订单状态
     *
     * @param key 订单状态key
     * @return 订单状态枚举对象 {@link OrderStatus}
     */
    public static OrderStatus getByKey(Integer key) {
        for (OrderStatus value : values()) {
            if (value.key.equals(key)) {
                return value;
            }
        }
        return null;
    }
}
