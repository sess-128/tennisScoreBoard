<%@ page import="java.util.UUID" %>
<%@ page import="com.rrtyui.dto.MatchScoreModel" %>
<%@ page import="com.rrtyui.util.MatchStorage" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Match-score</title>
    <link rel="stylesheet" href="css/index.css">
</head>
<body>
<%
    
    String uuidString = request.getParameter("UUID");
    MatchScoreModel match = MatchStorage.getMatch(uuidString);
    if (match.getPlayer1Sets() < 2 && match.getPlayer2Sets() < 2) {
%>
<div class="container">
<table>
    <thead>
    <tr>
        <th>Имя игрока</th>
        <th>Сеты</th>
        <th>Геймы</th>
        <th>Очки</th>
        <th>Действия</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td><%= match.getPlayer1().getName() %>
        </td>
        <td><%= match.getPlayer1Sets() %>
        </td>
        <td><%= match.getPlayer1Games() %>
        </td>
        <td><%= match.getPlayer1Points() %>
        </td>
        <td>
            <form action="${pageContext.request.contextPath}/match-score" method="post">
                <input type="hidden" name="UUID" value="<%= uuidString %>">
                <input type="hidden" name="player" value="1">
                <button type="submit">Очко 1-му игроку</button>
            </form>
        </td>
    </tr>
    <tr>
        <td><%= match.getPlayer2().getName() %>
        </td>
        <td><%= match.getPlayer2Sets() %>
        </td>
        <td><%= match.getPlayer2Games() %>
        </td>
        <td><%= match.getPlayer2Points() %>
        </td>
        <td>
            <form action="${pageContext.request.contextPath}/match-score" method="post">
                <input type="hidden" name="UUID" value="<%= uuidString %>">
                <input type="hidden" name="player" value="2">
                <button type="submit">Очко 2-му игроку</button>
            </form>
        </td>
    </tr>
    </tbody>
</table>
</div>
<%
} else {
%>
<div class="container">
<table>
    <thead>
    <tr>
        <th>Имя игрока</th>
        <th>Сеты</th>
        <th>Геймы</th>
        <th>Очки</th>
        <th colspan="2">Победитель</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td><%= match.getPlayer1().getName() %>
        </td>
        <td><%= match.getPlayer1Sets() %>
        </td>
        <td><%= match.getPlayer1Games() %>
        </td>
        <td><%= match.getPlayer1Points() %>
        </td>
        <td colspan="2" style="text-align: center;">
            <p><%= match.getPlayer1Sets() == 2 ? match.getPlayer1().getName() : match.getPlayer2().getName() %>
            </p>
        </td>
    </tr>
    <tr>
        <td><%= match.getPlayer2().getName() %>
        </td>
        <td><%= match.getPlayer2Sets() %>
        </td>
        <td><%= match.getPlayer2Games() %>
        </td>
        <td><%= match.getPlayer2Points() %>
        </td>
        <td></td>
        <td></td>
    </tr>
    </tbody>
</table>
</div>
<%
    }
%>
</body>
</html>