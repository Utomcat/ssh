package com.ranyikang.ssh.util;

import java.util.Arrays;
import java.util.Objects;

/**
 * CLASS_NAME: ArrayUtils.java <br/>
 * Description: 数组处理工具 <br/>
 *
 * @author ranyk <br/>
 * @version V1.0 <br/>
 * @date 2021 - 09 - 13
 */
public class ArrayUtils {

    public static boolean isAllBank(Object... obj) {
        if (obj == null) {
            return true;
        } else {
            if (obj.length > 0) {
                return Arrays.stream(obj).allMatch(Objects::isNull);
            } else {
                return true;
            }
        }
    }
}
