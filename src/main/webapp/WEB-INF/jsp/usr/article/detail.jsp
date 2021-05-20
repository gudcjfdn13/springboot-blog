<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageName" value="Article Detail" />
<%@ include file="../part/head.jspf"%>
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
<c:if test="${article.extra.canDelete }">
    <a href="/article/doDelete?id=${article.id }">삭제</a>
</c:if>
<c:if test="${article.extra.canDelete }">
    <a href="/article/modify?id=${article.id }">수정</a>
</c:if>
<a href="${listUri }">목록</a>
<%@ include file="../part/foot.jspf"%>