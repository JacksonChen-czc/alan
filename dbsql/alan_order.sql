/*
 Navicat Premium Data Transfer

 Source Server         : alan-local-alan
 Source Server Type    : MySQL
 Source Server Version : 50738
 Source Host           : localhost:3306
 Source Schema         : alan_order

 Target Server Type    : MySQL
 Target Server Version : 50738
 File Encoding         : 65001

 Date: 06/08/2022 17:27:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`
(
    `order_id`     bigint(20) UNSIGNED NOT NULL COMMENT '订单id',
    `account_id`   bigint(20) UNSIGNED NOT NULL COMMENT '用户id',
    `order_price`  bigint(20) UNSIGNED NOT NULL COMMENT '订单金额',
    `order_status` int(11) UNSIGNED    NOT NULL COMMENT '订单状态，1已创建，2已支付，3已取消',
    `update_time`  datetime(0)         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
    `create_time`  datetime(0)                  DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '订单表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_order_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_order_goods`;
CREATE TABLE `t_order_goods`
(
    `order_goods_id`  bigint(20) UNSIGNED NOT NULL COMMENT '记录id',
    `order_id`        bigint(20) UNSIGNED NOT NULL COMMENT '订单id',
    `goods_id`        bigint(20) UNSIGNED NOT NULL COMMENT '商品id',
    `amount`          int(11) UNSIGNED    NOT NULL COMMENT '商品数量',
    `goods_price_sum` bigint(20) UNSIGNED NOT NULL COMMENT '商品总价',
    `create_time`     datetime(0)         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`     datetime(0)         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
    PRIMARY KEY (`order_goods_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '订单商品表'
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
