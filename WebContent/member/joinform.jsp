<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>SSAFY</title>
</head>
<body>
	<%@ include file="/common/header.jsp"%>

	<div class="container">
		<div class="row justify-content-center">
			<div class="col-lg-8 col-md-10 col-sm-12">
				<h2 class="my-3 py-3 shadow-sm bg-light text-center">
					<mark class="orange">회원가입</mark>
				</h2>
			</div>
			<div class="col-lg-8 col-md-10 col-sm-12">
				<form action="${ root }/member" method="POST" id="form-join">
					<input type="hidden" name="action" value="join" />
					<div class="mb-3">
						<label for="username" class="form-label">이름 : </label> <input
							type="text" class="form-control" id="username" name="username"
							placeholder="이름..." />
					</div>
					<div class="mb-3">
						<label for="userid" class="form-label">아이디 : </label> <input
							type="text" class="form-control" id="userid" name="userid"
							placeholder="아이디..." />
					</div>
					<div id="result-view" class="mb-3"></div>
					<div class="mb-3">
						<label for="userpwd" class="form-label">비밀번호 : </label> <input
							type="password" class="form-control" id="userpwd" name="userpwd"
							placeholder="비밀번호..." />
					</div>
					<div class="mb-3">
						<label for="pwdcheck" class="form-label">비밀번호확인 : </label> <input
							type="password" class="form-control" id="pwdcheck"
							placeholder="비밀번호확인..." />
					</div>
					<div id="result-view2" class="mb-3"></div>
					<div class="col-auto text-center">
						<button type="button" id="btn-join" class="btn btn-outline-primary mb-3">회원가입</button>
						<button type="button" class="btn btn-outline-success mb-3" id="initBtn">초기화</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script>
      let isUseId = false;
      let input = document.querySelector("#userid");
      let resultDiv = document.querySelector("#result-view");
      input.addEventListener("keyup", function () {
        let checkid = input.value;
        let len = checkid.length;
        if (len < 4 || len > 16) {
          isUseId = false;
          resultDiv.setAttribute("class", "mb-3 fw-bold text-dark");
          resultDiv.innerHTML = "아이디는 4자이상 16자이하입니다.";
        } else {
          let url = "${root}/member?action=idcheck&checkid=" + checkid;
          // CSV
          fetch(url)
            .then((response) => response.text())
            .then((data) => resultViewText(data));

          // JSON
          //fetch(url)
          //  .then((response) => response.json())
          //  .then((data) => resultViewJSON(data));
        }
      });

      function resultViewText(data) {
        let val = data.split(",");
        let id = val[0];
        let cnt = val[1];
        if (cnt == 0) {
          isUseId = true;
          resultDiv.setAttribute("class", "mb-3 text-success");
          resultDiv.innerHTML = "<span class='fw-bold'>" + id + "</span>은 사용할 수 있습니다.";
        } else {
          isUseId = false;
          resultDiv.setAttribute("class", "mb-3 text-danger");
          resultDiv.innerHTML = "<span class='fw-bold'>" + id + "</span>은 사용할 수 없습니다.";
        }
      }

      function resultViewJSON(data) {
        if (data.cnt == 0) {
          isUseId = true;
          resultDiv.setAttribute("class", "mb-3 text-primary");
          resultDiv.innerHTML =
            "<span class='fw-bold'>" + data.checkid + "</span>은 사용할 수 있습니다.";
        } else {
          isUseId = false;
          resultDiv.setAttribute("class", "mb-3 text-warning");
          resultDiv.innerHTML =
            "<span class='fw-bold'>" + data.checkid + "</span>은 사용할 수 없습니다.";
        }
      }

      document.querySelector("#btn-join").addEventListener("click", function () {
        if (!document.querySelector("#username").value) {
          alert("이름 입력!!");
          return;
        } else if (!document.querySelector("#userid").value) {
          alert("아이디 입력!!");
          return;
        } else if (!isUseId) {
          alert("아이디 중복 확인!!");
          return;
        } else if (!document.querySelector("#userpwd").value) {
          alert("비밀번호 입력!!");
          return;
        } else if (document.querySelector("#userpwd").value != document.querySelector("#pwdcheck").value) {
          alert("비밀번호 확인!!");
          return;
        } else {
          let form = document.querySelector("#form-join");
          form.setAttribute("action", "${root}/member");
          form.submit();
        }
      });
    </script>

    <script>
    	let resultDiv2 = document.querySelector("#result-view2");
    	let pwdcheck = document.querySelector("#pwdcheck");
    	pwdcheck.addEventListener("keyup", function() {
	    	let userpwd = document.querySelector("#userpwd");
    		if (userpwd.value == pwdcheck.value) {
    			resultDiv2.setAttribute("class", "mb-3 text-success");
    	        resultDiv2.innerHTML = "<span class='fw-bold'></span>사용할 수 있습니다.";
    		} else {
    			resultDiv2.setAttribute("class", "mb-3 text-danger");
    			resultDiv2.innerHTML = "<span class='fw-bold'>사용할 수 없습니다.";
    		}
    	});
    </script>
    
    <script>
    	let initBtn = document.querySelector("#initBtn");
    	initBtn.addEventListener("click", function() {
    		let username = document.querySelector("#username");
    		let userid = document.querySelector("#userid");
    		let userpwd = document.querySelector("#userpwd");
    		let pwdcheck = document.querySelector("#pwdcheck");
    		username.value = "";
    		userid.value = "";
    		userpwd.value = "";
    		pwdcheck.value = "";
    		resultDiv.innerHTML = "";
    		resultDiv2.innerHTML = "";
    	});
    </script>

	<%@ include file="/common/footer.jsp"%>
</body>
</html>
