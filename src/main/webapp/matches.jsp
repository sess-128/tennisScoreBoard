<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Finished Matches</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
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
    <div class="container">
        <h1>Matches</h1>
        <div class="input-container">
            <form action="${pageContext.request.contextPath}/matches" method="get">
                <input class="input-filter" placeholder="Filter by name" type="text" name="filter_by_player_name" />
                <div>
                    <a href="${pageContext.request.contextPath}/matches">
                        <button class="btn-filter">Reset Filter</button>
                    </a>
                </div>
            </form>
        </div>
    </div>

        <table class="table-matches">
            <tr>
                <th>Player One</th>
                <th>Player Two</th>
                <th>Winner</th>
            </tr>

            <c:forEach var="match" items="${matches.matches}">
                <tr>
                    <td>${match.player1.name}</td>
                    <td>${match.player2.name}</td>
                    <td><span class="winner-name-td">${match.winner.name}</span></td>
                </tr>
            </c:forEach>

        </table>

        <div class="pagination">
            <a class="prev" href="${pageContext.request.contextPath}/matches?page=${matches.currentPage - 1}&filter_by_player_name=${param.filter_by_player_name}"> < </a>
            <c:forEach begin="1" end="${matches.totalPages}" var="page">
                <c:choose>
                    <c:when test="${page == matches.currentPage}">
                        <span>${page}</span>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/matches?page=${page}&filter_by_player_name=${param.filter_by_player_filter_by_player_name}">${page}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <a class="next" href="${pageContext.request.contextPath}/matches?page=${matches.currentPage + 1}&filter_by_player_name=${param.filter_by_player_name}"> > </a>
        </div>
    </div>
</main>
<footer>
    <div class="footer">
        <p>&copy; Tennis Scoreboard, project from <a href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a>
            roadmap.</p>
    </div>
</footer>
</body>
</html>
