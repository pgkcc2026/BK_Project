<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>出金 | Banking System</title>

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
    box-shadow: 0 0 10px rgba(0,0,0,0.1);
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
    background: #28a745;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

button:hover {
    background: #218838;
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
    <h2>出金</h2>

    <% if (request.getAttribute("message") != null) { %>
        <p class="message"><%= request.getAttribute("message") %></p>
    <% } %>

    <% if (request.getAttribute("error") != null) { %>
        <p class="error"><%= request.getAttribute("error") %></p>
    <% } %>

    <form action="WithdrawServlet" method="post">
        <label>出金額</label>
        <input type="text" name="amount" placeholder="出金額を入力してください">
        <button type="submit">出金する</button>
    </form>

    <a href="mypage.jsp" class="back">マイページへ戻る</a>
</div>

</body>