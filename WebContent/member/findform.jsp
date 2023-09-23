<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="/common/header.jsp"%>

	<div class="container p-4">
		<h2>비밀번호 찾기</h2>
		<form action="${ root }/member?action=findpwd" method="post">
			<div class="form-group">
				<label for="id">id:</label> <input type="text" class="form-control"
					id="id" name="id" placeholder="ID 입력">
			</div>
			<div class="text-danger mb-2">${msg}</div>
			<button type="submit" class="btn btn-primary">비밀번호 찾기</button>
		</form>
	</div>

<%@ include file="/common/footer.jsp" %>
</body>
</html>