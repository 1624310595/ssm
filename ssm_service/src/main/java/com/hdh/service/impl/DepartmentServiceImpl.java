package com.hdh.service.impl;

import com.hdh.mapper.DepartmentMapper;
import com.hdh.mapper.EmpMapper;
import com.hdh.pojo.Department;
import com.hdh.pojo.Employee;
import com.hdh.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private EmpMapper empMapper;

    public List<Department> department() {
        return departmentMapper.selectAll();
    }

    public void deleteDepartment(Integer departmentid) {
        departmentMapper.deleteByPrimaryKey(departmentid);
        Employee employee = new Employee();
        employee.setDepartmentid(departmentid);
        List<Employee> select = empMapper.select(employee);

        if (select!=null&& select.size()>0){
            for (Employee employee1 : select) {
                employee1.setDepartmentid(null);
                empMapper.updateByPrimaryKeySelective(employee1);
            }
        }
    }

    public void addDepartment(String departmentname) {
        Department department = new Department();
        department.setDepartmentname(departmentname);
        departmentMapper.insertSelective(department);
    }
}
