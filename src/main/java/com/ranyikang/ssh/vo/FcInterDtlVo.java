package com.ranyikang.ssh.vo;

import lombok.Data;

/**
 * CLASS_NAME: FcInterDtlVo.java <br/>
 *
 * @author ranyk           <br/>
 * @version V1.0           <br/>
 * @description: 利息明细实体VO对象  <br/>
 * @date: 2022-06-28 <br/>
 */
@Data
public class FcInterDtlVo {

    /**
     * 账号
     */
    private String account;
    /**
     * 账号名称
     */
    private String accName;
    /**
     * 计息日期
     */
    private String interestTime;
    /**
     * 币种
     */
    private String curName;
    /**
     * 积数
     */
    private String sigma;
    /**
     * 利率
     */
    private String interestRatePercent;
    /**
     * 利息
     */
    private String interest;
}
