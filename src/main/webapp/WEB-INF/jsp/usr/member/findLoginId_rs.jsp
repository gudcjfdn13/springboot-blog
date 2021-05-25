<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageName" value="Login" />
<%@ include file="../part/head.jspf"%>

<h1>회원님의 아이디는 ${loginId } 입니다</h1>
<a href="/member/login">로그인</a>
    <a href="/main/home">목록</a>
    <a href="/member/showFindLoginId">아이디 찾기</a>
    <a href="/member/showFindLoginPw">비밀번호 찾기</a>
<%@ include file="../part/foot.jspf"%>