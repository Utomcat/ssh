package com.ranyikang.ssh.util;

import java.util.Random;

/**
 * CLASS_NAME: ColorUtils.java <br/>
 * Description: 颜色处理工具 <br/>
 *
 * @author ranyk <br/>
 * @version V1.0 <br/>
 * @date 2021 - 10 - 09
 */
public class ColorUtils {

    /**
     * 随机生成 16 进制的颜色值
     *
     * @return 返回生成的颜色值, 格式为 #FFFFFF
     */
    public static String generateHexadecimalColor() {
        //红色
        String red;
        //绿色
        String green;
        //蓝色
        String blue;
        //生成随机对象
        Random random = new Random();
        //生成红色颜色代码
        red = Integer.toHexString(random.nextInt(256)).toUpperCase();
        //生成绿色颜色代码
        green = Integer.toHexString(random.nextInt(256)).toUpperCase();
        //生成蓝色颜色代码
        blue = Integer.toHexString(random.nextInt(256)).toUpperCase();
        //判断红色代码的位数
        red = red.length() == 1 ? "0" + red : red;
        //判断绿色代码的位数
        green = green.length() == 1 ? "0" + green : green;
        //判断蓝色代码的位数
        blue = blue.length() == 1 ? "0" + blue : blue;
        //生成十六进制颜色值
        return "#" + red + green + blue;
    }
}
