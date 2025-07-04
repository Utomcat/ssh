package com.ranyikang.ssh.design.patterns.create.singleton.hungry;

/**
 * CLASS_NAME: SingletonHungryModeStaticMethodBlock.java<br/>
 *
 * @author ranyk<br/>
 * @version V1.0<br />
 * @description: 单例模式 - 恶汉方式 - 静态方法块方式创建<br/>
 * @date: 2025-07-04<br/>
 */
@SuppressWarnings("all")
public class SingletonHungryModeStaticMethodBlock {

    private static SingletonHungryModeStaticMethodBlock instance;

    static {
        instance = new SingletonHungryModeStaticMethodBlock();
    }

    private SingletonHungryModeStaticMethodBlock() {
    }

    public static SingletonHungryModeStaticMethodBlock getInstance() {
        return instance;
    }

}
