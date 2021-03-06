<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="member.model.vo.*, java.util.*" %>
<%
	ArrayList<Member> mList = (ArrayList<Member>)request.getAttribute("mList") ;
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>마이페이지</title>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/index.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/common_sub.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/myPage/myPage.css">
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
	<div class="wrapper">
		<!--S:header-->
		<%@ include file="../common/header.jsp"%>
		<!--E:header-->
		<!--S:footer-->
		<div class="contents">
			<!--S:MyPageMenu-->
			<h2><span>마이페이지</span></h2>
			<p style="margin-top:-40px; margin-bottom:30px; font-size:2rem; font-weight:bold; text-align:center; background:#F2F2F2;">내가 팔로우한 사용자 리스트</p><hr>
			<%@ include file="../myPage/MyPageMenu.jsp"%><hr><br><br>
			<!--E:MyPageMenu-->
			<!--S:MyPageContent:Follow-->
			<div>
				<table>
					<% if(!mList.isEmpty()) { %>
						<tr><td colspan="5" style="text-align:center; font-size:1.7rem; font-weight:bold; border-bottom:1px solid;  border-top:1px solid; background:#E6E6E6;">팔로워 수 : <%=mList.size()%>명</td></tr>
					<% } %>
					<tr style="border-bottom:1px solid; background:#F2F2F2;">
						<th>닉네임</th><th>이메일</th><th>성별</th><th>팔로워 수</th><th>평점(x/5, x명)</th>
					</tr>
					<% if(mList.isEmpty()) { %>
						<tr><td colspan="5" style="text-align:center;">내용이 없습니다.</td></tr>
					<% } else { %>
						<% for(int i=0; i<mList.size(); i++) { %>
							<tr style="text-align:center;" class="listArea" id="member<%=i%>">
								<td class="nickName"><%=mList.get(i).getNickName()%></td>
								<td><%=mList.get(i).getEmail()%></td>
								<td><%=mList.get(i).getGender()%></td>
								<td><%=mList.get(i).getFollow()%></td>
								<td><%=mList.get(i).getGrade()%>/5, <%=mList.get(i).getgCount()%>명</td>
							</tr>
						<% } %>
					<% } %>
				</table>
			</div>
			<!--E:MyPageContent:Follow-->
		</div>
		<script>
			$(function() {
				$('.listArea').mouseenter(function() {
					$(this).css({'background':'rgb(246, 227, 206)','cursor':'pointer'}) ;
				}).mouseout(function() {
					$(this).css({'background':'none','cursor':'normal'}) ;
				}).click(function() {
					var userNick = $('.listArea.nickName').text() ;
					window.open('views/myPage/memberProfile.jsp?userNick='+userNick,'profileForm','width=500, height=700');
				}) ;
			}) ;
			
			$(function() {
				if(<%=loginUser.getmKind()%>==2) {
					$('#followList').css('display','none') ;
					$('#buddyList').css('display','none') ;
				} else {
					$('#followList').click(function() {
						location.href = "<%= request.getContextPath() %>/list.follow" ;
					}) ;
				}
			}) ;
		</script>
		<!--E:footer-->
		<!--S:footer-->
		<%@ include file="../common/footer.jsp"%>
		<!--E:footer-->
	</div>
</body>
</html>