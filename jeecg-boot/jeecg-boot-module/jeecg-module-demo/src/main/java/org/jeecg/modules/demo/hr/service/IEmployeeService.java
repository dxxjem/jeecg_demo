package org.jeecg.modules.demo.hr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.demo.hr.entity.Employee;

import java.util.List;

/**
 * @Description: 员工基本信息表
 * @Author: jeecg
 * @Date: 2023-12-01
 * @Version: V1.0
 */
public interface IEmployeeService extends IService<Employee> {

    /**
     * 分页列表查询
     * @param page 分页对象
     * @param employee 查询条件
     * @return 分页结果
     */
    IPage<Employee> queryPageList(Page<Employee> page, Employee employee);

    /**
     * 根据员工工号查询员工信息
     * @param employeeNo 员工工号
     * @return 员工信息
     */
    Employee queryByEmployeeNo(String employeeNo);

    /**
     * 根据身份证号查询员工信息
     * @param idCard 身份证号
     * @return 员工信息
     */
    Employee queryByIdCard(String idCard);

    /**
     * 根据手机号查询员工信息
     * @param mobile 手机号
     * @return 员工信息
     */
    Employee queryByMobile(String mobile);

    /**
     * 新增员工
     * @param employee 员工信息
     * @return 是否成功
     */
    boolean addEmployee(Employee employee);

    /**
     * 编辑员工
     * @param employee 员工信息
     * @return 是否成功
     */
    boolean editEmployee(Employee employee);

    /**
     * 根据ID删除员工（逻辑删除）
     * @param id 员工ID
     * @return 是否成功
     */
    boolean deleteEmployee(String id);

    /**
     * 批量删除员工（逻辑删除）
     * @param ids 员工ID列表
     * @return 是否成功
     */
    boolean deleteBatchEmployees(List<String> ids);

    /**
     * 根据ID查询员工详情
     * @param id 员工ID
     * @return 员工详情
     */
    Employee queryEmployeeById(String id);

    /**
     * 查询指定部门的员工数量
     * @param departmentId 部门ID
     * @return 员工数量
     */
    Integer countByDepartmentId(String departmentId);

    /**
     * 查询指定状态的员工数量
     * @param employeeStatus 员工状态
     * @return 员工数量
     */
    Integer countByEmployeeStatus(String employeeStatus);

    /**
     * 根据条件查询员工列表
     * @param employee 查询条件
     * @return 员工列表
     */
    List<Employee> queryEmployeeList(Employee employee);

    /**
     * 校验员工工号唯一性
     * @param employeeNo 员工工号
     * @param id 员工ID（编辑时传入）
     * @return 是否唯一
     */
    boolean checkEmployeeNoUnique(String employeeNo, String id);

    /**
     * 校验身份证号唯一性
     * @param idCard 身份证号
     * @param id 员工ID（编辑时传入）
     * @return 是否唯一
     */
    boolean checkIdCardUnique(String idCard, String id);

    /**
     * 校验手机号唯一性
     * @param mobile 手机号
     * @param id 员工ID（编辑时传入）
     * @return 是否唯一
     */
    boolean checkMobileUnique(String mobile, String id);

    /**
     * 校验员工数据
     * @param employee 员工信息
     * @param isEdit 是否为编辑操作
     * @return 校验结果
     */
    String validateEmployee(Employee employee, boolean isEdit);
}