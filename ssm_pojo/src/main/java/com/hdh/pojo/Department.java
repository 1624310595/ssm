package com.hdh.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
public class Department {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer departmentid;//部门标识
    private  String departmentname;//部门名称

}
