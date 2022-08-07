/*
 Navicat Premium Data Transfer

 Source Server         : alan-local-alan
 Source Server Type    : MySQL
 Source Server Version : 50738
 Source Host           : localhost:3306
 Source Schema         : alan_account

 Target Server Type    : MySQL
 Target Server Version : 50738
 File Encoding         : 65001

 Date: 06/08/2022 17:26:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_account
-- ----------------------------
DROP TABLE IF EXISTS `t_account`;
CREATE TABLE `t_account`
(
    `account_id`     bigint(20) UNSIGNED                                    NOT NULL COMMENT '账号id',
    `account_no`     varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户账号',
    `account_name`   varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
    `account_mobile` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户手机号',
    `account_gender` tinyint(3) UNSIGNED                                    NOT NULL COMMENT '用户性别，0未知，1男，2女',
    `update_time`    datetime(0)                                            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    `create_time`    datetime(0)                                            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`account_id`) USING BTREE,
    INDEX `idx_account_mobile` (`account_mobile`) USING BTREE COMMENT '用户手机索引',
    INDEX `idx_account_name` (`account_name`) USING BTREE COMMENT '用户名称索引',
    INDEX `idx_account_no` (`account_no`) USING BTREE COMMENT '用户账号索引'
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '账号表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_account_big
-- ----------------------------
DROP TABLE IF EXISTS `t_account_big`;
CREATE TABLE `t_account_big`
(
    `account_id`     bigint(20) UNSIGNED                                          NOT NULL COMMENT '账号id',
    `account_no`     varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
    `account_name`   varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名称',
    `account_mobile` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户手机号',
    `account_gender` tinyint(3) UNSIGNED                                          NOT NULL COMMENT '用户性别，0未知，1男，2女',
    `update_time`    datetime(0)                                                  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    `create_time`    datetime(0)                                                  NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`account_id`) USING BTREE,
    INDEX `idx_account_mobile` (`account_mobile`) USING BTREE COMMENT '用户手机索引',
    INDEX `idx_account_name` (`account_name`) USING BTREE COMMENT '用户名称索引',
    INDEX `idx_account_no` (`account_no`) USING BTREE COMMENT '用户账号索引'
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '账号大数据表'
  ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
