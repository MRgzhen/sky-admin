/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : sky_admin_generator

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2021-02-05 17:21:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `gen_attr`
-- ----------------------------
DROP TABLE IF EXISTS `gen_attr`;
CREATE TABLE `gen_attr` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `template_group_id` varchar(64) NOT NULL DEFAULT '' COMMENT '分组id',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '属性名',
  `value` varchar(64) NOT NULL DEFAULT '' COMMENT '属性值',
  `remark` varchar(64) NOT NULL DEFAULT '' COMMENT '描述',
  `type` char(4) NOT NULL DEFAULT '1' COMMENT '类型：1：自定义，2：默认',
  `sort` int(2) NOT NULL DEFAULT '0' COMMENT '排序',
  `create_user` varchar(64) NOT NULL DEFAULT '' COMMENT '创建用户',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(64) NOT NULL DEFAULT '' COMMENT '更新用户',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_del` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除，0：未删除，1：删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模板属性';

-- ----------------------------
-- Records of gen_attr
-- ----------------------------
INSERT INTO `gen_attr` VALUES ('1', 'DEFAULT', 'javaBeanName', 'DEFAULT', 'java Bean名字', '2', '1', '', '2021-01-08 10:15:40', '', '2021-01-08 15:35:43', '0');
INSERT INTO `gen_attr` VALUES ('1347386174098489346', '1329644366620233730', 'test', 'test', 'test', '1', '1', '', '2021-01-08 11:34:31', '', '2021-01-08 11:34:31', '0');
INSERT INTO `gen_attr` VALUES ('2', 'DEFAULT', 'javaPackageName', 'DEFAULT', 'java包名', '2', '2', '', '2021-01-08 10:16:20', '', '2021-01-08 15:35:45', '0');
INSERT INTO `gen_attr` VALUES ('22', '1329644366620233730', 'zhangsan', 'test', '3', '1', '1', '', '2021-01-08 13:48:23', '', '2021-01-08 13:48:36', '0');
INSERT INTO `gen_attr` VALUES ('3', 'DEFAULT', 'javaModuleName', 'DEFAULT', 'java模块名', '2', '3', '', '2021-01-08 10:16:38', '', '2021-01-08 15:35:46', '0');
INSERT INTO `gen_attr` VALUES ('4', 'DEFAULT', 'author', 'DEFAULT', '作者', '2', '4', '', '2020-11-19 13:18:10', '', '2021-01-08 15:35:48', '0');
INSERT INTO `gen_attr` VALUES ('5', 'DEFAULT', 'date', 'DEFAULT', '创建时间', '2', '5', '', '2021-01-08 10:17:22', '', '2021-01-08 15:35:48', '0');
INSERT INTO `gen_attr` VALUES ('6', 'DEFAULT', 'tableDesc', 'DEFAULT', '表描述', '2', '6', '', '2021-01-08 10:17:38', '', '2021-01-08 15:35:49', '0');
INSERT INTO `gen_attr` VALUES ('7', 'DEFAULT', 'tableName', 'DEFAULT', '表名', '2', '7', '', '2021-01-08 10:17:54', '', '2021-01-08 15:35:50', '0');
INSERT INTO `gen_attr` VALUES ('8', 'DEFAULT', 'tableColumns', 'DEFAULT', '表信息', '2', '8', '', '2021-01-08 10:18:57', '', '2021-01-08 15:35:50', '0');
INSERT INTO `gen_attr` VALUES ('9', 'DEFAULT', 'tableColumns[columnName]', 'DEFAULT', 'java属性类型', '2', '9', '', '2021-01-08 10:19:42', '', '2021-01-08 15:35:51', '0');
INSERT INTO `gen_attr` VALUES ('90', 'DEFAULT', 'tableColumns[attrName]', 'DEFAULT', 'java属性名', '2', '10', '', '2021-01-08 10:20:26', '', '2021-01-08 15:35:52', '0');
INSERT INTO `gen_attr` VALUES ('91', 'DEFAULT', 'tableColumns[attrDesc]', 'DEFAULT', 'java属性描述', '2', '11', '', '2021-01-08 10:20:30', '', '2021-01-08 15:35:53', '0');
INSERT INTO `gen_attr` VALUES ('92', 'DEFAULT', 'tableColumns[columnName]', 'DEFAULT', '表字段列名', '2', '12', '', '2021-01-08 10:20:34', '', '2021-01-08 15:35:54', '0');

-- ----------------------------
-- Table structure for `gen_ds_setting`
-- ----------------------------
DROP TABLE IF EXISTS `gen_ds_setting`;
CREATE TABLE `gen_ds_setting` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '数据库名字',
  `nick_name` varchar(64) NOT NULL DEFAULT '' COMMENT '数据库昵称（租户中唯一）',
  `driver` varchar(255) NOT NULL DEFAULT '' COMMENT '驱动',
  `url` varchar(255) NOT NULL DEFAULT '' COMMENT '连接地址',
  `username` varchar(100) NOT NULL DEFAULT '' COMMENT '连接用户名',
  `password` varchar(100) NOT NULL DEFAULT '' COMMENT '连接密码',
  `ds_dept` varchar(64) NOT NULL DEFAULT '' COMMENT '部门id',
  `create_user` varchar(64) NOT NULL DEFAULT '' COMMENT '创建用户',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(64) NOT NULL DEFAULT '' COMMENT '更新用户',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_del` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除，0：未删除，1：删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据库配置';

