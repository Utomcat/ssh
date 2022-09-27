package com.ranyikang.ssh.util.pageable;

import lombok.*;
import org.springframework.data.domain.Sort;

/**
 * CLASS_NAME: OrderVO.java <br/>
 *
 * @author ranyk           <br/>
 * @version V1.0           <br/>
 * @description: 分页排序工具实体  <br/>
 * @date: 2022-09-26 <br/>
 */
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OrderVO {

    /**
     * 排序字段
     */
    private String sort;
    /**
     * 排序方式, 参见 {@link   Sort.Direction#DESC} 和 {@link  Sort.Direction#ASC}, 默认 {@link Sort.Direction#DESC}
     */
    private Sort.Direction order;

    /**
     * 只指定排序字段,使用默认排序方式构造排序对象
     *
     * @param sort 需要排序的字段
     */
    public OrderVO(String sort) {
        this.sort = sort;
    }
}
