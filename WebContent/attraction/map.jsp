<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>떠나요 우리 - 떠우</title>
</head>

<body>
	<%@ include file="/common/header.jsp"%>
	<!-- 중앙 content start -->
	<div class="container">
		<div style="height: 70px"></div>
		<div class="row">
			<!-- 중앙 left content  start -->
			<div class="">
				<div class="alert alert-primary mt-3 text-center fw-bold"
					role="alert">전국 관광지 정보</div>
					
				<!-- 관광지 검색 start -->
				<form class="d-flex my-3">
					<select name="sido" id="sido" class="dropdown-toggle ms-3 me-3" required >
				    	<option value="" selected disabled hidden>시도선택</option>
				      	<c:forEach items="${sidoList}" var="sidoItem">
				        	<option value="${sidoItem.sido_code}">${sidoItem.sido_name}</option>
				      	</c:forEach>
				    </select>
				    <form class="d-flex my-3">
					<select name="gugun" id="gugun" class="dropdown-toggle ms-3 me-3" required>
						<option value="" selected disabled hidden>구군선택</option>
					</select>
					<input id="search-keyword" class="form-control me-3" type="search"
						placeholder="검색어" aria-label="검색어" name="keyword" />
					<button id="btn-search" class="btn btn-outline-success"
						type="button" >검색</button>
				</form>
				<div id="map" style="width: 1300px; height: 800px;"></div>
				
				<script type="text/javascript">
				    document.querySelector("#sido").addEventListener("change", function(e) {
						let code = e.target.value;
						fetch("${root}/attraction?action=getGugun&code="+code)
							.then(function(res){return res.json()})
							.then(function(data){
								const gugunSelect = document.querySelector("#gugun");
				            	gugunSelect.innerHTML = '<option value="" selected disabled hidden>구군선택</option>';
								data.forEach(function(item) {
									const option = document.createElement("option");
				                	option.value = item.gugun_code;
				                	option.textContent = item.gugun_name;
				                	gugunSelect.appendChild(option);
						      	});
							})
						});
				</script>
				
				<script
					src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
					integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
					crossorigin="anonymous"></script>
				<script type="text/javascript"
					src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a60a747c3ce60979f91fdeb2cb8081a7&libraries=services,clusterer,drawing"></script>
				<script>
					var positions; // marker 배열.
		            var markers = [];
		            function makeList(data) {
		            	for (let i = 0; i < markers.length; i++) {
		            		markers[i].setMap(null);
		            	}
		            	markers = [];
		                positions = [];
		                for (let i = 0; i < Object.keys(data).length; i++) {
		                	let area = Object.values(data)[i];
		                	let markerInfo = {
			                        title: area.title,
			                        latlng: new kakao.maps.LatLng(area.latitude, area.longtitude),
			                    };
			                    positions.push(markerInfo);
		                }
		                displayMarker();
		            }
		            
		            var mapContainer = document.getElementById("map"), // 지도를 표시할 div
	                mapOption = {
	                    center: new kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
	                    level: 5, // 지도의 확대 레벨
	                };

		            // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
		            var map = new kakao.maps.Map(mapContainer, mapOption);
		            
		            function displayMarker() {
		                // 마커 이미지의 이미지 주소입니다
		                var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

		                for (var i = 0; i < positions.length; i++) {
		                    // 마커 이미지의 이미지 크기 입니다
		                    var imageSize = new kakao.maps.Size(24, 35);

		                    // 마커 이미지를 생성합니다
		                    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

		                    // 마커를 생성합니다
		                    let marker = new kakao.maps.Marker({
		                        map: map, // 마커를 표시할 지도
		                        position: positions[i].latlng, // 마커를 표시할 위치
		                        title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
		                        image: markerImage, // 마커 이미지
		                    });
		                    markers.push(marker);
		                }

		                // 첫번째 검색 정보를 이용하여 지도 중심을 이동 시킵니다
		                map.setCenter(positions[0].latlng);
		            }
		            
		            function moveCenter(lat, lng) {
		                map.setCenter(new kakao.maps.LatLng(lat, lng));
		            }
		            
		            let searchBtn = document.getElementById("btn-search");
		            searchBtn.addEventListener("click", () => {
		            	let searchArea = document.getElementById("sido");
		            	let areaCode = searchArea.value;
		            	let searchContentId = document.getElementById("gugun");
		            	let contentTypeId = searchContentId.value;
		            	let searchKeyword = document.getElementById("search-keyword");
		            	let keyword = searchKeyword.value;
			            let url = "${root}/attraction?action=list&areaCode=" + areaCode + "&contentTypeId=" + contentTypeId + "&keyword=" + keyword;
		            	fetch(url)
		            		.then((response) => response.json())
		            		.then((data) => makeList(data))
		            });
	            </script>
				<!-- 관광지 검색 end -->
			</div>
		</div>
		<!-- 중앙 content end -->
		<%@ include file="/common/footer.jsp"%>
</body>

</html>