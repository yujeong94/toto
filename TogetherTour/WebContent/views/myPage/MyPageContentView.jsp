<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="member.model.vo.*"%>
<%  mAttachment profileImg = (mAttachment)request.getAttribute("mAttach") ; %>
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
		<!--S:contents-->
		<div class="contents">
			<h2><span>마이페이지</span></h2>
			<p style="margin-top:-40px; margin-bottom:30px; font-size:2rem; font-weight:bold; text-align:center;">내 정보 보기</p><hr>
			<%@ include file="../myPage/MyPageMenu.jsp"%><hr><br><br>
			<form id="myInfoArea" method="post" action="<%=request.getContextPath()%>/views/myPage/MyPageUpdateView.jsp">
				<div id="profileArea" onclick="" style="border:1px solid black;">
					<img id="profile" src="<%=request.getContextPath()%>/uploadFiles/<%=profileImg.getChangeName()%>">
					<input type="hidden" value="<%=profileImg.getChangeName()%>" id="profileImgName" name="profileImgName">
				</div><br>
				<table id="contentArea">
					<tr>
						<th>닉네임(NickName)</th>
						<td colspan="4">
							<input id="nickName" name="nickName" class="myContent" type="text" value="<%= loginUser.getNickName()%>" readonly>
						</td>
					</tr>
					<tr>
						<th>회원구분(Kind)</th>
						<td colspan="4">
							<% if(loginUser.getmKind() == 1) { %>
								<input id="mKind" class="myContent" type="text" value="일반회원">
							<% } else { %>
								<input id="mKind" class="myContent" type="text" value="가이드회원">
							<% } %>
						</td>
					</tr>
					<tr>
						<th>아이디(ID)</th>
						<td colspan="4">
							<input id="mId" name="mId" class="myContent" type="text" style="background-color:#D8D8D8;" value="<%= loginUser.getmId()%>" readonly>
						</td>
					</tr>
					<tr>
						<th>이름(Name)</th>
						<td colspan="4">
							<input id="userName" class="myContent" type="text" value="<%=loginUser.getUserName()%>" readonly>
						</td>
					</tr>
					<tr>
						<th>이메일(Email)</th>
						<td colspan="4">
							<input id="email" class="myContent" type="text" value="<%=loginUser.getEmail()%>" readonly>
						</td>
					</tr>
					<tr>
						<th>성별(Gender)</th>
						<td colspan="4">
							<input id="gender" class="myContent" type="text" value="" readonly>
						</td>
					</tr>
					<tr>
						<th>나이(Age)</th>
						<td colspan="4">
							<input id="age" class="myContent" type="text" value="<%=loginUser.getAge()%>" readonly>
						</td>
					</tr>
					<tr>
						<th>팔로워 수(Follower)</th>
						<td colspan="4">
							<input id="follow" class="myContent" type="text" value="<%=loginUser.getFollow()%>명" readonly>
						</td>
					</tr>
					<tr>
						<td colspan="5">
							<input type="submit" id="updateBtn" value="수정하기" style="background:#005952; color:white;">
						</td>
					</tr>
				</table><br><br><br><hr><br><br><br><br><br><br><br>
			</form>
			<div>
				<p id="starTitle">나의 평점</p>
				<table id="contentArea">
					<tr>
						<td colspan="4">
							<% int count = 0 ; %>
							<% for(int i=1; i<=loginUser.getGrade(); i++) { %>
								<span id="fullStar" class="star">★</span>
								<% count++ ; %>
							<% } %>
							<% for(int i=5; i>count; i--) { %>
								<span id="emptyStar" class="star">☆</span>
							<% } %>
							<div id="myGrade1"><%=loginUser.getGrade()%>&nbsp;/&nbsp;5</div><br>
							<div id="myGrade2">평가인원수 : <%=loginUser.getgCount()%>명</div>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<!--E:contents-->
		<!--S:footer-->
		<%@ include file="../common/footer.jsp"%>
		<!--E:footer-->
	</div>
	<script>
		function detailGrade() {
			window.open("detailGradeView.jsp", "checkNickForm", "width=500, height=300") ;
		}
		
		$(function() {
			var gender = "<%=loginUser.getGender()%>" ;
			if(gender == 'M')
				$('#gender').val("남자") ;
			else
				$('#gender').val("여자") ;
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
</body>
</html>