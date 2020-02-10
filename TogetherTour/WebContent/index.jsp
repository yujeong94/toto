<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여행동행사이트 투투</title>
<link rel="stylesheet" href="css/index.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
<div class="wrapper">
	<%@ include file="jsp/header.jsp" %>
	<div class="visual">
        <div class="slideshow">
            <div class="slideshow-slides">
                <a href="" class="slide" id="slide-1"><img src="images/slide-1.jpg" alt=""></a>
                <a href="" class="slide" id="slide-2"><img src="images/slide-2.jpg" alt=""></a>
                <a href="" class="slide" id="slide-3"><img src="images/slide-3.jpg" alt=""></a>
                <a href="" class="slide" id="slide-4"><img src="images/slide-4.jpg" alt=""></a>
            </div>
            <div class="slideshow-navi">
                <a href="#" class="prev">Prev</a>
                <a href="#" class="next">Next</a>
            </div>
            <div class="slideshow-indicator"></div>
        </div>
    </div>
    <div class="best-rank clear-fix">
    	<ol class="best best-trip">
    		<li><a href="">list</a></li>
    		<li><a href="">list</a></li>
    		<li><a href="">list</a></li>
    		<li><a href="">list</a></li>
    		<li><a href="">list</a></li>
    	</ol>
    	<ol class="best best-follower">
    		<li><a href="">list</a></li>
    		<li><a href="">list</a></li>
    		<li><a href="">list</a></li>
    		<li><a href="">list</a></li>
    		<li><a href="">list</a></li>
    	</ol>
    	<ol class="best best-guide">
    		<li><a href="">list</a></li>
    		<li><a href="">list</a></li>
    		<li><a href="">list</a></li>
    		<li><a href="">list</a></li>
    		<li><a href="">list</a></li>
    	</ol>
    </div>
    <div class="contents">ddddddd
	    <div class="site-info box1"></div>
	    <div class="site-info box2"></div>
	    <div class="site-info box3"></div>
    </div>
    <%@ include file="jsp/footer.jsp" %>
</div>
</body>
</html>