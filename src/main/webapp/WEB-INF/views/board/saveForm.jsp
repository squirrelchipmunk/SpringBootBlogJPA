<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
</head>
<body>

	<c:import url="/WEB-INF/views/layout/header.jsp"></c:import>

	<div class="container">
		<form action="" method="post">
			<div class="form-group">
				<label for="title">Title :</label> <input type="text" name="username" class="form-control" placeholder="Enter title" id="title">
			</div>
			<div class="form-group">
				<label for="content">Content:</label>
				<textarea class="form-control summernote" rows="5" id="content"></textarea>
			</div>
			<button id="btn-save" type="submit" class="btn btn-primary">글쓰기 완료</button>
		</form>
	</div>
	<script>
      $('#summernote').summernote({
        placeholder: 'Hello Bootstrap 4',
        tabsize: 2,
        height: 100
      });
    </script>
	<script src="/js/board.js"></script>
	<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>
	
</body>




</html>


