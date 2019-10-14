<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>CoolMeeting会议管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/code/styles/common.css"/>
    <style type="text/css">
        #divfrom {
            float: left;
            width: 150px;
        }

        #divto {
            float: left;
            width: 150px;
        }

        #divoperator {
            float: left;
            width: 50px;
            padding: 60px 5px;
        }

        #divoperator input[type="button"] {
            margin: 10px 0;
        }

        #selDepartments {
            display: block;
            width: 100%;
        }

        #selEmployees {
            display: block;
            width: 100%;
            height: 200px;
        }

        #selSelectedEmployees {
            display: block;
            width: 100%;
            height: 225px;
        }
    </style>
    <script type="application/javascript">
        function employee(employeeid, employeename) {
            this.employeeid = employeeid;
            this.employeename = employeename;
        }

        function department(departmentid, departmentname, employees) {
            this.departmentid = departmentid;
            this.departmentname = departmentname;
            this.employees = employees;
        }

        // var data = new Array(
        //     new department(1, "技术部", new Array(
        //         new employee(1001, "a00"), new employee(1002, "a01"), new employee(1003, "a02"), new employee(1004, "a03"))),
        //     new department(2, "销售部", new Array(
        //         new employee(2001, "b00"), new employee(2002, "b01"), new employee(2003, "b02"), new employee(2004, "b03"))),
        //     new department(3, "市场部", new Array(
        //         new employee(3001, "c00"), new employee(3002, "c01"), new employee(3003, "c02"), new employee(3004, "c03"))),
        //     new department(4, "行政部", new Array(
        //         new employee(4001, "d00"), new employee(4002, "d01"), new employee(4003, "d02"), new employee(4004, "d03"))));

        var selDepartments;
        var selEmployees;
        var selSelectedEmployees;

        function body_load() {
            selDepartments = document.getElementById("selDepartments");
            selEmployees = document.getElementById("selEmployees");
            selSelectedEmployees = document.getElementById("selSelectedEmployees");

            for (var i = 0; i < data.length; i++) {
                var dep = document.createElement("option");
                dep.value = data[i].departmentid;
                dep.text = data[i].departmentname;
                selDepartments.appendChild(dep);
            }

            fillEmployees();
        }

        function fillEmployees() {
            clearList(selEmployees);
            var departmentid = selDepartments.options[selDepartments.selectedIndex].value;
            var employees;
            for (var i = 0; i < data.length; i++) {
                if (departmentid == data[i].departmentid) {
                    employees = data[i].employees;
                    break;
                }
            }
            for (i = 0; i < employees.length; i++) {
                var emp = document.createElement("option");
                emp.value = employees[i].employeeid;
                emp.text = employees[i].employeename;
                selEmployees.appendChild(emp);
            }
        }

        function clearList(list) {
            while (list.childElementCount > 0) {
                list.removeChild(list.lastChild);
            }
        }

        function selectEmployees() {
            for (var i = 0; i < selEmployees.options.length; i++) {
                if (selEmployees.options[i].selected) {
                    addEmployee(selEmployees.options[i]);
                    selEmployees.options[i].selected = false;
                }
            }
        }

        function deSelectEmployees() {
            var elementsToRemoved = new Array();
            var options = selSelectedEmployees.options;
            for (var i = 0; i < options.length; i++) {
                if (options[i].selected) {
                    elementsToRemoved.push(options[i]);
                }
            }
            for (i = 0; i < elementsToRemoved.length; i++) {
                selSelectedEmployees.removeChild(elementsToRemoved[i]);
            }
        }

        function addEmployee(optEmployee) {
            var options = selSelectedEmployees.options;
            var i = 0;
            var insertIndex = -1;
            while (i < options.length) {
                if (optEmployee.value == options[i].value) {
                    return;
                } else if (optEmployee.value < options[i].value) {
                    insertIndex = i;
                    break;
                }
                i++;
            }
            var opt = document.createElement("option");
            opt.value = optEmployee.value;
            opt.text = optEmployee.text;

            if (insertIndex == -1) {
                selSelectedEmployees.appendChild(opt);
            } else {
                selSelectedEmployees.insertBefore(opt, options[insertIndex]);
            }
        }
    </script>
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.js" type="text/javascript"
            charset="utf-8"></script>
    <script>
        $(function () {
            $("#btn_sub").click(function () {
                alert("sss")
                $("#selSelectedEmployees option").attr("selected", "selected");
                $("form").submit();
            });
            /*$("form").submit(function () {
                $("#selSelectedEmployees option").attr("selected", "selected");
                return true;
            });*/
        });


    </script>
</head>
<body onload="body_load()">
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
                    <li class="sidebar-menuitem"><a href="${pageContext.request.contextPath}/departments/department">部门管理</a></li>
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
            会议预定 > 预定会议
        </div>
        <form method="post" action="${pageContext.request.contextPath}/metting/bookmeeting">
            <fieldset>
                <legend>会议信息</legend>
                <table class="formtable">
                    <tr>
                        <td>会议名称：</td>
                        <td>
                            <input type="text" id="meetingname" maxlength="20" name="meetingname"/>
                        </td>
                    </tr>
                    <tr>
                        <td>预计参加人数：</td>
                        <td>
                            <input type="text" id="numofattendents" name="numberofparticipants"/>
                        </td>
                    </tr>
                    <tr>
                        <td>预计开始时间：</td>
                        <td>
                            <input type="date" id="startdate" name="sdate"/>
                            <input type="time" id="starttime" name="stime"/>
                        </td>
                    </tr>
                    <tr>
                        <td>预计结束时间：</td>
                        <td>
                            <input type="date" id="enddate" name="edate"/>
                            <input type="time" id="endtime" name="etime"/>
                        </td>
                    </tr>
                    <tr>
                        <td>会议室名称：</td>
                        <td>
                            <select name="roomid">
                                <c:forEach items="${meetingrooms}" var="meetingroom">
                                    <option value="${meetingroom.roomid}">${meetingroom.roomname}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>会议说明：</td>
                        <td>
                            <textarea id="description" rows="5" name="description"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>选择参会人员：</td>
                        <td>
                            <div id="divfrom">
                                <%--<select id="selDepartments" onchange="fillEmployees()">
                                </select>--%>
                                <select id="selEmployees" multiple="true" style="height: 225px;">
                                    <c:forEach items="${employees}" var="employee">
                                        <option value="${employee.employeeid}">${employee.employeename}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div id="divoperator">
                                <input type="button" class="clickbutton" value="&gt;" onclick="selectEmployees()"/>
                                <input type="button" class="clickbutton" value="&lt;" onclick="deSelectEmployees()"/>
                            </div>
                            <div id="divto">
                                <select id="selSelectedEmployees" multiple="true" name="employeeids">

                                </select>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="command" colspan="2">
                            <input type="submit" class="clickbutton" value="预定会议" id="btn_sub"/>
                            <input type="reset" class="clickbutton" value="重置"/>
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
    <img src="images/footer.png" alt="CoolMeeting"/>
</div>
</body>
</html>