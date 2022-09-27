package com.ranyikang.ssh.vo;

import com.ranyikang.ssh.entity.Company;
import com.ranyikang.ssh.util.pageable.PageableVO;
import lombok.*;

/**
 * CLASS_NAME: CompanyVO.java <br/>
 *
 * @author ranyk           <br/>
 * @version V1.0           <br/>
 * @description: 公司成员信息请求对象  <br/>
 * @date: 2022-09-27 <br/>
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CompanyVO extends PageableVO{

    /**
     * 成员查询条件
     */
    private Company company;


}
