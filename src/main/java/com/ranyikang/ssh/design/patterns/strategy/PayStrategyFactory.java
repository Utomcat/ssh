package com.ranyikang.ssh.design.patterns.strategy;

import com.ranyikang.ssh.common.constant.PayTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * CLASS_NAME: PayStrategyFactory.java
 *
 * @author ranyk
 * @version V1.0
 * @description: 支付策略工厂, 主要用于将支付策略对象放进工厂缓存对象中
 * @date: 2025-05-24
 */
@Slf4j
@Service
public class PayStrategyFactory {

    /**
     * 支付策略具体实现对象缓存 Map 集合, key 为对应类型的支付方式枚举值, value 为对应类型的支付策略对象
     */
    public final ConcurrentHashMap<PayTypeEnum, PayStrategy> PAY_STRATEGY_CACHE_MAP = new ConcurrentHashMap<>(PayTypeEnum.values().length + 1);

    /**
     * 构造方法, 初始化支付策略对象缓存 Map 集合
     *
     * @param payStrategies: 支付策略对象列表
     */
    @Autowired
    public PayStrategyFactory(List<PayStrategy> payStrategies) {
        payStrategies.forEach(payStrategy -> PAY_STRATEGY_CACHE_MAP.put(payStrategy.getPayType(), payStrategy));
    }

    /**
     * 根据支付方式枚举值获取对应的支付策略对象
     *
     * @param payType: 支付方式枚举值 {@link PayTypeEnum}
     * @return 支付策略接口 {@link PayStrategy} 的实现类
     */
    public PayStrategy getPayStrategy(PayTypeEnum payType) {
        return PAY_STRATEGY_CACHE_MAP.get(payType);
    }

}
