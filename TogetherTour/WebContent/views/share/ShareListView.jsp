<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, share.model.vo.*" %>
<%
	ArrayList<Share> sList = (ArrayList<Share>)request.getAttribute("sList") ;
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>나눔 게시판 목록보기</title>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/index.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/common_sub.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/share/share_board.css">
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
	<div id="wrapper">
		<%@ include file="../common/header.jsp" %>
		<div class="contents">
			<h2><span>나눔 게시판</span></h2>
			<p style="margin-top:-70px; margin-bottom:30px; font-size:1.4rem; font-weight:normal; text-align:center;">여행하면서 서로 남은 물건들을 공유할 수 있는 곳입니다.</p>
			<hr>
			<form id="schForm">
				<div class="search">
					<select id="schKind">
						<option value="none">검색 옵션 선택</option>
						<option value="category">물품 종류</option>
						<option value="title">게시물 이름</option>
						<option value="nick">작성자 닉네임</option>
						<option value="state">거래 상태</option>
					</select>&nbsp;&nbsp;
					<input type="text" id="defaultSch" class="schElement" placeholder="물품 종류를 선택하세요." disabled>
					<select id="category" class="schElement" style="display:none;">
						<option value="none">물품 종류 선택</option>
						<option value="USIM">유심(USIM)</option>
						<option value="traffic">교통권</option>
						<option value="ticket">티켓(ticket)</option>
						<option value="necess">생필품(necessity)</option>
						<option value="money">돈(money)</option>
						<option value="etc">기타(..etc)</option>
					</select>
					<input type="text" placeholder="게시물 이름 입력" id="title" class="schElement" style="display:none;">
					<input type="text" placeholder="작성자 닉네임 입력" id="nick" class="schElement" style="display:none;">
					<select id="state" class="schElement" style="display:none;">
						<option value="none">거래 상태 설정</option>
						<option value="possible">거래 가능</option>
						<option value="complete">거래 완료</option>
						<option value="progress">진행중</option>
					</select>&nbsp;&nbsp;
					<input type="submit" value="검색하기" id="button" class="sch">
				</div>
				<hr><br>
				<table id="shareContent" class="listArea">
					<tr><th width="7.5%">번호</th><th width="12.5%">나눔카테고리</th><th width="20%">게시글명</th><th width="20%">작성자</th><th width="10%">작성일</th><th width="15%">거래지</th><th width="7%">조회수</th><th width="8%">거래 상태</th></tr>
					<% if(sList.size() == 0) { %>
						<tr>
							<td colspan="8" style="text-align:center;">
								게시글이 없습니다.
							</td>
						</tr>
					<% } else { %>
						<% for(int i=0; i<sList.size(); i++) {
							Share share = sList.get(i) ; %>
							<tr>
								<td><input type="hidden" value="<%= share.getSbNum() %>" id="sbNum"><%= share.getSbNum() %></td>
								<td><input type="hidden" value="<%= share.getCategory() %>"><b><%= share.getCategory() %></b></td>
								<td class="detailBtn"><input type="hidden" value="<%= share.getTitle() %>"><%= share.getTitle()	%></td>
								<td class="profileBtn"><input type="hidden" value="<%= share.getWriter() %>"><%= share.getWriter()	%></td>
								<td><input type="hidden" value="<%= share.getDate() %>"><%= share.getDate() %></td>
								<td>
									<input type="hidden" value="<%= share.getKind() %>">
									<input type="hidden" value="<%= share.getCountry() %>">
									
									<% if(share.getKind() == 1) { %>
										국내 / <%= share.getCity() %>
									<% } else { %>
										해외 / <%= share.getCountry() %> / <%= share.getCity() %>
									<% } %>
								</td>
								<td><input type="hidden" value="<%= share.getsCount()	%>"><%= share.getsCount()	%></td>
								<td><input type="hidden" value="<%= share.getStatus()	%>"><%= share.getStName()	%></td>
							</tr>
						<% } %>
					<% } %>
				</table>
			</form><br><hr><br>
			<div style="text-align:center;">
				<% if(loginUser != null) { %>
					<input type="button" onclick="location.href='<%=request.getContextPath()%>/views/share/ShareInsertView.jsp'" id="button" class="write" value="작성하기">
				<% } else { %>
					<input type="button" onclick="needsLogin();" id="button" class="write" value="작성하기">
				<% } %>
			</div>
		</div>
		<%@ include file="../common/footer.jsp" %>
		<script>
			function needsLogin() {
				alert('로그인 후에 이용하실 수 있습니다.') ;
				location.href="<%=request.getContextPath()%>/views/member/loginView.jsp" ;
			}
			
			$(function() {
				<% if(!sList.isEmpty()){ %>
					$('.listArea td').mouseenter(function() {
						$(this).parent().css('background','#D8D8D8') ;
					}).mouseout(function() {
						$(this).parent().css('background','none') ;
					}) ;
					
					$('.detailBtn').mouseenter(function() {
						$(this).parent().css('cursor','pointer') ;
						$('.detailBtn').css('text-weight','bold') ;
					}).mouseout(function() {
						$(this).parent().css('cursor','normal') ;
						$('.detailBtn').css('text-weight','normal') ;
					}).click(function() {
						var sbNum = $(this).parent().children().find('#sbNum').val() ;
						$(this).parent().css('cursor','pointer') ;
						// 로그인 한 사람만 상세보기 이용할 수 있게하기
						if('<%=loginUser%>'!='null') {
							location.href="<%= request.getContextPath() %>/detail.share?sbNum="+sbNum ;
						} else {
							alert('회원만 이용할 수 있는 서비스입니다.') ;
							location.href="<%= request.getContextPath() %>/views/member/loginView.jsp" ;
						}
					}) ;
					
					$('.profileBtn').click(function(){
						var userNick = $(this).text();
						window.open('views/myPage/memberProfile.jsp?userNick='+userNick,'profileForm','width=500, height=700');
					}) ;
				<% } %>
			}) ;
			
			$(function() {
				$('#schKind').change(function() {
					var schKind = $('#schKind').val() ;
					var defaultSch = $('#defaultSch') ;
					var category = $('#category') ;
					var title = $('#title') ;
					var nick = $('#nick') ;
					var state = $('#state') ;
					
					switch(schKind) {
					case "none" :
						defaultSch.css('display','inline-block') ;
						category.css('display','none') ;
						title.css('display','none') ;
						nick.css('display','none') ;
						state.css('display','none') ;
						break ;
					case "category" :
						defaultSch.css('display','none') ;
						category.css('display','inline-block') ;
						title.css('display','none') ;
						nick.css('display','none') ;
						state.css('display','none') ;
						break ;
					case "title" :
						defaultSch.css('display','none') ;
						category.css('display','none') ;
						title.css('display','inline-block') ;
						nick.css('display','none') ;
						state.css('display','none') ;
						break ;
					case "nick" :
						defaultSch.css('display','none') ;
						category.css('display','none') ;
						title.css('display','none') ;
						nick.css('display','inline-block') ;
						state.css('display','none') ;
						break ;
					case "state" :
						defaultSch.css('display','none') ;
						category.css('display','none') ;
						title.css('display','none') ;
						nick.css('display','none') ;
						state.css('display','inline-block') ;
						break ;
					default :
						alert('에러가 발생했습니다.') ;
						location.reload() ;
					}
				}) ;
			}) ;
		</script>
	</div>
</body>
</html>