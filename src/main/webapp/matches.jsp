<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/index.css">
</head>
<body>
<div class="container">
    <table border="1">
        <tr>
            <th>Игрок 1</th>
            <th>Игрок 2</th>
            <th>Результат</th>
        </tr>
        <c:forEach var="match" items="${matches.matches}">
            <tr>
                <td>${match.getPlayer1().getName()}</td>
                <td>${match.getPlayer2().getName()}</td>
                <td>${match.getWinner().getName()}</td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
