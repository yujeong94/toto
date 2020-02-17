<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header class="header">
			<div class="inner clear-fix">
				<h1 class="logo">
					<a href="<%= request.getContextPath() %>/index.jsp"><img src="<%= request.getContextPath() %>/images/logo-o.png"></a>
				</h1>
				<ul class="util">
					<li><a href="">로그인</a></li>
					<li><a href="">회원가입</a></li>
					<li><a href="">마이페이지</a></li>
				</ul>
				<br clear="both">
				<nav class="navi">
					<h2 class="blind">메인메뉴</h2>
					<ul class="main-menu clear-fix">
						<li><a href="" class="menu-item">여행일정</a>
							<ul class="sub-menu">
								<li><a href="<%= request.getContextPath() %>/list.trip">여행 리스트</a></li>
								<li><a href="<%= request.getContextPath() %>/insert.trip">일정 등록</a></li>
							</ul>
						</li>
						<li><a href="" class="menu-item">동행자 찾기</a>
							<ul class="sub-menu">
								<li><a href="<%= request.getContextPath() %>/list.buddy">동행자 리스트</a></li>
								<li><a href="<%= request.getContextPath() %>/insert.buddy">동행자 등록하기</a></li>
							</ul>
						</li>
						<li><a href="" class="menu-item">한국인 가이드 찾기</a>
							<ul class="sub-menu">
								<li><a href="<%= request.getContextPath() %>/list.guide">가이드 리스트</a></li>
                    			<li><a href="<%= request.getContextPath() %>/insert.guide">가이드 등록</a></li>
							</ul>
						</li>
						
						<li><a href="" class="menu-item">여행 후기 &amp; 팁</a>
							<ul class="sub-menu">
								<li><a href="<%=request.getContextPath()%>/list.rv"  id="review">후기 리스트</a></li>
                 				<li><a href="<%=request.getContextPath()%>/insert.rv"  id="review">일정 등록</a></li>
							</ul>
						
						</li>
						
						<li><a href="" class="menu-item">나눔</a>
							<ul class="sub-menu">
								<li><a href="<%=request.getContextPath()%>/list.sh"  id="review">나눔 리스트</a></li>
                 				<li><a href="<%=request.getContextPath()%>/insert.sh"  id="review">나눔 등록</a></li>
							</ul>						
						</li>
						
						<li><a href="" class="menu-item">Q &amp; A</a>
							<ul class="sub-menu">
								<li><a href="<%=request.getContextPath()%>/list.sh"  id="review">Q &amp; A 리스트</a></li>
                 				<li><a href="<%=request.getContextPath()%>/insert.sh"  id="review">Q &amp; A 등록</a></li>
							</ul>
						</li>
					</ul>
				</nav>
			</div>
		</header>

		<script>
			$(function() {
				/* 고정헤더 */
				$(".header").each(function() {
					var $window = $(window),
						  $header = $(this),
						  headerOffsetTop = $header.offset().top; // 헤더의 기본 위치

					// 윈도우 스크롤 이벤트를 모니터링
					$window.on('scroll', function() {
						// 윈도우 스크롤 양 확인
						// 헤더의 기본 위치를 벗어나면 class 속성값 변경
						// 기본 위치로 돌아오면 class 속성값 삭제
						if ($window.scrollTop() > headerOffsetTop) {
							$header.addClass('sticky');
						} else {
							$header.removeClass('sticky');
						}

					});

					// 윈도우 스크롤 이벤트를 강제로 발생시킴(헤더의 초기 위치 조정)
					$window.trigger('scroll');
				});

				/* 메뉴 */
				var $sub = $('.sub-menu'),
					  $menu = $('.main-menu li');
				// 모든 서브 메뉴는 숨긴다.
				$sub.hide();

				// mouseenter된 서브는 연다
				$menu.hover(function() {
						$(this).find('.sub-menu').show().css({
							height: '0%'
						}).stop().animate({
							height: '100%'
						}, 300);
						$(this).find('a:first').addClass('is-act');
					}, function() {
						$(this).find('.sub-menu').stop().animate({
							height: '0%'
						}, 300, function() {
							$(this).hide();
						});
						$(this).find('a:first').removeClass('is-act');
					});

			});

		</script>
</body>
</html>