package com.ranyikang.ssh.common.constant;

/**
 * CLASS_NAME: OrderStatusChangeEvent.java
 *
 * @author ranyk
 * @version V1.0
 * @description: 状态改变事件枚举类
 * @date: 2025-05-27
 */
public enum OrderStatusChangeEvent {

    /**
     * 支付
     */
    PAYED,
    /**
     * 发货
     */
    DELIVERED,
    /**
     * 确认收货
     */
    RECEIVED;

}
