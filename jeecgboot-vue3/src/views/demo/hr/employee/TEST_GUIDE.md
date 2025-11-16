# 员工模块测试指南

## 🔧 测试编辑功能

### 测试步骤

1. **启动应用**
   - 确保后端Spring Boot应用已启动
   - 确保前端Vue应用已启动

2. **访问员工管理页面**
   - 前端地址：http://localhost:3000/#/demo/hr/employee
   - 后端地址：http://localhost:8080/jeecg-boot

3. **测试编辑功能**
   - 在表格中点击任意员工行的"编辑"按钮
   - 检查弹窗是否正确打开并显示当前员工的数据
   - 查看浏览器控制台的日志输出

### 调试信息

编辑模式下，浏览器控制台应该显示以下日志：

```
弹窗数据: {record: {员工数据}, isUpdate: true}
编辑模式 - 原始数据: {完整的员工记录}
编辑模式 - 表单数据: {处理后的表单数据}
表单数据设置成功
```

### 预期行为

✅ **正确行为**：
- 编辑弹窗打开
- 表单中显示当前员工的所有信息
- 员工工号字段在编辑模式下应为只读或禁用
- 日期字段显示正确的日期格式
- 下拉选择框显示正确的选项

❌ **错误行为**：
- 弹窗打开但表单为空
- 某些字段没有正确填充
- 控制台显示错误信息

### 字段对照表

| 表单字段 | 后端字段 | 说明 |
|---------|---------|------|
| employeeNo | employeeNo | 员工工号 |
| name | name | 姓名 |
| gender | gender | 性别 |
| birthDate | birthDate | 出生日期 |
| idCard | idCard | 身份证号 |
| mobile | mobile | 手机号 |
| email | email | 邮箱 |
| position | position | 职位 |
| hireDate | hireDate | 入职日期 |
| employeeStatus | employeeStatus | 员工状态 |
| departmentId | departmentId | 部门ID |
| education | education | 学历 |
| address | address | 地址 |
| emergencyContact | emergencyContact | 紧急联系人 |
| emergencyPhone | emergencyPhone | 紧急电话 |
| remark | remark | 备注 |

### 常见问题排查

1. **表单数据未显示**
   - 检查控制台日志是否显示原始数据
   - 检查表单字段名是否匹配
   - 检查API返回的数据格式

2. **部分字段未填充**
   - 检查该字段在原始数据中是否存在
   - 检查字段名拼写是否正确
   - 检查字段值是否为null或undefined

3. **编辑功能不工作**
   - 检查isUpdate标志是否正确设置
   - 检查rowId是否正确赋值
   - 检查弹窗组件是否正确注册

### API测试

如果需要测试后端API，可以使用以下curl命令：

```bash
# 获取员工列表
curl -X GET "http://localhost:8080/jeecg-boot/demo/hr/employee/list?pageNo=1&pageSize=10" \
  -H "X-Access-Token: YOUR_TOKEN"

# 获取员工详情
curl -X GET "http://localhost:8080/jeecg-boot/demo/hr/employee/queryById?id=1" \
  -H "X-Access-Token: YOUR_TOKEN"
```

### 性能优化

编辑功能应该：
- 避免重复API调用（直接使用表格中的数据）
- 快速响应用户操作
- 正确处理字段映射和数据转换