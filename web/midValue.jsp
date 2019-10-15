<%--
  Created by IntelliJ IDEA.
  User: 1298509345
  Date: 2019/10/15
  Time: 19:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@ page import="Dao.*" %>
<%@ page import="java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>均价信息如下</title>
</head>
<%
    List<Double>list=(List<Double>)request.getAttribute("list");
%>
<body>
<p>今日股票的均价信息如下：</p>
<span><%=list.get(0) %></span></br>

</body>


</html>