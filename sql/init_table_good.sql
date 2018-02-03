SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `area`
-- ----------------------------
DROP TABLE IF EXISTS `area`;
CREATE TABLE `area` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(10) DEFAULT NULL,
  `area_name` varchar(255) DEFAULT NULL,
  `is_hot` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=346 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `content`
-- ----------------------------
DROP TABLE IF EXISTS `content`;
CREATE TABLE `content` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content_cat_id` bigint(20) DEFAULT NULL COMMENT '内容分类ID',
  `img` varchar(300) DEFAULT NULL COMMENT '图片地址',
  `title` varchar(200) DEFAULT NULL COMMENT '内容标题',
  `sub_title` varchar(200) DEFAULT NULL COMMENT '子标题',
  `title_desc` varchar(2000) DEFAULT NULL COMMENT '标题描述',
  `content` text COMMENT '内容',
  `url` varchar(500) DEFAULT NULL COMMENT '链接地址',
  `time` datetime DEFAULT NULL COMMENT '时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `content_cat`
-- ----------------------------
DROP TABLE IF EXISTS `content_cat`;
CREATE TABLE `content_cat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `name` varchar(168) DEFAULT NULL COMMENT '分类名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父分类ID,ID=0时，代表的是一级的类目',
  `parent_name` varchar(168) DEFAULT NULL COMMENT '父分类的名称',
  `sort` int(11) DEFAULT NULL COMMENT '排列序号,表示同级分类的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数',
  `is_parent` bit(1) DEFAULT NULL COMMENT '该分类是否为父类目，1为true，0为false',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `log`
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `app_name` char(36) NOT NULL COMMENT '所属应用',
  `log_type` int(11) DEFAULT NULL COMMENT '日志类型，0为操作日志，1为异常日志',
  `username` varchar(100) DEFAULT NULL COMMENT '访问者/请求者',
  `operation` varchar(100) DEFAULT NULL COMMENT '方法描述',
  `method_name` varchar(100) DEFAULT NULL COMMENT '请求方法名称(全路径)',
  `request_method` varchar(20) DEFAULT NULL COMMENT '请求方式(GET,POST,DELETE,PUT)',
  `request_params` varchar(500) DEFAULT NULL COMMENT '请求参数',
  `request_ip` varchar(50) DEFAULT NULL COMMENT '访问者IP',
  `request_uri` varchar(200) DEFAULT NULL COMMENT '请求URI',
  `exception_code` varchar(100) DEFAULT NULL COMMENT '异常码',
  `exception_detail` varchar(2000) DEFAULT NULL COMMENT '异常描述',
  `time_consuming` bigint(20) DEFAULT NULL COMMENT '请求耗时',
  `user_agent` varchar(500) DEFAULT NULL COMMENT '客户端信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=478 DEFAULT CHARSET=utf8 COMMENT='系统日志表';


-- ----------------------------
-- Table structure for `permission`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '资源名称',
  `type` varchar(32) DEFAULT NULL COMMENT '资源类型：0,1,2(目录,菜单or按钮)',
  `url` varchar(255) DEFAULT NULL COMMENT '访问url地址',
  `perms` varchar(128) DEFAULT NULL COMMENT '权限代码字符串',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父节点id',
  `parent_name` varchar(168) DEFAULT NULL COMMENT '父节点名称',
  `parent_ids` varchar(128) DEFAULT NULL COMMENT '父节点id列表串，用/分割',
  `icon` varchar(168) DEFAULT NULL COMMENT '图标',
  `sort` bigint(20) DEFAULT NULL COMMENT '排序号',
  `is_lock` bit(1) DEFAULT NULL COMMENT '账号是否锁定，1：锁定，0未锁定',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8 COMMENT='权限表';


-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '注解',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `perms` varchar(168) DEFAULT NULL COMMENT '角色标识',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='角色表';


