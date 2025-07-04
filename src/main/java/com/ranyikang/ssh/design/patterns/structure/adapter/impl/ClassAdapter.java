package com.ranyikang.ssh.design.patterns.structure.adapter.impl;

import com.ranyikang.ssh.design.patterns.structure.adapter.api.TargetAdapter;
import com.ranyikang.ssh.design.patterns.structure.adapter.parent.ParentAdaptee;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * CLASS_NAME: ImplAdapter.java<br/>
 *
 * @author ranyk<br/>
 * @version V1.0<br />
 * @description: 类适配器实现类<br />
 * @date: 2025-07-04<br/>
 */
@Data
@Slf4j
@ToString
@Component
@EqualsAndHashCode(callSuper = true)
public class ClassAdapter extends ParentAdaptee implements TargetAdapter {

    @Override
    public void method2() {
        log.info("类适配器: method2");
    }
}
