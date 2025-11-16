-- 插入学历字典项
INSERT IGNORE INTO `sys_dict_item` (`id`, `dict_id`, `item_text`, `item_value`, `sort_order`, `description`, `create_by`, `create_time`, `status`)
VALUES
('d7e5b8a1f4c06b5e0a7c9b6d0f3e9a5b', 'c8d6f7a0e3b95a4d9b6f8a5c9e2d8f4a', '博士', 'doctor', 1, '博士学历', 'admin', NOW(), 1),
('d7e5b8a1f4c06b5e0a7c9b6d0f3e9a5c', 'c8d6f7a0e3b95a4d9b6f8a5c9e2d8f4a', '硕士', 'master', 2, '硕士学历', 'admin', NOW(), 1),
('d7e5b8a1f4c06b5e0a7c9b6d0f3e9a5d', 'c8d6f7a0e3b95a4d9b6f8a5c9e2d8f4a', '本科', 'bachelor', 3, '本科学历', 'admin', NOW(), 1),
('d7e5b8a1f4c06b5e0a7c9b6d0f3e9a5e', 'c8d6f7a0e3b95a4d9b6f8a5c9e2d8f4a', '专科', 'college', 4, '专科学历', 'admin', NOW(), 1),
('d7e5b8a1f4c06b5e0a7c9b6d0f3e9a5f', 'c8d6f7a0e3b95a4d9b6f8a5c9e2d8f4a', '其他', 'other', 5, '其他学历', 'admin', NOW(), 1);