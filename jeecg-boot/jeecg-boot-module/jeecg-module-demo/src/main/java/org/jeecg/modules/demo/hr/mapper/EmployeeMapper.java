package org.jeecg.modules.demo.hr.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.demo.hr.entity.Employee;

/**
 * @Description: 员工基本信息表
 * @Author: jeecg
 * @Date: 2023-12-01
 * @Version: V1.0
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    /**
     * 分页列表查询
     * @param page 分页对象
     * @param employee 查询条件
     * @return 分页结果
     */
    IPage<Employee> selectPage(Page<Employee> page, @Param("employee") Employee employee);

    /**
     * 根据员工工号查询员工信息
     * @param employeeNo 员工工号
     * @return 员工信息
     */
    Employee selectByEmployeeNo(@Param("employeeNo") String employeeNo);

    /**
     * 根据身份证号查询员工信息
     * @param idCard 身份证号
     * @return 员工信息
     */
    Employee selectByIdCard(@Param("idCard") String idCard);

    /**
     * 根据手机号查询员工信息
     * @param mobile 手机号
     * @return 员工信息
     */
    Employee selectByMobile(@Param("mobile") String mobile);

    /**
     * 查询指定部门的员工数量
     * @param departmentId 部门ID
     * @return 员工数量
     */
    Integer countByDepartmentId(@Param("departmentId") String departmentId);

    /**
     * 查询指定状态的员工数量
     * @param employeeStatus 员工状态
     * @return 员工数量
     */
    Integer countByEmployeeStatus(@Param("employeeStatus") String employeeStatus);

    /**
     * 批量查询员工信息
     * @param ids 员工ID列表
     * @return 员工信息列表
     */
    List<Employee> selectBatchIds(@Param("ids") List<String> ids);

    /**
     * 根据条件查询员工列表
     * @param employee 查询条件
     * @return 员工列表
     */
    List<Employee> selectList(@Param("employee") Employee employee);
}