CREATE TABLE `t_goods` (
  `goods_id` bigint(20) NOT NULL COMMENT '商品id',
  `goods_name` varchar(64) NOT NULL COMMENT '商品名称',
  `goods_price` decimal(10,2) NOT NULL COMMENT '商品价格',
  `good_desc` json DEFAULT NULL COMMENT '商品描述',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`goods_id`),
  KEY `idx_goods_name` (`goods_name`) USING BTREE COMMENT '商品名称索引',
  KEY `idx_goods_price` (`goods_price`) USING BTREE COMMENT '商品价格索引'
) COMMENT='商品表';