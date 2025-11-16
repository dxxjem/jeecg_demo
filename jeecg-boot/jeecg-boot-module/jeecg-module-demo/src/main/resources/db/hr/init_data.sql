-- 员工模块初始化数据脚本
-- 执行顺序：先创建表，再插入字典数据，最后插入员工数据

-- 执行表创建脚本
-- SOURCE V1.0.0__Create_employee_table.sql;

-- 执行字典创建脚本
-- SOURCE dict/gender_dict.sql;
-- SOURCE dict/employee_status_dict.sql;
-- SOURCE dict/education_dict.sql;

-- 检查表是否创建成功
SELECT 'employee table exists' AS status FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'employee';

-- 检查字典数据是否插入成功
SELECT COUNT(*) AS gender_dict_count FROM sys_dict WHERE dict_code = 'gender';
SELECT COUNT(*) AS employee_status_dict_count FROM sys_dict WHERE dict_code = 'employee_status';
SELECT COUNT(*) AS education_dict_count FROM sys_dict WHERE dict_code = 'education';

-- 检查员工数据是否插入成功
SELECT COUNT(*) AS employee_count FROM employee WHERE del_flag = 0;