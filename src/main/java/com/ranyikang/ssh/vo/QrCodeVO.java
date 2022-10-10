package com.ranyikang.ssh.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * CLASS_NAME: QrCodeVO.java <br/>
 *
 * @author ranyk           <br/>
 * @version V1.0           <br/>
 * @description: 二维码检查封装对象  <br/>
 * @date: 2022-10-10 <br/>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class QrCodeVO {

    private String code;
}
