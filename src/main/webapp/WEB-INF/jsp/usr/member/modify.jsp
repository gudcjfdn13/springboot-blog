<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageName" value="User Info" />
<%@ include file="../part/head.jspf"%>
<script>
    function doModifyConfirm(form) {
        var isDone = false;
        if (isDone) {
            alert('처리중 입니다.');
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

        form.submit();
        isDone = true;
    }
</script>
<h1>회원정보 수정</h1>
<form action="/member/doModify" method="POST" onsubmit="doModifyConfirm(this); return false;">
    <input type="hidden" name="id" value="${member.id }" />
    <table>
        <tr>
            <th>ID</th>
            <td>
                <input name="loginId" type="text" value="${member.loginId }" readonly="readonly" />
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
                <input name="name" type="text" value="${member.name }" readonly="readonly" />
            </td>
        </tr>
    </table>
    <input type="submit" value="수정" />
    <a href="/main/home">목록</a>
</form>
<%@ include file="../part/foot.jspf"%>