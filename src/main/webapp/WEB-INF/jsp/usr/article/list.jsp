<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Article List</title>
<style>
th, td {
    border: 1px solid;
}

table {
    border-collapse: collapse;
}
</style>
</head>
<body>
    <table>
        <thead>
            <tr>
                <th>No</th>
                <th>Title</th>
                <th>Date</th>
            </tr>
        </thead>
        <c:forEach var="article" items="${articles }">
            <tr>
                <td>${article.id }</td>
                <td><a href="/article/detail?id=${article.id }">${article.title }</a></td>
                <td>${article.regDate }</td>
            </tr>
        </c:forEach>
    </table>

<a href="/article/write">글쓰기</a>
</body>
</html>