<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>
<link rel="stylesheet" type="text/css" href="/css/common.css">
<style>
	.circular--portrait {
	  position: relative;
	  width: 250px;
	  height: 250px;
	  overflow: hidden;
	  border-radius: 50%;
	}
	
	.circular--portrait img {
	  width: 100%;
	  height: 100%;	  
	}
	
	.frmContainer {
		margin-top: 20px;
	}
</style>
</head>
<body>
	<div class="flexContainer flexCenter" style="flex-direction: column;">
		<div class="circular--portrait">
			<c:if test="${img eq null}">
				<img src="/img/noprofile.jpg" alt="프로필 이미지 없음">
			</c:if>
			<c:if test="${img != null}">
				<a href="/profileImgs"><img src="/img/${loginUser.i_user}/${img}" alt="프로필 이미지"></a>
			</c:if>
		</div>
		<div class="frmContainer">
			<form id="frm" action="profileDetail" method="post"
				onsubmit="return chkProfile()" enctype="multipart/form-data">
				<!-- 파일 전송시 post, multipart/form-data" 필수 -->
				<label>수정 이미지: <input type="file" name="profileImg"
					accept="image/*"></label>
				<div>
					<input type="submit" value="업로드">
				</div>
			</form>
		</div>
	</div>
	<script>
		function chkProfile() {
			if (frm.profileImg.value == '') {
				alert('이미지를 선택해 주세요')
				return false
			}
		}
	</script>
</body>
</html>