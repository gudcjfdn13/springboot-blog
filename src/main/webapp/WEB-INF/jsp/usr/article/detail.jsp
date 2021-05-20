<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageName" value="Article Detail" />
<%@ include file="../part/head.jspf"%>
<!-- 게시물 상세 -->
<table>
    <thead>
        <tr>
            <th>No</th>
            <td>${article.id }</td>
            <th>Writer</th>
            <td>${article.extra.writer }</td>
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
<c:if test="${article.extra.canDelete }">
    <a href="/article/doDelete?id=${article.id }">삭제</a>
</c:if>
<c:if test="${article.extra.canModify }">
    <a href="/article/modify?id=${article.id }">수정</a>
</c:if>
<a href="${listUri }">목록</a>
<!-- 댓글 작성 -->
<form action="/reply/doWrite" method="POST">
    <input type="hidden" name="articleId" value="${article.id }" />
    <input type="hidden" name="memberId" value="${requestScope.loginedMemberId}" />
    <textarea name="body" cols="30" rows="5" placeholder="댓글"></textarea>
    <input type="submit" value="작성" />
</form>
<!-- 댓글 리스팅 -->
<c:forEach var="reply" items="${replies }">
    <div>작성자 : ${reply.extra.writer } <br /> 
    내용 : ${reply.body }</div>
    <br />
</c:forEach>
<%@ include file="../part/foot.jspf"%>