-- ----------------------------
-- Table structure for `role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `permission_id` bigint(20) NOT NULL COMMENT '权限id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2491 DEFAULT CHARSET=utf8 COMMENT='角色和权限中间表';


-- ----------------------------
-- Table structure for `schedule_job`
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job`;
CREATE TABLE `schedule_job` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `job_name` varchar(200) DEFAULT NULL COMMENT '任务名称',
  `job_group` varchar(200) DEFAULT NULL COMMENT '任务分组',
  `cron` varchar(200) DEFAULT NULL COMMENT '执行计划',
  `is_local` bit(1) DEFAULT NULL COMMENT '调度方式 false 远程 true 本地',
  `remote_request_method` varchar(10) DEFAULT NULL COMMENT '远程请求方式 只支持POST',
  `remote_url` varchar(200) DEFAULT NULL COMMENT '远程执行url',
  `bean_class` varchar(500) DEFAULT NULL COMMENT '执行类名称 包名+类名',
  `method_name` varchar(200) DEFAULT NULL COMMENT '执行方法名称',
  `params` varchar(500) DEFAULT NULL COMMENT '参数',
  `is_async` bit(1) DEFAULT NULL COMMENT '是否异步  0否 1是',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `modify_by` bigint(20) DEFAULT NULL COMMENT '修改者',
  `remarks` varchar(200) DEFAULT NULL COMMENT '描述',
  `status` int(11) DEFAULT NULL COMMENT '任务状态 0禁用 1启用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='任务调度';


-- ----------------------------
-- Table structure for `schedule_job_log`
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job_log`;
CREATE TABLE `schedule_job_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `job_id` bigint(20) DEFAULT NULL COMMENT '任务ID',
  `job_name` varchar(200) DEFAULT NULL COMMENT '任务名称',
  `job_group` varchar(200) DEFAULT NULL COMMENT '任务分组',
  `cron` varchar(200) DEFAULT NULL COMMENT '执行计划',
  `is_local` bit(1) DEFAULT NULL COMMENT '调度方式 false 远程 true 本地',
  `remote_request_method` varchar(10) DEFAULT NULL COMMENT '远程请求方式 只支持POST',
  `remote_url` varchar(200) DEFAULT NULL COMMENT '远程执行url',
  `bean_class` varchar(500) DEFAULT NULL COMMENT '执行类名称 包名+类名',
  `method_name` varchar(200) DEFAULT NULL COMMENT '执行方法名称',
  `job_trigger` varchar(255) DEFAULT NULL COMMENT '触发器',
  `params` varchar(500) DEFAULT NULL COMMENT '参数',
  `is_async` bit(1) DEFAULT NULL COMMENT '是否异步  0否 1是',
  `remarks` varchar(200) DEFAULT NULL COMMENT '描述',
  `status` int(11) DEFAULT NULL COMMENT '执行状态 0失败 1成功',
  `error` varchar(500) DEFAULT NULL COMMENT '失败信息',
  `times` bigint(20) DEFAULT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1689 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='任务调度执行日志';


-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) DEFAULT NULL COMMENT '账号',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `salt` varchar(64) DEFAULT NULL COMMENT '盐',
  `real_name` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `sex` bit(1) DEFAULT NULL COMMENT '性别  1 男  0 女',
  `mobile_phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `is_lock` bit(1) DEFAULT NULL COMMENT '账号是否锁定，1：锁定，0未锁定',
  `is_del` bit(1) DEFAULT NULL COMMENT '账号是否删除，1：删除，0未删除',
  `is_admin` bit(1) DEFAULT NULL COMMENT '是否是超级管理员',
  `login_time` datetime DEFAULT NULL COMMENT '最近一次登录时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8 COMMENT='用户表';


-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户和角色中间表';


-- ----------------------------
-- Table structure for `dept`
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `simple_name` varchar(50) DEFAULT NULL COMMENT '简称',
  `full_name` varchar(255) DEFAULT NULL COMMENT '全称',
  `is_parent` bit(1) DEFAULT NULL COMMENT '是否是父节点',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级部门ID，一级部门为0',
  `sort` bigint(20) DEFAULT NULL COMMENT '排序号',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='部门表';
