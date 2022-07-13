# alan
以一个简单的用户故事“用户抢购商品”学习分库分表，分布式事务，分布式id等分布式技术

## 快速开始

安装mysql,redis,中间件1,2,3...

初始化数据库表，运行各个服务中的test-db-init中的测试类初始化数据。

修改数据库，缓存，中间件ip，账号，密码

依次启动服务1,2,3...

启动后，调用模拟客户端接口各种测试接口，查看监控系统各接口性能与资源变化



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



## Todo List

- 调用模拟客户端**接口**触发并发调用，考虑使用redis广播，部署多个客户端实例增加并发数，每个客户端可配置并发数量。
- 高并发客户端使用模板设计模式，固定用户故事流程：查用户信息-查商品列表-查商品详情-抢购-下单。不同的实现类调用不同的底层和服务分布式技术实现接口。