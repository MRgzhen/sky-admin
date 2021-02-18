/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : sky_admin_sys

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2021-02-05 17:20:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_datascope`
-- ----------------------------
DROP TABLE IF EXISTS `sys_datascope`;
CREATE TABLE `sys_datascope` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `role_id` varchar(64) NOT NULL COMMENT '角色id',
  `dept_id` varchar(64) NOT NULL COMMENT '部门id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色部门关系表';

-- ----------------------------
-- Records of sys_datascope
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_datascope_test`
-- ----------------------------
DROP TABLE IF EXISTS `sys_datascope_test`;
CREATE TABLE `sys_datascope_test` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `name` varchar(64) NOT NULL COMMENT '数据权限测试',
  `ds_dept` varchar(64) DEFAULT NULL COMMENT '部门id',
  `create_user` varchar(64) NOT NULL DEFAULT '' COMMENT '创建用户',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(64) NOT NULL DEFAULT '' COMMENT '更新用户',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_del` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除，0：未删除，1：删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据权限测试';

-- ----------------------------
-- Records of sys_datascope_test
-- ----------------------------
INSERT INTO `sys_datascope_test` VALUES ('1346381729290084354', '11', null, '1346377101420843010', '2021-01-05 17:03:13', '1346377101420843010', '2021-01-05 17:03:13', '0');
INSERT INTO `sys_datascope_test` VALUES ('1346382020542554114', '22', '1', '1346377101420843010', '2021-01-05 17:04:23', '1346377101420843010', '2021-01-05 17:08:14', '0');
INSERT INTO `sys_datascope_test` VALUES ('1346382105875668993', '33', '1328580333041303554', '1346377101420843010', '2021-01-05 17:05:42', '1346377101420843010', '2021-01-05 17:34:53', '0');
INSERT INTO `sys_datascope_test` VALUES ('1346382871050915842', '44', '1328580333041303554', '1346377101420843010', '2021-01-05 17:07:51', '1346377101420843010', '2021-01-05 17:07:51', '0');

-- ----------------------------
-- Table structure for `sys_dept`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `parent_id` varchar(64) NOT NULL DEFAULT '0' COMMENT '父部门，0代表顶级部门',
  `parent_ids` varchar(255) DEFAULT '0' COMMENT '父部门ids',
  `dept_name` varchar(64) NOT NULL DEFAULT '' COMMENT '部门名',
  `type` varchar(64) DEFAULT '0' COMMENT '部门类型',
  `enabled` char(1) NOT NULL DEFAULT '1' COMMENT '启用状态, 1：启用；0：禁用',
  `sort` int(2) NOT NULL DEFAULT '0' COMMENT '部门顺序， 由小到大',
  `level` varchar(255) DEFAULT NULL COMMENT '层级',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `create_user` varchar(64) NOT NULL DEFAULT '' COMMENT '创建用户',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(64) NOT NULL DEFAULT '' COMMENT '更新用户',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_del` char(2) NOT NULL DEFAULT '0' COMMENT '是否删除，0：未删除，1：删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1328579986692456450', '0', '0', '言玉公司', '1', '1', '1', null, '', '', '2020-11-17 14:05:27', '1', '2021-01-05 15:13:04', '0');
INSERT INTO `sys_dept` VALUES ('1328580032703971329', '1328579986692456450', '0', '研发部', '2', '1', '1', null, '', '', '2020-11-17 14:05:38', '1', '2021-01-05 15:13:15', '0');
INSERT INTO `sys_dept` VALUES ('1328580078212169730', '1328579986692456450', '0', '销售部', '2', '1', '2', null, '', '', '2020-11-17 14:05:49', '', '2020-11-17 14:05:49', '0');
INSERT INTO `sys_dept` VALUES ('1328580114069274626', '1328579986692456450', '0', '财务部', '2', '1', '3', null, '', '', '2020-11-17 14:05:57', '', '2020-11-17 14:05:57', '0');
INSERT INTO `sys_dept` VALUES ('1328580153894191105', '1328580032703971329', '0', '研发组', '3', '1', '1', null, '', '', '2020-11-17 14:06:07', '1', '2021-01-05 15:13:12', '0');
INSERT INTO `sys_dept` VALUES ('1328580333041303554', '1328580032703971329', '0', '美工组', '3', '1', '2', null, '', '', '2020-11-17 14:06:49', '1', '2021-01-05 15:13:13', '0');
INSERT INTO `sys_dept` VALUES ('1328580394039066626', '1328580032703971329', '0', '测试组', '3', '1', '3', null, '', '', '2020-11-17 14:07:04', '1', '2021-01-05 15:13:14', '0');
INSERT INTO `sys_dept` VALUES ('1328605826633396226', '1328580032703971329', '0', '运维组', '3', '1', '4', null, '', '', '2020-11-17 15:48:08', '1', '2021-01-14 09:34:43', '0');
INSERT INTO `sys_dept` VALUES ('1328904546759434241', '1328580078212169730', '0', '销售一组', '3', '1', '1', null, '', '', '2020-11-18 11:35:08', '', '2020-11-18 11:35:08', '0');

