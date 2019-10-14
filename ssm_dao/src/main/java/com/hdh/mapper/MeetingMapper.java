package com.hdh.mapper;

import com.hdh.pojo.Meeting;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MeetingMapper extends Mapper<Meeting> {

    @Select("select * from meeting where meetingid in (select meetingid from meetingparticipants where employeeid=#{employeeid}) and  starttime between now() and date_add(now(), interval 7 day) and status=0")
    @Results(id = "notifications", value = {
            @Result(id = true, property = "meetingid", column = "meetingid"),
            @Result(property = "meetingname", column = "meetingname"),
            @Result(property = "roomid", column = "roomid"),
            @Result(property = "reservationistid", column = "reservationistid"),
            @Result(property = "numberofparticipants", column = "numberofparticipants"),
            @Result(property = "starttime", column = "starttime"),
            @Result(property = "endtime", column = "endtime"),
            @Result(property = "reservationtime", column = "reservationtime"),
            @Result(property = "canceledtime", column = "canceledtime"),
            @Result(property = "description", column = "description"),
            @Result(property = "status", column = "status"),
            @Result(property = "meetingroom", column = "roomid",many = @Many(select = "com.hdh.mapper.MeetingGroomMapper.findByMeetingId")),

    })
    List<Meeting> notifications(Integer employeeid);

    @Select("select * from meeting where meetingid in (select meetingid from meetingparticipants where employeeid=#{employeeid})and status=1")
    @ResultMap(value ="notifications" )
    List<Meeting> removeNotifications(Integer employeeid);


    @Select("select * from meeting where meetingid=#{meetingid} ")
    @Results(id = "details", value = {
            @Result(id = true, property = "meetingid", column = "meetingid"),
            @Result(property = "meetingname", column = "meetingname"),
            @Result(property = "roomid", column = "roomid"),
            @Result(property = "reservationistid", column = "reservationistid"),
            @Result(property = "numberofparticipants", column = "numberofparticipants"),
            @Result(property = "starttime", column = "starttime"),
            @Result(property = "endtime", column = "endtime"),
            @Result(property = "reservationtime", column = "reservationtime"),
            @Result(property = "canceledtime", column = "canceledtime"),
            @Result(property = "description", column = "description"),
            @Result(property = "status", column = "status"),
            @Result(property = "employees", column = "meetingid",many = @Many(select = "com.hdh.mapper.EmpMapper.findEmployeeBymeetingid")),
    })
     Meeting details(Integer meetingid);


    @Select("select * from meeting where meetingid in (select meetingid  from meetingparticipants where employeeid=1) and status=0")
    @ResultMap(value = "notifications")
    List<Meeting> bookmeeting(Integer employeeid);

    @Select("select * from meeting where meetingid=#{meetingid}")
    @ResultMap(value = "details")
    Meeting myMeetingDetails(Integer employeeid);

    @Select("select * from meeting where meetingid in (select meetingid from meetingparticipants where employeeid=#{employeeid})and status=0")
    @ResultMap(value = "notifications")
    List<Meeting> mymeetings(Integer meetingid);

    @Insert("insert into meetingparticipants values(#{param1},#{param2})")
    void addMeetingparticipants(Integer meetingid, Integer employeeid);

    @Update("update meeting set status=1 where meetingid=#{meetingid}")
    void revoke(Integer meetingid);
}
