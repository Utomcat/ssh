package com.ranyikang.ssh.design.patterns.create.singleton.lazy;

/**
 * CLASS_NAME: SingletonLazyModeDoubleCheckUpgrade.java<br/>
 *
 * @author ranyk<br/>
 * @version V1.0<br />
 * @description: 单例模式 - 懒汉模式 - 双重检测机制升级版创建(原来的双重检测机制可能因为局部变量的不可见性和指令重排序的原因导致创建的时候依然出现错误,故出现双重检测机制的升级版)<br/>
 * @date: 2025-07-04<br/>
 */
@SuppressWarnings("all")
public class SingletonLazyModeDoubleCheckUpgrade {

    private static volatile SingletonLazyModeDoubleCheckUpgrade instance;

    private SingletonLazyModeDoubleCheckUpgrade() {
    }

    public static SingletonLazyModeDoubleCheckUpgrade getInstance() {
        if (instance == null) {
            synchronized (SingletonLazyModeDoubleCheckUpgrade.class) {
                if (instance == null) {
                    instance = new SingletonLazyModeDoubleCheckUpgrade();
                }
            }
        }
        return instance;
    }

}
