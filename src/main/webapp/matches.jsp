<%@ page import="java.util.List" %>
<%@ page import="com.rrtyui.entity.Match" %><%--
  Created by IntelliJ IDEA.
  User: Андрей
  Date: 16.02.2025
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<table border="1">
  <tr>
    <th>Игрок 1</th>
    <th>Игрок 2</th>
    <th>Результат</th>
  </tr>
  <c:forEach var="match" items="${matches}">
    <tr>
      <td>${match.player1}</td>
      <td>${match.player2}</td>
      <td>${match.result}</td>
    </tr>
  </c:forEach>
</table>


</body>
</html>
