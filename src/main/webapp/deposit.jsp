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
<title>入金 | Banking System</title>
</head>
<body>

<h2>入金</h2>

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

<form action="DepositServlet" method="post">
    入金額：
    <input type="number" name="amount" min="1" required>
    <button type="submit">入金する</button>
</form>

<br>
<a href="mypage.jsp">マイページへ戻る</a>

</body>
</html>