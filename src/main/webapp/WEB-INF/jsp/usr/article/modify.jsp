<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageName" value="Article Modify" />
<%@ include file="../part/head.jspf"%>
<form action="/article/doModify" method="POST">
    <input type="hidden" name="id" value="${article.id }" />
    <table>
        <thead>
            <tr>
                <th>Title</th>
                <td>
                    <input name="title" type="text" placeholder="제목" autocomplete="off" value="${article.title }" />
                </td>
            </tr>
        </thead>
        <tr>
            <th>Body</th>
            <td>
                <textarea name="body" cols="30" rows="10">${article.body }</textarea>
            </td>
        </tr>
    </table>
    <input type="submit" value="작성" />
    <a href="/article/list">목록</a>
</form>
<%@ include file="../part/foot.jspf"%>