-- ----------------------------
-- Table structure for `sys_dic`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dic`;
CREATE TABLE `sys_dic` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `code` varchar(64) NOT NULL DEFAULT '' COMMENT '字典编码',
  `label` varchar(255) NOT NULL DEFAULT '' COMMENT '字典名称',
  `enabled` char(1) NOT NULL DEFAULT '0' COMMENT '启用状态, 1：启用；0：禁用',
  `type` char(1) NOT NULL DEFAULT '0' COMMENT '数据字典类型；1：系统数据字典（系统类不能删除）；0：业务数据字典',
  `sort` int(2) NOT NULL DEFAULT '0' COMMENT '部门顺序， 由小到大',
  `remark` varchar(255) DEFAULT '' COMMENT '数据字典描述',
  `create_user` varchar(64) NOT NULL DEFAULT '' COMMENT '创建用户',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(64) NOT NULL DEFAULT '' COMMENT '更新用户',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_del` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除，0：未删除，1：删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典';

-- ----------------------------
-- Records of sys_dic
-- ----------------------------
INSERT INTO `sys_dic` VALUES ('1328540525082394626', 'SYSTEM_DEPT', '部门类型', '1', '1', '1', '', '', '2020-11-17 11:28:38', '', '2020-11-17 11:28:38', '0');
INSERT INTO `sys_dic` VALUES ('1328541116554756098', 'SYSTEM_SEX', '性别', '1', '1', '1', '', '', '2020-11-17 11:30:59', '', '2020-11-17 11:30:59', '0');

-- ----------------------------
-- Table structure for `sys_dic_item`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dic_item`;
CREATE TABLE `sys_dic_item` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `code` varchar(64) NOT NULL DEFAULT '' COMMENT '字典编码',
  `value` varchar(64) DEFAULT '' COMMENT '字典项值',
  `name` varchar(255) DEFAULT '' COMMENT '字典项名称',
  `enabled` char(1) NOT NULL DEFAULT '0' COMMENT '启用状态, 1：启用；0：禁用',
  `is_default` char(1) DEFAULT '0' COMMENT '默认项（数据子项可以设置默认项，有且只有一个）, 1：默认；0：不是默认',
  `sort` int(2) NOT NULL DEFAULT '0' COMMENT '部门顺序， 由小到大',
  `remark` varchar(255) DEFAULT '' COMMENT '数据字典描述',
  `create_user` varchar(64) NOT NULL DEFAULT '' COMMENT '创建用户',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(64) NOT NULL DEFAULT '' COMMENT '更新用户',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_del` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除，0：未删除，1：删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典项';

