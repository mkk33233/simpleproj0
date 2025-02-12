/*
Navicat MySQL Data Transfer

Source Server         : con1
Source Server Version : 80034
Source Host           : localhost:3306
Source Database       : agriculture

Target Server Type    : MYSQL
Target Server Version : 80034
File Encoding         : 65001

Date: 2025-02-12 17:03:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `crop_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_device_id` (`id`),
  KEY `FKg3w9fkffdna7dqiu2hyho10ms` (`crop_id`),
  CONSTRAINT `FKg3w9fkffdna7dqiu2hyho10ms` FOREIGN KEY (`crop_id`) REFERENCES `crop` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=229 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of device
-- ----------------------------
INSERT INTO `device` VALUES ('162', '135sa湿度检测器', '湿度', 'active', '135');
INSERT INTO `device` VALUES ('163', '135sa温度检测器', '温度', 'active', '135');
INSERT INTO `device` VALUES ('164', '135sa水质检测器', '水质', 'active', '135');
INSERT INTO `device` VALUES ('165', '135sa综合检测器', '综合', 'active', '135');
INSERT INTO `device` VALUES ('166', '1361湿度检测器', '湿度', 'active', '136');
INSERT INTO `device` VALUES ('167', '1361温度检测器', '温度', 'active', '136');
INSERT INTO `device` VALUES ('168', '1361水质检测器', '水质', 'active', '136');
INSERT INTO `device` VALUES ('169', '1361综合检测器', '综合', 'active', '136');
INSERT INTO `device` VALUES ('170', '137a湿度检测器', '湿度', 'active', '137');
INSERT INTO `device` VALUES ('171', '137a温度检测器', '温度', 'active', '137');
INSERT INTO `device` VALUES ('172', '137a水质检测器', '水质', 'active', '137');
INSERT INTO `device` VALUES ('173', '137a综合检测器', '综合', 'active', '137');
INSERT INTO `device` VALUES ('174', '138ee湿度检测器', '湿度', 'active', '138');
INSERT INTO `device` VALUES ('175', '138ee温度检测器', '温度', 'active', '138');
INSERT INTO `device` VALUES ('176', '138ee水质检测器', '水质', 'active', '138');
INSERT INTO `device` VALUES ('177', '138ee综合检测器', '综合', 'active', '138');
INSERT INTO `device` VALUES ('178', '139qwe湿度检测器', '湿度', 'active', '139');
INSERT INTO `device` VALUES ('179', '139qwe温度检测器', '温度', 'active', '139');
INSERT INTO `device` VALUES ('180', '139qwe水质检测器', '水质', 'active', '139');
INSERT INTO `device` VALUES ('181', '139qwe综合检测器', '综合', 'active', '139');
INSERT INTO `device` VALUES ('186', '14113湿度检测器', '湿度', 'active', '141');
INSERT INTO `device` VALUES ('187', '14113温度检测器', '温度', 'active', '141');
INSERT INTO `device` VALUES ('188', '14113水质检测器', '水质', 'active', '141');
INSERT INTO `device` VALUES ('189', '14113综合检测器', '综合', 'active', '141');
INSERT INTO `device` VALUES ('194', '143qwe湿度检测器', '湿度', 'active', '143');
INSERT INTO `device` VALUES ('195', '143qwe温度检测器', '温度', 'active', '143');
INSERT INTO `device` VALUES ('196', '143qwe水质检测器', '水质', 'active', '143');
INSERT INTO `device` VALUES ('197', '143qwe综合检测器', '综合', 'active', '143');
INSERT INTO `device` VALUES ('198', '144qe湿度检测器', '湿度', 'active', '144');
INSERT INTO `device` VALUES ('199', '144qe温度检测器', '温度', 'active', '144');
INSERT INTO `device` VALUES ('200', '144qe水质检测器', '水质', 'active', '144');
INSERT INTO `device` VALUES ('201', '144qe综合检测器', '综合', 'active', '144');
INSERT INTO `device` VALUES ('202', '湿度检测器', '湿度', 'active', '2');
INSERT INTO `device` VALUES ('203', 'asa', '温度', 'active', '146');
INSERT INTO `device` VALUES ('204', '147qwe湿度检测器', '湿度', 'active', '147');
INSERT INTO `device` VALUES ('205', '147qwe温度检测器', '温度', 'active', '147');
INSERT INTO `device` VALUES ('206', '147qwe水质检测器', '水质', 'active', '147');
INSERT INTO `device` VALUES ('207', '147qwe综合检测器', '综合', 'active', '147');
INSERT INTO `device` VALUES ('208', '148qwe湿度检测器', '湿度', 'active', '148');
INSERT INTO `device` VALUES ('209', '148qwe温度检测器', '温度', 'active', '148');
INSERT INTO `device` VALUES ('210', '148qwe水质检测器', '水质', 'active', '148');
INSERT INTO `device` VALUES ('211', '148qwe综合检测器', '综合', 'active', '148');
INSERT INTO `device` VALUES ('212', '149asda湿度检测器', '湿度', 'active', '149');
INSERT INTO `device` VALUES ('213', '149asda温度检测器', '温度', 'active', '149');
INSERT INTO `device` VALUES ('214', '149asda水质检测器', '水质', 'active', '149');
INSERT INTO `device` VALUES ('215', '149asda综合检测器', '综合', 'active', '149');
INSERT INTO `device` VALUES ('216', '150eqw湿度检测器', '湿度', 'active', '150');
INSERT INTO `device` VALUES ('217', '150eqw温度检测器', '温度', 'active', '150');
INSERT INTO `device` VALUES ('218', '150eqw水质检测器', '水质', 'active', '150');
INSERT INTO `device` VALUES ('219', '150eqw综合检测器', '综合', 'active', '150');
INSERT INTO `device` VALUES ('220', '151eqw湿度检测器', '湿度', 'active', '151');
INSERT INTO `device` VALUES ('221', '151eqw温度检测器', '温度', 'active', '151');
INSERT INTO `device` VALUES ('222', '151eqw水质检测器', '水质', 'active', '151');
INSERT INTO `device` VALUES ('223', '151eqw综合检测器', '综合', 'active', '151');
INSERT INTO `device` VALUES ('224', '152eqw湿度检测器', '湿度', 'active', '152');
INSERT INTO `device` VALUES ('225', '152eqw温度检测器', '温度', 'active', '152');
INSERT INTO `device` VALUES ('226', '152eqw水质检测器', '水质', 'active', '152');
INSERT INTO `device` VALUES ('227', '152eqw综合检测器', '综合', 'active', '152');
INSERT INTO `device` VALUES ('228', 'asdda', '湿度', 'active', '153');
