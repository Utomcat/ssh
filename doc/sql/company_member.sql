drop table if exists company_member;
create table company_member
(
    id         int auto_increment primary key comment '数据主键',
    `name`     varchar(50) not null comment '姓名',
    phone      varchar(30) not null comment '联系电话',
    email      varchar(60) not null comment '公司邮箱',
    `group`    int         null comment '所属组别',
    position   varchar(20) not null default '1' comment '职位: 1: 员工; 0: 项目经理; 2:驻场经理; 3:经理助手; 4:技术总监; 5:小组组长; 默认: 1(员工)',
    on_the_job boolean     not null default true comment '在职状态: true: 在职; false: 不在职; 默认: true(在职)'
) engine = InnoDB
  Default charset = utf8 comment '公司人员信息表';


insert into test.company_member (id, `name`, phone, email, `group`, position)
values (1, '周阗', '13550552702', '1', null, '1'),
       (2, '谢杰', '17765322520', '1', null, '1'),
       (3, '宋相吉', '18782402756', '1', null, '1'),
       (4, '米兵', '15608216607', '1', null, '1'),
       (5, '廖乾助', '19138963169', '1', null, '1'),
       (6, '汤亚萍', '18780338135', '1', null, '1'),
       (7, '黄梁枭', '15281026785', '1', null, '1'),
       (8, '王寅澄', '18980478817', '1', null, '1'),
       (9, '唐波', '13548430993', '1', null, '1'),
       (10, '赵瑶', '17340174181', '1', null, '1'),
       (11, '施长瑞', '17621687058', '1', null, '1'),
       (12, '黄子昂', '18108120386', '1', null, '1'),
       (13, '唐君发', '13880499159', '1', null, '1'),
       (14, '王忠海', '17311308650', '1', null, '1'),
       (15, '宋敏', '18202817250', '1', null, '1'),
       (16, '李磊', '14780401015', '1', null, '1'),
       (17, '江泽众', '19180459894', '1', null, '1'),
       (18, '牟继攀', '13980361642', '1', null, '1'),
       (19, '万姝伶', '18280706061', '1', null, '1'),
       (20, '李常林', '17602847443', '1', null, '1'),
       (21, '唐智林', '18226629710', '1', null, '1'),
       (22, '薛佳咪', '15208272725', '1', null, '1'),
       (23, '罗静', '15680261580', '1', null, '1'),
       (24, '韩佳', '18384556907', '1', null, '1'),
       (25, '杨博坤', '15351629239', '1', null, '1'),
       (26, '梁琪涔', '13668234387', '1', null, '1'),
       (27, '刘建国', '18200487980', '1', null, '1'),
       (28, '冉意康', '13547180017', '1', null, '1'),
       (29, '严坤', '18116758782', '1', null, '1'),
       (30, '宣凯', '17628042501', '1', null, '1'),
       (31, '唐元东', '18428035326', '1', null, '1');