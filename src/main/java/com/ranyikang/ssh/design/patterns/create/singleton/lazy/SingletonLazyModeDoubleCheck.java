package com.ranyikang.ssh.design.patterns.create.singleton.lazy;

/**
 * CLASS_NAME: SingletonLazyModeDoubleCheck.java<br/>
 *
 * @author ranyk<br/>
 * @version V1.0<br />
 * @description: 单例模式 - 懒汉模式 - 使用双重检测机制进行创建(该方法不是对方法加锁故效率比方法加锁效率高)<br/>
 * @date: 2025-07-04<br/>
 */
@SuppressWarnings("all")
public class SingletonLazyModeDoubleCheck {
    private static SingletonLazyModeDoubleCheck instance;

    private SingletonLazyModeDoubleCheck() {
    }

    public static SingletonLazyModeDoubleCheck getInstance() {
        if (instance == null) {
            synchronized (SingletonLazyModeDoubleCheck.class) {
                if (instance == null) {
                    instance = new SingletonLazyModeDoubleCheck();
                }
            }
        }
        return instance;
    }

}
