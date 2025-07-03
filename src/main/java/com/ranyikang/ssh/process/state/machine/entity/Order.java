package com.ranyikang.ssh.process.state.machine.entity;

import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@Entity
@Builder
@ToString
@AllArgsConstructor
@Table(name = "tb_order")
@RequiredArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "order_code", length = 128)
    private String orderCode;

    @Column(name = "status")
    private Integer status;

    @Column(name = "name", length = 64)
    private String name;

    @Column(name = "price", precision = 12, scale = 2)
    private BigDecimal price;

    @Column(name = "delete_flag", nullable = false)
    private Byte deleteFlag;

    @Column(name = "create_time", nullable = false)
    private Instant createTime;

    @Column(name = "update_time", nullable = false)
    private Instant updateTime;

    @Column(name = "create_user_code", length = 32)
    private String createUserCode;

    @Column(name = "update_user_code", length = 32)
    private String updateUserCode;

    @Column(name = "version", nullable = false)
    private Integer version;

    @Column(name = "remark", length = 64)
    private String remark;

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) {
            return false;
        }
        Order tbOrder = (Order) o;
        return getId() != null && Objects.equals(getId(), tbOrder.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
