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

启动数据库

```shell
# 启动原始容器
docker run -d \
-p 3306:3306 \
--name mysql \
-e MYSQL_ROOT_PASSWORD=123456 \
mysql:5.7
# 拷贝配置和数据
docker cp mysql:/etc/mysql /home/docker/conf
docker cp mysql:/var/log /home/docker/mysql/logs
docker cp mysql:/var/lib/mysql /home/docker/mysql/data
# 删除原始容器
docker rm -f mysql
# 启动容器挂载配置和数据
docker run -d \
-p 3306:3306 \
--name mysql \
-v /home/docker/mysql/conf:/etc/mysql \
-v /home/docker/mysql/logs:/var/log/mysql \
-v /home/docker/mysql/data:/var/lib/mysql \
-e MYSQL_ROOT_PASSWORD=123456 \
mysql:5.7
```

创建数据库表

- 执行`dbsql`里的脚本

初始化数据库表，运行各个服务中的test-db-init中的测试类初始化数据。

### 启动Redis

```shell
docker run -itd --name redis -p 6379:6379 redis
```

### 启动nacos

注册中心和配置中心。

这里只是把配置缓存到容器中，如果想要持久化到数据库，请自行查阅官方文档

```shell
docker run -d \
--name nacos \
-e MODE=standalone \
-p 7848:7848 \
-p 8848:8848 \
-p 9848:9848 \
-p 9849:9849 \
nacos/nacos-server:v2.1.0-BETA
```

### 启动Sentinel

监控性能用。

```shell
docker run --name sentinel -d -p 8858:8858 -d bladex/sentinel-dashboard
```

### 启动Seata

配置请过程自行参考[Seata官方文档](https://seata.io/zh-cn/docs/ops/deploy-guide-beginner.html)

```shell
docker run -d \
--name seata-server \
-p 8091:8091 \
-p 7091:7091 \
seataio/seata-server:1.5.2

docker cp seata-server:/seata-server/resources /home/docker/seata/resources
docker rm -f seata-server

# SEATA_IP配置宿主机ip，防止注册nacos时是容器ip，导致本地服务无法调用
# NACOS_IP、MYSQL_IP配置中自定义的环境配置，用于seata访问mysql和nacos
# 配置文件中的配置可以用占位符+环境变量，但是放在nacos的配置不行，必须直接写真实值
docker run -d \
--name seata-server \
-e SEATA_IP=192.168.31.63 \
-e MYSQL_IP=192.168.31.63 \
-e NACOS_IP=192.168.31.63 \
-p 8091:8091 \
-p 7091:7091 \
-v /home/docker/seata/resources:/seata-server/resources  \
seataio/seata-server:1.5.2
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
