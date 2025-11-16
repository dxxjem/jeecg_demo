# 员工基本信息管理模块

基于JeecgBoot 3.8.2框架开发的员工基本信息管理模块，提供完整的员工信息增删改查、搜索、导入导出等功能。

## 📋 功能特性

### ✨ 核心功能
- **基础CRUD操作**：员工信息的添加、编辑、删除、查询
- **高级搜索**：支持多条件组合搜索和筛选
- **数据导入导出**：Excel格式数据的批量导入导出
- **表单验证**：完整的前后端数据验证规则
- **批量操作**：支持批量删除等操作
- **权限控制**：基于Shiro的细粒度权限控制

### 🎯 业务功能
- **员工基础信息管理**：工号、姓名、性别、出生日期、身份证号等
- **联系方式管理**：手机号、邮箱、联系地址等
- **工作信息管理**：职位、入职日期、员工状态、学历等
- **紧急联系人管理**：紧急联系人和联系电话
- **数据字典集成**：性别、员工状态、学历等字段使用数据字典

### 🔧 技术特性
- **前后端分离**：Vue3 + TypeScript + Spring Boot
- **响应式设计**：适配各种屏幕尺寸
- **高性能查询**：分页查询、索引优化
- **安全性保障**：SQL注入防护、XSS防护、权限验证
- **代码生成**：基于JeecgBoot代码生成器开发

## 📁 文件结构

### 后端文件结构
```
jeecg-boot/jeecg-boot-module/jeecg-module-demo/src/main/java/org/jeecg/modules/demo/hr/
├── entity/                          # 实体类
│   └── Employee.java               # 员工实体类
├── mapper/                         # 数据访问层
│   ├── EmployeeMapper.java         # Mapper接口
│   └── xml/
│       └── EmployeeMapper.xml      # MyBatis映射文件
├── service/                        # 业务逻辑层
│   ├── IEmployeeService.java       # Service接口
│   └── impl/
│       └── EmployeeServiceImpl.java # Service实现类
├── controller/                     # 控制器层
│   └── EmployeeController.java     # REST API控制器
├── vo/                            # 数据传输对象
│   ├── EmployeeVO.java            # 视图对象
│   └── EmployeeDTO.java           # 数据传输对象
```

### 前端文件结构
```
jeecgboot-vue3/src/views/demo/hr/employee/
├── index.vue                      # 员工列表页面
├── components/                    # 组件目录
│   ├── EmployeeModal.vue         # 新增编辑弹窗
│   └── EmployeeForm.vue          # 表单组件
├── api/
│   └── employee.ts               # API接口定义
├── model/
│   └── employeeModel.ts          # 数据模型定义
└── README.md                     # 模块说明文档
```

### 数据库文件结构
```
jeecg-boot/jeecg-boot-module/jeecg-module-demo/src/main/resources/db/hr/
├── migration/                     # 数据库迁移脚本
│   └── V1.0.0__Create_employee_table.sql
├── dict/                         # 数据字典脚本
│   ├── gender_dict.sql
│   ├── employee_status_dict.sql
│   └── education_dict.sql
└── init_data.sql                 # 初始化数据脚本
```

## 🚀 快速开始

### 1. 数据库初始化
执行以下SQL脚本来初始化数据库：

```sql
-- 1. 创建员工表
SOURCE jeecg-boot/jeecg-boot-module/jeecg-module-demo/src/main/resources/db/hr/migration/V1.0.0__Create_employee_table.sql;

-- 2. 插入数据字典
SOURCE jeecg-boot/jeecg-boot-module/jeecg-module-demo/src/main/resources/db/hr/dict/gender_dict.sql;
SOURCE jeecg-boot/jeecg-boot-module/jeecg-module-demo/src/main/resources/db/hr/dict/employee_status_dict.sql;
SOURCE jeecg-boot/jeecg-boot-module/jeecg-module-demo/src/main/resources/db/hr/dict/education_dict.sql;
```

### 2. 后端启动
确保数据库配置正确后，启动后端服务：

