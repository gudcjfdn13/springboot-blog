<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageName" value="Login" />
<%@ include file="../part/head.jspf"%>
<form action="/member/doLogin">
    <table>
        <tr>
            <th>ID</th>
            <td>
                <input name="loginId" type="text" placeholder="ID" autocomplete="off" />
            </td>
        </tr>
        <tr>
            <th>PW</th>
            <td>
                <input name="loginPw" type="password" placeholder="PW" autocomplete="off" />
            </td>
        </tr>
    </table>
    <input type="submit" value="로그인" />
    <a href="/article/list">목록</a>
</form>
<%@ include file="../part/foot.jspf"%>