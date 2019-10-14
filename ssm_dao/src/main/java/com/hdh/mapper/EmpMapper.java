package com.hdh.mapper;

import com.hdh.pojo.Department;
import com.hdh.pojo.Employee;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface EmpMapper extends Mapper<Employee> {

    @Select("select * from employee where employeeid in (select employeeid from meetingparticipants where  meetingid=#{meetingid})")
    List<Employee> findEmployeeBymeetingid(Integer meetingid);

    @Select("select * from department")
    List<Department> department();
}
