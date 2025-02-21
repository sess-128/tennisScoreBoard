<%--
  Created by IntelliJ IDEA.
  User: Андрей
  Date: 21.02.2025
  Time: 22:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>New match test</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/new-match" method="post" id="start">
  <label>
    <input type="text" name="player_1">
  </label>
  <label>
    <input type="text" name="player_2">
  </label>
  <button type="submit" form="start">Начать матч</button>
</form>

</body>
</html>
