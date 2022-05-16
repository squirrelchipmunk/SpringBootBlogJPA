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
	
		<c:forEach items="${boards.content}" var="board">
			<div class="card m-2">
				<div class="card-body">
					<h4 class="card-title">${board.title}</h4>
					<a href="/board/${board.id}" class="btn btn-primary">상세보기</a>
				</div>
			</div>
		</c:forEach>

		<ul class="pagination justify-content-center"><!-- -start -center -end -->
			
			<c:if test="${not boards.first}">
				<li class="page-item"><a class="page-link" href="?page=${boards.number-1}">◀</a></li>
			</c:if>
			
			<c:if test="${not boards.last}">
				<li class="page-item"><a class="page-link" href="?page=${boards.number+1}">▶</a></li>
			</c:if>
		</ul>
	</div>

	<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>

</body>
</html>


