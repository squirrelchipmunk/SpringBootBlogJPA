<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

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
		<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
		<button id="btn-update" class="btn btn-warning">수정</button>
		<c:if test="${board.user.id == principal.user.id}">
			<button id="btn-delete" class="btn btn-danger">삭제</button>
		</c:if>			
		<br><br>
		<div>
			글 번호 : <span id="id"><i>${board.id} </i></span>
			작성자 : <span><i>${board.user.username} </i></span>
		</div>
		<br>
		<div>
			<h3>${board.title}</h3>
		</div>
		<hr />
		<div>
			<div>${board.title}</div>
		</div>
		<hr />
	</div>
	<script src="/js/board.js"></script>
	<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>

</body>




</html>


