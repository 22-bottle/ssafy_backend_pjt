<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-white mt-3">
        <ul class="navbar-nav container">
            <li><a href="${ root }"><img src="assets/img/logo.png" width="120" alt=""></img></a></li>
            <li class="nav-item"> <a class="navbar-brand font-weight-bold" href="./map.html">지역별관광지</a></li>
            <li class="nav-item"> <a class="navbar-brand" href="#">나의여행계획</a></li>
            <li class="nav-item"> <a class="navbar-brand" href="#">핫플자랑하기</a></li>
            <li class="nav-item"> <a class="navbar-brand" href="#">여행정보공유</a></li>

            <!-- 로그인 전 -->
            <ul id="login" class="navbar-nav">
                <li class="nav-item"><a class="nav-link navbar-brand" href="${ root }/member?action=loginform">로그인</a></li>
                <li class="nav-item"><a class="nav-link navbar-brand" href="${ root }/member?action=join">회원가입</a></li>
            </ul>

            <!-- 로그인 후 -->
            <ul id="logout" class="navbar-nav me-2" style="display: none;">
                <li class="nav-item dropdown">
                    <button type="button" class="nav-link navbar-brand btn dropdown-toggle" data-bs-toggle="dropdown"
                        href="#">
                        마이페이지
                    </button>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="#">수정</a></li>
                        <li><a class="dropdown-item" href="#">조회</a></li>
                        <li><a class="dropdown-item" href="#">탈퇴</a></li>
                    </ul>
                </li>
                <li class="nav-item"> <button class="btn btn-outline-danger nav-link navbar-brand"
                        href="#">로그아웃</button></li>
            </ul>
        </ul>
    </nav>
</body>
</html>