<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageName" value="Article List" />
<%@ include file="../part/head.jspf"%>

<c:if test="${articles.size() == 0 }">
    <h1>게시물이 없습니다.</h1>
</c:if>

<c:if test="${articles.size() > 0 }">
    <table>
        <thead>
            <tr>
                <th>No</th>
                <th>Writer</th>
                <th>Title</th>
                <th>Date</th>
            </tr>
        </thead>
        <c:forEach var="article" items="${articles }">
            <tr>
                <td>${article.id }</td>
                <td>${article.extra.writer }</td>
                <td>
                    <a href="/article/detail?id=${article.id }">${article.title }</a>
                </td>
                <td>${article.regDate }</td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<a href="/article/list">처음으로</a>
<a href="/article/list?page=${page-1 }">◀</a>
<c:forEach var="num" begin="${startPage }" end="${lastPage }">
    <a href="/article/list?page=${num }">${num }</a>
</c:forEach>
<a href="/article/list?page=${page+1 }">▶</a>
<a href="/article/list?page=${pageCnt }">끝으로</a>
<a href="/article/write">글쓰기</a>

<%@ include file="../part/foot.jspf"%>