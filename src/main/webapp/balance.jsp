<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%@ page import="model.Account" %>

<%
User loginUser = (User)session.getAttribute("loginUser");

if(loginUser == null){
    response.sendRedirect("login.jsp");
    return;
}

Account account = (Account)request.getAttribute("account");
String error = (String)request.getAttribute("error");
%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>残高確認 | Banking System</title>
</head>
<body>

<h2>残高確認</h2>

<% if(error != null){ %>
    <p style="color:red;"><%= error %></p>
    <a href="accountCreate.jsp">口座開設へ</a>
<% } else if(account != null){ %>
    <p>口座番号：<%= account.getAccountNumber() %></p>
    <p>現在の残高：<%= account.getBalance() %> 円</p>
<% } %>

<br>
<a href="mypage.jsp">マイページへ戻る</a>

</body>
</html>