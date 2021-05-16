<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Article Detail</title>
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
                <td>${article.id }</td>
                <th>Title</th>
                <td>${article.title }</td>

                <th>Date</th>
                <td>${article.regDate }</td>
            </tr>
        </thead>
        <tr>
            <th>Body</th>
            <td>${article.body }</td>
        </tr>
    </table>
    <a href="/article/doDelete">삭제</a>
    <a href="/article/modify?id=${article.id }">수정</a>
</body>
<a href="/article/list">목록</a>
</html>