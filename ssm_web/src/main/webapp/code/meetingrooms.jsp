<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>CoolMeeting会议管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/code/styles/common.css"/>
</head>
<body>
<div class="page-header">
    <div class="header-banner">
        <img src="${pageContext.request.contextPath}/code/images/header.png" alt="CoolMeeting"/>
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
            会议预定 > 查看会议室
        </div>
        <table class="listtable">
            <caption>所有会议室:</caption>
            <tr class="listheader">
                <th>门牌编号</th>
                <th>会议室名称</th>
                <th>容纳人数</th>
                <th>当前状态</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${meetingrooms}" var="meetingroom">
                <tr>
                    <td>${meetingroom.roomnum}</td>
                    <td>${meetingroom.roomname}</td>
                    <td>${meetingroom.capacity}</td>
                    <td>${meetingroom.status}</td>
                    <td>
                        <a class="clickbutton"
                           href="${pageContext.request.contextPath}/meetinGroom/details?roomid=${meetingroom.roomid}">查看详情</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<div class="page-footer">
    <hr/>
    更多问题，欢迎联系<a href="mailto:webmaster@eeg.com">管理员</a>
    <img src="${pageContext.request.contextPath}/code/images/footer.png" alt="CoolMeeting"/>
</div>
</body>
</html>