package com.hdh.mapper;

import com.hdh.pojo.Meetingroom;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface MeetingGroomMapper extends Mapper<Meetingroom>{

    @Select("select * from meetingroom where roomid=#{meetingid}")
    Meetingroom findByMeetingId(Integer meetingid);
}
