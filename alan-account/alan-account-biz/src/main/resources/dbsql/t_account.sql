CREATE TABLE `t_account` (
  `account_id` bigint(20) unsigned NOT NULL COMMENT '账号id',
  `account_no` varchar(32) NOT NULL COMMENT '用户账号',
  `account_name` varchar(32) NOT NULL COMMENT '用户名称',
  `account_mobile` varchar(16) NOT NULL COMMENT '用户手机号',
  `account_gender` tinyint(3) unsigned NOT NULL COMMENT '用户性别，0未知，1男，2女',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`account_id`),
  KEY `idx_account_mobile` (`account_mobile`) USING BTREE COMMENT '用户手机索引',
  KEY `idx_account_name` (`account_name`) USING BTREE COMMENT '用户名称索引',
  KEY `idx_account_no` (`account_no`) USING BTREE COMMENT '用户账号索引'
) COMMENT='账号表';