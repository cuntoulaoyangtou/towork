/*
 Navicat Premium Data Transfer

 Source Server         : 本地连接
 Source Server Type    : MySQL
 Source Server Version : 50560
 Source Host           : localhost:3306
 Source Schema         : towork

 Target Server Type    : MySQL
 Target Server Version : 50560
 File Encoding         : 65001

 Date: 20/03/2020 07:35:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for heatday
-- ----------------------------
DROP TABLE IF EXISTS `heatday`;
CREATE TABLE `heatday`  (
  `hid` int(11) NOT NULL AUTO_INCREMENT COMMENT '\'健康Id\'',
  `uid` int(11) NULL DEFAULT NULL COMMENT '\'用户id\'',
  `create_date` datetime NOT NULL COMMENT '\'创建时间\'',
  `update_date` datetime NULL DEFAULT NULL COMMENT '\'修改时间\'',
  `temperature` double(4, 1) NOT NULL COMMENT '\'体温\'',
  `ishot` tinyint(1) NOT NULL COMMENT '\'是否发烧\'',
  `iskeke` tinyint(1) NOT NULL COMMENT '\'是否咳嗽\'',
  `towuhan` tinyint(1) NOT NULL COMMENT '\'是否到过武汉\'',
  `crosswh` tinyint(1) NOT NULL COMMENT '\'是否经过武汉\'',
  `firends_tor_cwh` tinyint(1) NOT NULL COMMENT '\'密切接触\'',
  `incomadd` tinyint(1) NOT NULL COMMENT '\'是否在公司\'',
  `other` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '\'其他\'',
  `update_reason` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '\'修改原因\'',
  PRIMARY KEY (`hid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo`  (
  `uid` int(11) NOT NULL AUTO_INCREMENT COMMENT '\'用户id\'',
  `uno` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '\'工号\'',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '\'用户名\'',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '\'密码\'',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '\'手机号\'',
  `age` int(3) NULL DEFAULT NULL COMMENT '\'年龄\'',
  `sex` tinyint(1) NULL DEFAULT NULL COMMENT '\'性别\'',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '\'家庭住址\'',
  `department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '\'所在车间\'',
  `grade` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '\'所在班次\'',
  `face_id` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '\'脸部id\'',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '\'头像\'',
  PRIMARY KEY (`uid`) USING BTREE,
  UNIQUE INDEX `UN_USERINFO_PHONE`(`phone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES (1, NULL, NULL, '25d55ad283aa400af464c76d713c07ad', '18567192712', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
