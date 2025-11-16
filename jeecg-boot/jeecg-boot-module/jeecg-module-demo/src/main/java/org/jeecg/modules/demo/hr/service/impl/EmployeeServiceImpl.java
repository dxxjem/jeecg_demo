package org.jeecg.modules.demo.hr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.modules.demo.hr.entity.Employee;
import org.jeecg.modules.demo.hr.mapper.EmployeeMapper;
import org.jeecg.modules.demo.hr.service.IEmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Description: 员工基本信息表
 * @Author: jeecg
 * @Date: 2023-12-01
 * @Version: V1.0
 */
@Service
@Slf4j
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    @Override
    public IPage<Employee> queryPageList(Page<Employee> page, Employee employee) {
        return baseMapper.selectPage(page, employee);
    }

    @Override
    public Employee queryByEmployeeNo(String employeeNo) {
        return baseMapper.selectByEmployeeNo(employeeNo);
    }

    @Override
    public Employee queryByIdCard(String idCard) {
        return baseMapper.selectByIdCard(idCard);
    }

    @Override
    public Employee queryByMobile(String mobile) {
        return baseMapper.selectByMobile(mobile);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addEmployee(Employee employee) {
        // 数据校验
        String validateResult = validateEmployee(employee, false);
        if (validateResult != null) {
            throw new RuntimeException(validateResult);
        }

        // 设置创建信息
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        if (sysUser != null) {
            employee.setCreateBy(sysUser.getUsername());
            employee.setUpdateBy(sysUser.getUsername());
        }
        employee.setCreateTime(new Date());
        employee.setUpdateTime(new Date());
        employee.setDelFlag(0);
        employee.setVersion(1);

        return this.save(employee);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean editEmployee(Employee employee) {
        if (!StringUtils.hasText(employee.getId())) {
            throw new RuntimeException("员工ID不能为空");
        }

        // 查询原数据
        Employee originalEmployee = this.getById(employee.getId());
        if (originalEmployee == null) {
            throw new RuntimeException("员工信息不存在");
        }

        // 数据校验
        String validateResult = validateEmployee(employee, true);
        if (validateResult != null) {
            throw new RuntimeException(validateResult);
        }

        // 设置更新信息
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        if (sysUser != null) {
            employee.setUpdateBy(sysUser.getUsername());
        }
        employee.setUpdateTime(new Date());

        // 复制不可修改的字段
        employee.setCreateBy(originalEmployee.getCreateBy());
        employee.setCreateTime(originalEmployee.getCreateTime());
        employee.setDelFlag(originalEmployee.getDelFlag());

        return this.updateById(employee);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteEmployee(String id) {
        Employee employee = this.getById(id);
        if (employee == null) {
            throw new RuntimeException("员工信息不存在");
        }

        // 逻辑删除
        employee.setDelFlag(1);
        employee.setUpdateTime(new Date());

        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        if (sysUser != null) {
            employee.setUpdateBy(sysUser.getUsername());
        }

        return this.updateById(employee);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatchEmployees(List<String> ids) {
        if (ids == null || ids.isEmpty()) {
            return true;
        }

        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String updateBy = sysUser != null ? sysUser.getUsername() : null;
        Date updateTime = new Date();

        for (String id : ids) {
            Employee employee = this.getById(id);
            if (employee != null) {
                employee.setDelFlag(1);
                employee.setUpdateTime(updateTime);
                employee.setUpdateBy(updateBy);
                this.updateById(employee);
            }
        }

        return true;
    }

    @Override
    public Employee queryEmployeeById(String id) {
        return this.getById(id);
    }

    @Override
    public Integer countByDepartmentId(String departmentId) {
        return baseMapper.countByDepartmentId(departmentId);
    }

    @Override
    public Integer countByEmployeeStatus(String employeeStatus) {
        return baseMapper.countByEmployeeStatus(employeeStatus);
    }

    @Override
    public List<Employee> queryEmployeeList(Employee employee) {
        return baseMapper.selectList(employee);
    }

    @Override
    public boolean checkEmployeeNoUnique(String employeeNo, String id) {
        if (!StringUtils.hasText(employeeNo)) {
            return false;
        }

        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getEmployeeNo, employeeNo)
                   .eq(Employee::getDelFlag, 0);

        if (StringUtils.hasText(id)) {
            queryWrapper.ne(Employee::getId, id);
        }

        return this.count(queryWrapper) == 0;
    }

    @Override
    public boolean checkIdCardUnique(String idCard, String id) {
        if (!StringUtils.hasText(idCard)) {
            return true; // 身份证号可以为空，允许重复
        }

        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getIdCard, idCard)
                   .eq(Employee::getDelFlag, 0);

        if (StringUtils.hasText(id)) {
            queryWrapper.ne(Employee::getId, id);
        }

        return this.count(queryWrapper) == 0;
    }

    @Override
    public boolean checkMobileUnique(String mobile, String id) {
        if (!StringUtils.hasText(mobile)) {
            return true; // 手机号可以为空，允许重复
        }

        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getMobile, mobile)
                   .eq(Employee::getDelFlag, 0);

        if (StringUtils.hasText(id)) {
            queryWrapper.ne(Employee::getId, id);
        }

        return this.count(queryWrapper) == 0;
    }

    @Override
    public String validateEmployee(Employee employee, boolean isEdit) {
        // 基本字段校验
        if (!StringUtils.hasText(employee.getEmployeeNo())) {
            return "员工工号不能为空";
        }
        if (employee.getEmployeeNo().length() < 2 || employee.getEmployeeNo().length() > 32) {
            return "员工工号长度必须在2-32字符之间";
        }

        if (!StringUtils.hasText(employee.getName())) {
            return "姓名不能为空";
        }
        if (employee.getName().length() < 2 || employee.getName().length() > 50) {
            return "姓名长度必须在2-50字符之间";
        }

        // 格式校验
        if (StringUtils.hasText(employee.getIdCard()) && !isValidIdCard(employee.getIdCard())) {
            return "身份证号格式错误";
        }

        if (StringUtils.hasText(employee.getMobile()) && !isValidMobile(employee.getMobile())) {
            return "手机号格式错误";
        }

        if (StringUtils.hasText(employee.getEmail()) && !isValidEmail(employee.getEmail())) {
            return "邮箱格式错误";
        }

        // 唯一性校验
        if (!checkEmployeeNoUnique(employee.getEmployeeNo(), employee.getId())) {
            return "员工工号已存在";
        }

        if (StringUtils.hasText(employee.getIdCard()) && !checkIdCardUnique(employee.getIdCard(), employee.getId())) {
            return "身份证号已存在";
        }

        // 根据业务需求，手机号是否需要唯一
        // if (StringUtils.hasText(employee.getMobile()) && !checkMobileUnique(employee.getMobile(), employee.getId())) {
        //     return "手机号已存在";
        // }

        // 日期校验
        Date now = new Date();
        if (employee.getBirthDate() != null && employee.getBirthDate().after(now)) {
            return "出生日期不能晚于当前日期";
        }

        if (employee.getHireDate() != null && employee.getHireDate().after(now)) {
            return "入职日期不能晚于当前日期";
        }

        // 年龄校验（18-65岁）
        if (employee.getBirthDate() != null) {
            int age = calculateAge(employee.getBirthDate());
            if (age < 18 || age > 65) {
                return "年龄必须在18-65岁之间";
            }
        }

        return null; // 校验通过
    }

    /**
     * 校验身份证号格式
     */
    private boolean isValidIdCard(String idCard) {
        if (idCard == null || idCard.length() != 18) {
            return false;
        }
        // 简单的18位身份证号格式校验
        return Pattern.matches("^[1-9]\\d{5}(18|19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])\\d{3}[0-9Xx]$", idCard);
    }

    /**
     * 校验手机号格式
     */
    private boolean isValidMobile(String mobile) {
        if (mobile == null || mobile.length() != 11) {
            return false;
        }
        return Pattern.matches("^1[3-9]\\d{9}$", mobile);
    }

    /**
     * 校验邮箱格式
     */
    private boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        return Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", email);
    }

    /**
     * 计算年龄
     */
    private int calculateAge(Date birthDate) {
        if (birthDate == null) {
            return 0;
        }

        long diff = System.currentTimeMillis() - birthDate.getTime();
        long days = diff / (1000 * 60 * 60 * 24);
        return (int) (days / 365);
    }
}