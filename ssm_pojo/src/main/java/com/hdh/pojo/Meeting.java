package com.hdh.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "meeting")
public class Meeting {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer meetingid;//会议标识主键
    private String meetingname;//会议名称
    private Integer roomid;//会议室标识
    private Integer reservationistid;//预定者标识
    private Integer numberofparticipants;//参会人员个数

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date starttime;//开始时间

    @Transient
    private String strstarttime;//开始时间

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endtime;//结束时间
    @Transient
    private String strendtime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date reservationtime;//预定时间
    @Transient
    private String strreservationtime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date canceledtime;//取消时间
    @Transient
    private String strcanceledtime;


    private String description;//描述
    private String status;//状态 0：正常 1：取消

    @Transient
    private List<Employee> employees;

    @Transient
    private Meetingroom meetingroom;

    public String getStrstarttime() {
        if (this.starttime != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            strstarttime = simpleDateFormat.format(starttime);
        }
        return strstarttime;
    }

    public String getStrendtime() {
        if (this.endtime != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            strendtime = simpleDateFormat.format(endtime);
        }
        return strendtime;
    }


    public String getStrreservationtime() {
        if (this.reservationtime != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            strreservationtime = simpleDateFormat.format(reservationtime);
        }
        return strreservationtime;
    }

    public String getStrcanceledtime() {
        if (this.reservationtime != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            strcanceledtime = simpleDateFormat.format(canceledtime);
        }
        return strcanceledtime;
    }















    //    public Date getStarttime() {
//        return starttime;
//    }
//    public void setStarttime(Date starttime) {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String format = simpleDateFormat.format(starttime);
//        Date date=null;
//        try {
//             date = simpleDateFormat.parse(format);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        this.starttime =  date;
//    }
//
//    public String getEndtime() {
//        return endtime;
//    }
//
//    public void setEndtime(Date endtime) {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String format = simpleDateFormat.format(endtime);
//        this.endtime = format;
//
//    }
//
//    public String getReservationtime() {
//        return reservationtime;
//    }
//
//    public void setReservationtime(Date reservationtime) {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String format = simpleDateFormat.format(reservationtime);
//        this.reservationtime = format;
//
//    }
//
//    public String getCanceledtime() {
//        return canceledtime;
//    }
//
//    public void setCanceledtime(Date canceledtime) {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String format = simpleDateFormat.format(canceledtime);
//        this.canceledtime = format;
//    }
}
