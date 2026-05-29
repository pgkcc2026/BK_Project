<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>

<%
User loginUser = (User)session.getAttribute("loginUser");

if(loginUser == null){
    response.sendRedirect("Login.jsp");
    return;
}
%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>口座開設 | Banking System</title>
</head>
<body>

<h2>口座開設</h2>

<p><%= loginUser.getName() %> さんの口座を開設します。</p>

<%
String message = (String)request.getAttribute("message");
String error = (String)request.getAttribute("error");

if(message != null){
%>
<p style="color:green;"><%= message %></p>
<%
}

if(error != null){
%>
<p style="color:red;"><%= error %></p>
<%
}
%>

<form action="AccountCreateServlet" method="post">
    <button type="submit">口座を開設する</button>
</form>

<br>
<a href="mypage.jsp">マイページへ戻る</a>

</body>
</html>