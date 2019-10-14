package com.hdh.web.controller;

import com.hdh.pojo.Employee;
import com.hdh.pojo.Meetingroom;
import com.hdh.pojo.SearchVo;
import com.hdh.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("emp")
public class EmpController {

    @Autowired
    private EmpService empService;

    /**
     * 用户登陆
     *
     * @param username 用户名
     * @param Password 密码
     * @return ModelAndView
     */
    @PostMapping("empLogin")
    public String empLogin(@RequestParam("username") String username, @RequestParam("password") String Password, HttpSession session, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Employee emps = empService.empLogin(username, Password);
        if (emps == null) {
            request.setAttribute("error","账户名或密码错误");
            mv.setViewName("login");
            return "login";
        } else {
            session.setAttribute("username", emps.getUsername());
            session.setAttribute("employeeid", emps.getEmployeeid());
            session.setAttribute("role", emps.getRole());
            mv.addObject(emps);
            return "redirect:/metting/notifications?employeeid=" + emps.getEmployeeid();
        }
    }
    @GetMapping("outLogin")
    public String outLogin(HttpSession session) {
        session.invalidate();
            return "login";
    }


    /**
     * 用户注册
     *
     * @param employee
     * @return
     */
    @PostMapping("register")
    public String register(Employee employee) {
        ModelAndView mv = new ModelAndView();
        empService.register(employee);
        return "login";
    }

    /**
     * 管理员对用户注册的审批
     *
     * @return
     */
    @GetMapping("approveaccount")
    public ModelAndView approveaccount() {
        ModelAndView mv = new ModelAndView();
        List<Employee> employees = empService.approveaccount();
        mv.addObject("employees",employees);
        mv.setViewName("approveaccount");
        return mv;
    }

    /**
     * 管理员对用户注册的审批的通过操作
     *
     * @return
     */
    @GetMapping("approveaccount/pass")
    public String pass(@RequestParam("employeeid") Integer employeeid) {
        empService.pass(employeeid);
        return "redirect:/emp/approveaccount";
    }

    /**
     * 管理员对用户注册的审批的删除操作
     *
     * @return
     */
    @GetMapping("approveaccount/delete")
    public String delete(@RequestParam("employeeid") Integer employeeid) {
        empService.delete(employeeid);
        return "redirect:/emp/approveaccount";
    }

    /**
     * 搜索员工
     *
     * @return
     */
    @PostMapping("search")
    public ModelAndView search(Employee employee, SearchVo searchVo) {
        ModelAndView mv = new ModelAndView();
        List<Employee> employees= empService.search(employee);
        mv.addObject("searchVo",searchVo);
        mv.addObject("employees",employees);
        mv.setViewName("searchemployees");
        return mv;
    }

    /**
     * 预定会议人员选择和会议室选择
     *
     * @return ModelAndView
     */
    @GetMapping("bookmeeting")
    public ModelAndView bookmeeting() {
        ModelAndView mv = new ModelAndView();
        List<Employee> employees = empService.bookmeeting();
        List<Meetingroom> meetingroom = empService.meetingroom();

        mv.addObject("meetingrooms", meetingroom);
        mv.addObject("employees", employees);
        mv.setViewName("bookmeeting");
        return mv;
    }

}
