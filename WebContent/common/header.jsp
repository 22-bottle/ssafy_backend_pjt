<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" />
<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
<style>
	.navbar-brand {
        font-family: 'Arial, sans-serif';
        font-weight: bold;
    }

    .nav-link.navbar-brand {
        font-family: 'Arial, sans-serif';
    }
</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-white mt-3">
        <ul class="navbar-nav container">
            <li><a href="${ root }"><img src="assets/img/logo.png" width="120" alt=""></img></a></li>
            <li class="nav-item"> <a class="navbar-brand font-weight-bold" href="${ root }/attraction?action=map">지역별관광지</a></li>
            <li class="nav-item"> <a class="navbar-brand" href="${ root }/article?action=mvwrite">글쓰기</a></li>
            <li class="nav-item"> <a class="navbar-brand" href="${ root }/article?action=list">글목록보기</a></li>
            <li class="nav-item"> <a class="navbar-brand" href="#">여행정보공유</a></li>
            
            <c:choose>
	            <%-- session에 userInfo 객체 없는 경우(로그인 X) --%>
				<c:when test="${ empty userInfo }">
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link navbar-brand" href="${ root }/member?action=loginform">로그인</a></li>
						<li class="nav-item"><a class="nav-link navbar-brand" href="${ root }/member?action=joinform">회원가입</a></li>
					</ul>
				</c:when>
				<%-- session에 userInfo 객체 있는 경우(로그인 O) --%>
				<c:otherwise>
					<ul class="navbar-nav me-2">
						<li class="nav-item"><a class="nav-link">${ userInfo.userId }님</a></li>
						<li class="nav-item"><a class="nav-link" href="${ root }/member?action=mypage">마이페이지</a></li>
						<li class="nav-item"><a class="nav-link" href="${ root }/member?action=logout">로그아웃</a></li>
					</ul>
				</c:otherwise>
			</c:choose>
        </ul>
    </nav>
</body>
</html>