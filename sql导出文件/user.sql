/*
Navicat MySQL Data Transfer

Source Server         : con1
Source Server Version : 80034
Source Host           : localhost:3306
Source Database       : agriculture

Target Server Type    : MYSQL
Target Server Version : 80034
File Encoding         : 65001

Date: 2025-02-12 17:04:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `role` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `username_2` (`username`),
  KEY `idx_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('26', 'ee', 'e', 'farmer');
INSERT INTO `user` VALUES ('32', 'ssssss', 'ss', 'admin');
INSERT INTO `user` VALUES ('34', 'eqweq', 'exeeqeq1', 'farmer');
INSERT INTO `user` VALUES ('35', 'asdadsada', 'seeeqw11www', 'farmer');
INSERT INTO `user` VALUES ('36', 'asas', 'qwqqwewqeqwwqeq1', 'farmer');
INSERT INTO `user` VALUES ('40', 'ddd', 'ddd', 'farmer');
INSERT INTO `user` VALUES ('43', 'zxc', 'zxc', 'farmer');
INSERT INTO `user` VALUES ('46', 'dd', 'dd', 'farmer');
INSERT INTO `user` VALUES ('47', 'asd', 'asd', 'farmer');
INSERT INTO `user` VALUES ('48', 'yhn', 'ikk', 'farmer');
INSERT INTO `user` VALUES ('52', 'asda', 'as', 'farmer');
INSERT INTO `user` VALUES ('54', 'wqe', 'eqwewq', 'farmer');
INSERT INTO `user` VALUES ('55', 'qwewqe', 'qweqewq', 'farmer');
INSERT INTO `user` VALUES ('56', 'qewewq', 'eqweqwq', 'farmer');
INSERT INTO `user` VALUES ('57', 'qwewq', 'qweqweqw', 'farmer');
INSERT INTO `user` VALUES ('58', 'qweqweqweqw', 'qeqweqqwe', 'farmer');
INSERT INTO `user` VALUES ('59', 'ccc', 'ccc', 'farmer');
INSERT INTO `user` VALUES ('60', 'cca', 'cca', 'farmer');
INSERT INTO `user` VALUES ('65', 'ewe', 'e', 'farmer');
INSERT INTO `user` VALUES ('66', 'qwe', 'qwe', 'farmer');
INSERT INTO `user` VALUES ('68', 'rrr', 'rrr', 'farmer');
INSERT INTO `user` VALUES ('69', 'hhh', 'hhh', 'farmer');
