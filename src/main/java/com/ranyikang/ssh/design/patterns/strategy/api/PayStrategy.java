package com.ranyikang.ssh.design.patterns.strategy.api;

import com.ranyikang.ssh.common.constant.PayTypeEnum;

/**
 * CLASS_NAME: PayStrategyFactory.java
 *
 * @author ranyk
 * @version V1.0
 * @description: 支付策略接口
 * @date: 2025-05-24
 */
public interface PayStrategy {

    /**
     * 获取支付方式类型
     *
     * @return 返回具体实现类的支付方式类型枚举对象, {@link PayTypeEnum}, 默认返回 {@link PayTypeEnum#UNKNOWN}, 代表不支持该类型
     */
    PayTypeEnum getPayType();

    /**
     * 支付方法接口
     *
     * @param amount  支付金额
     * @return 返回支付方式执行是否成功: true: 支付成功; false: 支付失败;
     */
    Boolean pay(Double amount);
}
