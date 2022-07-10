create database lhotse default character set utf8mb4 collate utf8mb4_unicode_ci;

create table if not exists app
(
    id          bigint primary key not null auto_increment comment '自动增长ID',
    code        varchar(50)        not null comment '应用编码',
    name        varchar(100)       not null comment '应用名称',
    remark      varchar(100)       null comment '备注',
    create_time datetime           not null default current_timestamp comment '创建时间',
    update_time datetime           not null default current_timestamp on update current_timestamp comment '修改时间',
    is_del      tinyint(1)         not null default 0 comment '删除标识'
) engine = InnoDb
  default charset = utf8mb4
  auto_increment = 1 comment '应用';

create table if not exists agent
(
    id          bigint primary key not null auto_increment comment '自动增长ID',
    ip          varchar(64)        not null comment 'IP',
    version     varchar(32)        not null comment '版本',
    status      varchar(20)        not null default 'INIT' comment '状态',
    create_time datetime           not null default current_timestamp comment '创建时间',
    update_time datetime           not null default current_timestamp on update current_timestamp comment '修改时间',
    is_del      tinyint(1)         not null default 0 comment '删除标识'
) engine = InnoDb
  default charset = utf8mb4
  auto_increment = 1 comment '代理';

create table if not exists td_env_category
(
    id          bigint primary key not null auto_increment comment '自动增长ID',
    code        varchar(50)        not null comment '类别编码',
    name        varchar(50)        not null comment '类别名称',
    remark      varchar(100)       null comment '备注',
    create_time datetime           not null default current_timestamp comment '创建时间',
    update_time datetime           not null default current_timestamp on update current_timestamp comment '修改时间',
    is_del      tinyint(1)         not null default 0 comment '删除标识'
) engine = InnoDb
  default charset = utf8mb4
  auto_increment = 1 comment '环境类别';

create table if not exists td_env
(
    id            bigint primary key not null auto_increment comment '自动增长ID',
    code          varchar(50)        not null comment '环境编码',
    name          varchar(50)        not null comment '环境名称',
    category_code varchar(50)        not null comment '环境类别',
    remark        varchar(100)       null comment '备注',
    create_time   datetime           not null default current_timestamp comment '创建时间',
    update_time   datetime           not null default current_timestamp on update current_timestamp comment '修改时间',
    is_del        tinyint(1)         not null default 0 comment '删除标识'
) engine = InnoDb
  default charset = utf8mb4
  auto_increment = 1 comment '环境';

create table if not exists td_instance
(
    id          bigint primary key not null auto_increment comment '自动增长ID',
    ip          varchar(64)        not null comment 'IP地址',
    port        int                not null comment '端口',
    create_time datetime           not null default current_timestamp comment '创建时间',
    update_time datetime           not null default current_timestamp on update current_timestamp comment '修改时间',
    is_del      tinyint(1)         not null default 0 comment '删除标识'
) engine = InnoDb
  default charset = utf8mb4
  auto_increment = 1 comment '实例';

create table if not exists td_label
(
    id           bigint primary key not null auto_increment comment '自动增长ID',
    label_key    varchar(20)        not null comment '标签名',
    label_value  varchar(50)        not null comment '标签值',
    label_target bigint             not null comment '目标对象',
    label_type   varchar(20)        not null default 'INSTANCE' comment '目标对象类型',
    create_time  datetime           not null default current_timestamp comment '创建时间',
    update_time  datetime           not null default current_timestamp on update current_timestamp comment '修改时间',
    is_del       tinyint(1)         not null default 0 comment '删除标识'
) engine = InnoDb
  default charset = utf8mb4
  auto_increment = 1 comment '标签';