package com.ranyikang.ssh.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * CLASS_NAME: Atest.java <br/>
 * Description: 测试类 <br/>
 *
 * @author ranyk <br/>
 * @version V1.0 <br/>
 * @date 2021 - 12 - 16
 */
@SuppressWarnings("all")
public class Atest {


    private static List<String> A_STR = new ArrayList<>();

    static {
        System.out.println("执行静态代码块!");
        A_STR.add("BBB");
    }

    public Atest() {
        System.out.println("无参构造函数!");
    }


}
