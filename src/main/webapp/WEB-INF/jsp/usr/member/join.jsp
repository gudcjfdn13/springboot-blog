<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageName" value="Sign Up" />
<%@ include file="../part/head.jspf"%>
<script>
    function doJoinConfirm(form) {
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
        form.loginPwCf.value = form.loginPwCf.value.trim();
        if (form.loginPw.value != form.loginPwCf.value) {
            alert('비밀번호가 일치하지 않습니다.');
            form.loginPwCf.focus();
            return;
        }
        form.name.value = form.name.value.trim();
        if (form.name.value.length == 0) {
            alert('이름을 입력하세요.');
            form.name.focus();
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
<h1>회원가입</h1>
<form action="/member/doJoin" method="POST" onsubmit="doJoinConfirm(this); return false;">
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
                <input name="name" type="text" placeholder="Name" autocomplete="off" />
            </td>
        </tr>
        <tr>
            <th>EMAIL</th>
            <td>
                <input name="email" type="email" placeholder="EMAIL" autocomplete="off" />
            </td>
        </tr>
    </table>
    <input type="submit" value="가입" />
    <a href="/main/home">목록</a>
</form>
<%@ include file="../part/foot.jspf"%>