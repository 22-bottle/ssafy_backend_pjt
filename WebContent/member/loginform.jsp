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
	
	<%-- 페이지만의 내용 --%>
	<div class="container p-4">
		<h2>로그인</h2>
		<form action="${ root }/member?action=login" method="post">
			<input type="hidden" name="action" value="login">
			<div class="form-group">
				<label for="id">id:</label> <input type="text" class="form-control"
					id="id" name="id" placeholder="ID 입력"
					<c:if test="${ not empty cookie.user_id }"> value="${ cookie.user_id.value }"</c:if>>
			</div>
			<div class="form-group">
				<label for="pw">Password:</label> <input type="password"
					class="form-control" id="pw" name="pw" placeholder="비밀번호 입력">
			</div>
			<div class="form-group form-check">
				<label class="form-check-label"> 
					<input class="form-check-input" type="checkbox" name="remember" <c:if test="${ !empty cookie.user_id }"> checked="checked"</c:if>>
					아이디 기억하기
				</label>
			</div>
			<div class="text-danger mb-2">${msg}</div>
			<button type="submit" class="btn btn-primary">로그인</button>
			<a class="btn btn-secondary" href="${root}">메인 페이지로</a>
		</form>
	</div>

<%@ include file="/common/footer.jsp" %>
</body>
</html>