-- ----------------------------
-- Records of sys_dic_item
-- ----------------------------
INSERT INTO `sys_dic_item` VALUES ('1328541009650335745', 'SYSTEM_DEPT', '1', '公司', '1', '0', '1', '', '', '2020-11-17 11:30:34', '', '2020-11-17 11:30:34', '0');
INSERT INTO `sys_dic_item` VALUES ('1328541034690330626', 'SYSTEM_DEPT', '2', '部门', '1', '0', '1', '', '', '2020-11-17 11:30:40', '', '2020-11-17 11:30:40', '0');
INSERT INTO `sys_dic_item` VALUES ('1328541057880637442', 'SYSTEM_DEPT', '3', '小组', '1', '0', '1', '', '', '2020-11-17 11:30:45', '', '2020-11-17 11:30:45', '0');
INSERT INTO `sys_dic_item` VALUES ('1328541176180981761', 'SYSTEM_SEX', '1', '男', '1', '1', '1', '', '', '2020-11-17 11:31:14', '', '2020-11-17 11:31:14', '0');
INSERT INTO `sys_dic_item` VALUES ('1328541198662451201', 'SYSTEM_SEX', '2', '女', '1', '0', '1', '', '', '2020-11-17 11:31:19', '', '2020-11-17 11:31:19', '0');
INSERT INTO `sys_dic_item` VALUES ('1328541225791209473', 'SYSTEM_SEX', '3', '未知', '1', '0', '1', '', '', '2020-11-17 11:31:25', '', '2020-11-17 11:31:25', '0');

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `parent_id` varchar(64) DEFAULT '0' COMMENT '菜单父id，0代表顶级菜单',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '菜单名',
  `path` varchar(255) NOT NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) DEFAULT '' COMMENT '组件路径',
  `icon` varchar(64) DEFAULT '' COMMENT '菜单图标',
  `type` char(1) NOT NULL DEFAULT '3' COMMENT '类型，0:目录，1：菜单，2：按钮，3：其他',
  `enabled` char(1) NOT NULL DEFAULT '1' COMMENT '状态，1：正常，0：冻结',
  `sort` int(2) NOT NULL DEFAULT '0' COMMENT '菜单顺序， 由小到大',
  `level` varchar(255) DEFAULT '' COMMENT '层级',
  `perms` varchar(255) DEFAULT '' COMMENT '权限标识，模块:业务:功能，如：system:user:add',
  `create_user` varchar(64) NOT NULL DEFAULT '' COMMENT '创建用户',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(64) NOT NULL DEFAULT '' COMMENT '更新用户',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_del` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除，0：未删除，1：删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统菜单';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1328889388452499457', '0', '系统工具', '', '', 'el-icon-setting', '0', '1', '1', '', '', '', '2020-11-18 10:34:54', '', '2020-11-18 10:35:52', '1');
INSERT INTO `sys_menu` VALUES ('1328919411234390017', '20002', '新增用户', '', '', '', '2', '1', '1', '', 'system:user:add', '', '2020-11-18 12:34:12', '', '2020-11-18 12:34:12', '0');
INSERT INTO `sys_menu` VALUES ('1328933803850153986', '0', '系统工具', '', '', 'el-icon-setting', '0', '1', '4', '', '', '', '2020-11-18 13:31:23', '', '2020-11-25 09:38:21', '0');
INSERT INTO `sys_menu` VALUES ('1328934081689239553', '0', '代码生成配置', '', '', 'el-icon-setting', '0', '1', '3', '', '', '', '2020-11-18 13:32:30', '', '2020-11-19 16:56:16', '0');
INSERT INTO `sys_menu` VALUES ('1328936949733343234', '1328934081689239553', '模板配置', 'generator/template', 'generator/template', 'el-icon-setting', '1', '1', '4', '', '', '', '2020-11-18 13:43:53', '', '2020-11-19 16:58:38', '0');
INSERT INTO `sys_menu` VALUES ('1328944968550531073', '1328934081689239553', '数据源配置', 'generator/ds', 'generator/ds', 'el-icon-s-tools', '1', '1', '1', '', '', '', '2020-11-18 14:15:45', '', '2020-11-18 14:15:45', '0');
INSERT INTO `sys_menu` VALUES ('1328947862540599297', '1328934081689239553', '代码生成', 'generator/code', 'generator/code', 'el-icon-s-tools', '1', '1', '5', '', '', '', '2020-11-18 14:27:15', '', '2020-11-19 16:58:43', '0');
INSERT INTO `sys_menu` VALUES ('1329279600668102657', '1328934081689239553', '参数配置', 'generator/attr', 'generator/attr', 'el-icon-menu', '1', '1', '2', '', '', '', '2020-11-19 12:25:28', '1', '2021-01-08 10:41:34', '0');
INSERT INTO `sys_menu` VALUES ('1329348249584111618', '1328934081689239553', '模板命名空间', 'generator/template-group', 'generator/templateGroup', 'el-icon-platform-eleme', '1', '1', '3', '', '', '', '2020-11-19 16:58:15', '', '2020-11-19 16:58:29', '0');
INSERT INTO `sys_menu` VALUES ('1331411824175710210', '1328933803850153986', '接口文档', '/tool/swagger', 'http://localhost:8081/swagger-ui.html', 'el-icon-menu', '1', '1', '1', '', '', '', '2020-11-25 09:38:10', '', '2020-11-25 09:38:22', '0');
INSERT INTO `sys_menu` VALUES ('1346360631152361473', '20000', '数据权限测试', 'sys/ds-test', 'sys/dsTest', 'el-icon-platform-eleme', '1', '1', '8', '', '', '1', '2021-01-05 15:39:23', '1346377101420843010', '2021-01-05 17:03:01', '0');
INSERT INTO `sys_menu` VALUES ('20000', '0', '系统管理', 'sys-perm', '', 'el-icon-location', '0', '1', '2', '0', '', '1', '2020-07-01 14:43:36', 'admin', '2020-11-17 11:45:02', '0');
INSERT INTO `sys_menu` VALUES ('20001', '20000', '组织架构', 'sys/dept', 'sys/dept', 'el-icon-location', '1', '1', '1', null, 'system:dept:search', '1', '2020-08-12 18:24:41', '', '2020-11-17 11:45:36', '0');
INSERT INTO `sys_menu` VALUES ('20002', '20000', '用户管理', 'sys/user', 'sys/user', 'el-icon-location', '1', '1', '2', '', '', '', '2020-11-09 16:13:51', '', '2020-11-17 11:45:30', '0');
INSERT INTO `sys_menu` VALUES ('20003', '20000', '角色管理', 'sys/role', 'sys/role', 'el-icon-location', '1', '1', '4', '0.1', 'system:role:search', '1', '2020-07-06 08:38:04', 'admin', '2020-11-17 11:45:57', '0');
INSERT INTO `sys_menu` VALUES ('20004', '20000', '菜单管理', 'sys/menu', 'sys/menu', 'el-icon-location', '1', '1', '5', '0.1', 'system:menu:search', '1', '2020-07-06 08:38:45', 'admin', '2020-11-17 11:45:58', '0');
INSERT INTO `sys_menu` VALUES ('20005', '20000', '权限管理', 'sys/perm', 'sys/perm', 'el-icon-location', '1', '1', '6', '0.1', 'system:perm:search', '1', '2020-07-07 17:49:29', '1', '2021-01-05 15:40:25', '0');
INSERT INTO `sys_menu` VALUES ('20006', '20000', '数据字典', 'sys/dic', 'sys/dic', 'el-icon-reading', '1', '1', '7', null, 'system:dic:search', '1', '2020-08-06 13:33:33', '1346377101420843010', '2021-01-05 17:03:05', '0');
INSERT INTO `sys_menu` VALUES ('30000', '0', '文件管理', '', '', 'el-icon-location', '0', '1', '4', '', '', '1', '2020-09-28 15:34:00', '', '2020-11-18 17:21:33', '0');
INSERT INTO `sys_menu` VALUES ('30001', '30000', '文件管理', 'info', 'file/info', 'el-icon-location', '1', '1', '1', '', 'file:info:list', '1', '2020-09-28 15:35:08', '', '2020-11-17 11:46:21', '0');
INSERT INTO `sys_menu` VALUES ('30002', '30000', '文件上传', 'upload', 'file/upload', 'el-icon-location', '1', '1', '1', '', 'file:upload:list', '1', '2020-09-29 17:19:38', '', '2020-11-17 11:46:21', '0');

-- ----------------------------
-- Table structure for `sys_post`
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `parent_id` varchar(64) NOT NULL DEFAULT '' COMMENT '上级岗位',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '岗位名',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `tenant_code` varchar(64) NOT NULL DEFAULT '' COMMENT '租户编码',
  `create_user` varchar(64) NOT NULL DEFAULT '' COMMENT '创建用户',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(64) NOT NULL DEFAULT '' COMMENT '更新用户',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_del` int(2) NOT NULL DEFAULT '0' COMMENT '是否删除，0：未删除，1：删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='职位';

