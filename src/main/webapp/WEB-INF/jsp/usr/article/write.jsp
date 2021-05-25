<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageName" value="Article Write" />
<%@ include file="../part/head.jspf"%>
<form action="/article/doWrite" method="POST">
<input type="hidden" name="memberId" value="${requestScope.loginedMemberId }" />
    <table>
        <thead>
            <tr>
                <th>Title</th>
                <td>
                    <input name="title" type="text" placeholder="제목" autocomplete="off" />
                    <select name="boardId">
                        <option value="none">선택</option>
                        <option value="1">공지</option>
                        <option value="2">자유</option>
                    </select>
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
    <a href="${listUri }">목록</a>
</form>
<%@ include file="../part/foot.jspf"%>