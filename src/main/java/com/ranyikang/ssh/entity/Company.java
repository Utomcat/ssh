package com.ranyikang.ssh.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * CLASS_NAME: Company.java <br/>
 *
 * @author ranyk           <br/>
 * @version V1.0           <br/>
 * @description: 公司人员信息实体类  <br/>
 * @date: 2022-07-07 <br/>
 */
@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "company_member", schema = "test")
public class Company implements Serializable {

    private static final long serialVersionUID = 2226879233051901707L;
    /**
     * 数据主键ID
     */
    @Id
    @Column(name = "id", length = 11)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 姓名
     */
    @Column(name = "name")
    private String name;
    /**
     * 联系电话
     */
    @Column(name = "phone")
    private String phone;
    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;
    /**
     * 组别
     */
    @Column(name = "belong_group")
    private Integer group;
    /**
     * 职位: 1: 员工; 0: 项目经理; 2: 驻场经理; 3: 经理助理; 4: 技术总监; 5:小组组长; 默认为 1
     */
    @Column(name = "position")
    private String position;
    /**
     * 在职状态(仅针对项目组): true: 在职; false: 不在职; 默认在职;
     */
    @Column(name = "on_the_job")
    private boolean onTheJob;

    /**
     * 住址
     */
    @Column(name = "address")
    private String address;
}
