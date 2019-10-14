package com.hdh.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Data
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer employeeid;//员工标识主键
    private  String employeename;//员工真实姓名
    private  String username;//注册用户名
    private  String phone;//用户电话
    private  String email;//用户邮件
    private  String status;//用户状态 0：待审批 1：审批通过 2：审批未通过


    private Integer departmentid;//部门标识
    private  String password;//密码
    private  String role;//角色1：管理员 2：普通用户

    @Transient
    private List<Meeting> meetings;

}
