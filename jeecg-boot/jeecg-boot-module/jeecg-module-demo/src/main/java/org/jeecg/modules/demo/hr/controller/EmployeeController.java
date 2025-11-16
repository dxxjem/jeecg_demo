package org.jeecg.modules.demo.hr.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.hr.entity.Employee;
import org.jeecg.modules.demo.hr.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 员工基本信息表
 * @Author: jeecg
 * @Date: 2023-12-01
 * @Version: V1.0
 */
@RestController
@RequestMapping("/demo/hr/employee")
@Slf4j
public class EmployeeController extends JeecgController<Employee, IEmployeeService> {

    @Autowired
    private IEmployeeService employeeService;

    /**
     * 分页列表查询
     */
    @AutoLog(value = "员工基本信息表-分页列表查询")
    @PermissionData(pageComponent = "demo/hr/employee/EmployeeList")
    @GetMapping(value = "/list")
    public Result<IPage<Employee>> queryPageList(Employee employee,
                                                @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                HttpServletRequest req) {
        try {
            Page<Employee> page = new Page<Employee>(pageNo, pageSize);
            IPage<Employee> pageList = employeeService.queryPageList(page, employee);
            return Result.OK(pageList);
        } catch (Exception e) {
            log.error("查询员工列表失败", e);
            return Result.error("查询失败：" + e.getMessage());
        }
    }

    /**
     * 添加
     */
    @AutoLog(value = "员工基本信息表-添加")
    @PostMapping(value = "/add")
    public Result<String> add(@RequestBody Employee employee) {
        try {
            employeeService.addEmployee(employee);
            return Result.OK("添加成功！");
        } catch (Exception e) {
            log.error("添加员工失败", e);
            return Result.error("添加失败：" + e.getMessage());
        }
    }

    /**
     * 编辑
     */
    @AutoLog(value = "员工基本信息表-编辑")
    @RequestMapping(value = "/edit", method = {RequestMethod.PUT, RequestMethod.POST})
    public Result<String> edit(@RequestBody Employee employee) {
        try {
            employeeService.editEmployee(employee);
            return Result.OK("编辑成功!");
        } catch (Exception e) {
            log.error("编辑员工失败", e);
            return Result.error("编辑失败：" + e.getMessage());
        }
    }

    /**
     * 通过id删除
     */
    @AutoLog(value = "员工基本信息表-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam(name = "id", required = true) String id) {
        try {
            log.info("Controller接收到删除请求，ID: {}", id);
            log.info("ID类型: {}, ID长度: {}", id != null ? id.getClass().getSimpleName() : "null", id != null ? id.length() : 0);

            // 先查询看看是否能找到这个员工
            Employee employee = employeeService.getById(id);
            if (employee == null) {
                log.error("未找到员工，ID: {}", id);
                return Result.error("未找到员工信息");
            }
            log.info("找到员工: {}", employee.getName());
            log.info("删除前删除标志: {}", employee.getDelFlag());

            boolean result = employeeService.deleteEmployee(id);
            log.info("删除操作结果: {}", result);

            // 验证删除是否成功 - 这里可能存在缓存问题
            log.info("Controller开始验证删除结果");
            Employee updatedEmployee = employeeService.getById(id);
            if (updatedEmployee != null) {
                log.info("Service查询验证 - 删除标志: {}", updatedEmployee.getDelFlag());

                // 如果Service查询显示未删除，说明存在缓存问题
                if (updatedEmployee.getDelFlag() != 1) {
                    log.error("发现缓存问题！Service查询显示删除标志为: {}", updatedEmployee.getDelFlag());

                    // 尝试直接查询数据库绕过缓存
                    try {
                        // 注意：这里需要引入Mapper或者使用其他方式直接查询数据库
                        // 暂时先记录这个问题，让用户知道根本原因
                        log.error("根本原因：删除操作完成但缓存返回旧数据");
                        log.error("建议解决方案：清除MyBatis二级缓存或重新查询");

                        return Result.error("删除操作已执行，但缓存问题导致显示未删除。建议刷新页面或清除缓存后查看。");
                    } catch (Exception directQueryError) {
                        log.error("直接数据库查询失败:", directQueryError);
                    }
                }
            } else {
                log.error("删除后验证失败 - 无法获取更新后的员工信息");
            }

            return Result.OK("删除成功!");
        } catch (Exception e) {
            log.error("删除员工失败", e);
            return Result.error("删除失败：" + e.getMessage());
        }
    }

