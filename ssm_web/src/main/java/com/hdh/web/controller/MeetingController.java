package com.hdh.web.controller;

import com.hdh.pojo.Meeting;
import com.hdh.pojo.SearhmeetingsVo;
import com.hdh.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("metting")
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    /**
     * 查看个人的会议通知
     *
     * @return ModelAndView
     */

    @RequestMapping("notifications")
    public ModelAndView notifications(@RequestParam("employeeid") Integer employeeid) {
        ModelAndView mv = new ModelAndView();
        List<Meeting> meetings = meetingService.notifications(employeeid);
        mv.addObject("meetings", meetings);

        List<Meeting> removeMeetings = meetingService.removeNotifications(employeeid);
        mv.addObject("removeMeetings", removeMeetings);

        for (Meeting removeMeeting : removeMeetings) {
            System.out.println(removeMeeting);
        }
        mv.setViewName("notifications");
        return mv;

    }

    /**
     * 查看会议详情
     *
     * @return ModelAndView
     */

    @GetMapping("details")
    public ModelAndView details(@RequestParam("meetingid") Integer meetingid) {
        ModelAndView mv = new ModelAndView();
        Meeting meetings = meetingService.details(meetingid);
        mv.addObject("details", meetings);
        mv.setViewName("meetingdetails");
        return mv;

    }

    /**
     * 查看我的预定会议
     *
     * @return ModelAndView
     */

    @GetMapping("bookmeeting")
    public ModelAndView bookmeeting(@RequestParam("employeeid") Integer employeeid) {
        ModelAndView mv = new ModelAndView();
        List<Meeting> meetings = meetingService.bookmeeting(employeeid);
        mv.addObject("mybookings", meetings);
        mv.setViewName("mybookings");
        return mv;

    }

    /**
     * 查看撤销我的预定会议
     *
     * @return ModelAndView
     */

    @GetMapping("myMeetingDetails")
    public ModelAndView myMeetingDetails(@RequestParam("meetingid") Integer meetingid) {
        ModelAndView mv = new ModelAndView();
        Meeting meetings = meetingService.myMeetingDetails(meetingid);
        mv.addObject("myMeetingDetails", meetings);
        mv.setViewName("mymeetingdetails");
        return mv;

    }
    /**
     * 查看我参加的会议的详情
     *
     * @return ModelAndView
     */

    @GetMapping("myDetails")
    public ModelAndView myDetails(@RequestParam("meetingid") Integer meetingid) {
        ModelAndView mv = new ModelAndView();
        Meeting meetings = meetingService.myDetails(meetingid);
        mv.addObject("myMeetingDetails", meetings);
        mv.setViewName("mymeetingdetails");
        return mv;

    }

    /**
     * 我将参加的会议
     *
     * @return ModelAndView
     */
    @GetMapping("mymeetings")
    public ModelAndView mymeetings(@RequestParam("employeeid") Integer employeeid) {
        ModelAndView mv = new ModelAndView();
        List<Meeting> meetings = meetingService.mymeetings(employeeid);
        mv.addObject("mymeetings", meetings);
        mv.setViewName("mymeetings");
        return mv;

    }


    /**
     * 会议多条件查询
     *
     * @return ModelAndView
     */
    @PostMapping("searchmeetings")
    public ModelAndView mymeetings(SearhmeetingsVo searhmeetingsVo) {
        ModelAndView mv = new ModelAndView();
        List<Meeting> meetings = meetingService.searchmeetings(searhmeetingsVo);
        mv.addObject("searchmeetings", meetings);
        mv.addObject("searhmeetingsVo", searhmeetingsVo);
        mv.setViewName("searchmeetings");
        return mv;

    }


    /**
     * 预定会议
     *
     * @return ModelAndView
     */
    @PostMapping("bookmeeting")
    public String bookmeeting(Meeting meeting,@RequestParam("sdate") String sdate,@RequestParam("stime") String stime,@RequestParam("edate") String edate,@RequestParam("etime") String etime,@RequestParam("employeeids") Integer[] ids) {
        ModelAndView mv = new ModelAndView();
        System.out.println(Arrays.toString(ids));
        meetingService.bookmeeting(meeting,sdate,stime,edate,etime,ids);
        return "redirect:/meetinGroom/meetingrooms";

    }

    /**
     * 撤销会议  修改会议的status为1
     *
     * @return ModelAndView
     */
    @GetMapping("revoke")
    public String revoke(@RequestParam("meetingid") Integer meetingid, HttpSession session) {
        meetingService.revoke(meetingid);

        Integer employeeid = (Integer) session.getAttribute("employeeid");
        return "redirect:/metting/bookmeeting?employeeid="+employeeid;

    }

}