-- ----------------------------
-- Records of gen_ds_setting
-- ----------------------------
INSERT INTO `gen_ds_setting` VALUES ('1329249639961907201', 'shiro', 'shiro', 'com.mysql.cj.jdbc.Driver', 'jdbc:mysql://127.0.0.1:3306/shiro?serverTimezone=GMT%2b8&useUnicode=true&characterEncoding=utf-8&useSSL=true', 'root', 'root', '', '', '2020-11-19 10:26:25', '', '2020-12-03 17:03:44', '0');
INSERT INTO `gen_ds_setting` VALUES ('1347339990067679233', 'sky_pms', 'sky_pms', 'com.mysql.cj.jdbc.Driver', 'jdbc:mysql://127.0.0.1:3306/sky_mall_pms?serverTimezone=GMT%2b8&useUnicode=true&characterEncoding=utf-8&useSSL=true', 'root', 'root', '', '1', '2021-01-08 08:31:01', '1', '2021-01-08 08:31:01', '0');
INSERT INTO `gen_ds_setting` VALUES ('1351765395881111553', 'sky_admin_sys', 'sky_admin_sys', 'com.mysql.cj.jdbc.Driver', 'jdbc:mysql://127.0.0.1:3306/sky_admin_sys?serverTimezone=GMT%2b8&useUnicode=true&characterEncoding=utf-8&useSSL=true', 'root', 'root', '', '1', '2021-01-20 13:36:01', '1', '2021-01-20 13:36:01', '0');

