package com.hdh.service;

import com.hdh.pojo.Employee;
import com.hdh.pojo.Meetingroom;

import java.util.List;

public interface EmpService {
    Employee empLogin(String username, String password);

    void register(Employee employee);

    List<Employee> approveaccount();

    void pass(Integer employeeid);

    void delete(Integer employeeid);

    List<Employee> search(Employee employee);

    List<Employee> bookmeeting();

    List<Meetingroom> meetingroom();
}
