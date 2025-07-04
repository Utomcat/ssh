package com.ranyikang.ssh.design.patterns.create.singleton.lazy;

/**
 * CLASS_NAME: SingletonLazyModeMethodSync.java<br/>
 *
 * @author ranyk<br/>
 * @version V1.0<br />
 * @description: 单例模式 - 懒汉模式 - 使用同步方法的方式创建(线程安全,但因为是对当前方法进行加锁故该方法效率会比较低)<br/>
 * @date: 2025-07-04<br/>
 */
@SuppressWarnings("all")
public class SingletonLazyModeMethodSync {

    private static SingletonLazyModeMethodSync instance;

    private SingletonLazyModeMethodSync() {
    }

    public static synchronized SingletonLazyModeMethodSync getInstance() {
        if (instance == null) {
            instance = new SingletonLazyModeMethodSync();
        }
        return instance;
    }
}
