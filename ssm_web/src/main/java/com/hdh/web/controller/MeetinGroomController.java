package com.hdh.web.controller;

import com.hdh.pojo.Meetingroom;
import com.hdh.service.MeetinGroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("meetinGroom")
public class MeetinGroomController {

    @Autowired
    private MeetinGroomService meetinGroomService;

    /**
     * 添加会议室
     */
    @PostMapping("addmeetingroom")
    public String addmeetingroom(Meetingroom meetingroom) {
        meetinGroomService.addmeetingroom(meetingroom);
        return "redirect:/meetinGroom/meetingrooms";
    }
    /**
     * 查看全部会议室
     * @return ModelAndView
     */
    @GetMapping("meetingrooms")
    public ModelAndView meetingrooms() {
        ModelAndView mv = new ModelAndView();
        List<Meetingroom> meetingrooms = meetinGroomService.meetingrooms();
        mv.addObject("meetingrooms", meetingrooms);
        mv.setViewName("meetingrooms");

        return mv;

    }

    /**
     * 会议室详情
     * @return ModelAndView
     */
    @GetMapping("details")
    public ModelAndView details(@RequestParam("roomid") Integer roomid) {
        ModelAndView mv = new ModelAndView();
        Meetingroom meetingrooms = meetinGroomService.details(roomid);
        mv.addObject("meetingrooms", meetingrooms);
        mv.setViewName("roomdetails");

        return mv;

    }

}