-- ----------------------------
-- Records of sys_post
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `code` varchar(64) NOT NULL DEFAULT '' COMMENT '角色编码',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '角色名',
  `type` char(1) NOT NULL DEFAULT '0' COMMENT '用户类型',
  `data_scope` char(1) NOT NULL DEFAULT '2' COMMENT '数据范围 1:本人，2：本部门，3：本部门及下级部门，4：全部，5：自定义数据权限',
  `enabled` char(1) NOT NULL DEFAULT '1' COMMENT '启用状态, 1：启用；0：禁用',
  `create_user` varchar(64) NOT NULL DEFAULT '' COMMENT '创建用户',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(64) NOT NULL DEFAULT '' COMMENT '更新用户',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_del` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除，0：未删除，1：删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `role_id` varchar(64) NOT NULL COMMENT '角色id',
  `menu_id` varchar(64) NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色菜单关系表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_role_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `role_id` varchar(64) NOT NULL COMMENT '角色id',
  `user_id` varchar(64) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色用户关系表';

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_social`
-- ----------------------------
DROP TABLE IF EXISTS `sys_social`;
CREATE TABLE `sys_social` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `user_id` varchar(255) DEFAULT '' COMMENT '用户表中的id',
  `app` varchar(255) NOT NULL COMMENT '社交标识，如：weixin，qq',
  `open_id` varchar(255) NOT NULL COMMENT '社交凭证，如：openid',
  `union_id` varchar(255) DEFAULT '' COMMENT '社交凭证，如：unionid',
  `nick_name` varchar(255) DEFAULT '' COMMENT '昵称',
  `avatar` varchar(512) DEFAULT '' COMMENT '头像地址',
  `company` varchar(255) DEFAULT '' COMMENT '机构',
  `address` varchar(255) DEFAULT '' COMMENT '地址',
  `sex` char(2) DEFAULT '' COMMENT '性别',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `access_token` varchar(512) NOT NULL COMMENT 'token',
  `refresh_token` varchar(512) DEFAULT NULL COMMENT '刷新token',
  `expire_time` bigint(20) DEFAULT NULL COMMENT 'token有效时间',
  `create_user` varchar(64) DEFAULT '' COMMENT '创建用户',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(64) DEFAULT '' COMMENT '更新用户',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_del` char(1) DEFAULT '0' COMMENT '是否删除，0：未删除，1：删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `app_openId_key` (`app`,`open_id`) USING BTREE,
  UNIQUE KEY `userid_app_key` (`user_id`,`app`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='社交';

-- ----------------------------
-- Records of sys_social
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `user_name` varchar(64) NOT NULL DEFAULT '' COMMENT '登录名',
  `password` varchar(64) NOT NULL DEFAULT '' COMMENT '密码',
  `nick_name` varchar(64) NOT NULL DEFAULT '' COMMENT '昵称',
  `real_name` varchar(64) DEFAULT '' COMMENT '真实姓名',
  `sex` varchar(64) DEFAULT '0' COMMENT '性别，数据字典',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `avatar` varchar(255) DEFAULT '' COMMENT '头像地址',
  `phone` varchar(32) DEFAULT '' COMMENT '手机号',
  `eamil` varchar(64) DEFAULT '' COMMENT 'email地址',
  `is_sys` char(1) DEFAULT '0' COMMENT '是否是超级管理员用户；0：不是；1：是',
  `type` char(1) DEFAULT '0' COMMENT '用户类型',
  `enabled` char(1) DEFAULT '1' COMMENT '启用状态, 1：启用；0：禁用',
  `password_valid` datetime NOT NULL COMMENT '密码失效时间',
  `dept_id` varchar(64) NOT NULL DEFAULT '' COMMENT '部门id',
  `post_id` varchar(64) DEFAULT '' COMMENT '职位id',
  `create_user` varchar(64) NOT NULL DEFAULT '' COMMENT '创建用户',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(64) NOT NULL DEFAULT '' COMMENT '更新用户',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_del` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除，0：未删除，1：删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '$2a$10$hYEir1NMegwQGyZOX15.Oercs5bulUbrA2BhemDILjpbUC/FAtcpm', '管理员', '管理员', '3', '2020-07-01 13:50:26', '', '18882222221', '8896@qq.com', '1', '0', '1', '2033-11-01 13:50:44', '', '000000', '1', '2020-07-01 13:51:08', 'admin', '2020-11-17 15:02:06', '0');

-- ----------------------------
-- Table structure for `sys_user_post`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `user_id` varchar(64) NOT NULL COMMENT '用户id',
  `post_id` varchar(64) NOT NULL COMMENT '岗位id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户岗位关系表';

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
