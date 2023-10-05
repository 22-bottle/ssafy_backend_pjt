<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
</head>
<body>
<%@ include file="/common/header.jsp"%>
	
	<%-- 페이지만의 내용 --%>
	<div class="container p-4">
		<h2>글쓰기</h2>
		<form action="${root}/board?action=writeform" method="post" action="">

			<div class="form-group">
				<label for="id">제목:</label> <input type="text" class="form-control"
				id="subject" name="subject" placeholder="제목을 입력하세요">
			</div>
			
			<div class="mb-10">
			  <label for="content" class="form-label">내용:</label>
			  <textarea class="form-control" id="content" rows="10"></textarea>
			</div>
			
			
			<button type="submit"  id="btn-register" name="btn-register" class="btn btn-primary">등록하기</button>
			<script>
		      document.querySelector("#btn-register").addEventListener("click", function () {
		        if (!document.querySelector("#subject").value) {
		          alert("제목 입력!!");
		          return;
		        } else if (!document.querySelector("#content").value) {
		          alert("내용 입력!!");
		          return;
		        } else {
		          let form = document.querySelector("#btn-register");
		          form.setAttribute("action", "${root}/board");
		          form.submit();
		        }
		      });
    </script>
		</form>
	</div>

<%@ include file="/common/footer.jsp" %>
</body>
</html>