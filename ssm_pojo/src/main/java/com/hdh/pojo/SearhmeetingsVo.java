package com.hdh.pojo;

import lombok.Data;

@Data
public class SearhmeetingsVo {
    private String meetingname;//会议名称
    private String roomname;//会议室名称
    private String reservername;//预定者姓名

    private String reservefromdate;//预定日期
    private String reservetodate;

    private String meetingfromdate;//会议日期
    private String meetingtodate;
}
