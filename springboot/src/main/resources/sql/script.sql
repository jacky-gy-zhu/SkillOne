create table persistent_logins (
	username varchar(64) not null,
    series varchar(64) not null,
    token varchar(64) not null,
    last_used timestamp not null default current_timestamp on update current_timestamp,
    primary key(series)
);

create table users (
	id int(11) primary key auto_increment,
    username varchar(255),
    password varchar(255)
);

insert into users (username, password) values ('lucy','123');
insert into users (username, password) values ('marry','456');

create table acl_permission (
    id char(19) not null default '' comment '编号',
    pid char(19) not null default '' comment '所属上级',
    name varchar(20) not null default '' comment '名称',
    type tinyint(3) not null default 0 comment '类型（1:菜单，2：按钮）',
    permission_value varchar(50) default null comment '权限值',
    path varchar(100) default null comment '访问路径',
    component varchar(100) default null comment '组件路径',
    icon varchar(50) default null comment '图标',
    status tinyint(4) default null comment '状态（0：禁止，1：正常）',
    is_deleted tinyint(1) unsigned not null default 0 comment '逻辑删除（1或0）',
    gmt_create datetime default null comment '创建时间',
    gmt_modified datetime default null comment '更新时间',
    primary key(id),
    key idx_pid(pid)
);

insert into acl_permission values (1,0,'全部数据',0,null,null,null,null,null,0,'2020-12-06','2020-12-06');

create table acl_role(
    id char(19) not null default '' comment '角色id',
    role_name varchar(20) not null default '' comment '角色名称',
    role_code varchar(20) default null comment '角色编码',
    remark varchar(255) default null comment '备注',
    is_deleted tinyint(1) unsigned not null default 0 comment '逻辑删除（1或0）',
    gmt_create datetime default null comment '创建时间',
    gmt_modified datetime default null comment '更新时间',
    primary key(id)
);

insert into acl_role values(1,'普通管理员',null,null,0,'2020-12-06','2020-12-06');

create table acl_role_permission(
    id char(19) not null default '',
    role_id char(19) not null default '',
    permission_id char(19) not null default '',
    is_deleted tinyint(1) unsigned not null default 0 comment '逻辑删除（1或0）',
    gmt_create datetime default null comment '创建时间',
    gmt_modified datetime default null comment '更新时间',
    primary key(id),
    key idx_role_id (role_id),
    key idx_permission_id (permission_id)
);

create table acl_user(
    id int(11) primary key auto_increment,
    username varchar(255) not null,
    password varchar(255) not null,
    nick_name varchar(255),
    salt varchar(255),
    token varchar(255),
    is_deleted tinyint(1) unsigned not null default 0 comment '逻辑删除（1或0）',
    gmt_create datetime default null comment '创建时间',
    gmt_modified datetime default null comment '更新时间'
);

create table acl_user_role(
    id int(11) primary key auto_increment,
    role_id char(19) not null,
    user_id int not null,
    is_deleted tinyint(1) unsigned not null default 0 comment '逻辑删除（1或0）',
    gmt_create datetime default null comment '创建时间',
    gmt_modified datetime default null comment '更新时间'
);
