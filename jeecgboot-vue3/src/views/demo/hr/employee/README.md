# 员工基本信息管理模块

## 📋 模块概述

基于JeecgBoot 3.8.2框架开发的员工基本信息管理模块，提供完整的员工信息增删改查、搜索、导入导出等功能。

## 🚀 快速开始

### 1. 数据库初始化
确保已执行以下SQL脚本：
- 员工表创建：`V1.0.0__Create_employee_table.sql`
- 数据字典配置：`gender_dict.sql`、`employee_status_dict.sql`、`education_dict.sql`

### 2. 启动应用
- 后端：启动Spring Boot应用
- 前端：启动Vue3开发服务器
- 访问：http://localhost:3000/#/demo/hr/employee

## 📁 文件结构

```
employee/
├── index.vue              # 员工列表页面
├── employee.data.ts       # 表格列和表单配置
├── README.md             # 模块说明
├── api/
│   └── employee.ts       # API接口定义
└── components/
    └── EmployeeModal.vue # 新增编辑弹窗
```

## 🎯 功能特性

- ✅ **列表展示**：分页、排序、筛选
- ✅ **搜索功能**：多条件组合搜索
- ✅ **新增员工**：完整的表单验证
- ✅ **编辑员工**：支持在线编辑
- ✅ **删除员工**：单个删除和批量删除
- ✅ **导入导出**：Excel格式数据导入导出
- ✅ **字典集成**：性别、状态、学历等字段
- ✅ **权限控制**：基于按钮级权限控制

## 🔧 主要API接口

### 列表查询
```typescript
GET /demo/hr/employee/list
```

### 新增员工
```typescript
POST /demo/hr/employee/add
```

### 编辑员工
```typescript
PUT /demo/hr/employee/edit
```

### 删除员工
```typescript
DELETE /demo/hr/employee/delete?id={id}
```

### 导出数据
```typescript
GET /demo/hr/employee/exportXls
```

### 导入数据
```typescript
POST /demo/hr/employee/importExcel
```

## 📊 数据字典

| 字典代码 | 说明 | 选项 |
|---------|------|------|
| gender | 性别 | 男(male)、女(female) |
| employee_status | 员工状态 | 在职(active)、离职(inactive)、试用(trial)、退休(retired) |
| education | 学历 | 博士(doctor)、硕士(master)、本科(bachelor)、专科(college)、高中(high_school)、其他(other) |

## 🛠️ 技术实现

- **前端框架**：Vue3 + TypeScript + Ant Design Vue
- **UI组件**：JeecgBoot组件库
- **状态管理**：Composition API
- **表格组件**：BasicTable
- **表单组件**：BasicForm
- **弹窗组件**：BasicModal

## 🔍 代码说明

### 表格配置
在 `employee.data.ts` 中定义了表格列和搜索表单配置，支持字典显示和筛选。

### 表单配置
使用 `BasicForm` 组件，支持动态表单验证和字典选择。

### API调用
统一使用 `defHttp` 进行API调用，支持请求拦截和响应处理。

## ⚠️ 注意事项

1. **权限配置**：需要在系统菜单中配置对应的权限点
2. **字典配置**：确保数据字典已正确配置
3. **文件上传**：导入功能需要配置正确的文件上传路径
4. **数据验证**：前后端都有完整的数据验证规则

## 📞 常见问题

### Q: 页面提示"Failed to resolve import"
A: 检查文件路径是否正确，确保已安装所有依赖包

### Q: 数据字典不显示
A: 检查数据库中的字典配置是否正确

### Q: 导入导出功能不工作
A: 检查文件上传配置和权限设置

## 🔄 更新日志

### v1.0.0 (2023-12-01)
- 初始版本发布
- 完整的CRUD功能
- 数据字典集成
- 导入导出功能