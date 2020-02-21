<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, share.model.vo.*" %>
<%
	ArrayList<Share> sList = (ArrayList<Share>)request.getAttribute("sList") ;
	System.out.println(sList);
	ArrayList<Sattachment> fList = (ArrayList<Sattachment>)request.getAttribute("fList") ;
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
				<table id="schArea">
					<tr>
						<!-- <td> -->
							<!-- <select id="schKind" class="search"> -->
								<!-- <option value="none">물품 종류 선택</option> -->
								<!-- <option value="USIM">유심(USIM)</option> -->
								<!-- <option value="traffic">교통권</option> -->
								<!-- <option value="ticket">티켓(ticket)</option> -->
								<!-- <option value="necess">생필품(necessity)</option> -->
								<!-- <option value="money">돈(money)</option> -->
								<!-- <option value="etc">기타(..etc)</option> -->
							<!-- </select> -->
						<!-- </td> -->
						<!-- <td><input type="text" placeholder="게시물 이름 입력" id="schTitle" class="search"></td> -->
						<!-- <td><input type="text" placeholder="작성자 닉네임 입력" id="schWrite" class="search"></td> -->
						<!-- <td> -->
							<!-- <select id="schState" class="search"> -->
								<!-- <option value="none">거래 상태 설정</option> -->
								<!-- <option value="possible">거래 가능</option> -->
								<!-- <option value="complete">거래 완료</option> -->
								<!-- <option value="progress">진행중</option> -->
							<!-- </select> -->
						<!-- </td> -->
						<td>
							<select id="schKind" class="search">
								<option value="none">물품 종류 선택</option>
								<option value="USIM">유심(USIM)</option>
								<option value="traffic">교통권</option>
								<option value="ticket">티켓(ticket)</option>
								<option value="necess">생필품(necessity)</option>
								<option value="money">돈(money)</option>
								<option value="etc">기타(..etc)</option>
							</select>
						</td>
						<td><input type="text" placeholder="게시물 이름 입력" id="schTitle" class="search"></td>
						<td><input type="text" placeholder="작성자 닉네임 입력" id="schWrite" class="search"></td>
						<td>
							<select id="schState" class="search">
								<option value="none">거래 상태 설정</option>
								<option value="possible">거래 가능</option>
								<option value="complete">거래 완료</option>
								<option value="progress">진행중</option>
							</select>
						</td>
						<td><input type="submit" value="검색하기" id="button" class="sch"></td>
					</tr>
				</table>
				<hr>
				<h2>나눔 리스트</h2>
				<table id="shareContent" class="listArea">
					<tr><th>대표사진</th><th>번호</th><th>나눔카테고리</th><th>게시글명</th><th>작성자</th><th>작성일</th><th>거래지</th><th>조회수</th><th>거래 상태</th></tr>
					<% for(int i=0; i<sList.size(); i++) {
						Share share = sList.get(i) ; %>
						<tr>
							<td>
								<% for(int j=0; j<fList.size(); j++) {
									Sattachment attch = fList.get(j) ;
									if(share.getSbNum() == attch.getSbNum()) { %>
									<% } else { %>
										<img src="defaultPageIcon.png">
									<% } %>
								<% } %>
							</td>
							<td><input type="hidden" value="<%= share.getSbNum()	%>" id="sbNum"><%= share.getSbNum() %></td>
							<td><input type="hidden" value="<%= share.getCategory()	%>"><%= share.getCategory()	%></td>
							<td><input type="hidden" value="<%= share.getTitle()	%>"><%= share.getTitle()	%></td>
							<td><input type="hidden" value="<%= share.getWriter()	%>"><%= share.getWriter()	%></td>
							<td><input type="hidden" value="<%= share.getDate()		%>"><%= share.getDate()		%></td>
							<td>
								<input type="hidden" value="<%= share.getKind()		%>">
								<input type="hidden" value="<%= share.getCountry()	%>">
								
								<% if(share.getKind() == 1) { %>
									국내 / <%= share.getCity() %>
								<% } else { %>
									해외 / <%= share.getCountry() %> / <%= share.getCity() %>
								<% } %>
							</td>
							<td><input type="hidden" value="<%= share.getsCount()	%>"><%= share.getsCount()	%></td>
							<td><input type="hidden" value="<%= share.getStatus()	%>"><%= share.getStatus()	%></td>
						</tr>
					<% } %>
				</table>
			</form><br>
			<input type="button" onclick="location.href='<%=request.getContextPath()%>/views/share/ShareInsertView.jsp'" id="button" class="write" value="작성하기">
		</div>
		<%@ include file="../common/footer.jsp" %>
		<script>
			$(function() {
				$(function() {
					$('.listArea td').mouseenter(function() {
						$(this).parent().css({'background':'darkgray','cursor':'pointer'}) ;
					}).mouseout(function() {
						$(this).parent().css('background', 'none');
					}).click(function() {
						var sbNum = $(this).parent().children().find('#sbNum').val() ;
						alert(sbNum) ;
						
						location.href="<%= request.getContextPath()%>/detail.share?sbNum="+sbNum ;
					}) ;
				}) ;
			}) ;
		</script>
	</div>
</body>
</html>