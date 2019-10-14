package com.hdh.service;

import com.hdh.pojo.Meetingroom;

import java.util.List;

public interface MeetinGroomService {


    void addmeetingroom(Meetingroom meetingroom);

    List<Meetingroom> meetingrooms();

    Meetingroom details(Integer roomid);
}
