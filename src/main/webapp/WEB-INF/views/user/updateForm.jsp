<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	
	<%@ include file="/WEB-INF/views/layout/header.jsp" %> <!--  c:import와 차이점 : 정적, 참조하는 파일 합쳐서 컴파일, 파라미터 x -->

	<div class="container">
		<form>
			<input type="hidden" id ="id" value="${principal.user.id}">
			<div class="form-group">
				<label for="username">User Name:</label> <input type="text" value="${principal.user.username}" class="form-control" placeholder="Enter username" id="username" readOnly>
			</div>
			<c:if test="${empty principal.user.oauth}">
				<div class="form-group">
					<label for="password">Password:</label> <input type="password" class="form-control" placeholder="Enter password" id="password">
				</div>
			</c:if>
			<div class="form-group">
				<label for="email">Email address:</label> <input type="email" value="${principal.user.email}" class="form-control" placeholder="Enter email" id="email" readOnly>
			</div> 
		</form>
		
		<c:if test="${empty principal.user.oauth}">
			<button id="btn-update" type="button" class="btn btn-primary">회원 수정 완료</button>
		</c:if>
		
	</div>
	
	<%@ include file="/WEB-INF/views/layout/footer.jsp" %>
	<script src="/js/user.js"></script>
</body>
</html>