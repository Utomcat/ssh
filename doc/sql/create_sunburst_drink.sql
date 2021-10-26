create table sunburst_drink
(
    id        varchar(255)              not null comment '数据主键'
        primary key,
    parent_id varchar(255) default '0'  not null comment '上一级数据主键,如果为第一级则设置为 0',
    `name`      varchar(100) default '默认' not null comment '名字',
    `value`     int          default 0    not null comment '值'
) character set utf8mb4
    comment ' ECharts sunburst drink 类型图表的数据存放';