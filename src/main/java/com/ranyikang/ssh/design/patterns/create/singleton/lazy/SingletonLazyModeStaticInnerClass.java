package com.ranyikang.ssh.design.patterns.create.singleton.lazy;

/**
 * CLASS_NAME: SingletonLazyModeStaticInnerClass.java<br/>
 *
 * @author ranyk<br/>
 * @version V1.0<br />
 * @description: 单例模式 - 懒汉模式 - 静态内部类方式创建<br/>
 * @date: 2025-07-04<br/>
 */
@SuppressWarnings("all")
public class SingletonLazyModeStaticInnerClass {

    private SingletonLazyModeStaticInnerClass() {
    }

    private static class SingletonLazyModeStaticInnerClassHolder {
        private static final SingletonLazyModeStaticInnerClass INSTANCE = new SingletonLazyModeStaticInnerClass();
    }

    public static SingletonLazyModeStaticInnerClass getInstance() {
        return SingletonLazyModeStaticInnerClassHolder.INSTANCE;
    }

}
