package com.ranyikang.ssh.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * CLASS_NAME: FcInterDtlSubVo.java <br/>
 *
 * @author ranyk           <br/>
 * @version V1.0           <br/>
 * @description: 利息明细实体VO多属性对象  <br/>
 * @date: 2022-06-28 <br/>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FcInterDtlSubVo extends FcInterDtlVo{


    /**
     * BigDecimal 积数
     */
    private BigDecimal bigSigma;
    /**
     * BigDecimal 利息
     */
    private BigDecimal bigInterest;
    /**
     * BigDecimal 利率
     */
    private BigDecimal bigInterestRatePercent;
}
