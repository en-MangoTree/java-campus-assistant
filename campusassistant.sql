/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : campusassistant

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2022-05-30 22:31:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for roles_menus
-- ----------------------------
DROP TABLE IF EXISTS `roles_menus`;
CREATE TABLE `roles_menus` (
  `role_id` bigint(20) DEFAULT NULL,
  `menu_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roles_menus
-- ----------------------------

-- ----------------------------
-- Table structure for roles_permissions
-- ----------------------------
DROP TABLE IF EXISTS `roles_permissions`;
CREATE TABLE `roles_permissions` (
  `role_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roles_permissions
-- ----------------------------

-- ----------------------------
-- Table structure for sort_trades
-- ----------------------------
DROP TABLE IF EXISTS `sort_trades`;
CREATE TABLE `sort_trades` (
  `trade_id` bigint(20) DEFAULT NULL,
  `sort_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sort_trades
-- ----------------------------

-- ----------------------------
-- Table structure for tb_apply
-- ----------------------------
DROP TABLE IF EXISTS `tb_apply`;
CREATE TABLE `tb_apply` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `buyer_nick_name` varchar(50) DEFAULT NULL COMMENT '买家昵称',
  `seller_nick_name` varchar(50) DEFAULT NULL COMMENT '卖家昵称',
  `trade_title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '交易标题',
  `price` decimal(50,0) DEFAULT NULL COMMENT '价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_apply
-- ----------------------------

-- ----------------------------
-- Table structure for tb_collection
-- ----------------------------
DROP TABLE IF EXISTS `tb_collection`;
CREATE TABLE `tb_collection` (
  `user_id` bigint(20) DEFAULT NULL,
  `trade_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_collection
-- ----------------------------

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `path` varchar(50) DEFAULT NULL COMMENT '菜单路径',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `title` varchar(50) DEFAULT NULL COMMENT '菜单标题',
  `component` varchar(50) DEFAULT NULL COMMENT '菜单组件',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级id',
  `status` tinyint(1) DEFAULT NULL COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES ('1', '/', 'el-icon-setting', '系统管理', 'home', null, '1');
INSERT INTO `tb_menu` VALUES ('2', '/system/user', 'fa fa-user', '用户管理', 'system/user', '1', '1');
INSERT INTO `tb_menu` VALUES ('3', '/system/role', 'fa fa-street-view', '角色管理', 'system/role', '1', '1');
INSERT INTO `tb_menu` VALUES ('4', '/system/permission', 'fa fa-ils', '权限管理', 'system/permission', '1', '1');
INSERT INTO `tb_menu` VALUES ('5', '/system/menu', 'el-icon-menu', '菜单管理', 'system/menu', '1', '1');

-- ----------------------------
-- Table structure for tb_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_permission`;
CREATE TABLE `tb_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `label` varchar(50) DEFAULT NULL COMMENT '权限标签',
  `code` varchar(50) DEFAULT NULL COMMENT '标签值',
  `status` tinyint(1) DEFAULT NULL COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_permission
-- ----------------------------
INSERT INTO `tb_permission` VALUES ('1', '增加用户', 'USER_INSERT', '1');
INSERT INTO `tb_permission` VALUES ('2', '修改用户', 'USER_UPDATE', '1');
INSERT INTO `tb_permission` VALUES ('3', '删除用户', 'USER_DELETE', '1');
INSERT INTO `tb_permission` VALUES ('4', '查询用户', 'USER_SELECT', '1');
INSERT INTO `tb_permission` VALUES ('5', '添加角色', 'PRE_ROLE_INSERT', '1');
INSERT INTO `tb_permission` VALUES ('6', '修改角色', 'PRE_ROLE_UPDATE', '1');
INSERT INTO `tb_permission` VALUES ('7', '删除角色', 'PRE_ROLE_DELETE', '1');
INSERT INTO `tb_permission` VALUES ('8', '查询角色', 'PRE_ROLE_SELECT', '1');
INSERT INTO `tb_permission` VALUES ('9', '添加权限', 'PRE_PERMISSION_INSERT', '1');
INSERT INTO `tb_permission` VALUES ('10', '修改权限', 'PRE_PERMISSION_UPDATE', '1');
INSERT INTO `tb_permission` VALUES ('11', '删除权限', 'PRE_PERMISSION_DELETE', '1');
INSERT INTO `tb_permission` VALUES ('12', '查询权限', 'PRE_PERMISSION_SELECT', '1');
INSERT INTO `tb_permission` VALUES ('13', '添加菜单', 'MENU_INSERT', '1');
INSERT INTO `tb_permission` VALUES ('14', '修改菜单', 'MENU_UPDATE', '1');
INSERT INTO `tb_permission` VALUES ('15', '删除菜单', 'MENU_DELETE', '1');
INSERT INTO `tb_permission` VALUES ('16', '查询菜单', 'MENU_SELECT', '1');

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `label` varchar(50) DEFAULT NULL COMMENT '角色标签',
  `code` varchar(50) DEFAULT NULL COMMENT '标签值',
  `status` tinyint(1) DEFAULT NULL COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('1', '管理员', 'BOSS', '1');
INSERT INTO `tb_role` VALUES ('2', '审核员', 'CHECKER', '1');
INSERT INTO `tb_role` VALUES ('3', '普通用户', 'USER', '1');

-- ----------------------------
-- Table structure for tb_sort
-- ----------------------------
DROP TABLE IF EXISTS `tb_sort`;
CREATE TABLE `tb_sort` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `label` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '分类标签',
  `code` varchar(50) DEFAULT NULL COMMENT '标签值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sort
-- ----------------------------
INSERT INTO `tb_sort` VALUES ('1', '书籍', 'book');
INSERT INTO `tb_sort` VALUES ('2', '替身', 'replacement ');
INSERT INTO `tb_sort` VALUES ('3', '校园用品', 'campus_supplies');
INSERT INTO `tb_sort` VALUES ('4', '招收', 'recruit');
INSERT INTO `tb_sort` VALUES ('5', '拼团', 'group');

-- ----------------------------
-- Table structure for tb_trade
-- ----------------------------
DROP TABLE IF EXISTS `tb_trade`;
CREATE TABLE `tb_trade` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `images` varchar(255) DEFAULT NULL COMMENT '图片集',
  `description` varchar(255) DEFAULT NULL COMMENT '交易描述',
  `price` decimal(50,0) DEFAULT NULL COMMENT '价格',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态（0为下架，1为上架）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_trade
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) DEFAULT NULL COMMENT '可登陆的用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `wx_number` varchar(50) DEFAULT NULL COMMENT '微信号',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `university` varchar(100) DEFAULT NULL COMMENT '高校名称',
  `open_id` varchar(100) DEFAULT NULL COMMENT '打开微信小程序的唯一标识',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像地址',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `status` tinyint(1) DEFAULT NULL COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'boss', '$2a$10$O5RmbDL6h4Gwr8JZlbcymezyGlFHyuyK6yxP9cPDWc77mVBK0D3Qa', '17878787878', '', null, null, null, null, '老大', '1');
INSERT INTO `tb_user` VALUES ('2', 'aqing', '$2a$10$O5RmbDL6h4Gwr8JZlbcymezyGlFHyuyK6yxP9cPDWc77mVBK0D3Qa', null, null, null, null, null, null, '阿晴', '1');

-- ----------------------------
-- Table structure for users_role
-- ----------------------------
DROP TABLE IF EXISTS `users_role`;
CREATE TABLE `users_role` (
  `user_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users_role
-- ----------------------------
INSERT INTO `users_role` VALUES ('1', '1');
INSERT INTO `users_role` VALUES ('2', '3');
INSERT INTO `users_role` VALUES (null, null);
INSERT INTO `users_role` VALUES (null, null);
INSERT INTO `users_role` VALUES ('3', null);
