package com.ranyikang.ssh.design.patterns.adapter.impl;

import com.ranyikang.ssh.design.patterns.adapter.api.TargetAdapter;
import com.ranyikang.ssh.design.patterns.adapter.parent.ParentAdaptee;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * CLASS_NAME: ObjectAdapter.java<br/>
 *
 * @author ranyk<br/>
 * @version V1.0<br />
 * @description: 对象适配器<br />
 * @date: 2025-07-04<br/>
 */
@Data
@Slf4j
@ToString
@Component
@EqualsAndHashCode
public class ObjectAdapter implements TargetAdapter {

    private final ParentAdaptee parentAdaptee;

    @Autowired
    public ObjectAdapter(ParentAdaptee parentAdaptee) {
        this.parentAdaptee = parentAdaptee;
    }

    @Override
    public void method1() {
        parentAdaptee.method1();
    }

    @Override
    public void method2() {
      log.info("对象适配器: method2");
    }
}
