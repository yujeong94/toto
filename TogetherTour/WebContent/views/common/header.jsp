<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member"%>
<% 
	Member loginUser = (Member)session.getAttribute("loginUser");
	
	// script에서 사용할 mKind 값
	int mKind = 0;
	
	// 로그인 하면 정보 보여줄 String mKind 값
	String Strkind = null;
	
	if(loginUser != null){
		
		mKind = loginUser.getmKind();
		
		if(loginUser.getmKind() == 1){
			Strkind = "일반";
		} else {
			Strkind = "가이드";
		}
	}
	
	String welcome = (String)request.getAttribute("welcome");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
	<header class="header">
			<div class="inner clear-fix">
				<h1 class="logo">
					<a href="<%= request.getContextPath() %>/index.jsp"><img src="<%= request.getContextPath() %>/images/logo-o.png"></a>
				</h1>
				<ul class="util">
					<% if(loginUser == null) { %>
						<li><a href="javascript:void(0)" onclick="login();">로그인</a></li>
						<li><a href="<%= request.getContextPath() %>/views/member/joinForm.jsp">회원가입</a></li>
					<% } else { %> 
						<li><span><%= Strkind %>회원 <%= loginUser.getNickName() %>님</span></li>
						<li><a href="javascript:void(0)" onclick="logout();">로그아웃</a></li>
						<li><a href="<%= request.getContextPath() %>/views/myPage/MyPageContentView.jsp">마이페이지</a></li> 
					<% } %>
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
                    			<li><a href="javascript:void(0)" onclick="guideCheck();">가이드 등록</a></li> <!-- 유정수정  -->
								<%-- <li><a href="<%= request.getContextPath() %>/views/guide/GuideWrite.jsp">가이드 등록</a></li> --%>
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
								<li><a href="<%=request.getContextPath()%>/list.share"  id="review">나눔 리스트</a></li>
                 				<li><a href="<%=request.getContextPath()%>/insert.share"  id="review">나눔 등록</a></li>
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
			
			// 유정: 주석함
			<%-- function login(){
				window.open('<%= request.getContextPath() %>/login.me', 'loginForm', 'width=500, height=300');
			} --%>
			// 유정
			
			// 유정: 로그인페이지 연결
			function login(){
				location.href='<%= request.getContextPath() %>/views/member/loginView.jsp';
			};
			// 유정
			
			// 로그인 시 알림창 
			var welcome = "<%= welcome %>";
			
			if(welcome != "null"){
				alert(welcome);
			}
			
			// 유정: 로그아웃
			function logout() {
				location.href='<%= request.getContextPath() %>/logout.me';
			}
			//유정
			
			// 유정 : 가이드만 등록하기 할 수 있도록
			function guideCheck(){
				console.log(<%= mKind %>);
				if('<%= loginUser %>' != 'null'){
					if(<%= mKind %> == 2) { 
						location.href="<%= request.getContextPath() %>/views/guide/GuideWrite.jsp";
					} else { 
						alert("가이드회원만 등록할 수 있습니다.");
					}
				} else {
					alert("로그인 후 이용하실 수 있습니다.");
				}
			}; 
			// 유정
			
		</script>
</body>
</html>