```bash
cd jeecg-boot/jeecg-module-system/jeecg-system-start
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### 3. 前端启动
启动前端开发服务器：

```bash
cd jeecgboot-vue3
npm install
npm run dev
```

### 4. 访问系统
- 前端访问：http://localhost:3000/#/demo/hr/employee
- API文档：http://localhost:8080/jeecg-boot/doc.html
- 后台管理：http://localhost:8080/jeecg-boot/

## 🔌 API接口文档

### 基础信息
- **Base URL**: `/jeecg-boot/demo/hr/employee`
- **认证方式**: JWT Token (Header: X-Access-Token)
- **数据格式**: JSON
- **字符编码**: UTF-8

### 主要接口

| 方法 | 路径 | 说明 |
|------|------|------|
| GET | /list | 分页查询员工列表 |
| GET | /queryById | 根据ID查询员工详情 |
| POST | /add | 新增员工 |
| PUT | /edit | 编辑员工 |
| DELETE | /delete | 删除单个员工 |
| DELETE | /deleteBatch | 批量删除员工 |
| GET | /exportXls | 导出员工数据 |
| POST | /importExcel | 导入员工数据 |
| GET | /checkEmployeeNo | 校验员工工号唯一性 |
| GET | /checkIdCard | 校验身份证号唯一性 |
| GET | /checkMobile | 校验手机号唯一性 |

## 📊 数据字典

### 性别字典 (gender)
| 值 | 标签 | 说明 |
|----|------|------|
| male | 男 | 男性 |
| female | 女 | 女性 |

### 员工状态字典 (employee_status)
| 值 | 标签 | 说明 |
|----|------|------|
| active | 在职 | 在职状态 |
| inactive | 离职 | 离职状态 |
| trial | 试用 | 试用期状态 |
| retired | 退休 | 退休状态 |

### 学历字典 (education)
| 值 | 标签 | 说明 |
|----|------|------|
| doctor | 博士 | 博士学位 |
| master | 硕士 | 硕士学位 |
| bachelor | 本科 | 学士学位 |
| college | 专科 | 专科文凭 |
| high_school | 高中 | 高中学历 |
| other | 其他 | 其他学历 |

## 🛡️ 安全特性

### 数据验证
- **前端验证**：Vue3表单验证规则
- **后端验证**：Spring Validation + 业务逻辑验证
- **唯一性校验**：员工工号、身份证号等字段唯一性保证

### 权限控制
- **菜单权限**：employee:list
- **新增权限**：employee:add
- **编辑权限**：employee:edit
- **删除权限**：employee:delete
- **导出权限**：employee:export
- **导入权限**：employee:import

### 安全防护
- **SQL注入防护**：MyBatis预编译语句
- **XSS防护**：输入输出过滤
- **CSRF防护**：Token验证
- **文件上传安全**：文件类型、大小限制

## 📈 性能优化

### 数据库优化
- **索引设计**：员工工号、部门ID、创建时间等关键字段索引
- **分页查询**：避免全表扫描，提高查询效率
- **逻辑删除**：保护数据安全，支持数据恢复

### 前端优化
- **组件懒加载**：按需加载组件，减少初始加载时间
- **虚拟滚动**：处理大量数据时的性能优化
- **缓存策略**：合理利用浏览器缓存

### 后端优化
- **异步处理**：耗时操作异步执行
- **缓存机制**：字典数据缓存，减少数据库查询
- **连接池优化**：数据库连接池配置优化

## 🧪 测试

### 单元测试
- **实体类测试**：数据验证逻辑测试
- **Service层测试**：业务逻辑测试
- **Controller层测试**：API接口测试

### 集成测试
- **数据库操作测试**：增删改查功能测试
- **权限验证测试**：各权限点功能测试
- **导入导出测试**：文件处理功能测试

## 🔧 部署配置

### 开发环境
- **数据库**：MySQL 5.7+
- **JDK**：1.8+
- **Node.js**：16+
- **Redis**：6.0+

### 生产环境
- **应用服务器**：Tomcat 8.5+
- **数据库**：MySQL 8.0+
- **缓存**：Redis Cluster
- **负载均衡**：Nginx

## 📝 更新日志

### v1.0.0 (2023-12-01)
- ✨ 基础CRUD功能实现
- ✨ 数据导入导出功能
- ✨ 权限控制集成
- ✨ 数据字典集成
- ✨ 表单验证实现
- 🐛 修复已知问题

## 🤝 贡献指南

1. Fork 本仓库
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启 Pull Request

## 📄 许可证

本项目基于 [MIT License](LICENSE) 许可证开源。

## 📞 联系我们

- **项目维护者**：JeecgBoot开发团队
- **问题反馈**：[GitHub Issues](https://github.com/jeecgboot/jeecg-boot/issues)
- **技术交流**：[JeecgBoot官方QQ群](https://jeecg.com)

## 🙏 致谢

感谢 JeecgBoot 框架提供的强大支持，以及所有贡献者的努力！