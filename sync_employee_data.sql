-- 插入数据字典 - 学历
INSERT IGNORE INTO `sys_dict` (`id`, `dict_name`, `dict_code`, `description`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`, `tenant_id`)
VALUES ('c8d6f7a0e3b95a4d9b6f8a5c9e2d8f4a', '员工学历', 'employee_education', '员工学历字典', 0, 'admin', NOW(), NULL, NULL, '0');

INSERT IGNORE INTO `sys_dict_item` (`id`, `dict_id`, `item_text`, `item_value`, `sort_order`, `description`, `create_by`, `create_time`, `update_by`, `update_time`, `status`)
VALUES
('d7e5b8a1f4c06b5e0a7c9b6d0f3e9a5b', 'c8d6f7a0e3b95a4d9b6f8a5c9e2d8f4a', '博士', 'doctor', 1, '博士学历', 'admin', NOW(), NULL, NULL, 1),
('d7e5b8a1f4c06b5e0a7c9b6d0f3e9a5c', 'c8d6f7a0e3b95a4d9b6f8a5c9e2d8f4a', '硕士', 'master', 2, '硕士学历', 'admin', NOW(), NULL, NULL, 1),
('d7e5b8a1f4c06b5e0a7c9b6d0f3e9a5d', 'c8d6f7a0e3b95a4d9b6f8a5c9e2d8f4a', '本科', 'bachelor', 3, '本科学历', 'admin', NOW(), NULL, NULL, 1),
('d7e5b8a1f4c06b5e0a7c9b6d0f3e9a5e', 'c8d6f7a0e3b95a4d9b6f8a5c9e2d8f4a', '专科', 'college', 4, '专科学历', 'admin', NOW(), NULL, NULL, 1),
('d7e5b8a1f4c06b5e0a7c9b6d0f3e9a5f', 'c8d6f7a0e3b95a4d9b6f8a5c9e2d8f4a', '其他', 'other', 5, '其他学历', 'admin', NOW(), NULL, NULL, 1);

-- 插入员工管理子菜单及操作权限
INSERT IGNORE INTO `sys_permission` (`id`, `parent_id`, `name`, `url`, `component`, `component_name`, `redirect`, `menu_type`, `perms`, `perms_type`, `sort_no`, `always_show`, `icon`, `is_route`, `is_leaf`, `keep_alive`, `hidden`, `hide_tab`, `description`, `create_by`, `create_time`, `update_by`, `update_time`, `del_flag`, `rule_flag`, `status`, `internal_or_external`, `tenant_id`)
VALUES
('f2d1e9c5b7g25b0f9e3c6d8f0g4b7e2f', 'e1c0f8b4a6f14a9e8d2b5c7e9f3a6d1e', '员工管理', '/demo/hr/employee/EmployeeList', 'demo/hr/employee/index', NULL, NULL, 1, NULL, 1, 1, 0, 'team', 1, 1, 0, 0, 0, '员工基本信息管理', 'admin', NOW(), NULL, NULL, 0, 0, 1, 0, '0'),
('g3e2f0d6c8h36c1g0f4d7e9g1h5c8f3g', 'f2d1e9c5b7g25b0f9e3c6d8f0g4b7e2f', '查看员工', NULL, NULL, NULL, NULL, 2, 'demo:hr:employee:list', 1, 1, 0, NULL, 0, 1, 0, 0, 1, '查看员工列表', 'admin', NOW(), NULL, NULL, 0, 0, 1, 0, '0'),
('h4f3g1e7d9i47d2h1g5e8f0h2i6d9h4h', 'f2d1e9c5b7g25b0f9e3c6d8f0g4b7e2f', '新增员工', NULL, NULL, NULL, NULL, 2, 'demo:hr:employee:add', 1, 2, 0, NULL, 0, 1, 0, 0, 1, '新增员工信息', 'admin', NOW(), NULL, NULL, 0, 0, 1, 0, '0'),
('i5g4f2f8e0j58e3i2h6f9g1j3i7e0i5i', 'f2d1e9c5b7g25b0f9e3c6d8f0g4b7e2f', '编辑员工', NULL, NULL, NULL, NULL, 2, 'demo:hr:employee:edit', 1, 3, 0, NULL, 0, 1, 0, 0, 1, '编辑员工信息', 'admin', NOW(), NULL, NULL, 0, 0, 1, 0, '0'),
('j6h5g3f9f1k69f4j3i7g0h2k4j8f1j6j', 'f2d1e9c5b7g25b0f9e3c6d8f0g4b7e2f', '删除员工', NULL, NULL, NULL, NULL, 2, 'demo:hr:employee:delete', 1, 4, 0, NULL, 0, 1, 0, 0, 1, '删除员工信息', 'admin', NOW(), NULL, NULL, 0, 0, 1, 0, '0'),
('k7i6h4g0g270a5k4j8h1i3l5k9g2k7k', 'f2d1e9c5b7g25b0f9e3c6d8f0g4b7e2f', '导出员工', NULL, NULL, NULL, NULL, 2, 'demo:hr:employee:export', 1, 5, 0, NULL, 0, 1, 0, 0, 1, '导出员工信息', 'admin', NOW(), NULL, NULL, 0, 0, 1, 0, '0'),
('l8j7i5h1h381b6l5k9i2j4m6l0h3l8l', 'f2d1e9c5b7g25b0f9e3c6d8f0g4b7e2f', '导入员工', NULL, NULL, NULL, NULL, 2, 'demo:hr:employee:import', 1, 6, 0, NULL, 0, 1, 0, 0, 1, '导入员工信息', 'admin', NOW(), NULL, NULL, 0, 0, 1, 0, '0');

-- 插入测试数据
INSERT IGNORE INTO `employee` (`id`, `employee_no`, `name`, `gender`, `birth_date`, `id_card`, `mobile`, `email`, `department_id`, `position`, `hire_date`, `employee_status`, `address`, `emergency_contact`, `emergency_phone`, `education`, `remark`, `create_by`, `create_time`, `update_by`, `update_time`, `del_flag`)
VALUES
('1', 'EMP001', '张三', 'male', '1990-01-01', '110101199001011234', '13800138001', 'zhangsan@example.com', '1', '软件工程师', '2023-01-01', 'active', '北京市朝阳区', '李四', '13900139001', 'bachelor', '优秀员工', 'admin', NOW(), 'admin', NOW(), 0),
('2', 'EMP002', '李四', 'female', '1992-02-02', '110101199002022345', '13800138002', 'lisi@example.com', '2', '产品经理', '2023-02-01', 'active', '北京市海淀区', '张三', '13800138001', 'master', '新入职员工', 'admin', NOW(), 'admin', NOW(), 0),
('3', 'EMP003', '王五', 'male', '1988-03-03', '110101198803033456', '13800138003', 'wangwu@example.com', '1', '高级软件工程师', '2022-03-01', 'active', '北京市西城区', '赵六', '13700137001', 'bachelor', '技术骨干', 'admin', NOW(), 'admin', NOW(), 0),
('4', 'EMP004', '赵六', 'female', '1995-04-04', '110101199504044567', '13800138004', 'zhaoliu@example.com', '3', 'UI设计师', '2023-04-01', 'trial', '北京市东城区', '钱七', '13600136001', 'college', '试用期员工', 'admin', NOW(), 'admin', NOW(), 0),
('5', 'EMP005', '钱七', 'male', '1985-05-05', '110101198505055678', '13800138005', 'qianqi@example.com', '2', '项目经理', '2021-05-01', 'active', '北京市丰台区', '孙八', '13500135001', 'master', '经验丰富', 'admin', NOW(), 'admin', NOW(), 0);