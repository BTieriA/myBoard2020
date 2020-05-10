<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" type = "text/css" href ="/css/common.css">
</head>
<body>

<div class="flexContainer flexCenter" style="flex-direction:column;">
 		<form class="solidForm" action="login" method="post">
 			<div class = "marBottom10">
 				<input type="text" name="u_id" placeholder="아이디" value ="Yubi">
 			</div>
 			<div class="marBottom10">
 				<input type="password" name="u_pw" placeholder="비밀번호" value="1111">
 			</div>
		 	<div class="flexContainer flexCenter" style="height:40px;">
 				<input type="submit" value="로그인">&nbsp;&nbsp;
 				<a href="/join">회원가입</a>		
 			</div>
  		</form>
 		<div style="color:red;">
 			${msg}
 		</div>
 	</div>
>
 	
  <!-- 뉴모피즘  -->
<!--  <form> 
  <div class="segment">
    <h1>Sign up</h1>
  </div>
  
  <label>
    <input type="text" placeholder="Email Address"/>
  </label>
  <label>
    <input type="password" placeholder="Password"/>
  </label>
  <button class="red" type="button"><i class="icon ion-md-lock"></i> Log in</button>
  
  <div class="segment">
    <button class="unit" type="button"><i class="icon ion-md-arrow-back"></i></button>
    <button class="unit" type="button"><i class="icon ion-md-bookmark"></i></button>
    <button class="unit" type="button"><i class="icon ion-md-settings"></i></button>
  </div>
  
  <div class="input-group">
    <label>
      <input type="text" placeholder="Email Address"/>
    </label>
    <button class="unit" type="button"><i class="icon ion-md-search"></i></button>
  </div>
  
</form> -->
<!-- 뉴모피즘 끝 -->
</body>
</html>