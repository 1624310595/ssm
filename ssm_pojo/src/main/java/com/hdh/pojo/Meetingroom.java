package com.hdh.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
public class Meetingroom {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer roomid;//会议室标识
    private Integer roomnum;//会议室门牌号
    private String roomname;//会议室标识
    private Integer capacity;//预定者标识
    private String status;//参会人员个数 0：启用 1：停用
    private String description;//描述


}
