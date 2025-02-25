<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Match-score</title>
    <link rel="stylesheet" href="css/index.css">
    <script type="text/javascript" src="js/ErrorMessageAlert.js"></script>
</head>
<body onload="showError('${errorMessage}')">
<c:choose>
    <c:when test="${match.player1Sets < 2 && match.player2Sets < 2}">
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
                    <td>${match.player1.name}</td>
                    <td>${match.player1Sets}</td>
                    <td>${match.player1Games}</td>
                    <td>${match.player1Points}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/match-score" method="post">
                            <input type="hidden" name="UUID" value="${param.UUID}">
                            <input type="hidden" name="player" value="1">
                            <button type="submit">Очко 1-му игроку</button>
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>${match.player2.name}</td>
                    <td>${match.player2Sets}</td>
                    <td>${match.player2Games}</td>
                    <td>${match.player2Points}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/match-score" method="post">
                            <input type="hidden" name="UUID" value="${param.UUID}">
                            <input type="hidden" name="player" value="2">
                            <button type="submit">Очко 2-му игроку</button>
                        </form>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </c:when>
    <c:otherwise>
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
                    <td>${match.player1.name}</td>
                    <td>${match.player1Sets}</td>
                    <td>${match.player1Games}</td>
                    <td>${match.player1Points}</td>
                    <td colspan="2" style="text-align: center;">
                        <p>${match.player1Sets == 2 ? match.player1.name : match.player2.name}</p>
                    </td>
                </tr>
                <tr>
                    <td>${match.player2.name}</td>
                    <td>${match.player2Sets}</td>
                    <td>${match.player2Games}</td>
                    <td>${match.player2Points}</td>
                    <td></td>
                    <td></td>
                </tr>
                </tbody>
            </table>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>