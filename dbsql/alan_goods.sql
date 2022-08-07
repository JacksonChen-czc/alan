/*
 Navicat Premium Data Transfer

 Source Server         : alan-local-alan
 Source Server Type    : MySQL
 Source Server Version : 50738
 Source Host           : localhost:3306
 Source Schema         : alan_goods

 Target Server Type    : MySQL
 Target Server Version : 50738
 File Encoding         : 65001

 Date: 06/08/2022 17:27:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods`
(
    `goods_id`    bigint(20)                                                   NOT NULL COMMENT '商品id',
    `goods_name`  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品名称',
    `goods_price` decimal(10, 2)                                               NOT NULL COMMENT '商品价格',
    `good_desc`   json COMMENT '商品描述',
    `create_time` datetime(0)                                                  NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime(0)                                                  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    PRIMARY KEY (`goods_id`) USING BTREE,
    INDEX `idx_goods_name` (`goods_name`) USING BTREE COMMENT '商品名称索引',
    INDEX `idx_goods_price` (`goods_price`) USING BTREE COMMENT '商品价格索引'
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '商品表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_stock
-- ----------------------------
DROP TABLE IF EXISTS `t_stock`;
CREATE TABLE `t_stock`
(
    `goods_id` bigint(20) NOT NULL COMMENT '库存对应的商品id',
    `sale_num` int(11)  DEFAULT NULL COMMENT '商品可售数量',
    `total`    int(255) DEFAULT NULL COMMENT '库存总数',
    PRIMARY KEY (`goods_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '商品库存表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`
(
    `branch_id`     bigint(20)                                                    NOT NULL COMMENT 'branch transaction id',
    `xid`           varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'global transaction id',
    `context`       varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'undo_log context,such as serialization',
    `rollback_info` longblob                                                      NOT NULL COMMENT 'rollback info',
    `log_status`    int(11)                                                       NOT NULL COMMENT '0:normal status,1:defense status',
    `log_created`   datetime(6)                                                   NOT NULL COMMENT 'create datetime',
    `log_modified`  datetime(6)                                                   NOT NULL COMMENT 'modify datetime',
    UNIQUE INDEX `ux_undo_log` (`xid`, `branch_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = 'AT transaction mode undo table'
  ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
