<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Match Score</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Mono:wght@300&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">

    <script src="js/app.js"></script>
</head>
<body>
<header class="header">
    <section class="nav-header">
        <div class="brand">
            <div class="nav-toggle">
                <img src="images/menu.png" alt="Logo" class="logo">
            </div>
            <span class="logo-text">TennisScoreboard</span>
        </div>
        <div>
            <nav class="nav-links">
                <a class="nav-link" href="${pageContext.request.contextPath}/index.jsp">Home</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/matches">Matches</a>
            </nav>
        </div>
    </section>
</header>
<main>
    <c:if test="${not empty errorMessage}">
        <div class="error-message" style="color: red;">
                ${errorMessage}
        </div>
    </c:if>

    <c:choose>
        <c:when test="${match.player1Sets < 2 && match.player2Sets < 2}">
            <div class="container">
                <h1>Current match</h1>
                <div class="current-match-image"></div>
                <section class="score">
                    <table class="table">
                        <thead class="result">
                        <tr>
                            <th class="table-text">Player</th>
                            <th class="table-text">Sets</th>
                            <th class="table-text">Games</th>
                            <th class="table-text">Points</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr class="player1">
                            <td class="table-text">${match.player1.name}</td>
                            <td class="table-text">${match.player1Sets}</td>
                            <td class="table-text">${match.player1Games}</td>
                            <td class="table-text">${match.player1Points}</td>
                            <td class="table-text">
                                <form action="${pageContext.request.contextPath}/match-score" method="post">
                                    <input type="hidden" name="UUID" value="${param.UUID}">
                                    <input type="hidden" name="player" value="1">
                                    <button type="submit" class="score-btn">Score</button>
                                </form>
                            </td>
                        </tr>
                        <tr class="player2">
                            <td class="table-text">${match.player2.name}</td>
                            <td class="table-text">${match.player2Sets}</td>
                            <td class="table-text">${match.player2Games}</td>
                            <td class="table-text">${match.player2Points}</td>
                            <td class="table-text">
                                <form action="${pageContext.request.contextPath}/match-score" method="post">
                                    <input type="hidden" name="UUID" value="${param.UUID}">
                                    <input type="hidden" name="player" value="2">
                                    <button type="submit" class="score-btn">Score</button>
                                </form>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </section>
            </div>
        </c:when>
        <c:otherwise>
            <div class="container">
                <h1>Current match</h1>
                <div class="current-match-image"></div>
                <section class="score">
                    <table class="table">
                        <thead class="result">
                        <tr>
                            <th class="table-text">Player</th>
                            <th class="table-text">Sets</th>
                            <th class="table-text">Games</th>
                            <th class="table-text">Points</th>
                            <th colspan="2">Winner</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr class="player1">
                            <td class="table-text">${match.player1.name}</td>
                            <td class="table-text">${match.player1Sets}</td>
                            <td class="table-text">${match.player1Games}</td>
                            <td class="table-text">${match.player1Points}</td>
                            <td class="table-text" colspan="2">
                                <p>${match.player1Sets == 2 ? match.player1.name : match.player2.name}</p>
                            </td>
                        </tr>
                        <tr class="player2">
                            <td class="table-text">${match.player2.name}</td>
                            <td class="table-text">${match.player2Sets}</td>
                            <td class="table-text">${match.player2Games}</td>
                            <td class="table-text">${match.player2Points}</td>
                            <td class="table-text">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </section>
            </div>
        </c:otherwise>
    </c:choose>

</main>
<footer>
    <div class="footer">
        <p>&copy; Tennis Scoreboard, project from <a href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a>
            roadmap.</p>
    </div>
</footer>
</body>
</html>
