CREATE TABLE `t_stock` (
  `goods_id` bigint(20) NOT NULL COMMENT '库存对应的商品id',
  `sale_num` int(11) DEFAULT NULL COMMENT '商品可售数量',
  `total` int(255) DEFAULT NULL COMMENT '库存总数',
  PRIMARY KEY (`goods_id`) USING BTREE
) COMMENT='商品库存表';