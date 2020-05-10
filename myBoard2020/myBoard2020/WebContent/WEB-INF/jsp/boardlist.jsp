<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board List</title>
<style>

	img {
		width: 20px;
	}
	table { 
		border: 1px solid #000;
		border-collapse: collapse;
		width: 80%;
	}
	a {
		text-decoration: none;		
	}
	th, td { border: 1px solid #000; }
	.fontCenter { text-align: center; }
	.pointer { 	cursor: pointer; }
	.trSelected:hover { background-color: #ecf0f1 }
	
	#searchContainer {
		margin-top: 40px;
		display: flex;
		justify-content: center;
	
	}
	
	#pageContainer {
		margin-top: 40px;
		display: flex;
		justify-content: center;
	}
	#pageContainer span {
		color: #3498db;
	}
	#pageContainer a:not(:last-child) {
		margin-right: 30px;
	}
	.selected {
		font-weight: bold;
		color: red !important;
	}
	
</style>
</head>
<body>
	${ loginUser.i_user }, ${ loginUser.u_nickname } 님 환영합니다.
	<!-- session에 저장된 변수랑 같아야한다(loginUser), getAttribute랑 같다 -->
	
	<a href="/regmod"><button>글쓰기</button></a>
	<a href="/profileDetail">
		<button>프로필</button>
	</a> 
	
	<div>
		<table>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>조회수</th>
				<th>등록일</th>
				<th>사진</th>
				<th>사용자</th>
			</tr>
			
				<c:forEach var="vo" items="${list}">
				<tr class="pointer trSelected" onclick="moveToDetail(${vo.i_board})">
					<td class="fontCenter">${vo.i_board}</td>
					<td>${vo.title}</td>
					<td class="fontCenter">${vo.hits}</td>
					<td class="fontCenter">${vo.r_dt}</td>
					<td class="fontCenter">
						<c:if test="${vo.img == ''}">
							<img src="/img/noprofile.jpg">
						</c:if>
						<c:if test="${vo.img != ''}">
							<img src="/img/${vo.i_user }/${vo.img }">
						</c:if>
					</td>
					<td class="fontCenter">${vo.u_nickname}</td>
				</tr>
				</c:forEach>
		</table>
		
		<div id="searchContainer">
			<form action="/boardList" method="get">
				<div>
					검색 <input type="search" name="search" value="${param.search }">
					<!--  getParameter에 저장 되어있으므로 서블릿에서 Attribute하지 않고 사용 가능-->
					<input type="submit" value="검색">
				</div>				
			</form>
		</div>
		
		<div id="pageContainer">
			<c:forEach var="i" begin="1" end="${totalPageCnt }">				
				<a href="/boardList?page=${i}&search=${param.search }">
				<span <c:if test="${i == page}">class="selected"</c:if>>				
				${i}
				</span>
				</a>
			</c:forEach>			
		</div>
	</div>
	
	<script>
	function moveToDetail(i_board) {
		location.href = '/boardDetail?i_board=' + i_board 
	}
	</script>
		
</body>
</html>