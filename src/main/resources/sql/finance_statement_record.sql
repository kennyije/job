/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : financial2

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-07-03 09:46:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `finance_statement_record`
-- ----------------------------
DROP TABLE IF EXISTS `finance_statement_record`;
CREATE TABLE `finance_statement_record` (
  `busi_inst_no` varchar(50) DEFAULT NULL COMMENT '机构号',
  `busi_app_no` varchar(50) DEFAULT NULL COMMENT '申请号',
  `card_no` varchar(50) DEFAULT NULL COMMENT '卡号',
  `channel_code` varchar(50) DEFAULT NULL COMMENT '渠道码',
  `channel_name` varchar(50) DEFAULT NULL COMMENT '渠道名称',
  `tx_code` varchar(10) DEFAULT NULL COMMENT '交易方向',
  `tx_amount` varchar(50) DEFAULT NULL COMMENT '交易金额',
  `req_time` varchar(50) DEFAULT NULL COMMENT '交易时间',
  `busi_req_no` varchar(50) DEFAULT NULL COMMENT '业务号',
  `bat_seq_no` varchar(50) DEFAULT NULL COMMENT '业务号（批量时使用）',
  `id` varchar(50) NOT NULL COMMENT '主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of finance_statement_record
-- ----------------------------
