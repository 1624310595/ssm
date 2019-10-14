package com.hdh.service.impl;


import com.hdh.mapper.MeetinGroomMapper;
import com.hdh.pojo.Meetingroom;
import com.hdh.service.MeetinGroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetinGroomServiceImpl implements MeetinGroomService {
    @Autowired
    private MeetinGroomMapper meetinGroomMapper;

    public void addmeetingroom(Meetingroom meetingroom) {
        meetinGroomMapper.insertSelective(meetingroom);
    }

    public List<Meetingroom> meetingrooms() {
        return meetinGroomMapper.selectAll();
    }

    public Meetingroom details(Integer roomid) {
        return meetinGroomMapper.selectByPrimaryKey(roomid);
    }
}
