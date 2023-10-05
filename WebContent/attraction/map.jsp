<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
					<input type="hidden" name="action" value="list"> <select
						id="search-area" class="form-select me-2" name="areaCode">
						<option value="0" selected>검색 할 지역 선택</option>
					</select> <select id="search-content-id" class="form-select me-2"
						name="contentTypeId">
						<option value="0" selected>관광지 유형</option>
						<option value="12">관광지</option>
						<option value="14">문화시설</option>
						<option value="15">축제공연행사</option>
						<option value="25">여행코스</option>
						<option value="28">레포츠</option>
						<option value="32">숙박</option>
						<option value="38">쇼핑</option>
						<option value="39">음식점</option>
					</select> <input id="search-keyword" class="form-control me-2" type="search"
						placeholder="검색어" aria-label="검색어" name="keyword" />
					<button id="btn-search" class="btn btn-outline-success"
						type="button">검색</button>
				</form>
				<div id="map" style="width: 1500px; height: 800px;"></div>
				<script
					src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
					integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
					crossorigin="anonymous"></script>
				<script type="text/javascript"
					src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a60a747c3ce60979f91fdeb2cb8081a7&libraries=services,clusterer,drawing"></script>
				<script>
		            // index page 로딩 후 전국의 시도 설정.
		            var serviceKey = "Vy5tpdZA4XGpjOZ2EdoYW6D33KzLN9NnVuTDRUEJmYFWO5D3Cs6djsqsCQ%2Fewb3744vH5GXZgULh9enBGNr67A%3D%3D"
		            let areaUrl =
		                "https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=" +
		                serviceKey +
		                "&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json";
		
		            fetch(areaUrl, { method: "GET" })
		                .then((response) => response.json())
		                .then((data) => makeOption(data));
		
		            function makeOption(data) {
		                let areas = data.response.body.items.item;
		                let sel = document.getElementById("search-area");
		                areas.forEach((area) => {
		                    let opt = document.createElement("option");
		                    opt.setAttribute("value", area.code);
		                    opt.appendChild(document.createTextNode(area.name));
		                    sel.appendChild(opt);
		                });
		            }
		            
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
		            	let searchArea = document.getElementById("search-area");
		            	let areaCode = searchArea.value;
		            	let searchContentId = document.getElementById("search-content-id");
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