package com.hdh.web.controller;

import com.hdh.pojo.Department;
import com.hdh.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    /**
     * 查询用户可用选的department
     */
    @GetMapping("department")
    public ModelAndView department() {
        ModelAndView mv = new ModelAndView();

        List<Department> departments = departmentService.department();
        mv.addObject("departments", departments);
        mv.setViewName("register");
        return mv;
    }

    /**
     * 查询部门管理页面可用的部门
     * @return
     */

    @GetMapping("departments")
    public ModelAndView departments() {
        ModelAndView mv = new ModelAndView();

        List<Department> departments = departmentService.department();
        mv.addObject("departments", departments);
        mv.setViewName("departments");
        return mv;
    }

    /**
     * 删除部门
     * @param departmentid
     * @return
     */
    @GetMapping("deleteDepartment")
    public String deleteDepartment(@RequestParam("departmentid") Integer departmentid) {
        ModelAndView mv = new ModelAndView();

        departmentService.deleteDepartment(departmentid);


        return "redirect:/departments/departments";
    }

    /**
     * 添加部门
     */

    @PostMapping("addDepartment")
    public String addDepartment(@RequestParam("departmentname") String departmentname) {
        ModelAndView mv = new ModelAndView();

        departmentService.addDepartment(departmentname);


        return "redirect:/departments/departments";
    }

}
