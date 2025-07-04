package com.ranyikang.ssh.design.patterns.create.singleton.lazy;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * CLASS_NAME: SingletonLazyModeEnum.java<br/>
 *
 * @author ranyk<br/>
 * @version V1.0<br />
 * @description: 单例模式 - 懒汉模式 - 使用枚举方式进行创建,该方式也是线程安全且唯一一种不用怕序列化合反序列化和反射破坏的方式<br/>
 * @date: 2025-07-04<br/>
 */
@Slf4j
@Getter
@SuppressWarnings("all")
public enum SingletonLazyModeEnum {
    INSTANCE("单例模式 - 懒汉模式 - 使用枚举方式进行创建", 18);


    private final String name;
    private final Integer age;

    SingletonLazyModeEnum(String name, Integer age) {
        this.name = name;
        this.age = age;
        System.out.println("单例对象创建成功");
    }

    public void getInfo() {
        log.info("本次对象信息为: {} - {}", this.name, this.age);
    }


}

