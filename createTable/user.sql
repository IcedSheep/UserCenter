-- 创建用户信息
create table user
(
    id           bigint                             not null comment '主键',
    userAccount  varchar(256)                       null comment '账号',
    userPassword varchar(256)                       null comment '密码',
    username     varchar(256)                       null comment '用户呢称',
    avatarUrl    varchar(512)                       null comment '用户头像',
    gender       tinyint                            null comment '性别',
    email        varchar(512)                       null comment '邮箱',
    profile      varchar(512)                       null comment '个人介绍',
    userStatus       tinyint  default 1                 null comment '用户状态 1-正常 0-封号',
    userRole         tinyint                            null comment '用户角色 0-普通用户 1-管理员',
    phone        varchar(256)                       null comment '手机号',
    deleted      int      default 0                 null comment '是否删除 0-正常 1-已删除',
    createTime   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint user_id_uindex
        unique (id),
    constraint user_userAccount_uindex
        unique (userAccount)
)
    comment '用户信息表';

alter table user
    add primary key (id);

