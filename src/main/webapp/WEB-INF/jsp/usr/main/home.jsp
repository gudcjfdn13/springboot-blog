<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageName" value="Home" />
<%@ include file="../part/head.jspf"%>
    <h1>Blog Home!!!</h1>
    <a href="/article-notice/list">공지사항</a>
    <a href="/article-free/list">자유게시판</a>
    
<%@ include file="../part/foot.jspf"%>