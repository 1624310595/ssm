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
                    <li class="sidebar-menuitem"><a href="${pageContext.request.contextPath}/departments/department">部门管理</a>
                    </li>
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
            会议预定 > 修改会议室信息
        </div>
        <form>
            <fieldset>
                <legend>会议室信息</legend>
                <table class="formtable">
                    <tr>
                        <td>门牌号:</td>
                        <td>
                            <input id="roomnumber" type="text" value="${meetingrooms.roomnum}" maxlength="10"/>
                        </td>
                    </tr>
                    <tr>
                        <td>会议室名称:</td>
                        <td>
                            <input id="capacity" type="text" value="${meetingrooms.roomname}" maxlength="20"/>
                        </td>
                    </tr>
                    <tr>
                        <td>最多容纳人数：</td>
                        <td>
                            <input id="roomcapacity" type="text" value="${meetingrooms.capacity}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>当前状态：</td>
                        <td>
                            <c:if test="${meetingrooms.status}==1">
                                <input type="radio" id="status" name="status" checked="checked" value="1"/><label
                                    for="status">启用</label>
                            </c:if>
                            <c:if test="${meetingrooms.status}==0">
                                <input type="radio" id="status" name="status" checked="checked" value="1"/><label
                                    for="status">启用</label>
                            </c:if>
                            <c:if test="${meetingrooms.status}==2">
                                <input type="radio" id="status" name="status" checked="checked" value="1"/><label
                                    for="status">启用</label>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>备注：</td>
                        <td>
                            <textarea id="description" maxlength="200" rows="5"
                                      cols="60">${meetingrooms.description}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" class="command">
                            <input type="submit" value="确认修改" class="clickbutton"/>
                            <input type="button" class="clickbutton" value="返回" onclick="window.history.back();"/>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </form>
    </div>
</div>
<div class="page-footer">
    <hr/>
    更多问题，欢迎联系<a href="mailto:webmaster@eeg.com">管理员</a>
    <img src="${pageContext.request.contextPath}/code/images/footer.png" alt="CoolMeeting"/>
</div>
</body>
</html>