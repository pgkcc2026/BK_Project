<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>

<%
User loginUser = (User) session.getAttribute("loginUser");

if (loginUser == null) {
    response.sendRedirect("Login.jsp");
    return;
}
%>


<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>送金</title>

<style>
body {
    font-family: Arial, sans-serif;
    background: #f4f6f8;
}
.container {
    width: 450px;
    margin: 80px auto;
    background: white;
    padding: 30px;
    border-radius: 10px;
}
h2 {
    text-align: center;
}

input {
    width: 100%;
    padding: 10px;
    margin: 10px 0;
    box-sizing: border-box;
}

button {
    width: 100%;
    padding: 10px;
    background: #007bff;
    color: white;
    border: none;
    border-radius: 5px;
}

.message {
    color: green;
    text-align: center;
}

.error {
    color: red;
    text-align: center;
}

.back {
    display: block;
    text-align: center;
    margin-top: 15px;
}
</style>
</head>

<body>

<div class="container">
    <h2>送金</h2>

    <% if (request.getAttribute("message") != null) { %>
        <p class="message"><%= request.getAttribute("message") %></p>
    <% } %>

    <% if (request.getAttribute("error") != null) { %>
        <p class="error"><%= request.getAttribute("error") %></p>
    <% } %>

    <form action="<%= request.getContextPath() %>/TransferServlet" method="post">
        <label>送金先口座ID</label>
        <input type="text" name="toAccountId" placeholder="送金先の口座IDを入力してください">

        <label>送金額</label>
        <input type="text" name="amount" placeholder="送金額を入力してください">

        <button type="submit">送金する</button>
    </form>

    <a href="mypage.jsp" class="back">マイページへ戻る</a>
</div>

</body>
</html>


