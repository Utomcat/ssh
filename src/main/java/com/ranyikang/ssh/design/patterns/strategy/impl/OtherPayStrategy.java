package com.ranyikang.ssh.design.patterns.strategy.impl;

import com.ranyikang.ssh.common.constant.PayTypeEnum;
import com.ranyikang.ssh.design.patterns.strategy.api.PayStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * CLASS_NAME: OtherPayStrategy.java
 *
 * @author ranyk
 * @version V1.0
 * @description: 其他支付方式策略实现
 * @date: 2025-05-24
 */
@Slf4j
@Service
public class OtherPayStrategy implements PayStrategy {
    /**
     * 获取支付方式类型
     *
     * @return 返回具体实现类的支付方式类型枚举对象, {@link PayTypeEnum}, 默认返回 {@link PayTypeEnum#UNKNOWN}, 代表不支持该类型
     */
    @Override
    public PayTypeEnum getPayType() {
        return PayTypeEnum.OTHER;
    }

    /**
     * 支付方法接口
     *
     * @param amount  支付金额
     * @return 返回支付方式执行是否成功: true: 支付成功; false: 支付失败;
     */
    @Override
    public Boolean pay(Double amount) {
        log.info("其他支付方式支付成功, 支付金额: {} 元", amount);
        return Boolean.TRUE;
    }
}
