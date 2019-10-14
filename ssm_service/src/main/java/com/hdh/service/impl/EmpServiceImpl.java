package com.hdh.service.impl;

import com.hdh.mapper.EmpMapper;
import com.hdh.mapper.MeetinGroomMapper;
import com.hdh.pojo.Employee;
import com.hdh.pojo.Meetingroom;
import com.hdh.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private MeetinGroomMapper meetinGroomMapper;

    public Employee empLogin(String username, String password) {

        Employee employee = new Employee();
        employee.setUsername(username);
        employee.setPassword(password);
        List<Employee> emps = empMapper.select(employee);
        if (emps != null && emps.size() > 0) {
            return emps.get(0);
        }
        return null;
    }

    public void register(Employee employee) {
        empMapper.insertSelective(employee);
    }

    public List<Employee> approveaccount() {
        Employee employee = new Employee();
        employee.setStatus("0");
        return empMapper.select(employee);
    }

    public void pass(Integer employeeid) {
        Employee employee = empMapper.selectByPrimaryKey(employeeid);
        employee.setStatus("1");
        empMapper.updateByPrimaryKeySelective(employee);

    }

    public void delete(Integer employeeid) {
        Employee employee = empMapper.selectByPrimaryKey(employeeid);
        employee.setStatus("2");
        empMapper.updateByPrimaryKeySelective(employee);

    }

    public List<Employee> search(Employee employee) {

        if (employee.getEmployeename() == "") {
            employee.setEmployeename(null);
        }
        if (employee.getUsername() == "") {
            employee.setUsername(null);
        }
        return empMapper.select(employee);
    }

    public List<Employee> bookmeeting() {
        return empMapper.selectAll();
    }

    public List<Meetingroom> meetingroom() {
        return meetinGroomMapper.selectAll();
    }


}
