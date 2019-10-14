package com.hdh.service;

import com.hdh.pojo.Meeting;
import com.hdh.pojo.SearhmeetingsVo;

import java.util.List;

public interface MeetingService {
    List<Meeting> notifications(Integer employeeid);

    List<Meeting> removeNotifications(Integer employeeid);

     Meeting details(Integer meetingid);

    List<Meeting> bookmeeting(Integer employeeid);

    Meeting myMeetingDetails(Integer meetingid);

    List<Meeting> mymeetings(Integer employeeid);

    List<Meeting> searchmeetings(SearhmeetingsVo searhmeetingsVo);

    void bookmeeting(Meeting meeting, String sdate, String stime, String edate, String etime, Integer[] ids);

    void revoke(Integer description);

    Meeting myDetails(Integer meetingid);
}
