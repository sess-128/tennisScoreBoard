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
    <link rel="stylesheet" href="css/index.css">
    <script type="text/javascript" src="js/ErrorMessageAlert.js"></script>
</head>
<body onload="showError('${errorMessage}')">
<div class="header">
    <a href="${pageContext.request.contextPath}/new-match">Новый матч</a>
    <a href="${pageContext.request.contextPath}/matches">Список матчей</a>
</div>
<div class="container">
    <form action="${pageContext.request.contextPath}/new-match" method="post" id="start">
        <label>
            <input type="text" name="player_1">
        </label>
        <label>
            <input type="text" name="player_2">
        </label>
        <button type="submit" form="start">Начать матч</button>
    </form>
</div>
<div class="footer">
    <p>© Tennis Scoreboard, project from <a href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a> roadmap.</p>
</div>
</body>
</html>
