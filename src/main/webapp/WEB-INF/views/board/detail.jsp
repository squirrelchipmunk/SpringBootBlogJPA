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

	<%@ include file="/WEB-INF/views/layout/header.jsp" %>
	<div class="container">
		<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
		
		<c:if test="${board.user.id == principal.user.id}">
			<a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
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
			<div>${board.content}</div>
		</div>
		<hr />
		
		<div class="card">
			<form>
				<input type="hidden" id="boardId" value="${board.id}">
				<div class="card-body">
					<textarea id="reply-content" class="form-control" rows="1"></textarea>
				</div>
				<div class="card-footer">
					<button id="btn-reply-save" class="btn btn-primary" type="button">등록</button>
				</div>
			</form>
		</div>
		
		<br />
		<div class="card">
			<div class="card-header">댓글 리스트</div>
			<ul id="reply--box" class="list-group">
			
				<c:forEach items="${board.replys}" var="reply">
					<li id="reply--1" class="list-group-item d-flex justify-content-between">
						<div> ${reply.content}</div>
						<div class="d-flex">
							<div class="font-italic">작성자 : ${reply.user.username} &nbsp;</div>
							<button class="badge">삭제</button>
						</div>
					</li>
				</c:forEach>
				
			</ul>
		</div>
		
		
	</div>
	<script src="/js/board.js"></script>
	<%@ include file="/WEB-INF/views/layout/footer.jsp" %>

</body>




</html>


