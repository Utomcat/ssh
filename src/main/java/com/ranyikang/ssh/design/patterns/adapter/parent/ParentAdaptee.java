package com.ranyikang.ssh.design.patterns.adapter.parent;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * CLASS_NAME: ParentAdaptee.java<br/>
 *
 * @author ranyk<br/>
 * @version V1.0<br />
 * @description: 类/对象适配器父类<br />
 * @date: 2025-07-04<br/>
 */
@Slf4j
@ToString
@Component
@EqualsAndHashCode
@NoArgsConstructor
public class ParentAdaptee {

    /**
     * 适配器方法1
     */
    public void method1() {
        log.info("类适配器父类/对象适配器对象: method1");
    }
}
