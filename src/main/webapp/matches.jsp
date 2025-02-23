<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<label>
  <input type="text" name="player">
</label>

<table border="1">
  <tr>
    <th>Игрок 1</th>
    <th>Игрок 2</th>
    <th>Результат</th>
  </tr>
  <c:forEach var="match" items="${matchPageResponseDto.matches()}">
    <tr>
      <td>${match.getPlayer1().getName()}</td>
      <td>${match.getPlayer2().getName()}</td>
      <td>${match.getWinner().getName()}</td>
    </tr>
  </c:forEach>
</table>

<div>
  <c:if test="${matchPageResponseDto.currentPage() > 1}">
    <a href="/matches?page=${matchPageResponseDto.currentPage() - 1}&filter_by_player_name=${param.filter_by_player_name}">Previous</a>
  </c:if>

  <c:forEach begin="1" end="${matchPageResponseDto.totalPages()}" var="i">
    <c:choose>
      <c:when test="${i == matchPageResponseDto.currentPage()}">
        <strong>${i}</strong>
      </c:when>
      <c:otherwise>
        <a href="/matches?page=${i}&filter_by_player_name=${param.filter_by_player_name}">${i}</a>
      </c:otherwise>
    </c:choose>
  </c:forEach>

  <c:if test="${matchPageResponseDto.currentPage() < paginaed.totalPages()}">
    <a href="/matches?page=${paginaed.currentPage() + 1}&filter_by_player_name=${param.filter_by_player_name}">Next</a>
  </c:if>
</div>


</body>
</html>
