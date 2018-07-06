/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : financial2

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-07-03 10:03:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `finance_balanceofaccount_record`
-- ----------------------------
DROP TABLE IF EXISTS `finance_balanceofaccount_record`;
CREATE TABLE `finance_balanceofaccount_record` (
  `busi_inst_no_pay` varchar(50) DEFAULT NULL COMMENT '机构号',
  `busi_app_no_pay` varchar(50) DEFAULT NULL COMMENT '申请号',
  `card_no_pay` varchar(50) DEFAULT NULL,
  `channel_code_pay` varchar(50) DEFAULT NULL,
  `channel_name_pay` varchar(50) DEFAULT NULL COMMENT '渠道名称',
  `tx_code_pay` varchar(10) DEFAULT NULL COMMENT '交易方向',
  `tx_amount_pay` varchar(50) DEFAULT NULL COMMENT '交易金额',
  `req_time_pay` varchar(10) DEFAULT NULL COMMENT '交易时间',
  `busi_req_no_pay` varchar(50) DEFAULT NULL COMMENT '业务号',
  `bat_seq_no_pay` varchar(50) DEFAULT NULL COMMENT '业务号（批量时使用）',
  `busi_inst_no_finance` varchar(50) DEFAULT NULL COMMENT '主键',
  `busi_app_no_finance` varchar(50) DEFAULT NULL,
  `card_no_finance` varchar(50) DEFAULT NULL,
  `channel_code_finance` varchar(50) DEFAULT NULL,
  `channel_name_finance` varchar(50) DEFAULT NULL,
  `tx_code_finance` varchar(10) DEFAULT NULL,
  `tx_amount_finance` varchar(50) DEFAULT NULL,
  `req_time_finance` varchar(50) DEFAULT NULL,
  `busi_req_no_finance` varchar(50) DEFAULT NULL,
  `bat_seq_no_finance` varchar(50) DEFAULT NULL,
  `balance_result` varchar(10) DEFAULT NULL COMMENT '0:平账 1:不平 2:错账',
  `balance_time` varchar(50) DEFAULT NULL,
  `id` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of finance_balanceofaccount_record
-- ----------------------------