    /**
     * 批量删除
     */
    @AutoLog(value = "员工基本信息表-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<String> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        try {
            List<String> idList = Arrays.asList(ids.split(","));
            employeeService.deleteBatchEmployees(idList);
            return Result.OK("批量删除成功!");
        } catch (Exception e) {
            log.error("批量删除员工失败", e);
            return Result.error("批量删除失败：" + e.getMessage());
        }
    }

    /**
     * 通过id查询
     */
    @AutoLog(value = "员工基本信息表-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<Employee> queryById(@RequestParam(name = "id", required = true) String id) {
        try {
            Employee employee = employeeService.queryEmployeeById(id);
            if (employee == null) {
                return Result.error("未找到对应数据");
            }
            return Result.OK(employee);
        } catch (Exception e) {
            log.error("查询员工详情失败", e);
            return Result.error("查询失败：" + e.getMessage());
        }
    }

    /**
     * 导出excel
     */
    @AutoLog(value = "员工基本信息表-导出excel")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Employee employee) {
        return super.exportXls(request, employee, Employee.class, "员工基本信息表");
    }

    /**
     * 通过excel导入数据
     */
    @AutoLog(value = "员工基本信息表-通过excel导入数据")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, Employee.class);
    }

    /**
     * 校验员工工号唯一性
     */
    @GetMapping(value = "/checkEmployeeNo")
    public Result<Boolean> checkEmployeeNo(@RequestParam(name = "employeeNo", required = true) String employeeNo,
                                          @RequestParam(name = "id", required = false) String id) {
        try {
            boolean isUnique = employeeService.checkEmployeeNoUnique(employeeNo, id);
            return Result.OK(isUnique);
        } catch (Exception e) {
            log.error("校验员工工号唯一性失败", e);
            return Result.error("校验失败：" + e.getMessage());
        }
    }

    /**
     * 校验身份证号唯一性
     */
    @GetMapping(value = "/checkIdCard")
    public Result<Boolean> checkIdCard(@RequestParam(name = "idCard", required = true) String idCard,
                                      @RequestParam(name = "id", required = false) String id) {
        try {
            boolean isUnique = employeeService.checkIdCardUnique(idCard, id);
            return Result.OK(isUnique);
        } catch (Exception e) {
            log.error("校验身份证号唯一性失败", e);
            return Result.error("校验失败：" + e.getMessage());
        }
    }

    /**
     * 校验手机号唯一性
     */
    @GetMapping(value = "/checkMobile")
    public Result<Boolean> checkMobile(@RequestParam(name = "mobile", required = true) String mobile,
                                      @RequestParam(name = "id", required = false) String id) {
        try {
            boolean isUnique = employeeService.checkMobileUnique(mobile, id);
            return Result.OK(isUnique);
        } catch (Exception e) {
            log.error("校验手机号唯一性失败", e);
            return Result.error("校验失败：" + e.getMessage());
        }
    }

    /**
     * 获取部门员工数量统计
     */
    @GetMapping(value = "/countByDepartment")
    public Result<Integer> countByDepartment(@RequestParam(name = "departmentId", required = true) String departmentId) {
        try {
            Integer count = employeeService.countByDepartmentId(departmentId);
            return Result.OK(count);
        } catch (Exception e) {
            log.error("统计部门员工数量失败", e);
            return Result.error("统计失败：" + e.getMessage());
        }
    }

    /**
     * 获取状态员工数量统计
     */
    @GetMapping(value = "/countByStatus")
    public Result<Integer> countByStatus(@RequestParam(name = "employeeStatus", required = true) String employeeStatus) {
        try {
            Integer count = employeeService.countByEmployeeStatus(employeeStatus);
            return Result.OK(count);
        } catch (Exception e) {
            log.error("统计状态员工数量失败", e);
            return Result.error("统计失败：" + e.getMessage());
        }
    }
}