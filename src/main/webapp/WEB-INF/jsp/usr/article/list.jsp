<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageName" value="Article List" />
<%@ include file="../part/head.jspf"%>
<c:set var="listUri" value="${encodedCurrentUri }"></c:set>
<h1>${board.name }</h1>
<a href="/article-notice/list">공지사항</a>
<a href="/article-free/list">자유게시판</a>
<!-- 게시물 없을때 -->
<c:if test="${articles.size() == 0 }">
    <h1>게시물이 없습니다.</h1>
</c:if>
<!-- 게시물 리스팅 -->
<div>총 게시물 : ${articlesCnt }</div>
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
                    <a href="/article/detail?id=${article.id }&listUri=${listUri}">${article.title }</a>
                </td>
                <td>${article.regDate }</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<!-- 게시물 서칭 -->
<form action="/article-${board.code }/list">
    <input type="text" name="searchKeyword" placeholder="Search" value="${searchKeyword }" />
    <input type="submit" value="검색" />
</form>
<!-- 게시물 페이징 -->
<c:set var="articleListUri" value="/article-${board.code }/list?searchKeyword=${searchKeyword }" />

<a href="${articleListUri }">처음으로</a>
<a href="${articleListUri }&page=${page-1 }">◀</a>
<c:forEach var="num" begin="${startPage }" end="${lastPage }">
    <a href="${articleListUri }&page=${num }">${num }</a>
</c:forEach>
<a href="${articleListUri }&page=${page+1 }">▶</a>
<a href="${articleListUri }&page=${pageCnt }">끝으로</a>
<a href="/article/write?listUri=${listUri }">글쓰기</a>

<%@ include file="../part/foot.jspf"%>