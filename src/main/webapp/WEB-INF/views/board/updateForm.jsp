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
			<input type="hidden" id="id" value="${board.id}">
			<div class="form-group">
				<input type="text" name="username" class="form-control" value="${board.title}" id="title">
			</div>
			<div class="form-group">
				<textarea class="form-control summernote" rows="5" id="content">${board.content}</textarea>
			</div>
			<button id="btn-update" type="button" class="btn btn-primary">수정 완료</button>
		</form>
	</div>
	<script>
      $('.summernote').summernote({
        tabsize: 2,
        height: 300
      });
    </script>
	<script src="/js/board.js"></script>
	<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>
	
</body>




</html>


