-- 学历字典配置
INSERT INTO `sys_dict` (`id`, `dict_name`, `dict_code`, `description`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`, `type`) VALUES
('1219389258464342018', '学历', 'education', '学历字典', 0, 'admin', '2023-12-01 10:00:00', NULL, NULL, 0);

-- 学历字典数据
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `item_text`, `item_value`, `description`, `sort_order`, `status`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES
('1219389313522550790', '1219389258464342018', '博士', 'doctor', '博士学位', 1, 1, 'admin', '2023-12-01 10:00:00', NULL, NULL),
('1219389355786141700', '1219389258464342018', '硕士', 'master', '硕士学位', 2, 1, 'admin', '2023-12-01 10:00:00', NULL, NULL),
('1219389355786141701', '1219389258464342018', '本科', 'bachelor', '学士学位', 3, 1, 'admin', '2023-12-01 10:00:00', NULL, NULL),
('1219389355786141702', '1219389258464342018', '专科', 'college', '专科文凭', 4, 1, 'admin', '2023-12-01 10:00:00', NULL, NULL),
('1219389355786141703', '1219389258464342018', '高中', 'high_school', '高中学历', 5, 1, 'admin', '2023-12-01 10:00:00', NULL, NULL),
('1219389355786141704', '1219389258464342018', '其他', 'other', '其他学历', 6, 1, 'admin', '2023-12-01 10:00:00', NULL, NULL);