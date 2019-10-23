<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>CoolMeeting会议管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/code/styles/common.css"/>
    <style type="text/css">

    </style>
</head>
<body>
<div class="page-header">
    <div class="header-banner">
        <img src="images/header.png" alt="CoolMeeting"/>
    </div>
    <div class="header-title">
        欢迎访问Cool-Meeting会议管理系统
    </div>
    <div class="header-quicklink">
        欢迎您，<strong>${username}</strong>
        <a href="${pageContext.request.contextPath}/emp/outLogin">[注销]</a>
    </div>
</div>
<div class="page-body">
    <div class="page-sidebar">
        <div class="sidebar-menugroup">
            <div class="sidebar-grouptitle">个人中心</div>
            <ul class="sidebar-menu">
                <li class="sidebar-menuitem"><a
                        href="${pageContext.request.contextPath}/metting/notifications?employeeid=${employeeid}">最新通知</a>
                </li>
                <li class="sidebar-menuitem active"><a
                        href="${pageContext.request.contextPath}/metting/bookmeeting?employeeid=${employeeid}">我的预定</a>
                </li>
                <li class="sidebar-menuitem"><a
                        href="${pageContext.request.contextPath}/metting/mymeetings?employeeid=${employeeid}">我的会议</a>
                </li>
            </ul>
        </div>
        <c:if test="${role==1}">
            <div class="sidebar-menugroup">
                <div class="sidebar-grouptitle">人员管理</div>
                <ul class="sidebar-menu">
                    <li class="sidebar-menuitem"><a href="${pageContext.request.contextPath}/departments/departments">部门管理</a></li>
                    <li class="sidebar-menuitem"><a href="${pageContext.request.contextPath}/departments/department">员工注册</a>
                    </li>
                    <li class="sidebar-menuitem"><a
                            href="${pageContext.request.contextPath}/emp/approveaccount">注册审批</a></li>
                    <li class="sidebar-menuitem"><a href="${pageContext.request.contextPath}/code/searchemployees.jsp">搜索员工</a>
                    </li>
                </ul>
            </div>
        </c:if>
        <div class="sidebar-menugroup">
            <div class="sidebar-grouptitle">会议预定</div>
            <ul class="sidebar-menu">
                <c:if test="${role==1}">
                <li class="sidebar-menuitem"><a
                        href="${pageContext.request.contextPath}/code/addmeetingroom.jsp">添加会议室</a></li>
                <li class="sidebar-menuitem"></c:if>
                    <a href="${pageContext.request.contextPath}/meetinGroom/meetingrooms">查看会议室</a></li>
                <li class="sidebar-menuitem"><a href="${pageContext.request.contextPath}/emp/bookmeeting">预定会议</a></li>
                <li class="sidebar-menuitem"><a
                        href="${pageContext.request.contextPath}/code/searchmeetings.jsp">搜索会议</a></li>
            </ul>
        </div>
    </div>
    <div class="page-content">
        <div class="content-nav">
            会议预定 > 搜索会议
        </div>
        <form method="post" action="${pageContext.request.contextPath}/metting/searchmeetings">
            <fieldset>
                <legend>搜索会议</legend>
                <table class="formtable">
                    <tr>
                        <td>会议名称：</td>
                        <td>
                            <input type="text" id="meetingname" maxlength="20" name="meetingname" value="${searhmeetingsVo.meetingname}"/>
                        </td>
                        <td>会议室名称：</td>
                        <td>
                            <input type="text" id="roomname" maxlength="20" name="roomname" value="${searhmeetingsVo.roomname}"/>
                        </td>
                        <td>预定者姓名：</td>
                        <td>
                            <input type="text" id="reservername" maxlength="20" name="reservername" value="${searhmeetingsVo.reservername}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>预定日期：</td>
                        <td colspan="5">
                            从&nbsp;<input type="date" id="reservefromdate" placeholder="例如：2013-10-20"
                                          name="reservefromdate" value="${searhmeetingsVo.reservefromdate}"/>
                            到&nbsp;<input type="date" id="reservetodate" placeholder="例如：2013-10-22"
                                          name="reservetodate" value="${searhmeetingsVo.reservetodate}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>会议日期：</td>
                        <td colspan="5">
                            从&nbsp;<input type="date" id="meetingfromdate" placeholder="例如：2013-10-20"
                                          name="meetingfromdate" value="${searhmeetingsVo.meetingfromdate}"/>
                            到&nbsp;<input type="date" id="meetingtodate" placeholder="例如：2013-10-22"
                                          name="meetingtodate" value="${searhmeetingsVo.meetingtodate}"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="6" class="command">
                            <input type="submit" class="clickbutton" value="查询"/>
                            <input type="reset" class="clickbutton" value="重置"/>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </form>
        <div>
            <h3 style="text-align:center;color:black">查询结果</h3>
            <div class="pager-header">
                <div class="header-info">
                    共<span class="info-number">54</span>条结果，
                    分成<span class="info-number">6</span>页显示，
                    当前第<span class="info-number">1</span>页
                </div>
                <div class="header-nav">
                    <input type="button" class="clickbutton" value="首页"/>
                    <input type="button" class="clickbutton" value="上页"/>
                    <input type="button" class="clickbutton" value="下页"/>
                    <input type="button" class="clickbutton" value="末页"/>
                    跳到第<input type="text" id="pagenum" class="nav-number"/>页
                    <input type="button" class="clickbutton" value="跳转"/>
                </div>
            </div>
        </div>
        <table class="listtable">
            <tr class="listheader">
                <th>会议名称</th>
                <th>会议室名称</th>
                <th>会议开始时间</th>
                <th>会议结束时间</th>
                <th>会议预定时间</th>
                <th>预定者</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${searchmeetings}" var="searchmeeting">
                <tr>
                    <td>${searchmeeting.meetingname}</td>
                    <td>${searchmeeting.meetingroom.roomname}</td>
                    <td>${searchmeeting.strstarttime}</td>
                    <td>${searchmeeting.strendtime}</td>
                    <td>${searchmeeting.strreservationtime}</td>
                    <c:forEach items="${searchmeeting.employees}" var="employees">
                        <td>${employees.employeename}</td>
                    </c:forEach>
                    <td>
                        <a class="clickbutton" href="meetingdetails.jsp">查看详情</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<div class="page-footer">
    <hr/>
    更多问题，欢迎联系<a href="mailto:webmaster@eeg.com">管理员</a>
    <img src="images/footer.png" alt="CoolMeeting"/>
</div>
</body>
</html>