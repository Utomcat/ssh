package com.ranyikang.ssh.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * CLASS_NAME: SunburstDrinkEntity.java <br/>
 * Description: ECharts sunburst drink 数据模型实体对象 <br/>
 *
 * @author ranyk <br/>
 * @version V1.0 <br/>
 * @date 2021 - 10 - 08
 */
@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sunburst_drink",schema = "test")
public class SunburstDrinkEntity implements Serializable {

    private static final long serialVersionUID = -3272998801433787813L;

    /**
     * 数据主键 ID ,不为空
     */
    @Id
    @Column(name = "id")
    private String id;
    /**
     * 上一级数据主键ID,可为空,第一级数据没有parent_id
     */
    @Column(name = "parent_id")
    private String parentId;
    /**
     * 数据name
     */
    @Column(name = "name")
    private String name;
    /**
     * 数据值,当数据存在下一级时,当前级别的 value 值为 0
     */
    @Column(name = "value")
    private Integer value;
}
