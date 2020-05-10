<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detail</title>
</head>
<body>
	<div>제목: ${detail.title }</div>
	<div>조회수: ${detail.hits }</div>
	<div>작성일시: ${detail.r_dt }</div>
	<div>수정일시: ${detail.m_dt }</div>
	<div>작성자: ${detail.u_nickname }</div>
	<div>내용</div>
	<div>${detail.content }</div>
	<c:if test="${loginUser.i_user == detail.i_user }">
		<div>
			<button>수정</button>
			<button onclick="clkDelBtn(${detail.i_board})">삭제</button>
		</div>
	</c:if>
	
	<script>
		function clkDelBtn(i_board) {
			var result = confirm(i_board + '번 글을 삭제하시곘습니까')
			/* console.log('i_board : ' + i_board) */
			if(result) {
				location.href = '/boardDel?i_board=' + i_board
			}
		}
	</script>
</body>
</html>