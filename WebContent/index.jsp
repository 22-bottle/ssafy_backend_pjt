<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>떠나요 우리 - 떠우</title>
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

    /* carousel 크기 조절 */
    #carouselExampleCaptions {
        max-width: 100%;
        /* 원하는 최대 너비로 조절 */
        margin: 0 auto;
        /* 가운데 정렬을 위한 margin 설정 */
    }

    /* 이미지 크기 조절 */
    .carousel-inner img {
        max-height: 500px;
        /* 원하는 최대 높이로 조절 */
        object-fit: cover;
        /* 이미지를 확대 또는 축소하여 채우도록 설정 */
    }
</style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-white mt-3">
        <ul class="navbar-nav container">
            <li href="#"><a href="#" onclick="javascript:mainpage();"><img src="assets/img/logo.png" width="120"
                        alt=""></img></a></li>
            <li class="nav-item"> <a class="navbar-brand font-weight-bold" href="./map.html">지역별관광지</a></li>
            <li class="nav-item"> <a class="navbar-brand" href="#">나의여행계획</a></li>
            <li class="nav-item"> <a class="navbar-brand" href="#">핫플자랑하기</a></li>
            <li class="nav-item"> <a class="navbar-brand" href="#">여행정보공유</a></li>

            <!-- 로그인 전 -->
            <ul id="login" class="navbar-nav">
                <li class="nav-item"> <button class="nav-link navbar-brand" href="#" data-bs-toggle="modal"
                        data-bs-target="#loginModal">로그인</button></li>
                <li class="nav-item"> <button class="nav-link navbar-brand" href="#" data-bs-toggle="modal"
                        data-bs-target="#pollModal">회원가입</button></li>
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

    <div id="carouselExampleCaptions" class="carousel slide mt-3" data-bs-ride="carousel">
        <div class="carousel-indicators">
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active"
                aria-current="true" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1"
                aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2"
                aria-label="Slide 3"></button>
        </div>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="assets/img/japan.jpg" class="d-block w-100" alt="...">
                <div class="carousel-caption d-none d-md-block">
                    <h1>관광지 정보를 알아보고 떠나요 우리</h1>
                </div>
            </div>
            <div class="carousel-item">
                <img src="assets/img/jeju.jpg" class="d-block w-100" alt="...">
                <div class="carousel-caption d-none d-md-block">
                    <h1>관광지 정보를 알아보고 떠나요 우리</h1>
                </div>
            </div>
            <div class="carousel-item">
                <img src="assets/img/china.jpg" class="d-block w-100" alt="...">
                <div class="carousel-caption d-none d-md-block">
                    <h1>관광지 정보를 알아보고 떠나요 우리</h1>
                </div>
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions"
            data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions"
            data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>

    <div class="container mt-5">
        <div class="row justify-content-center text-center">
            <div class="col-md-12">
                <h1>나만의 여행 계획</h1>
            </div>
            <div class="col-md-12 mt-4">
                <h5>여행 경로, 숙박, 광광지, 예상금액등 나만의 멋진 계획을 세워 공유해 주세요!!!</h5>
            </div>
        </div>
    </div>

    <div class="row justify-content-center mt-3" id="img">
        <div class="col-md-2"></div>
        <div class="col-md-2 mb-4">
            <img src="assets/img/img1.png" alt="" class="img-fluid">
        </div>
        <div class="col-md-2 mb-4">
            <img src="assets/img/img2.png" alt="" class="img-fluid">
        </div>
        <div class="col-md-2 mb-4">
            <img src="assets/img/img3.png" alt="" class="img-fluid">
        </div>
        <div class="col-md-2 mb-4">
            <img src="assets/img/img4.png" alt="" class="img-fluid">
        </div>
        <div class="col-md-2"></div>
    </div>

    <footer class="navbar navbar-expand-md justify-content-end">
        <div class="row">
            <div class="col-md-12">
                <ul class="navbar-nav">
                    <li><a class="nav-link text-secondary" href="#">개인정보처리방침</a></li>
                    <li><a class="nav-link text-secondary" href="#">이용약관</a></li>
                    <li><label class="nav-link text-secondary">&copy; SSAFY Corp.</label></li>
                </ul>
            </div>
        </div>
    </footer>
    <!-- 하단 Footer End  -->
</body>
</html>