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

textarea {
    resize: none;
}
</style>
</head>
<body>
    <form action="/article/doWrite" method="POST">
        <table>
            <thead>
                <tr>
                    <th>Title</th>
                    <td>
                        <input name="title" type="text" placeholder="제목" autocomplete="off" />
                    </td>
                </tr>
            </thead>
            <tr>
                <th>Body</th>
                <td>
                    <textarea name="body" cols="30" rows="10"></textarea>
                </td>
            </tr>
        </table>
        <input type="submit" value="작성" />
        <a href="/article/list">목록</a>
    </form>
</body>
</html>