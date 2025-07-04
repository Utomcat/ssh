package com.ranyikang.ssh.design.patterns.create.singleton.hungry;

/**
 * CLASS_NAME: SingletonHungryModeStaticVariable.java<br/>
 *
 * @author ranyk<br/>
 * @version V1.0<br />
 * @description: 单例模式 - 饿汉方式 - 静态变量方式创建<br/>
 * @date: 2025-07-04<br/>
 */
@SuppressWarnings("all")
public class SingletonHungryModeStaticVariable {

    private static SingletonHungryModeStaticVariable instance = new SingletonHungryModeStaticVariable();

    private SingletonHungryModeStaticVariable() {
    }

    public static SingletonHungryModeStaticVariable getInstance() {
        return instance;
    }
}
