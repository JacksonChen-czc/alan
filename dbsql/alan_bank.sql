/*
 Navicat Premium Data Transfer

 Source Server         : alan-local-alan
 Source Server Type    : MySQL
 Source Server Version : 50738
 Source Host           : localhost:3306
 Source Schema         : alan_bank

 Target Server Type    : MySQL
 Target Server Version : 50738
 File Encoding         : 65001

 Date: 06/08/2022 17:26:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_bank_account
-- ----------------------------
DROP TABLE IF EXISTS `t_bank_account`;
CREATE TABLE `t_bank_account`
(
    `bank_account_id` bigint(20)     NOT NULL COMMENT '银行账户id',
    `account_id`      bigint(20)     NOT NULL COMMENT '用户id',
    `banlance`        decimal(16, 2) NOT NULL COMMENT '用户余额',
    `update_time`     datetime(0)    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
    `create_time`     datetime(0)    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`bank_account_id`) USING BTREE,
    UNIQUE INDEX `idx_account_id` (`account_id`) USING BTREE COMMENT '账号唯一索引'
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
