-- 员工状态字典配置
INSERT INTO `sys_dict` (`id`, `dict_name`, `dict_code`, `description`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`, `type`) VALUES
('1219389258464342017', '员工状态', 'employee_status', '员工状态字典', 0, 'admin', '2023-12-01 10:00:00', NULL, NULL, 0);

-- 员工状态字典数据
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `item_text`, `item_value`, `description`, `sort_order`, `status`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES
('1219389313522550785', '1219389258464342017', '在职', 'active', '在职状态', 1, 1, 'admin', '2023-12-01 10:00:00', NULL, NULL),
('1219389355786141697', '1219389258464342017', '离职', 'inactive', '离职状态', 2, 1, 'admin', '2023-12-01 10:00:00', NULL, NULL),
('1219389355786141698', '1219389258464342017', '试用', 'trial', '试用期状态', 3, 1, 'admin', '2023-12-01 10:00:00', NULL, NULL),
('1219389355786141699', '1219389258464342017', '退休', 'retired', '退休状态', 4, 1, 'admin', '2023-12-01 10:00:00', NULL, NULL);