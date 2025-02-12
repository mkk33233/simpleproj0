/*
Navicat MySQL Data Transfer

Source Server         : con1
Source Server Version : 80034
Source Host           : localhost:3306
Source Database       : agriculture

Target Server Type    : MYSQL
Target Server Version : 80034
File Encoding         : 65001

Date: 2025-02-12 17:03:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for crop
-- ----------------------------
DROP TABLE IF EXISTS `crop`;
CREATE TABLE `crop` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `growth_cycle` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=154 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of crop
-- ----------------------------
INSERT INTO `crop` VALUES ('2', '水稻', '120天');
INSERT INTO `crop` VALUES ('4', 'ewq', '额温枪');
INSERT INTO `crop` VALUES ('135', 'sa', '11天');
INSERT INTO `crop` VALUES ('136', '1', '2天');
INSERT INTO `crop` VALUES ('137', 'a', '12天');
INSERT INTO `crop` VALUES ('138', 'ee', '1天');
INSERT INTO `crop` VALUES ('139', 'qwe', '1天');
INSERT INTO `crop` VALUES ('141', '13', '1天');
INSERT INTO `crop` VALUES ('143', 'qwe', '2天');
INSERT INTO `crop` VALUES ('144', 'qe', '2天');
INSERT INTO `crop` VALUES ('145', 'ee', '22');
INSERT INTO `crop` VALUES ('146', 'weq', '2天');
INSERT INTO `crop` VALUES ('147', 'qwe', '2天');
INSERT INTO `crop` VALUES ('148', 'qwe', '2天');
INSERT INTO `crop` VALUES ('149', 'asda', '1天');
INSERT INTO `crop` VALUES ('150', 'eqw', '2天');
INSERT INTO `crop` VALUES ('151', 'eqw', '2天');
INSERT INTO `crop` VALUES ('152', 'eqw', '2天');
INSERT INTO `crop` VALUES ('153', 'das', '213天');
