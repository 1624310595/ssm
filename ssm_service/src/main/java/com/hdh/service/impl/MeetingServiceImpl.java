package com.hdh.service.impl;

import com.hdh.mapper.EmpMapper;
import com.hdh.mapper.MeetinGroomMapper;
import com.hdh.mapper.MeetingMapper;
import com.hdh.pojo.Employee;
import com.hdh.pojo.Meeting;
import com.hdh.pojo.Meetingroom;
import com.hdh.pojo.SearhmeetingsVo;
import com.hdh.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    private MeetingMapper meetingMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private MeetinGroomMapper meetinGroomMapper;


    public List<Meeting> notifications(Integer employeeid) {
        return meetingMapper.notifications(employeeid);
    }

    public List<Meeting> removeNotifications(Integer employeeid) {
        return meetingMapper.removeNotifications(employeeid);
    }

    public Meeting details(Integer meetingid) {

        return meetingMapper.details(meetingid);
    }

    public List<Meeting> bookmeeting(Integer employeeid) {
        return meetingMapper.bookmeeting(employeeid);
    }

    public Meeting myMeetingDetails(Integer meetingid) {
        return meetingMapper.myMeetingDetails(meetingid);
    }

    public List<Meeting> mymeetings(Integer employeeid) {
        return meetingMapper.mymeetings(employeeid);
    }

    public List<Meeting> searchmeetings(SearhmeetingsVo searhmeetingsVo) {

        Example example = new Example(Meeting.class);

        //1.查询到预订者姓名
        if (searhmeetingsVo.getReservername() != null && searhmeetingsVo.getReservername().length() != 0) {
            Employee employee = new Employee();
            employee.setEmployeename(searhmeetingsVo.getReservername());
            List<Employee> select = empMapper.select(employee);

            if (select != null && select.size() > 0) {
                Integer reservationistid = select.get(0).getEmployeeid();
                example.createCriteria().andEqualTo("reservationistid", reservationistid);
            } else {
                example.createCriteria().andEqualTo("reservationistid", "-1");
            }
        }
        //2.查询会议名称
        if (searhmeetingsVo.getMeetingname() != null && searhmeetingsVo.getMeetingname().length() != 0) {
            example.createCriteria().andEqualTo("meetingname", searhmeetingsVo.getMeetingname());
        }

        //3.查询会议室名称
        if (searhmeetingsVo.getRoomname() != null && searhmeetingsVo.getRoomname().length() != 0) {
            Meetingroom meetingroom = new Meetingroom();
            meetingroom.setRoomname(searhmeetingsVo.getRoomname());
            List<Meetingroom> select = meetinGroomMapper.select(meetingroom);
            if (select != null && select.size() > 0) {
                Integer roomid = select.get(0).getRoomid();
                example.createCriteria().andEqualTo("roomid", roomid);
            }
        }
        if (!searhmeetingsVo.getReservefromdate().equals("") && !searhmeetingsVo.getReservetodate().equals("")) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date reservefromdate = null;
            Date reservetodate = null;
            try {
                reservefromdate = formatter.parse(searhmeetingsVo.getReservefromdate());
                reservetodate = formatter.parse(searhmeetingsVo.getReservetodate());

            } catch (ParseException e) {
                e.printStackTrace();
            }
            example.createCriteria().andBetween("reservationtime", reservefromdate, reservetodate);
        }

        if (!searhmeetingsVo.getMeetingfromdate().equals("") && !searhmeetingsVo.getMeetingtodate().equals("")) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date meetingfromdate = null;
            Date meetingtodate = null;
            try {
                meetingfromdate = formatter.parse(searhmeetingsVo.getMeetingfromdate());
                meetingtodate = formatter.parse(searhmeetingsVo.getMeetingtodate());

            } catch (ParseException e) {
                e.printStackTrace();
            }
            example.createCriteria().andBetween("starttime", meetingfromdate, meetingtodate);
        }


        List<Meeting> meetings = meetingMapper.selectByExample(example);

        if (meetings != null) {
            for (Meeting meeting : meetings) {
                Employee employee = new Employee();
                employee.setEmployeeid(meeting.getReservationistid());
                List<Employee> select = empMapper.select(employee);
                meeting.setEmployees(select);
            }
        }

        if (meetings != null) {
            for (Meeting meeting : meetings) {
                Meetingroom meetingroom = meetinGroomMapper.selectByPrimaryKey(meeting.getRoomid());
                meeting.setMeetingroom(meetingroom);
            }
        }
        return meetings;
    }

    public void bookmeeting(Meeting meeting, String sdate, String stime, String edate, String etime, Integer[] ids) {
        if (!"".equals(sdate) && !"".equals(stime) && !"".equals(edate) && !"".equals(etime)) {
            Date starttime = null;
            Date endtime = null;

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            try {
                String str1=sdate + " "+stime;
                String str2=edate +" "+ etime;
                starttime = format.parse(str1);
                endtime = format.parse(str2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            meeting.setStarttime(starttime);
            meeting.setStarttime(endtime);
        }
        meetingMapper.insertSelective(meeting);
        if (ids.length > 0) {
            for (Integer employeeid : ids) {
                meetingMapper.addMeetingparticipants(meeting.getMeetingid(),employeeid);
            }

        }


    }
    public void revoke(Integer meetingid) {
        meetingMapper.revoke(meetingid);

    }

    public Meeting myDetails(Integer meetingid) {
        return meetingMapper.myMeetingDetails(meetingid);
    }
}
