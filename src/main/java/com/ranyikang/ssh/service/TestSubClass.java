package com.ranyikang.ssh.service;

import com.ranyikang.ssh.common.AbstractTestClass;
import com.ranyikang.ssh.entity.User;

/**
 * CLASS_NAME: TestSubClass.java <br/>
 * Description: 测试父类方法的子类 <br/>
 *
 * @author ranyk <br/>
 * @version V1.0 <br/>
 * @date 2021 - 11 - 04
 */
public class TestSubClass extends AbstractTestClass {
    /**
     * 获取用户类型方法
     *
     * @return 返回获取到的用户对象
     */
    @Override
    public User getUserType() {
        return new User(2,"获取子类类型",false);
    }

    @Override
    public User getUserInfo() {
        return new User(1,"子类",false);
    }
}
