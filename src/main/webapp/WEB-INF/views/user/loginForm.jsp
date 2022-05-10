<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<c:import url="/WEB-INF/views/layout/header.jsp"></c:import>

	<div class="container">
		<form>
			<div class="form-group">
				<label for="userName">User Name:</label> <input type="text" class="form-control" placeholder="Enter email" id="userName">
			</div>
			<div class="form-group">
				<label for="password">Password:</label> <input type="password" class="form-control" placeholder="Enter password" id="password">
			</div>
			<div class="form-group form-check">
				<label class="form-check-label"> <input class="form-check-input" type="checkbox"> Remember me
				</label>
			</div>
		</form>
		<button id="btn-login" type="button" class="btn btn-primary">로그인</button>
	</div>
	
	<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>
	<script src="/js/user.js"></script>
</body>
</html>