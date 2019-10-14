package com.hdh.service;

import com.hdh.pojo.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> department();

    void deleteDepartment(Integer departmentid);

    void addDepartment(String departmentname);
}
