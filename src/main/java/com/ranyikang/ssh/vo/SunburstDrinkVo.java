package com.ranyikang.ssh.vo;

import com.ranyikang.ssh.entity.ItemStyle;
import com.ranyikang.ssh.entity.SunburstDrinkEntity;
import lombok.*;

import java.util.List;

/**
 * CLASS_NAME: SunburstDrinkVo.java <br/>
 * Description: ECharts sunburst drink 数据类型的前端结果类 <br/>
 *
 * @author ranyk <br/>
 * @version V1.0 <br/>
 * @date 2021 - 10 - 09
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SunburstDrinkVo extends SunburstDrinkEntity {

    private static final long serialVersionUID = 4576919896121342244L;

    private ItemStyle itemStyle;
    private List<SunburstDrinkVo> children;
}
