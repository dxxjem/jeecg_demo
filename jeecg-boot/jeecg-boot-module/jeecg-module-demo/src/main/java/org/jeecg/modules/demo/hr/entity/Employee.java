package org.jeecg.modules.demo.hr.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 员工基本信息表
 * @Author: jeecg
 * @Date: 2023-12-01
 * @Version: V1.0
 */
@Data
@TableName("employee")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 员工工号
     */
    @Excel(name = "员工工号", width = 15)
    private String employeeNo;

    /**
     * 姓名
     */
    @Excel(name = "姓名", width = 15)
    private String name;

    /**
     * 性别
     */
    @Excel(name = "性别", width = 10, dicCode = "gender")
    @Dict(dicCode = "gender")
    private String gender;

    /**
     * 性别字典文本
     */
    @TableField(exist = false)
    private String gender_dictText;

    /**
     * 出生日期
     */
    @Excel(name = "出生日期", width = 20, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    /**
     * 身份证号
     */
    @Excel(name = "身份证号", width = 20)
    private String idCard;

    /**
     * 手机号码
     */
    @Excel(name = "手机号码", width = 15)
    private String mobile;

    /**
     * 邮箱
     */
    @Excel(name = "邮箱", width = 25)
    private String email;

    /**
     * 所属部门ID
     */
    private String departmentId;

    /**
     * 职位
     */
    @Excel(name = "职位", width = 15)
    private String position;

    /**
     * 入职日期
     */
    @Excel(name = "入职日期", width = 20, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hireDate;

    /**
     * 员工状态
     */
    @Excel(name = "员工状态", width = 15, dicCode = "employee_status")
    @Dict(dicCode = "employee_status")
    private String employeeStatus;

    /**
     * 员工状态字典文本
     */
    @TableField(exist = false)
    private String employeeStatus_dictText;

    /**
     * 联系地址
     */
    @Excel(name = "联系地址", width = 30)
    private String address;

    /**
     * 紧急联系人
     */
    @Excel(name = "紧急联系人", width = 15)
    private String emergencyContact;

    /**
     * 紧急联系电话
     */
    @Excel(name = "紧急联系电话", width = 15)
    private String emergencyPhone;

    /**
     * 学历
     */
    @Excel(name = "学历", width = 15, dicCode = "education")
    @Dict(dicCode = "education")
    private String education;

    /**
     * 学历字典文本
     */
    @TableField(exist = false)
    private String education_dictText;

    /**
     * 备注
     */
    @Excel(name = "备注", width = 30)
    private String remark;

    /**
     * 删除标志（0：正常，1：删除）
     */
    @TableLogic
    private Integer delFlag;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 版本号（乐观锁）
     */
    @Version
    private Integer version;
}