<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Img</title>

<script src ="https://cdn.jsdelivr.net/npm/@glidejs/glide" type="text/javascript"></script>
<!-- Required Core Stylesheet -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@glidejs/glide/dist/css/glide.core.min.css">

</head>
<body>

	<div class="glide">
	  <div class="glide__track" data-glide-el="track">
	    <ul class="glide__slides">
	    	<c:forEach var="item" items="${imgList}">
	      		<li class="glide__slide">
	      			<img src="/img/${loginUser.i_user}/${item.img}">
	      		</li>
	     	</c:forEach>
	    </ul>
	  </div>
	  
	  <div class="glide__arrows" data-glide-el="controls">
	    <button class="glide__arrow glide__arrow--left" data-glide-dir="<">prev</button>
	    <button class="glide__arrow glide__arrow--right" data-glide-dir=">">next</button>
	  </div>
	  
	  <div data-glide-el="controls">
		  <button data-glide-dir="<<">Start</button>
		  <button data-glide-dir=">>">End</button>
		</div>
	</div>
	
	<script>
	  new Glide('.glide').mount()
	</script>
	
</body>
</html>