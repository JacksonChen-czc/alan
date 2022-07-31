# alan

以一个简单的用户故事“用户抢购商品”学习分库分表，分布式事务，分布式id等分布式技术

## 技术选型

| 框架                 | 功能                   | 版本     |
| -------------------- | ---------------------- | -------- |
| Spring Boot          | 主框架                 | 2.4.2    |
| spring-cloud-alibaba | 主框架，版本管理       | 2021.1   |
| spring-cloud         | 主框架，版本管理       | 2020.0.1 |
| MyBatis-Plus         | ORM，代码生成          | 3.5.2    |
| Hikari               | 数据库连接池           |          |
| Nacos                | 服务注册发现和统一配置 |          |
| Feign                | 远程调用               |          |
| Redisson             | 分布式缓存，分布式锁   |          |

## 项目结构

初步架构图

![image-20220713150314241](https://jack-pic.oss-cn-hangzhou.aliyuncs.com/doc/image/image-20220713150314241.png)

## 快速开始

- 启动Mysql，建库建表，Redis
- 使用账号服务，商品服务，银行服务中的test-DbInit类，初始化数据库数据
- 启动注册中心Nacos，并发监控Sentinel（可选），和Spring Boot Admin（可选）
- 启动账号服务，商品服务，订单服务，银行服务
- 启动模拟客户端服务，调用模拟客户端服务的接口，进行系统测试。

### 启动数据库

```dockerfile
docker run -p 3306:3306 
--name mysql 
-v D:/docker-data/mysql/conf:/etc/mysql 
-v D:/docker-data/mysql/logs:/var/log/mysql 
-v D:/docker-data/mysql/data:/var/lib/mysql 
-e MYSQL_ROOT_PASSWORD=123456 
-d mysql:5.7
```

创建数据库

```sql
CREATE
DATABASE `alan_account` CHARACTER SET 'utf8mb4';
CREATE
DATABASE `alan_bank` CHARACTER SET 'utf8mb4';
CREATE
DATABASE `alan_goods` CHARACTER SET 'utf8mb4';
CREATE
DATABASE `alan_order` CHARACTER SET 'utf8mb4';
```

创建数据库用户

```
# 创建用户
create user alan@'127.0.0.1' identified by 'alan123';

# 授权普通数据用户，查询、插入、更新、删除 数据库中所有表数据的权利。
grant select, insert, update, delete on alan_account.* to alan@'127.0.0.1';
grant select, insert, update, delete on alan_bank.* to alan@'127.0.0.1';
grant select, insert, update, delete on alan_goods.* to alan@'127.0.0.1';
grant select, insert, update, delete on alan_order.* to alan@'127.0.0.1';

# 授权数据库开发人员，创建、修改、删除 MySQL 数据表结构权限。(可选)
grant create on testdb.* to alan@'127.0.0.1';
grant alter on testdb.* to alan@'127.0.0.1';
grant drop   on testdb.* to alan@'127.0.0.1';
```

初始化数据库表，运行各个服务中的test-db-init中的测试类初始化数据。

### 启动Redis

```
docker run -itd --name redis -p 6379:6379 redis
```

### 启动nacos

```
docker run -it \
-e MODE=standalone \
--restart=always \
--name nacos \
-p 8848:8848 \
nacos/nacos-server:v2.1.0-BETA
```

### 启动Sentinel

```
docker run --name sentinel -d -p 8858:8858 -d bladex/sentinel-dashboard
```

启动后，调用模拟客户端接口各种测试接口，查看监控系统各接口性能与资源变化

## 更新日志

- 安装`Mysql`，`Redis`，`Nacos`，`Sentinel`，将**服务注册**到`Nacos`
- `Spring Boot Admin`监控服务资源
- `Sentinel`实现**`QPS`监控**和**限流熔断**
- `Redisson`实现**分布式锁**
- 实现用户**秒杀商品**的逻辑，并测试通过
- 增加一张千万级别的账号表，并初始化1000 W数据到数据库
- 增加`account_sharding`模块，整合`Sharding-JDBC`并实现**分库分表**

## 待办

- （`Seata`）使用分布式事务，实现用户付款流程
