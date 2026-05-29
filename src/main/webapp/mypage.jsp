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
<title>マイページ | Banking System</title>

<style>
body{
    font-family: Arial, sans-serif;
    background:#f4f6f8;
}

.container{
    width:500px;
    margin:80px auto;
    background:white;
    padding:30px;
    border-radius:10px;
    box-shadow:0 0 10px rgba(0,0,0,0.1);
}

h2{
    text-align:center;
    margin-bottom:25px;
}

.info{
    margin-bottom:20px;
    line-height:2;
}

.menu a{
    display:block;
    text-decoration:none;
    background:#007bff;
    color:white;
    padding:12px;
    margin-bottom:10px;
    text-align:center;
    border-radius:5px;
}

.menu a:hover{
    background:#0056b3;
}

.logout{
    background:#dc3545 !important;
}

.logout:hover{
    background:#b52a37 !important;
}
</style>
</head>

<body>

<div class="container">

<h2>マイページ</h2>

<div class="info">
<b>ようこそ、<%= loginUser.getName() %> さん</b><br>
メールアドレス：<%= loginUser.getEmail() %>
</div>

<div class="menu">

<a href="accountCreate.jsp">口座開設</a>

<a href="deposit.jsp">入金</a>

<a href="withdraw.jsp">出金</a>

<a href="money_transfer.jsp">送金</a>

<a href="BalanceServlet">残高確認</a>

<a href="transactionHistoryServlet">取引履歴</a>

<a href="Logout.jsp" class="logout">ログアウト</a>

</div>

</div>