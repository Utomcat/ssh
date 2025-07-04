package com.ranyikang.ssh.design.patterns.create.singleton.lazy;

/**
 * CLASS_NAME: SingletonLazyModeNormal.java<br/>
 *
 * @author ranyk<br/>
 * @version V1.0<br />
 * @description: 单例模式 - 懒汉模式 - 常规方式创建(线程不安全)<br/>
 * @date: 2025-07-04<br/>
 */
@SuppressWarnings("all")
public class SingletonLazyModeNormal {

    private static SingletonLazyModeNormal instance;

    private SingletonLazyModeNormal() {
    }

    public static SingletonLazyModeNormal getInstance() {
        if (instance == null) {
            instance = new SingletonLazyModeNormal();
        }
        return instance;
    }
}
