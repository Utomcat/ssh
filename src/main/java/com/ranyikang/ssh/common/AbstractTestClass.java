package com.ranyikang.ssh.common;

import com.ranyikang.ssh.entity.User;

/**
 * CLASS_NAME: AbstractTestClass.java <br/>
 * Description: 抽象测试类 <br/>
 *
 * @author ranyk <br/>
 * @version V1.0 <br/>
 * @date 2021 - 11 - 04
 */
public abstract class AbstractTestClass {

    /**
     * 获取用户类型方法
     *
     * @return 返回获取到的用户对象
     */
    public abstract User getUserType();

    public User getUserInfo(){
        return new User(0,"父类",false);
    }


}
