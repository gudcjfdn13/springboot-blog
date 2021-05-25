<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageName" value="findLoginId" />
<%@ include file="../part/head.jspf"%>
<script>
    function doFindLoginPwConfirm(form) {
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
        
        form.email.value = form.email.value.trim();
        if (form.email.value.length == 0) {
            alert('이메일을 입력하세요.');
            form.email.focus();
            return;
        }


        form.submit();
        isDone = true;
    }
</script>
<h1>비밀번호 찾기</h1>
<form action="/member/doFindLoginPw" onsubmit="doFindLoginPwConfirm(this); return false;">
    <table>
        <tr>
            <th>ID</th>
            <td>
                <input name="loginId" type="text" placeholder="LOGINID" autocomplete="off" />
            </td>
        </tr>
        <tr>
            <th>EMAIL</th>
            <td>
                <input name="email" type="email" placeholder="EMAIL" autocomplete="off" />
            </td>
        </tr>
    </table>
    <input type="submit" value="찾기" />
    <a href="/main/home">목록</a>
    <a href="/member/showFindLoginId">아이디 찾기</a>
    <a href="/member/showFindLoginPw">비밀번호 찾기</a>
</form>
<%@ include file="../part/foot.jspf"%>