package org.jeecg.modules.demo.hr.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jeecg.modules.demo.hr.entity.Employee;

import java.io.Serializable;

/**
 * @Description: 员工信息视图对象
 * @Author: jeecg
 * @Date: 2023-12-01
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EmployeeVO extends Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 工作年限（年）
     */
    private Integer workYears;

    /**
     * 转正日期
     */
    private String regularDate;

    /**
     * 合同到期日期
     */
    private String contractEndDate;

    /**
     * 社保号
     */
    private String socialSecurityNo;

    /**
     * 公积金账号
     */
    private String providentFundNo;

    /**
     * 银行账号
     */
    private String bankAccount;

    /**
     * 开户银行
     */
    private String bankName;

    /**
     * 紧急联系人与本人关系
     */
    private String emergencyRelation;

    /**
     * 婚姻状况
     */
    private String maritalStatus;

    /**
     * 民族
     */
    private String nationality;

    /**
     * 政治面貌
     */
    private String politicalStatus;

    /**
     * 户籍所在地
     */
    private String registeredAddress;

    /**
     * 现居住地
     */
    private String currentAddress;

    /**
     * 毕业院校
     */
    private String graduateSchool;

    /**
     * 专业
     */
    private String major;

    /**
     * 毕业时间
     */
    private String graduationDate;

    /**
     * 证书信息
     */
    private String certificates;

    /**
     * 技能特长
     */
    private String skills;

    /**
     * 工作经历
     */
    private String workExperience;

    /**
     * 家庭成员信息
     */
    private String familyMembers;

    /**
     * 健康状况
     */
    private String healthStatus;

    /**
     * 员工照片URL
     */
    private String photoUrl;

    /**
     * 审批状态
     */
    private String approvalStatus;

    /**
     * 审批人
     */
    private String approver;

    /**
     * 审批时间
     */
    private String approvalTime;

    /**
     * 审批意见
     */
    private String approvalRemark;

}