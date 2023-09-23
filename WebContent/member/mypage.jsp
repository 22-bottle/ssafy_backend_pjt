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
					<mark class="orange">마이페이지</mark>
				</h2>
			</div>
			<div class="col-lg-8 col-md-10 col-sm-12">
				<form action="${ root }/member" method="POST" id="form-mypage">
					<input type="hidden" name="action" value="" id="action"/>
					<div class="mb-3">
						<label for="username" class="form-label">이름 : </label> <input
							type="text" class="form-control" id="username" name="username"
							placeholder="이름..." value="${ userInfo.userName }" />
					</div>
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
						<button type="button" class="btn btn-outline-primary mb-3" id="btn-modify">정보 수정</button>
						<button type="button" class="btn btn-outline-danger mb-3" id="btn-withdraw">회원 탈퇴</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script>
      document.querySelector("#btn-modify").addEventListener("click", function () {
        if (!document.querySelector("#username").value) {
          alert("이름 입력!!");
          return;
        } else if (!document.querySelector("#userpwd").value) {
          alert("비밀번호 입력!!");
          return;
        } else if (document.querySelector("#userpwd").value != document.querySelector("#pwdcheck").value) {
          alert("비밀번호 확인!!");
          return;
        } else {
        	let action = document.querySelector("#action");
        	action.setAttribute("value", "modify");
          let form = document.querySelector("#form-mypage");
          form.setAttribute("action", "${root}/member");
          form.submit();
        }
      });
    </script>
    
    <script>
    	document.querySelector("#btn-withdraw").addEventListener("click", function() {
    		if (!document.querySelector("#userpwd").value) {
   	          alert("비밀번호 입력!!");
   	          return;
   	        } else if (document.querySelector("#userpwd").value != document.querySelector("#pwdcheck").value) {
   	          alert("비밀번호 확인!!");
   	          return;
   	        } else {
   	        	let action = document.querySelector("#action");
   	        	action.setAttribute("value", "withdraw");
   	        	let form = document.querySelector("#form-mypage");
	   	          form.setAttribute("action", "${root}/member");
	   	          form.submit();
   	        }
    	})
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