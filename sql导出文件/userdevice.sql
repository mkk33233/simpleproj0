/*
Navicat MySQL Data Transfer

Source Server         : con1
Source Server Version : 80034
Source Host           : localhost:3306
Source Database       : agriculture

Target Server Type    : MYSQL
Target Server Version : 80034
File Encoding         : 65001

Date: 2025-02-12 17:04:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for userdevice
-- ----------------------------
DROP TABLE IF EXISTS `userdevice`;
CREATE TABLE `userdevice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ded` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `username` (`username`),
  KEY `ded` (`ded`),
  CONSTRAINT `ded` FOREIGN KEY (`ded`) REFERENCES `device` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `username` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of userdevice
-- ----------------------------
INSERT INTO `userdevice` VALUES ('79', 'ee', '203');
INSERT INTO `userdevice` VALUES ('80', 'ee', '228');
