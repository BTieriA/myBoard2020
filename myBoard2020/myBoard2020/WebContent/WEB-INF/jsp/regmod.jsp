<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
</head>
<body>
	<form class="solidForm" id="frm" action="/regmod" method="post">
		<div class = "marBottom10">
			<input type="text" name="title" placeholder="제목">
		</div>
		<div class = "marBottom10">
			<textarea name="content" cols="50" rows="10"></textarea>
		</div>
		<div class = "marBottom10">
			<input type="submit" value="글쓰기">
		</div>
	</form>
</body>
</html>