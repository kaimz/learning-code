/*
Navicat MySQL Data Transfer

Source Server         : localhost mysql
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : learn

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-03-08 18:29:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `detail_id` bigint(20) DEFAULT NULL,
  `job_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', '1', '1');
INSERT INTO `employee` VALUES ('2', '2', '1');
INSERT INTO `employee` VALUES ('3', '4', null);
INSERT INTO `employee` VALUES ('4', '5', null);

-- ----------------------------
-- Table structure for employee_detail
-- ----------------------------
DROP TABLE IF EXISTS `employee_detail`;
CREATE TABLE `employee_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `age` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee_detail
-- ----------------------------
INSERT INTO `employee_detail` VALUES ('1', '24', 'kronchan', '18772383543');
INSERT INTO `employee_detail` VALUES ('2', '23', 'tom', '123456');
INSERT INTO `employee_detail` VALUES ('4', '22', 'jams', '42345');
INSERT INTO `employee_detail` VALUES ('5', '22', 'jams', '42345');

-- ----------------------------
-- Table structure for job
-- ----------------------------
DROP TABLE IF EXISTS `job`;
CREATE TABLE `job` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of job
-- ----------------------------
INSERT INTO `job` VALUES ('1', '程序猿');
