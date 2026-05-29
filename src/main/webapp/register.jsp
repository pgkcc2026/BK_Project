<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">

<head>
<meta charset="UTF-8">
<title>会員登録 | Banking System</title>

<style>
body{
    font-family: Arial, sans-serif;
    background:#f4f6f8;
}

.container{
    width:400px;
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

label{
    font-weight:bold;
}

input{
    width:100%;
    padding:10px;
    margin:8px 0 15px 0;
    border:1px solid #ccc;
    border-radius:5px;
    box-sizing:border-box;
}

button{
    width:100%;
    padding:12px;
    background:#007bff;
    color:white;
    border:none;
    border-radius:5px;
    cursor:pointer;
}

button:hover{
    background:#0056b3;
}

.link{
    text-align:center;
    margin-top:15px;
}

.error{
    color:red;
    text-align:center;
    margin-bottom:15px;
}
</style>
</head>

<body>

<div class="container">

<h2>会員登録</h2>

<%
String error = (String)request.getAttribute("error");
if(error != null){
%>
<div class="error"><%= error %></div>
<%
}
%>

<form action="RegisterServlet" method="post">

<label>名前</label>
<input type="text" name="name"
value="<%= request.getParameter("name") != null ? request.getParameter("name") : "" %>"
maxlength="30" required>

<label>メールアドレス</label>
<input type="email" name="email"
value="<%= request.getParameter("email") != null ? request.getParameter("email") : "" %>"
maxlength="100" required>

<label>パスワード</label>
<input type="password" name="password" maxlength="20" required>

<label>パスワード確認</label>
<input type="password" name="confirmPassword" maxlength="20" required>

<button type="submit" >登録する</button>

</form>

<div class="link">
<a href="Login.jsp">ログインはこちら</a>
</div>

</div>

</body>
</html>