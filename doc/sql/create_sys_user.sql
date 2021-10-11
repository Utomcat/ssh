create table sys_user
(
    id      int auto_increment comment '数据主键'
        primary key,
    name    varchar(32)      null comment '姓名',
    deleted bit default b'0' null comment '逻辑删除标志: false:未删除(默认); true:已删除;'
)
    comment '测试用户表';