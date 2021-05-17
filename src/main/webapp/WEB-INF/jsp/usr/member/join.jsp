<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageName" value="Sign Up" />
<%@ include file="../part/head.jspf"%>
<form action="/member/doJoin" method="POST">
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
                <input name="loginPw" type="password" placeholder="Password" autocomplete="off" />
            </td>
        </tr>
        <tr>
            <th>PW CONFIRM</th>
            <td>
                <input name="loginPwCf" type="password" placeholder="password Confirm" autocomplete="off" />
            </td>
        </tr>
        <tr>
            <th>NAME</th>
            <td>
                <input name="name" type="password" placeholder="Name" autocomplete="off" />
            </td>
        </tr>
    </table>
    <input type="submit" value="가입" />
    <a href="/article/list">목록</a>
</form>
<%@ include file="../part/foot.jspf"%>