<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" type = "text/css" href ="/css/common.css">
</head>
<body>
 	<div class="flexContainer flexCenter" >
 		<form class="solidForm" id="frm" action="join" method="post" onsubmit="return submitChk()">
 			<div class = "marBottom10">
 				<input type="text" name="u_id" placeholder="아이디">
 			</div>
 			<div class="marBottom10">
 				<input type="password" name="u_pw" placeholder="비밀번호">
 			</div>
 			<div class="marBottom10">
 				<input type="password" name="re_pw" placeholder="비밀번호확인">
 			</div> 			
 			<div class="marBottom10">
 				<input type="text" name="u_nickname" placeholder="닉네임">
 			</div> 			
 			<div class="marBottom10">
 				<input type="text" name="email" placeholder="이메일">
 			</div> 			
 			<div class="marBottom10">
 				<input type="text" name="ph" placeholder="휴대폰번호 ex(010-1234-1234)">
 			</div> 			
 			<div class="marBottom10">
 				<input type="text" name="addr" placeholder="주소">
 			</div> 			
 			<div class="marBottom10">
 				<label>여 <input type="radio" name="sex" value="0" checked></label>
 				<label for="radio_man">남 <input id="radio_man" type="radio" name="sex" value="1"></label>
 			</div> 	
 			<div class="marBottom10">
 				<input type="text" name="birth" placeholder="생년월일">
 			</div> 				
		 	<div class="flexContainer flexCenter" >
 				<a href="/join"><input type="submit" value="회원가입"></a>		
 			</div>
 			
 		</form>
 		
 	</div>
 	
 	<script>
 		function submitChk(){
 			
 			if(itemChk(frm.u_id)){
 				return false
 				/* return false = submit을 날리지 마라 */
 			} else if (itemChk(frm.u_pw)){
 				return false
 			} else if (frm.u_pw.value != frm.re_pw.value) {
 				msg.innerHTML = '비밀번호을 확인해 주세요'
 				return false
 			} else if (itemChk(frm.u_nickname)){
 				return false
 			} else if (itemChk(frm.email)){
 				return false
 			} else if (itemChk(frm.ph)){
 				return false
 			} else if (itemChk(frm.addr)){
 				return false
 			} else if (itemChk(frm.birth)){
 				return false
 			} else if (itemChk(frm.birth)){
 				return false
 			}
 			
 			function emailChk(){
 				var emailRegExp='/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;'
 				return !emailRegExp.exec(frm.emal.value)			
 				
 			} 			

 		}
 		
 		function itemChk(ele, pMsg) {
 			if(ele.value.length == 0) {
 				ele.focus()
 				msg.innerHTML = ele.placeholder + '을(를) 입력해주세요'
 				/* innerHTML = markup 언어로 삽입  */
 				return true
 			}
 		}
 	</script>
</body>
</html>