-- ----------------------------
-- Table structure for `gen_template`
-- ----------------------------
DROP TABLE IF EXISTS `gen_template`;
CREATE TABLE `gen_template` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `template_group_id` varchar(64) NOT NULL DEFAULT '' COMMENT '分组id',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '模板名',
  `module_name` varchar(64) NOT NULL DEFAULT '' COMMENT '模块名',
  `remark` varchar(255) DEFAULT '' COMMENT '模板描述',
  `ds_dept` varchar(64) NOT NULL DEFAULT '' COMMENT '部门id',
  `create_user` varchar(64) NOT NULL DEFAULT '' COMMENT '创建用户',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(64) NOT NULL DEFAULT '' COMMENT '更新用户',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_del` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除，0：未删除，1：删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模板信息';

-- ----------------------------
-- Records of gen_template
-- ----------------------------
INSERT INTO `gen_template` VALUES ('1', '1329644366620233730', '${NAME}.java', 'bean.po', '实体类', '', '', '2020-11-27 14:58:24', '1', '2021-01-08 17:01:55', '0');
INSERT INTO `gen_template` VALUES ('1334762314154303489', '1329644366620233730', '${NAME}Controller.java', 'controller', '前端控制器', '', '1', '2020-12-04 15:31:49', '1', '2021-01-08 17:01:23', '0');
INSERT INTO `gen_template` VALUES ('1334762511672467457', '1329644366620233730', '${NAME}Mapper.java', 'dao', 'Mapper接口', '', '1', '2020-12-04 15:32:36', '1', '2021-01-08 17:01:44', '0');
INSERT INTO `gen_template` VALUES ('1334762667490861058', '1329644366620233730', 'I${NAME}Service.java', 'service', '业务接口', '', '1', '2020-12-04 15:33:13', '1', '2021-01-08 17:01:49', '0');
INSERT INTO `gen_template` VALUES ('1334762796813836289', '1329644366620233730', '${NAME}ServiceImpl.java', 'service.impl', '业务实现类', '', '1', '2020-12-04 15:33:44', '1', '2021-01-08 17:01:39', '0');
INSERT INTO `gen_template` VALUES ('1334770758089416705', '1329644366620233730', '${NAME}Mapper.xml', 'mapper', 'MapperXml', '', '1', '2020-12-04 16:05:22', '1', '2021-01-08 17:01:32', '0');
INSERT INTO `gen_template` VALUES ('1347454524065411073', '1329644366620233730', 'add.vue', 'vue.${PATH}', '新增', '', '1', '2021-01-08 16:06:08', '1', '2021-01-08 17:07:15', '0');
INSERT INTO `gen_template` VALUES ('1347454756299829250', '1329644366620233730', 'index.vue', 'vue.${PATH}', '前端index', '', '1', '2021-01-08 16:07:03', '1', '2021-01-08 17:07:24', '0');
INSERT INTO `gen_template` VALUES ('1347454869571203074', '1329644366620233730', 'rule.js', 'vue.${PATH}', '', '', '1', '2021-01-08 16:07:30', '1', '2021-01-08 17:04:02', '0');
INSERT INTO `gen_template` VALUES ('1347454967986352130', '1329644366620233730', 'api.js', 'vue.${PATH}', '', '', '1', '2021-01-08 16:07:54', '1', '2021-01-08 17:07:33', '0');

-- ----------------------------
-- Table structure for `gen_template_desc`
-- ----------------------------
DROP TABLE IF EXISTS `gen_template_desc`;
CREATE TABLE `gen_template_desc` (
  `template_id` varchar(64) NOT NULL COMMENT '主键',
  `content` longtext NOT NULL COMMENT '模板内容',
  `ds_dept` varchar(64) NOT NULL DEFAULT '' COMMENT '部门id',
  `create_user` varchar(64) NOT NULL DEFAULT '' COMMENT '创建用户',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(64) NOT NULL DEFAULT '' COMMENT '更新用户',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_del` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除，0：未删除，1：删除',
  PRIMARY KEY (`template_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模板信息';

-- ----------------------------
-- Records of gen_template_desc
-- ----------------------------
INSERT INTO `gen_template_desc` VALUES ('1', 'package ${javaPackageName}.${javaModuleName}.bean.po;\n\nimport com.baomidou.mybatisplus.annotation.TableName;\nimport io.swagger.annotations.ApiModel;\nimport io.swagger.annotations.ApiModelProperty;\nimport lombok.Data;\nimport java.io.Serializable;\n\n/**\n * 功能: ${tableDesc} 实体类\n * @author ${author}\n * @date ${date}\n */\n@Data\n@TableName(\"${tableName}\")\n@ApiModel(value = \"${javaBeanName}对象}\", description = \"${tableDesc}\")\npublic class ${javaBeanName} implements Serializable {\n\n    private static final long serialVersionUID = 1L;\n\n    #foreach ($column in $tableColumns)\n@ApiModelProperty(value = \"${column.attrDesc}\")\n    private $column.attrType $column.attrName;\n    #end\n}', '', '', '2020-11-27 14:58:38', '1', '2021-01-08 17:01:55', '0');
INSERT INTO `gen_template_desc` VALUES ('1334755397470224386', 'package ${javaPackageName}.${javaModuleName}.bean.po;\r\n\r\nimport com.baomidou.mybatisplus.annotation.TableName;\r\nimport io.swagger.annotations.ApiModel;\r\nimport io.swagger.annotations.ApiModelProperty;\r\nimport lombok.Data;\r\n\r\n/**\r\n * <br/>功能: ${tableDesc} 实体类\r\n * <br/>版本: 1.0\r\n * <br/>开发人员: ${author}\r\n * <br/>创建日期: ${date}\r\n * <br/>修改日期: ${date}\r\n * <br/>修改列表:\r\n */\r\n@Data\r\n@TableName(\"${tableName}\")\r\n@ApiModel(value = \"${javaBeanName}对象}\", description = \"${tableDesc}\")\r\npublic class ${javaBeanName} implements Serializable {\r\n\r\n    private static final long serialVersionUID = 1L;\r\n\r\n    #foreach ($column in $tableColumns)\r\n    @ApiModelProperty(value = \"${column.attrDesc}\")\r\n    private $column.attrType $column.attrName;\r\n    #end\r\n}\r\n', '', '', '2020-12-04 15:04:19', '', '2020-12-04 15:04:19', '0');
INSERT INTO `gen_template_desc` VALUES ('1334755490541830146', 'package ${javaPackageName}.${javaModuleName}.bean.po;\r\n\r\nimport com.baomidou.mybatisplus.annotation.TableName;\r\nimport io.swagger.annotations.ApiModel;\r\nimport io.swagger.annotations.ApiModelProperty;\r\nimport lombok.Data;\r\n\r\n/**\r\n * <br/>功能: ${tableDesc} 实体类\r\n * <br/>版本: 1.0\r\n * <br/>开发人员: ${author}\r\n * <br/>创建日期: ${date}\r\n * <br/>修改日期: ${date}\r\n * <br/>修改列表:\r\n */\r\n@Data\r\n@TableName(\"${tableName}\")\r\n@ApiModel(value = \"${javaBeanName}对象}\", description = \"${tableDesc}\")\r\npublic class ${javaBeanName} implements Serializable {\r\n\r\n    private static final long serialVersionUID = 1L;\r\n\r\n    #foreach ($column in $tableColumns)\r\n    @ApiModelProperty(value = \"${column.attrDesc}\")\r\n    private $column.attrType $column.attrName;\r\n    #end\r\n}\r\n', '', '', '2020-12-04 15:04:42', '', '2020-12-04 15:04:42', '0');
INSERT INTO `gen_template_desc` VALUES ('1334755659710693378', 'package ${javaPackageName}.${javaModuleName}.bean.po;\r\n\r\nimport com.baomidou.mybatisplus.annotation.TableName;\r\nimport io.swagger.annotations.ApiModel;\r\nimport io.swagger.annotations.ApiModelProperty;\r\nimport lombok.Data;\r\n\r\n/**\r\n * <br/>功能: ${tableDesc} 实体类\r\n * <br/>版本: 1.0\r\n * <br/>开发人员: ${author}\r\n * <br/>创建日期: ${date}\r\n * <br/>修改日期: ${date}\r\n * <br/>修改列表:\r\n */\r\n@Data\r\n@TableName(\"${tableName}\")\r\n@ApiModel(value = \"${javaBeanName}对象}\", description = \"${tableDesc}\")\r\npublic class ${javaBeanName} implements Serializable {\r\n\r\n    private static final long serialVersionUID = 1L;\r\n\r\n    #foreach ($column in $tableColumns)\r\n    @ApiModelProperty(value = \"${column.attrDesc}\")\r\n    private $column.attrType $column.attrName;\r\n    #end\r\n}\r\n', '', '', '2020-12-04 15:05:22', '', '2020-12-04 15:05:22', '0');
INSERT INTO `gen_template_desc` VALUES ('1334755947398012929', 'package ${javaPackageName}.${javaModuleName}.bean.po;\r\n\r\nimport com.baomidou.mybatisplus.annotation.TableName;\r\nimport io.swagger.annotations.ApiModel;\r\nimport io.swagger.annotations.ApiModelProperty;\r\nimport lombok.Data;\r\n\r\n/**\r\n * <br/>功能: ${tableDesc} 实体类\r\n * <br/>版本: 1.0\r\n * <br/>开发人员: ${author}\r\n * <br/>创建日期: ${date}\r\n * <br/>修改日期: ${date}\r\n * <br/>修改列表:\r\n */\r\n@Data\r\n@TableName(\"${tableName}\")\r\n@ApiModel(value = \"${javaBeanName}对象}\", description = \"${tableDesc}\")\r\npublic class ${javaBeanName} implements Serializable {\r\n\r\n    private static final long serialVersionUID = 1L;\r\n\r\n    #foreach ($column in $tableColumns)\r\n    @ApiModelProperty(value = \"${column.attrDesc}\")\r\n    private $column.attrType $column.attrName;\r\n    #end\r\n}\r\n', '', '', '2020-12-04 15:06:31', '', '2020-12-04 15:06:31', '0');
INSERT INTO `gen_template_desc` VALUES ('1334756597225721858', 'package ${javaPackageName}.${javaModuleName}.bean.po;\r\n\r\nimport com.baomidou.mybatisplus.annotation.TableName;\r\nimport io.swagger.annotations.ApiModel;\r\nimport io.swagger.annotations.ApiModelProperty;\r\nimport lombok.Data;\r\n\r\n/**\r\n * <br/>功能: ${tableDesc} 实体类\r\n * <br/>版本: 1.0\r\n * <br/>开发人员: ${author}\r\n * <br/>创建日期: ${date}\r\n * <br/>修改日期: ${date}\r\n * <br/>修改列表:\r\n */\r\n@Data\r\n@TableName(\"${tableName}\")\r\n@ApiModel(value = \"${javaBeanName}对象}\", description = \"${tableDesc}\")\r\npublic class ${javaBeanName} implements Serializable {\r\n\r\n    private static final long serialVersionUID = 1L;\r\n\r\n    #foreach ($column in $tableColumns)\r\n    @ApiModelProperty(value = \"${column.attrDesc}\")\r\n    private $column.attrType $column.attrName;\r\n    #end\r\n}\r\n', '', '', '2020-12-04 15:09:06', '', '2020-12-04 15:09:06', '0');
INSERT INTO `gen_template_desc` VALUES ('1334756706785136641', 'package ${javaPackageName}.${javaModuleName}.bean.po;\r\n\r\nimport com.baomidou.mybatisplus.annotation.TableName;\r\nimport io.swagger.annotations.ApiModel;\r\nimport io.swagger.annotations.ApiModelProperty;\r\nimport lombok.Data;\r\n\r\n/**\r\n * <br/>功能: ${tableDesc} 实体类\r\n * <br/>版本: 1.0\r\n * <br/>开发人员: ${author}\r\n * <br/>创建日期: ${date}\r\n * <br/>修改日期: ${date}\r\n * <br/>修改列表:\r\n */\r\n@Data\r\n@TableName(\"${tableName}\")\r\n@ApiModel(value = \"${javaBeanName}对象}\", description = \"${tableDesc}\")\r\npublic class ${javaBeanName} implements Serializable {\r\n\r\n    private static final long serialVersionUID = 1L;\r\n\r\n    #foreach ($column in $tableColumns)\r\n    @ApiModelProperty(value = \"${column.attrDesc}\")\r\n    private $column.attrType $column.attrName;\r\n    #end\r\n}\r\n', '', '', '2020-12-04 15:09:32', '', '2020-12-04 15:09:32', '0');
INSERT INTO `gen_template_desc` VALUES ('1334756988399095809', 'package ${javaPackageName}.${javaModuleName}.bean.po;\r\n\r\nimport com.baomidou.mybatisplus.annotation.TableName;\r\nimport io.swagger.annotations.ApiModel;\r\nimport io.swagger.annotations.ApiModelProperty;\r\nimport lombok.Data;\r\n\r\n/**\r\n * <br/>功能: ${tableDesc} 实体类\r\n * <br/>版本: 1.0\r\n * <br/>开发人员: ${author}\r\n * <br/>创建日期: ${date}\r\n * <br/>修改日期: ${date}\r\n * <br/>修改列表:\r\n */\r\n@Data\r\n@TableName(\"${tableName}\")\r\n@ApiModel(value = \"${javaBeanName}对象}\", description = \"${tableDesc}\")\r\npublic class ${javaBeanName} implements Serializable {\r\n\r\n    private static final long serialVersionUID = 1L;\r\n\r\n    #foreach ($column in $tableColumns)\r\n    @ApiModelProperty(value = \"${column.attrDesc}\")\r\n    private $column.attrType $column.attrName;\r\n    #end\r\n}\r\n', '', '', '2020-12-04 15:10:39', '', '2020-12-04 15:10:39', '0');
INSERT INTO `gen_template_desc` VALUES ('1334757325688246273', 'package ${javaPackageName}.${javaModuleName}.bean.po;\r\n\r\nimport com.baomidou.mybatisplus.annotation.TableName;\r\nimport io.swagger.annotations.ApiModel;\r\nimport io.swagger.annotations.ApiModelProperty;\r\nimport lombok.Data;\r\n\r\n/**\r\n * <br/>功能: ${tableDesc} 实体类\r\n * <br/>版本: 1.0\r\n * <br/>开发人员: ${author}\r\n * <br/>创建日期: ${date}\r\n * <br/>修改日期: ${date}\r\n * <br/>修改列表:\r\n */\r\n@Data\r\n@TableName(\"${tableName}\")\r\n@ApiModel(value = \"${javaBeanName}对象}\", description = \"${tableDesc}\")\r\npublic class ${javaBeanName} implements Serializable {\r\n\r\n    private static final long serialVersionUID = 1L;\r\n\r\n    #foreach ($column in $tableColumns)\r\n    @ApiModelProperty(value = \"${column.attrDesc}\")\r\n    private $column.attrType $column.attrName;\r\n    #end\r\n}\r\n', '', '', '2020-12-04 15:11:59', '', '2020-12-04 15:11:59', '0');
INSERT INTO `gen_template_desc` VALUES ('1334758561133088770', 'package ${javaPackageName}.${javaModuleName}.bean.po;\r\n\r\nimport com.baomidou.mybatisplus.annotation.TableName;\r\nimport io.swagger.annotations.ApiModel;\r\nimport io.swagger.annotations.ApiModelProperty;\r\nimport lombok.Data;\r\n\r\n/**\r\n * <br/>功能: ${tableDesc} 实体类\r\n * <br/>版本: 1.0\r\n * <br/>开发人员: ${author}\r\n * <br/>创建日期: ${date}\r\n * <br/>修改日期: ${date}\r\n * <br/>修改列表:\r\n */\r\n@Data\r\n@TableName(\"${tableName}\")\r\n@ApiModel(value = \"${javaBeanName}对象}\", description = \"${tableDesc}\")\r\npublic class ${javaBeanName} implements Serializable {\r\n\r\n    private static final long serialVersionUID = 1L;\r\n\r\n    #foreach ($column in $tableColumns)\r\n    @ApiModelProperty(value = \"${column.attrDesc}\")\r\n    private $column.attrType $column.attrName;\r\n    #end\r\n}\r\n', '', '1', '2020-12-04 15:16:54', '1', '2020-12-04 15:16:54', '0');
INSERT INTO `gen_template_desc` VALUES ('1334758713419878401', 'package ${javaPackageName}.${javaModuleName}.bean.po;\r\n\r\nimport com.baomidou.mybatisplus.annotation.TableName;\r\nimport io.swagger.annotations.ApiModel;\r\nimport io.swagger.annotations.ApiModelProperty;\r\nimport lombok.Data;\r\n\r\n/**\r\n * <br/>功能: ${tableDesc} 实体类\r\n * <br/>版本: 1.0\r\n * <br/>开发人员: ${author}\r\n * <br/>创建日期: ${date}\r\n * <br/>修改日期: ${date}\r\n * <br/>修改列表:\r\n */\r\n@Data\r\n@TableName(\"${tableName}\")\r\n@ApiModel(value = \"${javaBeanName}对象}\", description = \"${tableDesc}\")\r\npublic class ${javaBeanName} implements Serializable {\r\n\r\n    private static final long serialVersionUID = 1L;\r\n\r\n    #foreach ($column in $tableColumns)\r\n    @ApiModelProperty(value = \"${column.attrDesc}\")\r\n    private $column.attrType $column.attrName;\r\n    #end\r\n}\r\n', '', '1', '2020-12-04 15:17:31', '1', '2020-12-04 15:17:31', '0');
INSERT INTO `gen_template_desc` VALUES ('1334759621260201985', 'package ${javaPackageName}.${javaModuleName}.bean.po;\r\n\r\nimport com.baomidou.mybatisplus.annotation.TableName;\r\nimport io.swagger.annotations.ApiModel;\r\nimport io.swagger.annotations.ApiModelProperty;\r\nimport lombok.Data;\r\n\r\n/**\r\n * <br/>功能: ${tableDesc} 实体类\r\n * <br/>版本: 1.0\r\n * <br/>开发人员: ${author}\r\n * <br/>创建日期: ${date}\r\n * <br/>修改日期: ${date}\r\n * <br/>修改列表:\r\n */\r\n@Data\r\n@TableName(\"${tableName}\")\r\n@ApiModel(value = \"${javaBeanName}对象}\", description = \"${tableDesc}\")\r\npublic class ${javaBeanName} implements Serializable {\r\n\r\n    private static final long serialVersionUID = 1L;\r\n\r\n    #foreach ($column in $tableColumns)\r\n    @ApiModelProperty(value = \"${column.attrDesc}\")\r\n    private $column.attrType $column.attrName;\r\n    #end\r\n}\r\n', '', '1', '2020-12-04 15:21:07', '1', '2020-12-04 15:21:07', '0');
INSERT INTO `gen_template_desc` VALUES ('1334762314154303489', 'package ${javaPackageName}.${javaModuleName}.controller;\n\nimport com.baomidou.mybatisplus.core.metadata.IPage;\nimport com.github.mrgzhen.core.web.Result;\nimport ${javaPackageName}.${javaModuleName}.bean.po.${javaBeanName};\nimport ${javaPackageName}.${javaModuleName}.service.I${javaBeanName}Service;\nimport io.swagger.annotations.Api;\nimport io.swagger.annotations.ApiOperation;\nimport org.springframework.beans.factory.annotation.Autowired;\nimport org.springframework.web.bind.annotation.*;\n\nimport java.util.Arrays;\n\n/**\n * 功能: ${tableDesc} 前端控制器\n * @author ${author}\n * @date ${date}\n */\n@RestController\n@RequestMapping(\"/${javaModuleName}/${lowJavaBeanName}\")\n@Api(tags = \"${tableDesc}\")\npublic class ${javaBeanName}Controller {\n\n    @Autowired\n    private I${javaBeanName}Service ${lowJavaBeanName}Service;\n\n    @GetMapping(\"/list\")\n    @ApiOperation(value = \"查询${tableDesc}信息\")\n    public Result list(Integer pageNum, Integer pageSize, ${javaBeanName} vo) {\n        IPage<${javaBeanName}> page = ${lowJavaBeanName}Service.listPage(pageNum, pageSize, vo);\n        return Result.success(page);\n    }\n\n    @PostMapping(\"/add\")\n    @ApiOperation(value = \"新增${tableDesc}\")\n    public Result add(@RequestBody ${javaBeanName} vo) {\n        ${lowJavaBeanName}Service.save(vo);\n        return Result.success();\n    }\n\n    @PostMapping(\"/update\")\n    @ApiOperation(value = \"修改${tableDesc}\")\n    public Result update(@RequestBody ${javaBeanName} vo) {\n        ${lowJavaBeanName}Service.updateById(vo);\n        return Result.success();\n    }\n\n    @PostMapping(\"/delete\")\n    @ApiOperation(value = \"删除${tableDesc}\")\n    public Result delete(@RequestBody String[] ids) {\n        ${lowJavaBeanName}Service.removeByIds(Arrays.asList(ids));\n        return Result.success();\n    }\n}', '', '1', '2020-12-04 15:31:49', '1', '2021-01-08 17:01:23', '0');
INSERT INTO `gen_template_desc` VALUES ('1334762511672467457', 'package ${javaPackageName}.${javaModuleName}.dao;\n\nimport com.baomidou.mybatisplus.core.mapper.BaseMapper;\nimport com.yanyu.sky.pms.bean.po.${javaBeanName};\n\n/**\n * 功能: ${tableDesc} Mapper接口\n * @author ${author}\n * @date ${date}\n */\npublic interface ${javaBeanName}Mapper extends BaseMapper<${javaBeanName}> {\n\n}', '', '1', '2020-12-04 15:32:36', '1', '2021-01-08 17:01:44', '0');
INSERT INTO `gen_template_desc` VALUES ('1334762667490861058', 'package ${javaPackageName}.${javaModuleName}.service;\n\nimport com.baomidou.mybatisplus.core.metadata.IPage;\nimport com.baomidou.mybatisplus.extension.service.IService;\nimport ${javaPackageName}.${javaModuleName}.bean.po.${javaBeanName};\n\n/**\n * 功能: ${tableDesc} 业务接口\n * @author ${author}\n * @date ${date}\n */\npublic interface I${javaBeanName}Service extends IService<${javaBeanName}> {\n\n    IPage<${javaBeanName}> listPage(Integer pageNum, Integer pageSize, ${javaBeanName} vo);\n}', '', '1', '2020-12-04 15:33:13', '1', '2021-01-08 17:01:49', '0');
INSERT INTO `gen_template_desc` VALUES ('1334762796813836289', 'package ${javaPackageName}.${javaModuleName}.service.impl;\n\n\nimport com.baomidou.mybatisplus.core.metadata.IPage;\nimport com.baomidou.mybatisplus.core.toolkit.Wrappers;\nimport com.baomidou.mybatisplus.extension.plugins.pagination.Page;\nimport com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;\nimport ${javaPackageName}.${javaModuleName}.bean.po.${javaBeanName};\nimport ${javaPackageName}.${javaModuleName}.dao.${javaBeanName}Mapper;\nimport ${javaPackageName}.${javaModuleName}.service.I${javaBeanName}Service;\nimport lombok.extern.slf4j.Slf4j;\nimport org.springframework.stereotype.Service;\n\n/**\n * 功能: ${tableDesc} 业务实现类\n * @author ${author}\n * @date ${date}\n */\n@Service\n@Slf4j\npublic class ${javaBeanName}ServiceImpl extends ServiceImpl<${javaBeanName}Mapper, ${javaBeanName}> implements I${javaBeanName}Service {\n\n    @Override\n    public IPage<${javaBeanName}> listPage(Integer pageNum, Integer pageSize, ${javaBeanName} vo) {\n        return baseMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<${javaBeanName}>lambdaQuery(vo));\n    }\n\n}', '', '1', '2020-12-04 15:33:44', '1', '2021-01-08 17:01:39', '0');
INSERT INTO `gen_template_desc` VALUES ('1334770758089416705', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n<mapper namespace=\" ${javaPackageName}.${javaModuleName}.dao.${javaBeanName}Mapper\">\n\n    <!-- 通用查询映射结果 -->\n    <resultMap id=\"BaseResultMap\" type=\"${javaPackageName}.${javaModuleName}.bean.po.${javaBeanName}\">\n        #foreach ($column in $tableColumns)\n            <result column=\"${column.columnName}\" property=\"${column.attrName}\" />\n        #end\n    </resultMap>\n</mapper>', '', '1', '2020-12-04 16:05:22', '1', '2021-01-08 17:01:32', '0');
INSERT INTO `gen_template_desc` VALUES ('1347454524065411073', '<template>\n    <el-form ref=\"form\" :model=\"curData\" label-width=\"100px\" :inline=\"true\" :rules=\"rules\" status-icon>\n        #foreach ($column in $tableColumns)\n            <el-form-item label=\"${column.attrDesc}:\" prop=\"name\">\n                <el-input v-model.trim=\"curData.$column.attrName\" placeholder=\"请输入${column.attrDesc}\" size=\"small\" />\n            </el-form-item>\n        #end\n        <el-form-item class=\"formDialogBtn\">\n            <el-button type=\"info\" size=\"medium\" @click=\"handleCancel\">取消</el-button>\n            <el-button type=\"primary\" size=\"medium\" @click=\"handleSubmit\">确定</el-button>\n        </el-form-item>\n    </el-form>\n</template>\n\n<script>\n    import edtiMixin from \'@/mixins/edit.mixin\'\n    import rules from \'./rules\'\n\n    export default {\n        mixins: [edtiMixin],\n        data() {\n            return {\n                rules: rules(this),\n                addApi: this.$api.${javaModuleName}.${lowJavaBeanName}.add,\n                editApi: this.$api.${javaModuleName}.${lowJavaBeanName}.edit\n            }\n        }\n    }\n</script>\n', '', '1', '2021-01-08 16:06:08', '1', '2021-01-08 17:07:15', '0');
INSERT INTO `gen_template_desc` VALUES ('1347454756299829250', '<template>\n    <table-view>\n        <el-form ref=\"form\" slot=\"formSlot\" :model=\"form\" label-width=\"100px\" :inline=\"true\">\n            <el-form-item class=\"formQueryBtn\">\n                <el-button type=\"primary\" plain icon=\"el-icon-search\" size=\"small\" @click=\"handleSearch\">查询</el-button>\n                <el-button plain icon=\"el-icon-refresh\" size=\"small\" @click=\"handleRest\">重置</el-button>\n            </el-form-item>\n        </el-form>\n\n        <template slot=\"btnBeforeSlot\">\n            <el-button type=\"primary\" plain icon=\"el-icon-plus\" size=\"small\" @click=\"handleCustomAdd\">新增</el-button>\n            <el-button type=\"danger\" plain icon=\"el-icon-delete\" size=\"small\" @click=\"handleBatchDel\">删除</el-button>\n        </template>\n        <template slot=\"btnAfterSlot\" />\n\n        <el-table slot=\"tableSlot\" v-loading=\"loading\" :data=\"tableData\" stripe checkbox height=\"100%\" size=\"small\" width=\"100%\" border>\n            <el-table-column type=\"selection\" width=\"55\" />\n            #foreach ($column in $tableColumns)\n                <el-table-column prop=\"${column.attrName}\" label=\"${column.attrDesc}\" width=\"220\" />\n            #end\n            <el-table-column label=\"操作\" fixed=\"right\" width=\"350\" align=\"center\">\n                <template slot-scope=\"scope\">\n                    <el-link type=\"success\" :underline=\"false\" icon=\"el-icon-edit\" @click=\"handleCustomAdd(scope.row)\">修改</el-link>\n                    <el-link type=\"danger\" :underline=\"false\" icon=\"el-icon-delete\" @click=\"handleDel(scope.row)\">删除</el-link>\n                </template>\n            </el-table-column>\n        </el-table>\n\n        <el-pagination\n                slot=\"paginationSlot\"\n                :current-page=\"page.pageNum\"\n                :page-sizes=\"[10, 20, 30, 40]\"\n                :page-size=\"page.pageSize\"\n                layout=\"total, sizes, prev, pager, next, jumper\"\n                :total=\"page.total\"\n                @size-change=\"handleSizeChange\"\n                @current-change=\"handleCurrentChange\"\n        />\n\n        <dialog-view ref=\"dialogRef\" slot=\"other\" :title=\"dialogParams.title\" :width=\"dialogParams.width\">\n            <template slot=\"dialogSlot\">\n                <component\n                        :is=\"dialogParams.componentName\"\n                        :edit-data=\"editRow\"\n                        @submitSuccess=\"handleSearch\"\n                />\n            </template>\n        </dialog-view>\n    </table-view>\n</template>\n\n<script>\n    import tableMixin from \'@/mixins/table.mixin\'\n    export default {\n        components: {\n            Add: () => import(\'./add\')\n    },\n    mixins: [tableMixin],\n    data() {\n        return {\n            searchApi: this.$api.${javaModuleName}.${lowJavaBeanName}.list,\n            title: ${tableDesc}\n        }\n    },\n    methods: {\n        handleCustomAdd(row) {\n            if (row.constructor.name === \'MouseEvent\') {\n                this.handleAdd({})\n            } else {\n                this.handleAdd(row)\n            }\n        }\n    }\n    }\n</script>\n', '', '1', '2021-01-08 16:07:03', '1', '2021-01-08 17:07:24', '0');
INSERT INTO `gen_template_desc` VALUES ('1347454869571203074', 'export default function(context) {\n    return {\n    }\n}\n', '', '1', '2021-01-08 16:07:30', '1', '2021-01-08 17:04:02', '0');
INSERT INTO `gen_template_desc` VALUES ('1347454967986352130', 'const ${javaModuleName} = {\n    ${lowJavaBeanName}: {\n        list: \'/${javaModuleName}/${lowJavaBeanName}/list\',\n        add: \'/${javaModuleName}/${lowJavaBeanName}/add\',\n        edit: \'/${javaModuleName}/${lowJavaBeanName}/update\',\n        delete: \'/${javaModuleName}/${lowJavaBeanName}/delete\'\n    }\n\n}\nexport default ${javaModuleName}\n', '', '1', '2021-01-08 16:07:54', '1', '2021-01-08 17:07:33', '0');

-- ----------------------------
-- Table structure for `gen_template_group`
-- ----------------------------
DROP TABLE IF EXISTS `gen_template_group`;
CREATE TABLE `gen_template_group` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '组名称',
  `sort` int(2) NOT NULL DEFAULT '0' COMMENT '排序',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '组描述',
  `ds_dept` varchar(64) NOT NULL DEFAULT '' COMMENT '部门id',
  `create_user` varchar(64) NOT NULL DEFAULT '' COMMENT '创建用户',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(64) NOT NULL DEFAULT '' COMMENT '更新用户',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_del` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除，0：未删除，1：删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模板分组';

-- ----------------------------
-- Records of gen_template_group
-- ----------------------------
INSERT INTO `gen_template_group` VALUES ('1329346808123478018', 'sky', '2', '', '', '', '2020-11-19 16:52:31', '', '2021-01-08 08:54:32', '1');
INSERT INTO `gen_template_group` VALUES ('1329644366620233730', 'sky', '1', 'sky项目', '', '', '2020-11-20 12:34:55', '', '2020-11-20 12:34:55', '0');
INSERT INTO `gen_template_group` VALUES ('1332135866385989634', 'Controller', '0', '模板', '', '', '2020-11-27 09:35:15', '', '2020-11-27 09:35:43', '1');
INSERT INTO `gen_template_group` VALUES ('1347106692473131010', '11', '1', '11', '', '1', '2021-01-07 17:03:58', '1', '2021-01-08 08:54:26', '1');
INSERT INTO `gen_template_group` VALUES ('1347374253106348034', 'test', '2', '只是测试一下', '', '1', '2021-01-08 10:47:10', '1', '2021-01-08 10:47:10', '0');

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` varchar(32) NOT NULL,
  `username` varchar(32) NOT NULL COMMENT '用户名称',
  `age` datetime DEFAULT NULL COMMENT '生日',
  `CREATE_BY` varchar(64) NOT NULL COMMENT '创建人',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_BY` varchar(64) DEFAULT NULL COMMENT '更新人',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '更新时间',
  `REMARKS` varchar(255) DEFAULT NULL COMMENT '备注',
  `DEL_FLAG` char(1) NOT NULL DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('253d4471d59d90cc427991aabd967cfa', '张三3', '2020-06-10 16:24:34', 'admin', '2020-06-10 16:24:35', 'admin', '2020-06-10 16:24:35', null, '1');
INSERT INTO `student` VALUES ('6a4dd1b00fadd2edc93c862af55e08b7', '张三3', '2020-06-10 09:14:11', 'admin', null, null, null, null, '0');
INSERT INTO `student` VALUES ('84891dec67a0975cc99e895950775705', '张三3', '2020-06-10 09:15:10', 'admin', '2020-06-10 09:15:10', null, null, null, '0');
INSERT INTO `student` VALUES ('d4e1f8a6ac2b9c2d5cc176f1f0e23a69', '张三3', '2020-06-10 09:13:14', 'admin', null, null, null, null, '0');
