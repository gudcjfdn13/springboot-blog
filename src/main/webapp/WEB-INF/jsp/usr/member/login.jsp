<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageName" value="Login" />
<%@ include file="../part/head.jspf"%>
<script>
    function doLoginConfirm(form) {
        var isDone = false;
        if (isDone) {
            alert('처리중 입니다.');
            return;
        }

        form.loginId.value = form.loginId.value.trim();
        if (form.loginId.value.length == 0) {
            alert('아이디를 입력하세요.');
            form.loginId.focus();
            return;
        }

        form.loginPw.value = form.loginPw.value.trim();
        if (form.loginPw.value.length == 0) {
            alert('비밀번호를 입력하세요.');
            form.loginPw.focus();
            return;
        }

        form.submit();
        isDone = true;
    }
</script>
<form action="/member/doLogin" onsubmit="doLoginConfirm(this); return false;">
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
    <a href="/main/home">목록</a>
</form>
<%@ include file="../part/foot.jspf"%>