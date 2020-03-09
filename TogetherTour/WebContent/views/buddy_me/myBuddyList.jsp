<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList,buddy_me.model.vo.Buddy"%>
<% 
	ArrayList<Buddy> list = (ArrayList<Buddy>)request.getAttribute("mybuddyList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 동행자 리스트 | TogetherTour</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/index.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common_sub.css">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/myPage/myPage.css">
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.4.1.min.js"></script>
<style>
	#buddyListTh, #buddyListTd{border-top:1px solid; border-bottom:1px solid;}
	#buddyList td{border-bottom:1px solid lightgray; text-align:center;}
	.gradeBtn{color:#FF8000; font-weight:bold; cursor:pointer;}
</style>
</head>
<body>
	<div class="wrapper">
		<%@ include file="../common/header.jsp" %>
		<div class=contents>
			<h2 align=center><span>마이페이지</span></h2>
			<p style="margin-top:-85px; margin-bottom:30px; font-size:2rem; font-weight:bold; text-align:center;">내 동행자 리스트</p><hr>
			<%@ include file="../myPage/MyPageMenu.jsp"%><hr><br><br>
			<form method=post id=buddyListForm>
				<table id="buddyList">
					<tr>
						<th id="buddyListTh">회원명</th>
						<th id="buddyListTh">닉네임</th>
						<th id="buddyListTh">국가</th>
						<th id="buddyListTh">도시</th>
						<th id="buddyListTh">여행기간</th>
						<th width=100px; id="buddyListTh">동행인원</th>
						<th width=100px; id="buddyListTh"></th>
					</tr>
					<% if(list.isEmpty()) { %>
						<tr>
							<td colspan=7 id="buddyListTd">조회된 리스트가 없습니다.</td>
						</tr>
					<% } else { %>
						<% for(Buddy b : list) { %>
							<% if(!loginUser.getmId().equals(b.getmId())) { %>
								<tr>
									<td id="buddyListTd"><%= b.getName() %><input type=hidden value='<%= b.getmId() %>' class=buddyId></td>
									<td id="buddyListTd" class=nick><%= b.getNick() %></td>
									<td id="buddyListTd"><%= b.getCountry() %></td>
									<td id="buddyListTd"><%= b.getCity() %></td>
									<td id="buddyListTd"><%= b.getStart_date() %> - <%= b.getEnd_date() %></td>
									<td id="buddyListTd"><%= b.getHeadCnt() %></td>
									<td id="buddyListTd" class=gradeBtn>평점주기</td>
								</tr>
							<% } %>
						<% } %>
					<% } %>
				</table>
			</form>
		</div>
		<%@ include file="../common/footer.jsp" %>
	</div>
	<script>
		$('.gradeBtn').click(function(){
			var gradeMid = $(this).parent().children().children('.buddyId').val();
			var gradeNick = $(this).parent().children('.nick').text();
			window.open("views/buddy_me/buddyGrade.jsp?gradeNick="+gradeNick+"&gradeMid="+gradeMid,"buddyGradeFrom","width=700px, height=800px");
		});
		
		$(function() {
			if(<%=loginUser.getmKind()%>==2) {
				$('#followList').css('display','none') ;
			} else {
				$('#followList').click(function() {
					location.href = "<%= request.getContextPath() %>/list.follow" ;
				}) ;
			}
		}) ;
	</script>
</body>	
</html>