-- 性别字典配置
INSERT INTO `sys_dict` (`id`, `dict_name`, `dict_code`, `description`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`, `type`) VALUES
('1219389258464342016', '性别', 'gender', '性别字典', 0, 'admin', '2023-12-01 10:00:00', NULL, NULL, 0);

-- 性别字典数据
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `item_text`, `item_value`, `description`, `sort_order`, `status`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES
('1219389313522550784', '1219389258464342016', '男', 'male', '男性', 1, 1, 'admin', '2023-12-01 10:00:00', NULL, NULL),
('1219389355786141696', '1219389258464342016', '女', 'female', '女性', 2, 1, 'admin', '2023-12-01 10:00:00', NULL, NULL);