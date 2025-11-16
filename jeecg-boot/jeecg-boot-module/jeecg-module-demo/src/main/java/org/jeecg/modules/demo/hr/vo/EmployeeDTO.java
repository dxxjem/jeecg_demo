package org.jeecg.modules.demo.hr.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 员工信息数据传输对象
 * @Author: jeecg
 * @Date: 2023-12-01
 * @Version: V1.0
 */
@Data
@Accessors(chain = true)
public class EmployeeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID（编辑时传入）
     */
    private String id;

    /**
     * 员工工号
     */
    @NotBlank(message = "员工工号不能为空")
    @Size(min = 2, max = 32, message = "员工工号长度必须在2-32字符之间")
    private String employeeNo;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空")
    @Size(min = 2, max = 50, message = "姓名长度必须在2-50字符之间")
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 出生日期
     */
    private Date birthDate;

    /**
     * 身份证号
     */
    @Pattern(regexp = "^[1-9]\\d{5}(18|19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])\\d{3}[0-9Xx]$",
             message = "身份证号格式错误")
    private String idCard;

    /**
     * 手机号码
     */
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式错误")
    private String mobile;

    /**
     * 邮箱
     */
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
             message = "邮箱格式错误")
    private String email;

    /**
     * 所属部门ID
     */
    private String departmentId;

    /**
     * 部门名称（前端显示用）
     */
    private String departmentName;

    /**
     * 职位
     */
    private String position;

    /**
     * 入职日期
     */
    private Date hireDate;

    /**
     * 员工状态
     */
    private String employeeStatus;

    /**
     * 联系地址
     */
    private String address;

    /**
     * 紧急联系人
     */
    private String emergencyContact;

    /**
     * 紧急联系电话
     */
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "紧急联系电话格式错误")
    private String emergencyPhone;

    /**
     * 学历
     */
    private String education;

    /**
     * 备注
     */
    private String remark;

    /**
     * 批量操作员工ID列表
     */
    private String[] employeeIds;

    /**
     * 搜索条件开始时间
     */
    private Date beginTime;

    /**
     * 搜索条件结束时间
     */
    private Date endTime;

    /**
     * 排序字段
     */
    private String column;

    /**
     * 排序方式（asc/desc）
     */
    private String order;

    /**
     * 当前页码
     */
    private Integer pageNo;

    /**
     * 每页数量
     */
    private Integer pageSize;

}