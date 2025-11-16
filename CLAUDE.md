# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 项目概述

JeecgBoot 是一款企业级AI低代码平台，基于 Spring Boot 2.7.18 + MyBatis + Vue3。这是后端Java源码项目，支持单体和微服务架构。提供完整的低代码开发能力和AI应用平台功能。

## 架构设计

### 多模块结构
- **jeecg-boot-base-core**: 核心基础模块，包含通用配置、工具类和基础功能
- **jeecg-module-system**: 系统模块，包括用户管理、权限管理和核心业务逻辑
  - `jeecg-system-start`: 单体部署的主应用启动器
  - `jeecg-system-api`: API接口（本地版本和云版本）
  - `jeecg-system-biz`: 业务逻辑实现
- **jeecg-boot-module**: 扩展模块
  - `jeecg-module-demo`: 示例模块和演示
  - `jeecg-boot-module-airag`: AI RAG（检索增强生成）模块
- **jeecg-server-cloud**: 微服务组件
  - `jeecg-cloud-gateway`: API网关
  - `jeecg-cloud-nacos`: Nacos服务发现
  - `jeecg-system-cloud-start`: 云系统启动器
  - `jeecg-visual`: 可视化组件和监控工具

### 核心技术栈
- **框架**: Spring Boot 2.7.18, Spring Cloud Alibaba 2021.0.6.2
- **数据库**: MyBatis-Plus 3.5.12, 动态数据源管理
- **安全**: Apache Shiro 1.13.0, JWT 4.5.0
- **AI集成**: 支持ChatGPT、DeepSeek、Ollama等大模型
- **报表**: JimuReport 2.1.3 报表生成工具
- **缓存**: Redis with Redisson分布式锁
- **消息队列**: RabbitMQ、RocketMQ支持
- **工作流**: Flowable引擎集成

## 常用开发命令

### 构建和运行
```bash
# 构建整个项目
mvn clean install

# 运行单体应用（主类: org.jeecg.JeecgSystemApplication）
cd jeecg-boot/jeecg-module-system/jeecg-system-start
mvn spring-boot:run

# 运行微服务（网关）
cd jeecg-boot/jeecg-server-cloud/jeecg-cloud-gateway
mvn spring-boot:run

# 使用特定配置文件运行
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### 测试
```bash
# 运行所有测试
mvn test

# 运行特定模块测试
cd jeecg-module-system/jeecg-system-biz
mvn test
```

### Docker部署
```bash
# 启动docker-compose（单体模式）
docker-compose up -d

# 启动docker-compose（微服务模式）
docker-compose -f docker-compose-cloud.yml up -d
```

## 配置文件

### 数据库配置
- 主配置: `jeecg-module-system/jeecg-system-start/src/main/resources/application-dev.yml`
- 数据库特定配置: `application-mysql.yml`, `application-postgresql.yml`, `application-oracle.yml` 等
- 默认MySQL数据库: localhost:3306 上的 `jeecg-boot`

### 关键配置部分
- **数据源**: 支持主从配置的动态数据源
- **Redis**: 缓存和会话管理
- **AI集成**: `jeecg.ai-chat` 部分的DeepSeek/ChatGPT配置
- **文件上传**: 支持本地、MinIO或阿里云OSS
- **安全**: Shiro配置和JWT设置

## 默认账号密码
- **管理员**: admin / 123456
- **Druid监控**: admin / 123456
- **数据库**: root / root (MySQL)

## 开发模式

### 代码生成
JeecgBoot提供强大的代码生成功能：
- 通过Web界面访问在线代码生成器
- 基于模板的生成，支持单表、树结构、一对多关系
- 前端Vue3代码生成，支持TypeScript

### API开发
- RESTful API，支持Swagger文档（访问 `/doc.html`）
- 统一的响应格式（Result对象）
- 通过注解和Shiro集成进行权限验证

### 数据库访问
- 使用MyBatis-Plus进行ORM，自定义BaseMapper
- 支持动态数据源切换
- 多租户环境下的自动租户隔离

## AI功能

### AI聊天集成
- 默认模型: deepseek-chat
- 可配置的API密钥和端点
- 支持图像识别和OCR功能

### RAG（检索增强生成）
- 向量数据库集成（PostgreSQL）
- 知识库管理
- 文档嵌入和相似性搜索

## 重要说明

- 项目支持JDK 8和JDK 17，但默认使用JDK 17
- 启用Flyway进行数据库迁移（生产环境确保 `clean-disabled: true`）
- 多种部署模式：单体、微服务、Docker、Kubernetes
- 内置丰富的日志和监控功能
- 强大的安全特性，包括SQL注入防护、XSS防护和限流

## 微服务架构

微服务模式下：
- **网关**: 端口9999，处理路由和认证
- **Nacos**: 端口8848的服务注册中心和配置中心
- **系统服务**: 核心业务逻辑服务
- **监控**: Sentinel熔断、Skywalking分布式链路追踪

## 测试和质量

- 使用JUnit 5和Spring Boot Test进行单元测试
- 数据库操作和API端点的集成测试
- 包含性能测试工具和监控面板
- 使用Maven插件进行代码质量检查