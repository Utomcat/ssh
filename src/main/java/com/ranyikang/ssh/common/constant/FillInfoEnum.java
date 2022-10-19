package com.ranyikang.ssh.common.constant;

import lombok.Getter;

/**
 * CLASS_NAME: FillInfoEnum.java <br/>
 *
 * @author ranyk           <br/>
 * @version V1.0           <br/>
 * @description: 二维码填报信息异常枚举  <br/>
 * @date: 2022-10-19 <br/>
 */
@Getter
public enum FillInfoEnum {

    ABNORMAL_SOJOURN_HISTORY(1,"旅居史填报有异常!"),
    ABNORMAL_HEALTH(2,"健康情况填报有异常!"),
    ABNORMAL_BODY_TEMPERATURE(3,"体温填报有异常!"),
    ;

    private final Integer code;

    private final String message;


    FillInfoEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }


}
