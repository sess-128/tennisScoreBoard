<%@ page import="java.util.UUID" %>
<%@ page import="com.rrtyui.dto.MatchScoreModel" %>
<%@ page import="com.rrtyui.util.MatchStorage" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Match-score</title>
</head>
<body>
<p>Страница текущего матча</p>
<%
    String uuidString = request.getParameter("UUID");

    if (uuidString != null && !uuidString.isEmpty()) {
        UUID uuid = UUID.fromString(uuidString);
        MatchScoreModel match = MatchStorage.getMatch(uuidString);

        if (match != null) {
            if (match.getPlayer1Sets() < 2 && match.getPlayer2Sets() < 2) {
%>
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
        <td><%= match.getPlayer1().getName() %></td>
        <td><%= match.getPlayer1Sets() %></td>
        <td><%= match.getPlayer1Games() %></td>
        <td><%= match.getPlayer1Points() %></td>
        <td>
            <form action="${pageContext.request.contextPath}/match-score" method="post">
                <input type="hidden" name="UUID" value="<%= uuidString %>">
                <input type="hidden" name="player" value="1">
                <button type="submit">Очко 1-му игроку</button>
            </form>
        </td>
    </tr>
    <tr>
        <td><%= match.getPlayer2().getName() %></td>
        <td><%= match.getPlayer2Sets() %></td>
        <td><%= match.getPlayer2Games() %></td>
        <td><%= match.getPlayer2Points() %></td>
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
<%
} else {
%>
<table>
    <thead>
    <tr>
        <th>Имя игрока</th>
        <th>Сеты</th>
        <th>Геймы</th>
        <th>Очки</th>
        <th colspan="2">Победитель</th> <!-- Объединяем две ячейки -->
    </tr>
    </thead>
    <tbody>
    <tr>
        <td><%= match.getPlayer1().getName() %></td>
        <td><%= match.getPlayer1Sets() %></td>
        <td><%= match.getPlayer1Games() %></td>
        <td><%= match.getPlayer1Points() %></td>
        <td colspan="2" style="text-align: center;">
            <p><%= match.getPlayer1Sets() == 2 ? match.getPlayer1().getName() : match.getPlayer2().getName() %></p>
        </td>
    </tr>
    <tr>
        <td><%= match.getPlayer2().getName() %></td>
        <td><%= match.getPlayer2Sets() %></td>
        <td><%= match.getPlayer2Games() %></td>
        <td><%= match.getPlayer2Points() %></td>
        <!-- Пустые ячейки, так как они объединены в первой строке -->
        <td></td>
        <td></td>
    </tr>
    </tbody>
</table>
<%
            }
        } else {
            out.println("<p>Матч не найден для UUID: " + uuidString + "</p>");
        }
    } else {
        out.println("<p>UUID не указан.</p>");
    }
%>
</body>
</html>