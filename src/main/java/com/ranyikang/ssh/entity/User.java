package com.ranyikang.ssh.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * CLASS_NAME: User<br/>
 * Description: 用户实体对象<br/>
 *
 * @author ranYk <br/>
 * @version 1.0
 * @date 2021 - 09 - 02 <br/>
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_user")
public class User implements Serializable {

    private static final long serialVersionUID = 3559914667481191591L;

    /**
     * 主键ID
     */
    @Id
    @Column(name = "id", length = 11)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 姓名
     */
    @Column(name = "name", length = 32)
    private String name;
    /**
     * 逻辑删除标志, deleted=1/true: 已删除; deleted=0/false: 未删除;
     */
    @Column(name = "deleted", length = 1)
    private boolean deleted = Boolean.FALSE;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
