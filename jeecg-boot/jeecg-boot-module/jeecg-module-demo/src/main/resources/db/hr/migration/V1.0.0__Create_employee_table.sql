-- 创建员工基本信息表
CREATE TABLE `employee` (
  `id` varchar(36) NOT NULL COMMENT '主键ID',
  `employee_no` varchar(32) NOT NULL COMMENT '员工工号',
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `gender` varchar(10) DEFAULT NULL COMMENT '性别（male/female）',
  `birth_date` date DEFAULT NULL COMMENT '出生日期',
  `id_card` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `department_id` varchar(36) DEFAULT NULL COMMENT '所属部门ID',
  `position` varchar(50) DEFAULT NULL COMMENT '职位',
  `hire_date` date DEFAULT NULL COMMENT '入职日期',
  `employee_status` varchar(20) DEFAULT NULL COMMENT '员工状态（active/inactive/trial）',
  `address` varchar(200) DEFAULT NULL COMMENT '联系地址',
  `emergency_contact` varchar(50) DEFAULT NULL COMMENT '紧急联系人',
  `emergency_phone` varchar(11) DEFAULT NULL COMMENT '紧急联系电话',
  `education` varchar(20) DEFAULT NULL COMMENT '学历（doctor/master/bachelor/college/other）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志（0：正常，1：删除）',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `version` int(11) DEFAULT '1' COMMENT '版本号（乐观锁）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_employee_no` (`employee_no`),
  KEY `idx_department_id` (`department_id`),
  KEY `idx_employee_status` (`employee_status`),
  KEY `idx_create_time` (`create_time`),
  KEY `idx_mobile` (`mobile`),
  KEY `idx_id_card` (`id_card`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='员工基本信息表';

-- 插入示例数据
INSERT INTO `employee` (`id`, `employee_no`, `name`, `gender`, `birth_date`, `id_card`, `mobile`, `email`, `department_id`, `position`, `hire_date`, `employee_status`, `address`, `emergency_contact`, `emergency_phone`, `education`, `remark`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES
('1', 'EMP001', '张三', 'male', '1990-01-01', '110101199001011234', '13800138001', 'zhangsan@example.com', 'DEPT001', '软件工程师', '2023-01-01', 'active', '北京市朝阳区', '李四', '13900139001', 'bachelor', '优秀员工', 'admin', '2023-01-01 10:00:00', 'admin', '2023-12-01 15:30:00'),
('2', 'EMP002', '李四', 'female', '1992-02-02', '110101199002022345', '13800138002', 'lisi@example.com', 'DEPT002', '产品经理', '2023-02-01', 'active', '北京市海淀区', '张三', '13800138001', 'master', '新入职员工', 'admin', '2023-02-01 09:30:00', 'admin', '2023-12-01 16:00:00'),
('3', 'EMP003', '王五', 'male', '1988-03-03', '110101198803033456', '13800138003', 'wangwu@example.com', 'DEPT001', '高级软件工程师', '2022-03-01', 'active', '北京市西城区', '赵六', '13900139002', 'bachelor', '资深员工', 'admin', '2022-03-01 14:20:00', 'admin', '2023-12-01 14:45:00'),
('4', 'EMP004', '赵六', 'female', '1995-04-04', '110101199504044567', '13800138004', 'zhaoliu@example.com', 'DEPT003', 'UI设计师', '2023-04-01', 'trial', '北京市东城区', '钱七', '13900139003', 'college', '试用期员工', 'admin', '2023-04-01 11:15:00', 'admin', '2023-12-01 13:20:00'),
('5', 'EMP005', '钱七', 'male', '1985-05-05', '110101198505055678', '13800138005', 'qianqi@example.com', 'DEPT004', '项目经理', '2021-05-01', 'active', '北京市丰台区', '孙八', '13900139004', 'master', '管理人员', 'admin', '2021-05-01 16:45:00', 'admin', '2023-12-01 12:10:00');