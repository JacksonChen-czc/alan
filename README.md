# alan
以一个简单的用户故事“用户抢购商品”学习分库分表，分布式事务，分布式id等分布式技术

## 技术选型

| 技术         | 名称                   | 版本 | 官网                                        |
| ------------ | ---------------------- | ---- | ------------------------------------------- |
| Spring Boot  | 主框架                 |      | https://spring.io/projects/spring-boot      |
| MyBatis-Plus | ORM                    |      | https://mp.baomidou.com/                    |
| Hikari       | 数据库连接池           |      | https://github.com/brettwooldridge/HikariCP |
| Nacos        | 服务注册发现和统一配置 |      |                                             |
| Feign        | 远程调用               |      |                                             |



## 开发日志

### Day1

初步架构图

![image-20220713150314241](https://jack-pic.oss-cn-hangzhou.aliyuncs.com/doc/image/image-20220713150314241.png)

搭建项目架子：模拟客户端mock-client，通用模块common，用户模块account，商品模块goods，银行模块bank，商品（分库分表）goods-dis

> 参考[GuoHuaijian/SpringCloudAlibaba](https://github.com/GuoHuaijian/SpringCloudAlibaba)

数据库设计，造数据：100w用户，1000w商品（大表），500w商品（小表），100w用户的银行账户余额

