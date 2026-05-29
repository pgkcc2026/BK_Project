<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page import="model.transaction" %>

<%
User loginUser = (User) session.getAttribute("loginUser");

if (loginUser == null) {
    response.sendRedirect("Login.jsp");
    return;
}

List<transaction> transactionList =
    (List<transaction>) request.getAttribute("transactionList");

String error = (String) request.getAttribute("error");
%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>取引履歴</title>

<style>
body {
    font-family: Arial, sans-serif;
    background: #f4f6f8;
}

.container {
    width: 850px;
    margin: 60px auto;
    background: white;
    padding: 30px;
    border-radius: 10px;
}

h2 {
    text-align: center;
}

table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 25px;
}

th, td {
    border: 1px solid #ccc;
    padding: 10px;
    text-align: center;
}

th {
    background: #007bff;
    color: white;
}

.deposit {
    color: blue;
    font-weight: bold;
}

.withdraw {
    color: red;
    font-weight: bold;
}

.error {
    color: red;
    text-align: center;
}

.back {
    display: block;
    margin-top: 20px;
    text-align: center;
}
</style>
</head>

<body>

<div class="container">

<h2>取引履歴</h2>

<% if (error != null) { %>

    <p class="error"><%= error %></p>

<% } else if (transactionList == null || transactionList.size() == 0) { %>

    <p>取引履歴はありません。</p>

<% } else { %>

<table>
    <tr>
        <th>取引ID</th>
        <th>取引種別</th>
        <th>金額</th>
        <th>取引後残高</th>
        <th>送金先ID</th>
        <th>取引日時</th>
    </tr>
    
    

    <% for (transaction transaction : transactionList) { %>
    <tr>
        <td><%= transaction.getTransactionId() %></td>

        <% if ("入金".equals(transaction.getTransactionType())) { %>
            <td class="deposit">入金</td>
        <% } else if ("出金".equals(transaction.getTransactionType())) { %>
            <td class="withdraw">出金</td>
        <% } else if ("送金".equals(transaction.getTransactionType())) { %>
            <td class="transfer">送金</td>
        <% } else if ("受取".equals(transaction.getTransactionType())) { %>
            <td class="receive">受取</td>
        <% } else { %>
            <td><%= transaction.getTransactionType() %></td>
        <% } %>
  
        <td><%= transaction.getAmount() %> 円</td>
        <td><%= transaction.getBalanceAfter() %> 円</td>
        
        
        <td>
            <% if (transaction.getRelatedAccountId() == 0) { %>
                -
            <% } else { %>
                <%= transaction.getRelatedAccountId() %>
            <% } %>
        </td>
        <td><%= transaction.getTransactionDate() %></td>
    </tr>
    <% } %>
</table>

<% } %>

<a href="mypage.jsp" class="back">マイページへ戻る</a>

</div>

</body>
</html>