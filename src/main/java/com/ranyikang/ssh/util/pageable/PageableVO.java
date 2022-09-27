package com.ranyikang.ssh.util.pageable;


import lombok.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * CLASS_NAME: PageableVO.java <br/>
 *
 * @author ranyk           <br/>
 * @version V1.0           <br/>
 * @description: 分页工具实体  <br/>
 * @date: 2022-09-26 <br/>
 */
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PageableVO {

    /**
     * 当前页数
     */
    private Integer pageNumber;
    /**
     * 当前每页显示数据条数
     */
    private Integer pageSize;
    /**
     * 排序对象,单个排序对象参见: {@link OrderVO}
     */
    private List<OrderVO> orders;

    /**
     * 获取分页对象
     *
     * @return 返回分页对象 {@link Pageable}
     */
    public Pageable getPageable() {
        Pageable pageable;
        if (null == pageNumber || pageNumber < 1) {
            pageNumber = 1;
        }
        if (null == pageSize || pageSize < 1) {
            pageSize = 10;
        }
        if (orders != null && orders.size() > 0) {
            List<Sort.Order> sortOrders = new ArrayList<>(orders.size());
            orders.forEach((item) -> {
                Sort.Order order = new Sort.Order(item.getOrder(), item.getSort());
                sortOrders.add(order);
            });
            Sort sort = Sort.by(sortOrders);
            pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        } else {
            pageable = PageRequest.of(pageNumber - 1, pageSize);
        }
        return pageable;

    }

}
